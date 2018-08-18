package com.trj.jk.web.controller.v3;

import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.service.*;
import com.trj.jk.web.service.face.IFaceService;
import com.trj.jk.web.util.FileUtil;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;import java.util.List;
import java.util.Map;

@RestController("UserController_v3")
@RequestMapping(value = {"/v3/user"})
public class UserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Value("${app.upload.path}")
    private String uploadPath;
    @Autowired
    private FileService fileService;
    @Resource
    private IApplyLoanService applyLoanService;
    @Autowired
    private IFaceService faceService;
    @Autowired
    private UserBasicService userBasicService;
    @Autowired
    private UserFaceLogService userFaceLogService;
    @Autowired
    private LoanApplyService loanApplyService;

    /**
     * 身份证识别接口
     *
     * @param idcardBase64Img
     * @return
     */
    @PostMapping("/idCardOcr")
    public Result<Map<String, String>> idCardOcr(@RequestParam String idcardBase64Img) {
        LOG.info("身份证识别接口开始执行...");
        Integer uid = getCurrentUid();
        Assert.isNull(uid,"token失效,用户ID不能为空");
        Result<Map<String, String>> result = new Result<Map<String, String>>();
        //调用发生异常失败数据信息入库
        UserFaceLog userFaceLog = new UserFaceLog();
        userFaceLog.setType((byte) 0);
        userFaceLog.setUid(uid);
        userFaceLog.setStatus((byte) 0);
        try {
            Map<String, String> data = faceService.idCardOcr(uid, idcardBase64Img);
            if (null == data) {
                return Results.newFailedResult("身份证识别失败");
            }
            result.setData(data);
        } catch (Exception ex) {
            logger.error("身份识别异常",ex);
            userFaceLog.setRemark(ex.getMessage());
            userFaceLogService.insert(userFaceLog);
           return Results.newFailedResult("身份证识别失败");
        }
        return result;
    }

    /**
     * 身份认证及保存
     * 描述:合并之前三个接口功能
     * authentication/identity/auth
     * authentication/identity/save
     * attachment/upload
     *
     * @param identityReq
     * @return
     * @author xierongli
     * @date 17/8/9 上午10:54
     */
    @PostMapping("/identityAuth")
    public Result<Boolean> identityAuth(String identityCardFrontImage, String identityCardOppositeImage, String headImage,String orderNo, IdentityReq identityReq) {
        logger.info("用户身份验证 and 保存");
        ValidatorUtils.validateEntity(identityReq);
        Integer uid = getCurrentUid();
        UserFaceLog userFaceLog = userFaceLogService.generate(uid,1,"",0);
        Assert.isTrue(StringUtils.isEmpty(identityCardFrontImage) || StringUtils.isEmpty(identityCardOppositeImage) || StringUtils.isEmpty(headImage), "身份证正面,反面,抠图截取失败");

        UserBasic userBasic = userBasicService.getByIdentityId(identityReq.getIdentityId());
        if (userBasic != null && !userBasic.getId().equals(uid)) {
            return Results.newFailedResult(ErrorMessageConstant.ERR_IDENTITYID_USE_OTHER);
        } else if (userBasic != null && userBasic.getId().equals(uid)) {
            loanApplyService.createLoanApply(uid,orderNo);
            return Results.newSuccessResult(true);
        }
        try {
            //上传头像
            File file1 = FileUtil.decodeBase64ToImage(identityCardFrontImage, uploadPath);
            File file2 = FileUtil.decodeBase64ToImage(identityCardOppositeImage, uploadPath);
            File file3 = FileUtil.decodeBase64ToImage(headImage, uploadPath);
            fileService.asyncUploadImage(3, uid, file3, identityReq);
            //认证头像
            boolean flag = faceService.checkFaceImg(uid, identityReq.getIdentityId(), identityReq.getName(), file3);
            logger.info("公积金身份证抠图比对结果: "+flag);
            Assert.isTrue(!flag, "联网核查失败");
            userBasicService.doIdentityAuth(uid, file1, file2, identityReq,orderNo);
        } catch (IllegalArgumentException ex) {
            logger.error("用户身份验证异常IllegalArgumentException", ex.getMessage());
            userFaceLog.setRemark(ex.getMessage());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult("用户身份验证异常");
        } catch (RRException e){
            logger.error("用户身份验证异常RRException", e.getMessage());
            userFaceLog.setRemark(e.getMsg());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult(e.getMsg());
        } catch (Exception e) {
            logger.error("用户身份验证异常Exception", e.getMessage());
            userFaceLog.setRemark(e.getMessage());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult("用户身份验证异常");
        }
        return Results.newSuccessResult(true);
    }

    /**
     * （姓名,身份证号，活体）比对匹配接口
     *
     * @param files
     * @param identityReq
     * @param orderNo
     * @return
     */
    @PostMapping("/livingBodyAuthAndSave")
    public Result<Boolean> livingBodyAuthAndSave(@RequestParam("file") List<MultipartFile> files, IdentityReq identityReq, String orderNo) {
        //参数校验
        Assert.isTrue(CollectionUtils.isEmpty(files), "活体照不能为空");
        ValidatorUtils.validateEntity(identityReq);
        Assert.isBlank(orderNo, "订单编号不为空");
        Integer uid = getCurrentUid();
        Assert.isNull(uid,"用户ID不存在");
        LoanApply loanApply = applyLoanService.getLoanApplyByOrderNo(uid,orderNo);
        Assert.isNull(loanApply, "订单编号不合法");

        //调用发生异常失败数据信息入库
        UserFaceLog userFaceLog = new UserFaceLog();
        userFaceLog.setType((byte) 3);
        userFaceLog.setUid(uid);
        userFaceLog.setStatus((byte) 0);
        try {
            UserBasic userBasic = userBasicService.getById(uid);
            Assert.isTrue(userBasic.getIsIdentityAuth() != 2,"请先进行身份认证");
            //保存活体照
            fileService.asyncUploadImage(4, uid, files.get(0), identityReq);
            //将采集的人脸与身份证抠图进行比对
            boolean isSuccess = userBasicService.compareTrueFaceAndHeadImage(uid, files.get(0));
            Assert.isTrue(!isSuccess,"身份证抠图比对失败");
            userBasicService.liveBodyAuthAndSave(uid, loanApply.getId());
        }catch (RRException e){
            userFaceLog.setRemark(e.getMessage());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult(e.getMessage());
        }catch (Exception e) {
            userFaceLog.setRemark(e.getMessage());
            userFaceLogService.insert(userFaceLog);
            return Results.newFailedResult("活体检测失败");
        }
        return Results.newSuccessResult(true);
    }

}
