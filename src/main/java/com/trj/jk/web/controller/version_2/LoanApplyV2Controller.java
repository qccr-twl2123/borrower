package com.trj.jk.web.controller.version_2;

import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.authentication.bi.CmsResult;
import com.trj.jk.web.domain.entity.authentication.bi.GjjCallbackResult;
import com.trj.jk.web.model.request.LoanApplyCarReq;
import com.trj.jk.web.model.request.UserAddressAndCompanyReq;
import com.trj.jk.web.model.response.LoanApplyCarRes;
import com.trj.jk.web.model.response.LoanApplyRes;
import com.trj.jk.web.service.*;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 借款申请控制器
 * 1.对每一笔借款申请增加步骤码
 * 2.新增查询用户未完成借款申请接口
 * Created by xierongli on 17/7/27.
 */
@RestController
@RequestMapping(value={"/loan/apply"})
public class LoanApplyV2Controller extends BaseController {


    @Autowired
    private LoanApplyAddressService loanApplyAddressService;
    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private LoanApplyCarService loanApplyCarService;
    @Autowired
    private ILoanProductService loanProductService;

    @Value("${app.service.cfdCms.domain}")
    private String cfdCmsDomain;

    @Value("${app.service.cfdCms.appKey}")
    private String cfdCmsAppKey;

    @Value("${app.service.cfdCms.appSecret}")
    private String cfdCmsAppSecret;


    /**
     * 申请
     * @return
     */
    @PostMapping("/addV2")
    public Result<LoanApplyRes> addLoanApply(Integer productId, String invitationCode, String city, BigDecimal amount) {
        logger.info("用户提交借款申请productId[{}],invitationCode[{}]",productId,invitationCode);
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();;
        Assert.isNull(productId, ErrorMessageConstant.ERR_NULL_PRODUCT);
        LoanProduct product = loanProductService.getProductById(productId);
        if (StringUtils.isNotEmpty(invitationCode) && StringUtils.isNotEmpty(city)) {
            try {
                String body = "{\"invitationCode\":\"" + invitationCode + "\",\"city\":\"" + city + "\",\"proName\":\"" + product.getName() + "\"}";
                String result = HttpClientUtils.doCfdCmsPost(cfdCmsDomain + "api/checkInvitationCode", body, cfdCmsAppKey, cfdCmsAppSecret);
                logger.info("邀请码检测结果：{}",result);
                CmsResult cmsResult = new Gson().fromJson(result, CmsResult.class);
                if (null != cmsResult && null != cmsResult.getRetresult()) {
                    if (cmsResult.getRetresult().getPermission().equals("no")) {
                        return Results.newFailedResult(cmsResult.getRetresult().getForbidden_reason());
                    }
                }
            } catch (Exception e) {
                logger.error("邀请码检测系统错误：{}", e.getMessage());
                return Results.newFailedResult("邀请码检测系统错误");
            }
        }
        LoanApply loanApply = new LoanApply();
        loanApply.setUid(uid);
        loanApply.setInvitationCode(invitationCode);
        loanApply.setProductId(productId);
        loanApply.setAmount(amount);
        loanApply.setTenant(UtilConstant.TENANT_JKWEB);
        int num = loanApplyService.addLoanApply(loanApply);
        Assert.isTrue(num<0,"借款申请新增失败");
        LoanApplyRes loanApplyRes = new LoanApplyRes();
        loanApplyRes.setLoanApplyId(loanApply.getId());
        loanApplyRes.setProductId(productId);
        return Results.newSuccessResult(loanApplyRes);

    }

    /**
     * 保存个人资料信息
     * @author xierongli
     * @date 17/8/16 下午2:58
     */
    @PostMapping("/UserAddressAndCompany/fill")
    public Result<Boolean> addUserAddressAndCompany(UserAddressAndCompanyReq userAddressAndCompanyReq){
        logger.info("保存个人资料信息userAddressAndCompanyReq[{}]",userAddressAndCompanyReq);
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        ValidatorUtils.validateEntity(userAddressAndCompanyReq);
        loanApplyAddressService.insertLoanApplyPersonInfo(userAddressAndCompanyReq,uid);
        return Results.newSuccessResult(true);
    }



    @PostMapping("/car/saveOrUpdate")
    public Result<Boolean> saveOrUpdateCarInfo(LoanApplyCarReq loanApplyCarReq){
        logger.info("购车信息保存或更新loanApplyCarReq[{}]",loanApplyCarReq);
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        ValidatorUtils.validateEntity(loanApplyCarReq);
        loanApplyCarReq.setUid(uid);
        int num = loanApplyCarService.saveOrUpdate(loanApplyCarReq);
        Assert.isTrue(num <0,"更新数据失败");
        return Results.newSuccessResult(true);
    }


    @RequestMapping("/car/getLoanApplyInfo")
    public Result<LoanApplyCarRes> getLoanApplyCar(@RequestParam  Integer loanApplyId){
        logger.info("获取借款申请填写的购车信息loanApplyId[{}]",loanApplyId);
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        LoanApplyCarRes loanApplyCarRes = loanApplyCarService.getLoanApplyInfo(uid,loanApplyId);
        return Results.newSuccessResult(loanApplyCarRes);
    }


}
