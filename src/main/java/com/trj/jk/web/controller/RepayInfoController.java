package com.trj.jk.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.RepayRecordBean;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.service.IRepayInfoService;
import com.trj.jk.web.util.SessionUtil;

@RestController
@RequestMapping(value={"/repayInfo"})
public class RepayInfoController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(RepayInfoController.class);

	
	@Resource
	private IRepayInfoService repayInfoService;
	
	@RequestMapping(value={"/getRepayRecord"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getRepayRecord(PageBean bean){
		LOG.info("获取还款记录接口开始执行....");
		final Result<Object> result = new Result<Object>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			PageBounds pageBounds=getPageBounds(bean);
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null){			
				PageList<RepayRecordBean> repayRecorBeans = repayInfoService.getRepayRecord(uid,pageBounds);
				dataMap.put("list", repayRecorBeans);
				dataMap.put("limit", repayRecorBeans.getPaginator().getLimit());
				dataMap.put("totalPages", repayRecorBeans.getPaginator().getTotalPages());
				dataMap.put("page", repayRecorBeans.getPaginator().getPage());
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_REPAY_RECORD_LIST_SUCCESS);
				result.setData(dataMap);
			}else{
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
