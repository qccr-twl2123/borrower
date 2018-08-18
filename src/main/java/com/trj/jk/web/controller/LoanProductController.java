package com.trj.jk.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.Banner;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ProductSearchBean;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.loanProduct.ShowLoanProdcut;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.service.ILoanProductService;

@RestController
@RequestMapping(value={"/product"})
public class LoanProductController extends BaseController{

	private static final Logger LOG = Logger.getLogger(LoanProductController.class);
	
	@Resource
	private ILoanProductService loanProductService;
	
	/**
	 * 获取Banner信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/getBanner"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getBanner(){
		final Result<Object> result = new Result<Object>();
		LOG.info("获取Banner信息接口开始执行....");
		try {
			List<Banner> dateMap = loanProductService.getBanner();
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_BANNER_SUCCESS);	
			result.setData(dateMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取Banner信息接口返回:%s",result.toString()));
		return result;
	}
	
	/**
	 * 获取查询条件信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/getSearchConditionInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getSearchConditionInfo(){
		final Result<Object> result = new Result<Object>();
		LOG.info("获取查询条件信息接口开始执行....");
		try {
			Map<String, Object> dateMap = loanProductService.getSearchConditionInfo();
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_PRODUCT_SEARCH_SUCCESS);	
			result.setData(dateMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取查询条件信息接口返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 查询产品信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/searchLoanProducts"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> searchLoanProducts(ProductSearchBean searchBean,PageBean bean){
		final Result<Object> result = new Result<Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		LOG.info("查询产品信息接口开始执行....");
		LOG.info(String.format("参数：%s",searchBean.toString()));
		try {
			PageBounds pageBounds=getPageBounds(bean);
			PageList<ShowLoanProdcut> loanProducts = loanProductService.SearchLoanProducts(searchBean,pageBounds);
			dataMap.put("list", loanProducts);
			dataMap.put("limit", loanProducts.getPaginator().getLimit());
			dataMap.put("totalPages", loanProducts.getPaginator().getTotalPages());
			dataMap.put("page", loanProducts.getPaginator().getPage());
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_LOAN_PRODUCT_LIST_SUCCESS);
			result.setData(dataMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("查询产品信息接口返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 首页产品信息接口
	 * @param uid:对应用户id
	 * @return
	 */
	@RequestMapping(value={"/getPropelAndHotProducts"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getPropelAndHotProducts(PageBean bean){
		final Result<Object> result = new Result<Object>();
		LOG.info("首页产品信息接口开始执行....");
		try {
			Map<String, Object> dataMap= loanProductService.getPropelAndHotProducts();
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_LOAN_PRODUCT_LIST_SUCCESS);
			result.setData(dataMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("首页产品信息接口返回：",result.toString()));
		return result;
	}
	
	
	/**
	 * 获取借款产品详情接口
	 * @return
	 */
	@RequestMapping(value={"/getLoanProductInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getLoanProductInfo(Integer productId) {
		final Result<Object> result = new Result<Object>();
		LOG.info("获取借款产品详情接口开始执行....");
		LOG.info(String.format("参数：%s",productId));
		try {
			if(productId!=null){
				Map<String, Object> productInfo = loanProductService.getLoanProductInfo(productId);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_LOAN_PRODUCT_SUCCESS);
				result.setData(productInfo);
			}else {
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取借款产品详情接口返回：%s",result.toString()));
		return result;
	}
}
