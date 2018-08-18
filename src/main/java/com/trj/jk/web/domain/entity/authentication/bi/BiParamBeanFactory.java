package com.trj.jk.web.domain.entity.authentication.bi;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.LoanApplyContacts;
import com.trj.jk.web.domain.LoanApplyContactsCriteria;
import com.trj.jk.web.domain.entity.authentication.AuthenticationBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.mapper.LoanApplyContactsMapper;
import com.trj.jk.web.service.ICodeService;
import com.trj.jk.web.service.IUserService;

@Component
public class BiParamBeanFactory {
	private static final Logger LOG = LoggerFactory.getLogger(BiParamBeanFactory.class);


	private static LoanApplyContactsMapper loanApplyContactsMapper;
	private static IUserService userService;
	
	private static ICodeService codeService;
	
	public static ICodeService getCodeService() {
		return codeService;
	}
	
	@Autowired
	public void setCodeService(ICodeService codeService) {
		BiParamBeanFactory.codeService = codeService;
	}

	public static IUserService getUserService() {
		return userService;
	}
	
	@Autowired
	public  void setUserService(IUserService userService) {
		BiParamBeanFactory.userService = userService;
	}



	public static LoanApplyContactsMapper getLoanApplyContactsMapper() {
		return loanApplyContactsMapper;
	}

	@Autowired
	public  void setLoanApplyContactsMapper(
			LoanApplyContactsMapper loanApplyContactsMapper) {
		BiParamBeanFactory.loanApplyContactsMapper = loanApplyContactsMapper;
	}


	public static BiParamBase getBean(byte type, AuthenticationBean authBean, Integer uid){
		//学历认证
		if(type==Constant.CERTIFICATION_TYPE_DIPLOMA){
			String name=userService.getName(uid);
			String identityId=userService.getIdentityId(uid);
					
			return new DiplomaBean(name,identityId);
		}
		//公积金认证
		if(type==Constant.CERTIFICATION_TYPE_GJJ){
			if(Constant.CODE_HANGZHOU.equals(authBean.getArea())) {
				authBean.setArea(Constant.CODE_BI_HANGZHOU);
			}else if(Constant.CODE_NINGBO.equals(authBean.getArea())){
				authBean.setArea(Constant.CODE_BI_NINGBO);
			}
			return new GjjBean(authBean.getName(),authBean.getIdentityId(), authBean.getArea(),authBean.getHouseFundAccount(),
					authBean.getHouseFundPwd(), authBean.getMobile(),authBean.getSort());
		}
		//社保认证
		if(type==Constant.CERTIFICATION_TYPE_SOCIAL){
			return new SoinsBean();
//			if(Constant.CODE_HANGZHOU.equals(authBean.getArea())) {
//				authBean.setArea(Constant.CODE_BI_HANGZHOU);
//				return new SoinsBean(authBean.getName(),authBean.getIdentityId(), authBean.getArea(),authBean.getSocialInsuranceAccount(),
//						authBean.getSocialInsurancePwd(), authBean.getMobile(), Constant.HANGZHOU_SHEBAO_WEBSITE,Constant.HANGZHOU_SHEBAO_TYPE, authBean.getSort(), authBean.getEmail());
//			}else if(Constant.CODE_NINGBO.equals(authBean.getArea())){
//				authBean.setArea(Constant.CODE_BI_NINGBO);
//				return new SoinsBean(authBean.getName(),authBean.getIdentityId(), authBean.getArea(),authBean.getSocialInsuranceAccount(),
//						authBean.getSocialInsurancePwd(), authBean.getMobile(), Constant.NINGBO_SHEBAO_WEBSITE,Constant.HANGZHOU_SHEBAO_TYPE,  authBean.getSort(), authBean.getEmail());
//			}else if(Constant.CODE_WENZHOU.equals(authBean.getArea())){
//				authBean.setArea(Constant.CODE_BI_WENZHOU);
//				return new SoinsBean(authBean.getName(),authBean.getIdentityId(), authBean.getArea(),authBean.getSocialInsuranceAccount(),
//						authBean.getSocialInsurancePwd(), authBean.getMobile(), Constant.WENZHOU_SHEBAO_WEBSITE,Constant.HANGZHOU_SHEBAO_TYPE,  authBean.getSort(), "");
//			}
		}
		//征信认证
		if(type==Constant.CERTIFICATION_TYPE_CREDIT){
			return new CreditBean(authBean.getName(), authBean.getIdentityId(),authBean.getMobileValidateCode(),
					authBean.getCreditAccount(),authBean.getCreditPwd(), authBean.getMobile());
		}
		//信用卡认证
		if(type==Constant.CERTIFICATION_TYPE_CREDITCARD){
			return new CreditcardBean(authBean.getName(), authBean.getIdentityId(),authBean.getEmail(),authBean.getEmailPwd(),
					authBean.getMobile(), Constant.CREDITCARD_LOGINTYPE, "");
		}
		
		//京东认证
		if(type==Constant.CERTIFICATION_TYPE_JD){
			if(StringUtils.isEmpty(authBean.getName()) && StringUtils.isEmpty(authBean.getIdentityId())){
				String name=userService.getName(uid);
				String identityId=userService.getIdentityId(uid);
				return new JDBean(name, identityId,authBean.getMobileValidateCode(),
						authBean.getAccountJd(), authBean.getPasswordJd(), authBean.getMobile());
			}else{
				return new JDBean(authBean.getName(), authBean.getIdentityId(),authBean.getMobileValidateCode(),
						authBean.getAccountJd(), authBean.getPasswordJd(), authBean.getMobile());				
			}

		}	
		
		//运营商认证
		if(type==Constant.CERTIFICATION_TYPE_OPERATOR ){
			if(authBean.getLoanApplyId()!=null){
				String name=userService.getName(uid);
				String identityId=userService.getIdentityId(uid);
			    List<Code> codeList = codeService.getCodeByKey(Constant.CODE_CONTACT_TYPE);
			    HashMap map = new HashMap();
			    for(Code code:codeList){
			    	map.put(code.getCodeName(), code.getCodeNo());
			    }
			
				
				String contact_tel1=null;
				String contact_name1=null;
				String contact_type1=null;
				String contact_tel2=null;
				String contact_name2=null;
				String contact_type2=null;
				String contact_tel3=null;
				String contact_name3=null;
				String contact_type3=null;
				
				LoanApplyContactsCriteria criteria = new LoanApplyContactsCriteria();
				criteria.createCriteria().andLoanApplyIdEqualTo(authBean.getLoanApplyId());
				List<LoanApplyContacts> contacts = loanApplyContactsMapper.selectByCriteria(criteria);
				if(contacts!=null && contacts.size()>0){
					if(contacts.get(0)!=null) {
						contact_tel1 = contacts.get(0).getMobile();
						contact_name1 = contacts.get(0).getName();
						contact_type1 = map.get(contacts.get(0).getRelation()).toString();
					}
					
					if(contacts.get(1)!=null) {
						contact_tel2 = contacts.get(1).getMobile();
						contact_name2 = contacts.get(1).getName();
						contact_type2 = map.get(contacts.get(1).getRelation()).toString();
					}	
					
					if(contacts.get(2)!=null) {
						contact_tel3 = contacts.get(2).getMobile();
						contact_name3 = contacts.get(2).getName();
						contact_type3 = map.get(contacts.get(2).getRelation()).toString();
					}	
					
					return new OperatorJXLBean(name, identityId, authBean.getMobile(), contact_tel1, contact_name1, contact_type1,
							contact_tel2,contact_name2,contact_type2,contact_tel3,contact_name3,contact_type3,authBean.getMobile(), authBean.getServicePwd(),"");
				}
				
			} else {
				return new OperatorJXLTwoBean(authBean.getIdentityId(), authBean.getMobile(), authBean.getServicePwd(), authBean.getMobileValidateCode());
			}
			return null;
		}
		
		//移动运营商实名认证
		if(type==Constant.CERTIFICATION_TYPE_OPERATOR_STEP2) {
			String identityId=userService.getIdentityId(uid);
			return new OperatorJXLTwoBean(identityId, authBean.getMobile(), authBean.getServicePwd(), authBean.getMobileValidateCode());
		}
		
		//运营商实名认证
		if(type==Constant.CERTIFICATION_TYPE_PHONERZ) {
			return new OperatorHDBean(authBean.getName(), authBean.getIdentityId(), authBean.getMobile());
		}
		return null;
	}
}
