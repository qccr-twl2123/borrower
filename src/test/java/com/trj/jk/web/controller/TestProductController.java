package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.jk.web.controller.version_2.LoanProductV2Controller;
import com.trj.jk.web.domain.entity.ProductSearchBean;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.model.response.LoanProductDetailRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

/**
 * Created by xierongli on 17/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductController {


    @Autowired
    private LoanProductController loanProductController;
	@Autowired
	private  LoanApplyController loanApplyController;

    @Autowired
    private LoanProductV2Controller loanProductV2Controller;


	@Test
	public void getBuyCarInfo(){
		System.out.println(JSON.toJSONString(loanApplyController.getBuyCarInfo(3075)));
	}

    @Test
    public void getPropelAndHotProducts(){
        PageBean pageBean = new PageBean();
        pageBean.setLimit(10);
        pageBean.setPage(3);
        Result<Object> result = loanProductController.getPropelAndHotProducts(pageBean);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void getLoanProductInfo(){
        Result<Object> result = loanProductController.getLoanProductInfo(1);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void searchLoanProducts(){
        ProductSearchBean productSearchBean = new ProductSearchBean();
        productSearchBean.setLimitCode((byte)0);
        productSearchBean.setTermCode((byte)0);
        Result<Object> result = loanProductController.searchLoanProducts(productSearchBean, null);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void queryLoanProducts(){
        Result<LoanProductDetailRes> result = loanProductV2Controller.queryLoanProductDetail(1);
        System.out.println(JSON.toJSONString(result));
    }


}
