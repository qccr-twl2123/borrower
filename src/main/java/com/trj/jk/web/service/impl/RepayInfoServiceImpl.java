package com.trj.jk.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.entity.RepayRecordBean;
import com.trj.jk.web.mapper.LoanRepayRecordMapper;
import com.trj.jk.web.service.IRepayInfoService;

@Service
@Transactional
public class RepayInfoServiceImpl implements IRepayInfoService{

	@Resource
	private LoanRepayRecordMapper loanRepayRecordMapper;

	@Override
	public PageList<RepayRecordBean> getRepayRecord(Integer uid, PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return (PageList<RepayRecordBean>)loanRepayRecordMapper.selectRepayRecordByUid(paramMap,pageBounds);
	}

	@Override
	public PageList<RepayRecordBean> getRepayRecordByLoanApplyId(Integer loanApplyId, PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanApplyId", loanApplyId);
		return (PageList<RepayRecordBean>)loanRepayRecordMapper.selectRepayRecordByLoanApplyId(paramMap,pageBounds);
	}
	
}
