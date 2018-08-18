package com.trj.jk.web.controller.v3;

import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.model.response.OpenLoanApplyRes;
import com.trj.jk.web.service.*;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 融资申请相关业务controller类
 *
 * @author l46001
 */
@RestController("LoanApplyController_v3")
@RequestMapping(value = {"/v3/apply"})
public class LoanApplyController extends BaseController {

    @Autowired
    private UserBasicService userBasicService;

    @Autowired
	private IApplyLoanService applyLoanService;

    /**
     * 黑名单检测
     *
     * @return
     */
    @RequestMapping(value = "/isBlacklist", method = RequestMethod.GET)
    public Result<Map< String, Object>> isBlacklist(String name, String identityId) {
        logger.info("黑名单检测接口开始执行...");
        Assert.isBlank(name, "姓名获取为空");
        Assert.isBlank(identityId, "身份证号获取为空");
        Map< String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("isBlacklist", false);
        return Results.newSuccessResult(dataMap);
    }

    @PostMapping("register")
    public Result<Boolean> register(String mobile){
        Assert.isTrue(!RegexContext.match(RegexContext.MOBILE_REGEX,mobile),"手机格式不正确");
        UserBasic userBasic = userBasicService.queryByMobile(mobile);
        if (null == userBasic) {
            userBasicService.insert(generateUserBasic(mobile));
        }
        return Results.newSuccessResult(true);
    }

    public UserBasic generateUserBasic(String mobile){
        UserBasic userBasic =  new UserBasic();
        userBasic.setMobile(mobile);
        userBasic.setPassword(DigestUtil.getMD5(DigestUtil.getMD5("m123456")));
        userBasic.setTenant("jkWeb");
        userBasic.setChannel("51gjj");
        return userBasic;
    }


    /**
     * 订单修改邀请码
     *
     * @param applyId
     * @param invitationCode
     * @param request
     * @return
     */
    @RequestMapping(value = {"/changeInvitationCode"}, method = RequestMethod.POST)
    public Result<OpenLoanApplyRes> changeInvitationCode(String applyId, String invitationCode, HttpServletRequest request) {
        logger.info("订单修改邀请码接口开始执行...");
        Assert.isTrue(!checkOauthClient(request), "非法客户端请求");
        Assert.isTrue(StringUtils.isEmpty(applyId), "申请号为空");
        LoanApply loanApply = applyLoanService.getLoanApplyByApplyId(applyId);
        Assert.isNull(loanApply, "订单不存在");
        loanApply.setInvitationCode(null == invitationCode ? "" : invitationCode);
        applyLoanService.updateLoanApply(loanApply);
        return Results.newSuccessResult(null);
    }





}


