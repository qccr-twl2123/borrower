package com.trj.jk.web.controller.interestfree;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.IBankCardService;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.goodsorder.IOrderService;
import com.trj.jk.web.service.goodsorder.IdentityService;
import com.trj.jk.web.util.SessionUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * Created by zhangxin on 2017/6/2.
 */
@RestController
@RequestMapping(value = {"/identity"},method = RequestMethod.POST)
public class IdentityController {

    private static final Logger LOG = Logger.getLogger(IdentityController.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBankCardService bankCardService;
	
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value={"/addIdentity"})
    @ResponseBody
    public Result<Object> saveIdentity(UserExt userExt,Integer orderId){
        LOG.info("uid:"+userExt.getUid()+";getIdentityCardFrontImageId:"+userExt.getIdentityCardFrontImageId()+";getIdentityCardOppositeImageId:"+userExt.getIdentityCardOppositeImageId());
        final Result<Object> result = new Result<Object>();
        try {
            if(null==userExt.getUid()||userExt.getUid()<=0||null==userExt.getIdentityCardFrontImageId()||userExt.getIdentityCardFrontImageId()<=0||null==userExt.getIdentityCardOppositeImageId()||userExt.getIdentityCardOppositeImageId()<=0||null==orderId||orderId<=0){
                result.setSuccess(false);
                result.setMessage("值参错误，值不能为0或空！");
                return result;
            }
            identityService.saveIdentity(userExt);
            result.setSuccess(true);
            result.setMessage(ErrorMessageConstant.SAVE_SUCCESS);
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }


    @RequestMapping(value={"/getVerifyCode"},method=RequestMethod.GET)
    @ResponseBody
    public Result<Object> getVerifyCode(Integer uid,String mobile,Byte mtype,Integer bankCardId){
        final Result<Object> result = new Result<Object>();
        try{
            if(null==uid){
                uid=(Integer) SessionUtil.getUserLogonInfo();
            }
            if(bankCardId==null && mobile==null){
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
                return result;
            }
            
            String value=stringRedisTemplate.opsForValue().get("verify_code_bank_card_" + bankCardId.toString());
            if(StringUtils.isNotEmpty(value)){
            	result.setData("No need to send verify code");
            	return result;
            }
            
            if(mtype!=null){
                userService.getVerifyCode(uid,mobile,mtype,bankCardId);
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_VERIFY_CODE_SUCCESS);
            }else{
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        catch(Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }


    @RequestMapping(value="/verifyCode/verify",method=RequestMethod.POST)
    @ResponseBody
    public Result<Object> verifyCode(String mobile,String  verifyCode,Integer bankCardId){
        final Result<Object> result = new Result<Object>();
        try{
            Map<String, Object> dataMap = userService.verifyCode(mobile,verifyCode,bankCardId);
            if(Boolean.valueOf(dataMap.get("isValid").toString())){
                String value=stringRedisTemplate.opsForValue().get("verify_code_bank_card_" + bankCardId.toString());
                if(StringUtils.isEmpty(value)){
                	stringRedisTemplate.opsForValue().set("verify_code_bank_card_" + bankCardId.toString(), "not_expire", 15, TimeUnit.DAYS);
                }   	
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.VERIFYCODE_CHECK_SUCCESS);
            }else{
                result.setSuccess(false);
                result.setMessage(dataMap.get("message").toString());
            }
            result.setData(dataMap);
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch(Exception e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }


    @RequestMapping(value="/card/list",method=RequestMethod.POST)
    public Result getCardList(String mobile){
        final Result<Object> result = new Result<Object>();
        try {
            if(!StringUtils.isEmpty(mobile)){
                UserBasic userBasic=userService.getUserBasicByMobile(mobile);
                if(null!=userBasic){
                    List<Map<String, Object>> list=bankCardService.getCardListByUid(userBasic.getId());
                    result.setSuccess(true);
                    result.setMessage(ErrorMessageConstant.SUCCESS);
                    result.setData(list);
                }
            }else{
                result.setSuccess(false);
                result.setMessage("值参错误或值不能为空！");
                return result;
            }
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

    @RequestMapping(value={"/esOpenAccount"},method=RequestMethod.GET)
    @ResponseBody
    public Result<Object> esOpenAccount(Integer uid) {
        final Result<Object> result = new Result<Object>();
        try {
            //Integer uid=(Integer)SessionUtil.getUserLogonInfo();
            if(null==uid){
                uid=(Integer)SessionUtil.getUserLogonInfo();
            }
            if(uid!=null){
                userService.esOpenAccount(uid);
                result.setSuccess(true);
                result.setMessage(ErrorMessageConstant.SAVE_SUCCESS);
            }else{
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

    /**
     * 合同签章授权接口（即电子签章发送验证码接口）
     */
    @RequestMapping(value={"/signatureAccredit"},method=RequestMethod.GET)
    @ResponseBody
    public Result<Object> signatureAccredit(Integer uid) {
        final Result<Object> result = new Result<Object>();
        try {
            //Integer uid=(Integer)SessionUtil.getUserLogonInfo();
            if(null==uid){
                uid=(Integer)SessionUtil.getUserLogonInfo();
            }
            if(uid!=null){
                userService.signatureAccredit(uid);
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_VERIFY_CODE_SUCCESS);
            }else{
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        }catch(CheckException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

}
