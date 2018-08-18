package com.trj.jk.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaoleilu.hutool.util.ObjectUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.contract.IContractService;

@RestController
@RequestMapping(value={"/contract"})
public class ContractController {

	private static final Logger LOG = Logger.getLogger(ContractController.class);
	
	@Resource
	private IContractService contractService;
	
	@RequestMapping(value={"/look"},method=RequestMethod.GET)
	public Result<Object> lookContract(Integer loanLimitId,Integer flag){
		Result<Object> result = new Result<Object>();
		LOG.info("合同查看接口调用》》》》》》");
		LOG.info(String.format("合同查看接口调用参数：loanLimitId:%s",loanLimitId));
		flag = ObjectUtil.defaultIfNull(flag,0);
		try{
			if(loanLimitId!=null){
				 List<Map<String, Object>> dataList=contractService.lookContract(loanLimitId,flag);
				 result.setSuccess(true);
				 result.setMessage(ResultMessageConstant.LOOK_CONTRACT_SUCCESS);
				 result.setData(dataList);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.info(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e){
			LOG.info(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("合同查看接口返回:%s",result.toString()));
		return result;
	}

	
	
}
