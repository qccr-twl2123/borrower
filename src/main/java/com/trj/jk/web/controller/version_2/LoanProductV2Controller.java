package com.trj.jk.web.controller.version_2;

import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.service.ILoanProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import com.trj.jk.web.model.response.LoanProductDetailRes;

@RestController
@RequestMapping(value={"/product"})
public class LoanProductV2Controller extends BaseController{


	@Resource
	private ILoanProductService loanProductService;



	/**
	 * 获取借款产品详情接口
	 * @return
	 */
	@RequestMapping(value={"/queryLoanProductDetailV2"},method=RequestMethod.GET)
	@ResponseBody
	public Result<LoanProductDetailRes> queryLoanProductDetail(Integer productId) {
		logger.info("获取借款产品详情接口开始执行productId[{}]",productId);
		return Results.newSuccessResult(loanProductService.queryLoanProductsV2(productId));
	}


}

