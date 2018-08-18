package com.trj.jk.web.service;



import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.model.response.BankConfRes;

public interface IBankCardService {
	PageList<Map<String, Object>> getCardListByUid(Integer uid, PageBounds pageBounds);

	public List<Map<String, Object>> getCardListByUid(Integer uid);

	boolean isBindCard(Integer uid);

	List<BankConfRes> getBankConfList();
}
