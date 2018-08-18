package com.trj.jk.web.controller.v3;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.commons.result.Result;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.RepayRecordBean;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.service.IApplyLoanService;
import com.trj.jk.web.service.IRepayInfoService;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.validator.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController("LoanRepayController_v3")
@RequestMapping(value={"/v3/order"})
public class LoanRepayController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(LoanRepayController.class);
	@Resource
	private IRepayInfoService repayInfoService;
	@Resource
	private IApplyLoanService applyLoanService;



	/**
	 *
	 * @param bean
	 * @return
     */
	@RequestMapping(value={"/getRepayRecord"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getRepayRecord(PageBean bean,String orderNo){
		LOG.info("获取还款记录接口开始执行....");
		final Result<Object> result = new Result<Object>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Integer uid = getCurrentUid();
		Assert.isNull(uid,"用户ID不能为空");
		try {
			PageBounds pageBounds=getPageBounds(bean);
			LoanApply loanApply = applyLoanService.getLoanApplyByOrderNo(uid,orderNo);
			if (loanApply != null) {
				PageList<RepayRecordBean> repayRecordBeans = repayInfoService.getRepayRecordByLoanApplyId(loanApply.getId(), pageBounds);
				dataMap.put("list", repayRecordBeans);
				dataMap.put("limit", repayRecordBeans.getPaginator().getLimit());
				dataMap.put("totalPages", repayRecordBeans.getPaginator().getTotalPages());
				dataMap.put("page", repayRecordBeans.getPaginator().getPage());
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_REPAY_RECORD_LIST_SUCCESS);
				result.setData(dataMap);
			} else {
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("获取还款记录接口返回:%s",result.toString()));
		return result;
	}
}
