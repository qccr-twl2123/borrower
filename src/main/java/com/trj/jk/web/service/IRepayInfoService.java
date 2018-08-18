package com.trj.jk.web.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.entity.RepayRecordBean;

public interface IRepayInfoService {
	
	public PageList<RepayRecordBean> getRepayRecord(Integer uid,PageBounds pageBounds);

	public PageList<RepayRecordBean> getRepayRecordByLoanApplyId(Integer loanApplyId, PageBounds pageBounds);

}
