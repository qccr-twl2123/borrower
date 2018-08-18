package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.annotation.TokenCheck;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.Token;
import com.trj.jk.web.domain.TokenCriteria;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.jms.IQueueService;
import com.trj.jk.web.model.request.OutRegisterReq;
import com.trj.jk.web.service.TokenService;
import com.trj.jk.web.service.UserBasicService;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.util.VerifyCodeUtils;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import com.trj.message.service.api.SmsSendParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 外部注册 控制器
 * 1.H5 推广页面注册逻辑
 * Created by xierongli on 17/8/2.
 */
@RestController
@RequestMapping("outRegister")
public class OutRegisterController extends BaseController {

    private static final java.util.Random random = new java.util.Random();
    private final static  Integer WIDTH = 200;
    private final static  Integer HEIGHT = 50;

    @Autowired
    private TokenService tokenService;

    @Value("${app.upload.url}")
    private String uploadImgUrl;
    @Value("${app.upload.download}")
    private String downloadImgUrl;

    @Autowired
    private IQueueService queueService;

    @Autowired
    private UserBasicService userBasicService;

    @Autowired
    private com.trj.jk.web.mapper.jdbc.JdbcDao JdbcDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取图形验证码
     * @param width  验证码宽度
     * @param height 验证高度
     * @author xierongli
     * @date 17/8/2 上午11:14
     */
    @RequestMapping("getVerificationCode")
    public Result<Map<String,String>> getVerificationCode(Integer width, Integer height){
        Map<String,String> resultMap = generateVerifyCode(width,height);
        if(resultMap == null || resultMap.size() == 0){
            return Results.newFailedResult("生成验证码失败");
        }
        return Results.newSuccessResult(resultMap);
    }

    public Map<String,String> generateVerifyCode(Integer width,Integer height){
        if(width == null){width = WIDTH;}
        if(height == null){height = HEIGHT;}
        File dir = new File(this.getClass().getClassLoader().getResource("").getPath());
        Map<String,String> map = VerifyCodeUtils.getVerifyCodeUrl(dir,width,height, uploadImgUrl);
        if(map != null && map.size() != 0){
            String imageUrl = downloadImgUrl + map.get(VerifyCodeUtils.URL_KEY);
            //生成token and 保存图片验证码
            String token = tokenService.createToken(map.get(VerifyCodeUtils.CODE_KEY));
            Map<String,String> resultMap = Maps.newHashMap();
            resultMap.put("token", token);
            resultMap.put("imageUrl",imageUrl);
            return resultMap;
        }
        return null;
    }



    /**
     * 验证图形验证码
     * @param token
     * @param imgVerifyCode
     * @return 成功与否
     * @author xierongli
     * @date 17/8/2 上午11:18
     */
    @TokenCheck
    @RequestMapping("checkVerificationCode")
    public Result<Boolean> checkVerificationCode(@RequestParam String mobile,@RequestParam  String imgVerifyCode, @RequestParam String token){
        //验证手机号
        Assert.isTrue(!(mobile.matches(RegexContext.MOBILE_REGEX)),"手机号格式不正确");
        Assert.isTrue(userBasicService.queryByMobile(mobile) != null,"该手机号已注册");
        //验证图形验证码
        Assert.isTrue(!tokenService.queryByCodeAndToken(imgVerifyCode,token),"验证码不正确");
        //更新token
        int updateNum = tokenService.updateToken(mobile,token);
        Assert.isTrue(updateNum < 0, "更新token失败");
        return Results.newSuccessResult(true);
    }

    /**
     * 获取手机验证码
     * @param  token
     * @param  mobile  手机号
     * @return 手机验证码
     * @author xierongli
     * @date 17/8/2 上午11:20
     */
    @TokenCheck
    @RequestMapping("getMobileVerificationCode")
    public Result<Boolean> getMobileVerificationCode(@RequestParam  String token,@RequestParam  String mobile){
        Assert.isTrue(!(mobile.matches(RegexContext.MOBILE_REGEX)),"手机号格式不正确");
        //调用频率限制
        Assert.isTrue(stringRedisTemplate.hasKey(token),"验证码刷新太频繁");
        //1.生成验证码
        String verifyCode="";
        for(int i=0;i<6;i++){
            verifyCode+=random.nextInt(10);
        }
        //构建消息文本
        String content = "注册验证码为: %s";
        queueService.sendSms(new SmsSendParam(mobile, String.format(content,verifyCode)));
        //保存验证码
        Token tokenModel = tokenService.queryByToken(token);
        Assert.isNull(tokenModel, "token不存在");
        tokenModel.setMobileVerificationCode(verifyCode);
        TokenCriteria tokenCriteria = new TokenCriteria();
        tokenCriteria.createCriteria().andTokenEqualTo(token);
        int num = tokenService.update(tokenModel,tokenCriteria);
        Assert.isTrue(num < 0,"手机验证码更新失败");
        stringRedisTemplate.opsForValue().set(token,verifyCode,60, TimeUnit.SECONDS);
        return Results.newSuccessResult(true);
    }

    /**
     * 注册
     * @param outRegisterReq
     * @return
     * @author xierongli
     * @date 17/8/2 上午11:29
     */
    @TokenCheck
    @RequestMapping("register")
    public Result<Boolean> register(OutRegisterReq outRegisterReq){
        ValidatorUtils.validateEntity(outRegisterReq);
        //验证手机验证码
        Assert.isTrue(userBasicService.queryByMobile(outRegisterReq.getMobile()) != null,"该手机号已注册");
        Assert.isNull(tokenService.queryByMobileCodeAndToken(outRegisterReq.getToken(),outRegisterReq.getMobileCheckPwd()),"手机验证码不正确");
        String md5Pwd= DigestUtil.getMD5(outRegisterReq.getPassword());
        outRegisterReq.setPassword(DigestUtil.getMD5(md5Pwd));
        UserBasic userBasic = ObjectConvert.convertObject(outRegisterReq,UserBasic.class);
        userBasic.setTenant(UtilConstant.TENANT_JKWEB);
        userBasicService.insert(userBasic);
        return Results.newSuccessResult(true);
    }




}
