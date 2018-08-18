package com.trj.jk.web.controller.version_2;

import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.model.response.UserAddressAndCompanyRes;
import com.trj.jk.web.model.response.UserContactsRes;
import com.trj.jk.web.service.IPersonInfoService;
import com.trj.jk.web.service.UserContactService;
import com.trj.jk.web.service.UserProfessionService;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户信息控制器
 * Created by xierongli on 17/8/9.
 */
@RestController
@RequestMapping(value={"/personInfo"})
public class PersonInfoV2Controller extends BaseController{


    @Autowired
    private IPersonInfoService personInfoService;


    @Autowired
    private UserContactService userContactService;

    @Autowired
    private UserProfessionService userProfessionService;

    /**
     * 获取联系人列表
     * @return
     */
    @RequestMapping(value={"/queryLoanApplyContactsV2"})
    @ResponseBody
    public Result<UserContactsRes> queryLoanApplyContacts() {
        logger.info("获取联系人列表");
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        UserContactsRes userContactsRes = userContactService.queryForList(uid);
        return Results.newSuccessResult(userContactsRes);
    }


    /**
     * 获取用户的地址信息 and 公司信息,用于工薪贷个人资料首页进入获取数据
     *
     * @author xierongli
     * @date 17/8/16 上午11:08
     */
    @RequestMapping("/queryUserAddressAndCompany")
    public Result<UserAddressAndCompanyRes> queryUserAddressAndCompany(Integer loanApplyId){
        logger.info("获取用户的地址信息");
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        Assert.isNull(loanApplyId,"借款号不能为空");
        return Results.newSuccessResult(userProfessionService.queryUserProfession(uid,loanApplyId));
    }


}
