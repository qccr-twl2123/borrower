package com.trj.jk.web.controller.version_2;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.service.*;
import com.trj.jk.web.service.face.IFaceService;
import com.trj.jk.web.util.*;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.List;

/**
 * 认证管理controller类 version2
 * Created by xierongli on 17/8/9.
 */
@RestController
@RequestMapping(value={"/authentication"})
public class AuthenticationV2Controller extends BaseController{


    @Value("${app.upload.path}")
    private String uploadPath;
    @Autowired
    private UserBasicService userBasicService;
    @Resource
    private IFaceService faceService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserFaceLogService userFaceLogService;


    /**
     * 身份认证及保存
     * 描述:合并之前三个接口功能
     * authentication/identity/auth
     * authentication/identity/save
     * attachment/upload
     * @param identityReq
     * @return
     * @author xierongli
     * @date 17/8/9 上午10:54
     */
    @PostMapping("/identity/authAndSave")
    public Result<Boolean> identityAuthAndSave(@RequestParam("file")  List<MultipartFile> files, IdentityReq identityReq,ServletRequest request){
        logger.info("用户身份验证 and 保存identityReq[{}]", JSON.toJSONString(identityReq));
        ValidatorUtils.validateEntity(identityReq);
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        UserFaceLog userFaceLog = userFaceLogService.generate(uid,1,"",0);
        Assert.isTrue(CollectionUtils.isEmpty(files)|| files.size() <3,"身份证抠图截取失败");
        Assert.isTrue(userBasicService.getByIdentityId(identityReq.getIdentityId()) != null, ErrorMessageConstant.ERR_IDENTITYID_EXIST);
        try {
            //上传头像
            fileService.asyncUploadImage(3,uid,files.get(2),identityReq);
            //认证头像
            MultipartFile headImage = files.get(2);
            Result<Boolean> checkResult = faceService.checkFaceImgPlus(uid,identityReq.getIdentityId(), identityReq.getName(), FileUtil.saveFile(headImage,uploadPath));
            if(checkResult.isSuccess()){
                userFaceLog.setStatus((byte) 1);
                userFaceLog.setRemark("身份证抠像比对成功");
                userFaceLogService.insert(userFaceLog);
                userBasicService.doIdentityAuth(uid,files,identityReq);
            }else{
                Assert.isTrue(!checkResult.isSuccess(),checkResult.getMessage());
            }
        }catch(IllegalArgumentException ex){
            logger.error("用户身份验证异常IllegalArgumentException",ex.getMessage());
            userFaceLog.setRemark(ex.getMessage());
            userFaceLogService.insert(userFaceLog);
            return  Results.newFailedResult("用户身份验证异常");
        }catch (RRException e){
            logger.error("用户身份验证异常RRException", e.getMessage());
            userFaceLog.setRemark(e.getMsg());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult(e.getMsg());
        } catch (Exception e){
            logger.error("用户身份验证异常Exception",e.getMessage());
            userFaceLog.setRemark(e.getMessage());
            userFaceLogService.insert(userFaceLog);
            return  Results.newFailedResult("用户身份验证异常");
        }
        return Results.newSuccessResult(true);
    }









}
