package com.trj.jk.web.service.thirdparty.soopay;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.UserBankCard;
import com.trj.jk.web.domain.UserBankCardCriteria;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.domain.entity.bankcard.BankCardBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;
import com.trj.jk.web.domain.entity.thirdparty.ThirdPartyProcessResult;
import com.trj.jk.web.domain.exception.ServiceException;
import com.trj.jk.web.mapper.UserBankCardMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.service.ICodeService;
import com.trj.jk.web.service.thirdparty.IThirdPartyCardService;
import com.trj.jk.web.service.thirdparty.IThirdPartyService;



@Transactional
public class CardSoopayServiceImpl extends AbstractSoopayServiceImpl implements IThirdPartyCardService{
	
	@Resource
	private UserBasicMapper   userBasicMapper;	
	@Resource
	private UserExtMapper userExtMapper;
	@Resource
	private UserBankCardMapper userBankCardMapper;	
	@Autowired
	private ICodeService codeService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

	/**
	 * 绑卡申请
	 * @param bean
	 * @param cardBean
	 * @param flag  true 长富贷 false 51公积金
	 * @return
	 * @throws Exception
	 */
	@Override
	public ErrorResult bindCard(Object bean, BankCardBean cardBean,Boolean flag) throws Exception {
		//save bank card info
		UserBasic user = userBasicMapper.selectByPrimaryKey(cardBean.getUid());
		if(user != null) {
			//校验只能绑自己的银行卡
			if(user.getIdentityId()!= null) {
				if(!user.getIdentityId().equals(cardBean.getIdentityCode())) {
					throw new ServiceException("只能绑定自己的银行卡");
				}
			}
			
			String cardId = cardBean.getCardId();
			UserBankCard card = null;			
			UserBankCardCriteria criteria = new UserBankCardCriteria();
			criteria.createCriteria().andCardIdEqualTo(cardId);
			
			List<UserBankCard> cardList = userBankCardMapper.selectByCriteria(criteria);
			if(cardList!=null && !cardList.isEmpty()){
				card=cardList.get(0);
			}
					
			if(card != null) {
				if( card.getStatus()==Status.STATUS_BIND_DONE || card.getStatus()==Status.STATUS_BIND_CONFIRM) {
					if(flag){
						throw new ServiceException("不能重复绑定同一张银行卡");
					}
					cardBean.setId(card.getId());
				}else {
					card.setCardHolder(cardBean.getCardHolder());
					card.setMediaId(cardBean.getMediaId());
					card.setIdentityCode(cardBean.getIdentityCode());
					card.setUid(cardBean.getUid());
					userBankCardMapper.updateByPrimaryKeySelective(card);
					cardBean.setId(card.getId());
				}
			} else {
				cardBean.setMerCustId(user.getId().toString());
				userBankCardMapper.insertSelective(cardBean);
			
			}

			
		}else{
			throw new ServiceException("未找到用户身份信息");
		}
		
		ThirdPartyProcessResult result = sendRequestProcess(bean, IThirdPartyService.REQUEST_TYPE_BIND_APPLY);
		result.getLog().setUid(user.getId());
		result.getLog().setRefId("bind_no: " + result.getData().get("bind_no"));
		
		saveRequestResponseLog(result.getLog());
		
		if(result.getResult().getError()== null) {
			cardBean.setStatus(Status.STATUS_BIND_VERIFY);
			cardBean.setBindNo((String)result.getData().get("bind_no"));
			userBankCardMapper.updateByPrimaryKeySelective(cardBean);	
			
			//如果原先账户未实名认证，则认为实名认证成功
			if(user.getIsIdentityAuth()==Status.STATUS_CERT_UNDO || user.getIsIdentityAuth()==Status.STATUS_CERT_FAIL){
				user.setIdentityId(cardBean.getIdentityCode());
				user.setIdentityAuthType(Constant.IDENTITY_AUTH_TYPE_BANKCARD);
				user.setIsIdentityAuth(Status.STATUS_CERT_SUCCESS);
				user.setName(cardBean.getCardHolder());
				userBasicMapper.updateByPrimaryKeySelective(user);
				
				UserExt ext = new UserExt();
				ext.setIdentityId(cardBean.getIdentityCode());
				ext.setName(cardBean.getCardHolder());
				UserExtCriteria criteria = new UserExtCriteria();
				criteria.createCriteria().andUidEqualTo(user.getId());
				userExtMapper.updateByCriteriaSelective(ext, criteria);
				
			}
			
			
		}else{
			throw new ServiceException(result.getResult().getError().getErrorMsg()); 
		}

		return result.getResult();
	}
	

	@Override
	public ErrorResult unBindCard(Object requestBean) throws Exception {
		return null;
	}
	

	@Override
	public ErrorResult bindConfirm(Object bean, UserBankCard cardBean) throws Exception{
		
		ThirdPartyProcessResult result = sendRequestProcess(bean, IThirdPartyService.REQUEST_TYPE_BIND_CONFIRM);
		result.getLog().setUid(cardBean.getUid());
		result.getLog().setRefId("bind_no: " + cardBean.getBindNo());
		
		saveRequestResponseLog(result.getLog());
		
		if(result.getResult().getError()== null) {			
			Map data = result.getData();
			cardBean.setStatus(Status.STATUS_BIND_CONFIRM);
			cardBean.setUsrBusiAgreementId((String)data.get("usr_busi_agreement_id"));
			cardBean.setUsrPayAgreementId((String)data.get("usr_pay_agreement_id"));
			cardBean.setGateId((String)data.get("gate_id"));
			if(!StringUtils.isEmpty(cardBean.getGateId())){
				Code code = codeService.getCodeByKeyAndNo(Constant.CODE_BIND_CARD_BANK,cardBean.getGateId());
				cardBean.setBankName(code.getCodeName());
			}			
			cardBean.setBankCardType((String)data.get("bank_card_type"));
			userBankCardMapper.updateByPrimaryKeySelective(cardBean);		
			
            String value=stringRedisTemplate.opsForValue().get("verify_code_bank_card_" + cardBean.getId().toString());
            if(StringUtils.isEmpty(value)){
            	stringRedisTemplate.opsForValue().set("verify_code_bank_card_" +  cardBean.getId().toString(), "not_expire", 15, TimeUnit.DAYS);
            }   				
		}else{
			throw new ServiceException(result.getResult().getError().getErrorMsg()); 
		}

		return result.getResult();
	}

	@Override
	public ErrorResult bindCardNotify(Object bean) {
		ThirdPartyProcessResult result = notifyProcess(bean, IThirdPartyService.REQUEST_TYPE_BIND_NOTIFY);
		String payAgreementId =  (String)result.getData().get("usr_pay_agreement_id");
		UserBankCardCriteria criteria = new UserBankCardCriteria();
		criteria.createCriteria().andUsrPayAgreementIdEqualTo(payAgreementId);
		List<UserBankCard> cardList = userBankCardMapper.selectByCriteria(criteria);
		UserBankCard card = null;
		if(cardList!=null && !cardList.isEmpty()){
			card=cardList.get(0);
		}

		if(card!=null){
			result.getLog().setUid(card.getUid());
		}else{
			result.getLog().setUid(00000);
		}
		result.getLog().setRefId("usr_pay_agreement_id: " + payAgreementId);
		
		saveRequestResponseLog(result.getLog());

		if(result.getResult().getError()== null) {
			Map data = result.getData();
			if(StringUtils.isNotBlank(payAgreementId)) {
				card.setStatus(Status.STATUS_BIND_DONE);
				card.setLastFourCardid((String)data.get("last_four_cardid"));
				userBankCardMapper.updateByPrimaryKeySelective(card);
			}	
		}
		
		return result.getResult();
		
	}


	@Override
	protected void setResponseParam(Map requestMap, Map responseParamMap) {
		// TODO Auto-generated method stub
		
	}		
}
