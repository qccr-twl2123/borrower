package com.trj.jk.web.service.impl;


import java.util.*;

import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.enums.UserBindCardStatusEnum;
import com.trj.jk.web.mapper.*;
import com.trj.jk.web.model.response.BankConfRes;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.service.IBankCardService;
import com.trj.jk.web.util.UtilConstant;

@Service
@Transactional
public class BankCardServiceImpl implements IBankCardService{

	@Autowired
	private UserBankCardMapper userBankCardMapper;

	@Autowired
	private UserBasicMapper userBasicMapper;

	@Autowired
	private BankConfMapper bankConfMapper;

	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private CodeMapper codeMapper;

	@Override
	public PageList<Map<String, Object>> getCardListByUid(Integer uid, PageBounds pageBounds) {
		UserBankCardCriteria criteria = new UserBankCardCriteria();
		criteria.createCriteria().andUidEqualTo(uid);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("cardBindBank", UtilConstant.CARD_BIND_BANK);
		paramMap.put("cardType",UtilConstant.CARD_TYPE);
		paramMap.put("bankBanner",UtilConstant.BANK_BANNER);
		return (PageList<Map<String, Object>>)userBankCardMapper.selectBankCardInfoByUid(paramMap,pageBounds);
	}


	@Override
	public List<Map<String, Object>> getCardListByUid(Integer uid) {
		UserBankCardCriteria criteria = new UserBankCardCriteria();
		criteria.createCriteria().andUidEqualTo(uid);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("cardBindBank", UtilConstant.CARD_BIND_BANK);
		paramMap.put("cardType",UtilConstant.CARD_TYPE);
		paramMap.put("bankBanner",UtilConstant.BANK_BANNER);
		return userBankCardMapper.selectBankCardInfoByUid(paramMap);
	}

	@Override
	public boolean isBindCard(Integer uid) {
		UserBankCardCriteria userBankCardCriteria = new UserBankCardCriteria();
		List<Byte> statusList=new ArrayList<Byte>();
		statusList.add(UserBindCardStatusEnum.CONFIRM.getStatus());
		statusList.add(UserBindCardStatusEnum.SUCCESS.getStatus());
		userBankCardCriteria.createCriteria().andUidEqualTo(uid).andStatusIn(statusList);
		List<UserBankCard> bankCards = userBankCardMapper.selectByCriteria(userBankCardCriteria);
		return CollectionUtils.isNotEmpty(bankCards)?true:false;
	}

	@Override
	public List<BankConfRes> getBankConfList(){
		BankConfCriteria bankConfCriteria = new BankConfCriteria();
		List<BankConf> bankConfList = bankConfMapper.selectByCriteria(bankConfCriteria);
		if(CollectionUtils.isNotEmpty(bankConfList)){
			return ObjectConvert.convertList(bankConfList,BankConfRes.class);
		}
		return null;
	}

}
