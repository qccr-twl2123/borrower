package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.jk.web.controller.version_2.LoanApplyV2Controller;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.model.dto.UserAddressDTO;
import com.trj.jk.web.model.request.LoanApplyAddressReq;
import com.trj.jk.web.model.request.LoanApplyCarReq;
import com.trj.jk.web.model.request.UserAddressAndCompanyReq;
import com.trj.jk.web.service.LoanApplyService;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xierongli on 17/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoanApplyController {

    @Autowired
    private LoanApplyController loanApplyController;

    @Autowired
    private LoanApplyV2Controller loanApplyV2Controller;

    @Autowired
    private LoanInfoController loanInfoController;
    @Autowired
    private ContractController contractController;

    @Autowired
    private LoanApplyMapper loanApplyMapper;

    @Test
    public void testGetBuyCarInfo(){
        Result<Object> result = loanApplyController.getBuyCarInfo(3075);
        System.out.println(JSON.toJSONString(result));
    }


    @Test
    public void getFinishLoansDetails(){
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setLimit(10);
        System.out.println(JSON.toJSONString(loanInfoController.getFinishLoansDetails(834,pageBean)));
    }





    @Test
    public void testGetCutRateAmount(){
//        Result<Object> result = loanInfoController.getCutRateAmount(3,24,new BigDecimal(100000),new BigDecimal(0.02));
//        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testLookContract(){
        Result<Object> result =  contractController.lookContract(1265,1);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetLimitAuditList(){
        PageBean pageBean =new PageBean();
        pageBean.setPage(1);
        pageBean.setLimit(10);
        Result<Object> result = loanInfoController.getLimitAuditList(pageBean,new Byte("0"));
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testGetCar(){
        Result<Object> result = loanApplyController.getCar(3075);
        System.out.println(JSON.toJSONString(result));
    }
    @Test
    public void testGetLoanApplyCar(){
        System.out.println(JSON.toJSONString(loanApplyV2Controller.getLoanApplyCar(6505)));
    }

    @Test
    public void testGetCertfication(){
        System.out.println(JSON.toJSONString(loanApplyController.getCertfication(3,3)));
    }

    @Test
    public void testSaveOrUpdateCarInfo(){
        LoanApplyCarReq loanApplyCarReq = new LoanApplyCarReq();
        loanApplyCarReq.setAttachId("12");
        loanApplyCarReq.setBrand("brand111");
        loanApplyCarReq.setLoanApplyId(90021);
        loanApplyCarReq.setModel("model111");
        loanApplyCarReq.setNakedBikeAmount(new BigDecimal("1233000"));
        loanApplyCarReq.setResidentialProvince("zhejiang11");
        loanApplyCarReq.setResidentialCity("hz111");
        loanApplyCarReq.setResidentialDistrict("xihu111");
        loanApplyCarReq.setResidentialAddress("address111");
        loanApplyCarReq.setSalesOrganization(1);
        System.out.println(loanApplyV2Controller.saveOrUpdateCarInfo(loanApplyCarReq));
    }

    @Test
    public void addUserAddressAndCompany(){
        UserAddressAndCompanyReq userAddressAndCompanyReq = new UserAddressAndCompanyReq();

        userAddressAndCompanyReq.setCorpProvince("dsadada");
        userAddressAndCompanyReq.setCorpCity("city");
        userAddressAndCompanyReq.setCorpDistrict("district");
        userAddressAndCompanyReq.setCorpAddress("address");
        userAddressAndCompanyReq.setCorpName("tourongjia");
        userAddressAndCompanyReq.setPosition("java");
        userAddressAndCompanyReq.setDepartment("jishubus");
        userAddressAndCompanyReq.setLoanApplyId(1903);
        loanApplyV2Controller.addUserAddressAndCompany(userAddressAndCompanyReq);

    }

    @Test
    public void testGetCutRateAmount222() {
        LoanApply loanApplyRefuse = loanApplyMapper.queryRefuseApplyByVaildDate(76179, 1);
//        LoanApply loanApplyRefuse = loanApplyMapper.queryRefuseApplyByVaildDate(76192, 1);
        String tipMsg = "亲,感谢您的支持!您只有在上次申请30天后才能重新申请哦!";
        if (null != loanApplyRefuse && StringUtils.isNotEmpty(loanApplyRefuse.getRejectDayInfo())) {
            System.out.println(loanApplyRefuse.getRejectDayInfo());
            tipMsg = "亲,感谢您的支持!" + loanApplyRefuse.getRejectDayInfo() + "!";
        }
        Assert.isTrue(loanApplyRefuse != null, tipMsg);
    }

}
