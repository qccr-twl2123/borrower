package com.trj.jk.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.enums.LoanApplyStatusEnum;
import com.trj.jk.web.enums.LoanApplyStepEnum;
import com.trj.jk.web.enums.LoanLimitStatusEnum;
import com.trj.jk.web.model.request.LoanApplyReq;
import com.trj.jk.web.model.request.OpenLoanApplyReq;
import com.trj.jk.web.model.request.UserProfessionReq;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.apache.commons.collections.CollectionUtils;

import com.trj.jk.web.domain.*;
import com.trj.jk.web.service.UserBasicService;
import com.trj.jk.web.util.DigestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.CarSalesOrganization;
import com.trj.jk.web.domain.CarSalesOrganizationCriteria;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.LoanApplyAddress;
import com.trj.jk.web.domain.LoanApplyAddressCriteria;
import com.trj.jk.web.domain.LoanApplyCar;
import com.trj.jk.web.domain.LoanApplyCarCriteria;
import com.trj.jk.web.domain.LoanApplyContacts;
import com.trj.jk.web.domain.LoanApplyContactsCriteria;
import com.trj.jk.web.domain.LoanApplyCriteria;
import com.trj.jk.web.domain.LoanApplyDriveLicence;
import com.trj.jk.web.domain.LoanApplyDriveLicenceCriteria;
import com.trj.jk.web.domain.LoanApplyProfession;
import com.trj.jk.web.domain.LoanApplyProfessionCriteria;
import com.trj.jk.web.domain.LoanAudit;
import com.trj.jk.web.domain.LoanAuditCriteria;
import com.trj.jk.web.domain.LoanFaceAuth;
import com.trj.jk.web.domain.LoanFaceAuthCriteria;
import com.trj.jk.web.domain.LoanProduct;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserContactsCriteria;
import com.trj.jk.web.domain.UserDriveLicence;
import com.trj.jk.web.domain.UserDriveLicenceCriteria;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.domain.UserProfession;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.LoanRepayPlanBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.personInfo.certfication.CertficationInfo;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.AttachmentMapper;
import com.trj.jk.web.mapper.CarSalesOrganizationMapper;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanApplyAddressMapper;
import com.trj.jk.web.mapper.LoanApplyCarMapper;
import com.trj.jk.web.mapper.LoanApplyContactsMapper;
import com.trj.jk.web.mapper.LoanApplyDriveLicenceMapper;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.mapper.LoanApplyProfessionMapper;
import com.trj.jk.web.mapper.LoanAuditMapper;
import com.trj.jk.web.mapper.LoanFaceAuthMapper;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.mapper.LoanProductMapper;
import com.trj.jk.web.mapper.LoanRepayGeneralMapper;
import com.trj.jk.web.mapper.LoanRepayPlanMapper;
import com.trj.jk.web.mapper.LoanRepayRecordMapper;
import com.trj.jk.web.mapper.MobileLocationIntervalMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserContactsMapper;
import com.trj.jk.web.mapper.UserDriveLicenceMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.UserProfessionMapper;
import com.trj.jk.web.service.IApplyLoanService;
import com.trj.jk.web.service.RedisNumberGenerator;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.util.UtilConstant;
import com.umpay.api.util.StringUtil;

@Service
@Transactional
public class ApplyLoanServiceImpl implements IApplyLoanService{

	private static final Logger LOG = Logger.getLogger(IApplyLoanService.class);

	@Resource
	private  LoanApplyMapper loanApplyMapper;

	@Resource
	private  LoanProductMapper loanProductMapper;

	@Resource
	private LoanFaceAuthMapper loanFaceAuthMapper;

	@Resource
	private LoanLimitMapper loanLimitMapper;

	@Resource
	private LoanRepayPlanMapper loanRepayPlanMapper;

	@Resource
	private LoanRepayRecordMapper loanRepayRecordMapper;

	@Resource
	private LoanApplyContactsMapper loanApplyContactsMapper;

	@Resource
	private UserContactsMapper userContactsMapper;

	@Resource
	private LoanApplyProfessionMapper loanApplyProfessionMapper;

	@Resource
	private UserProfessionMapper userProfessionMapper;

	@Resource
	private LoanAuditMapper loanAuditMapper;

	@Autowired
	private RedisNumberGenerator redisNumberGenerator;

	@Resource
	private CodeMapper codeMapper;

	@Resource
	private JdbcTemplate		cfdJdbcTemplate;

	@Resource
	private UserBasicMapper userBasicMapper;

	@Resource
	private UserExtMapper userExtMapper;

	@Resource
	private AttachmentMapper attachmentMapper;
	@Resource
	private LoanApplyAddressMapper loanApplyAddressMapper;

	@Resource
	private LoanApplyDriveLicenceMapper loanApplyDriveLicenceMapper;

	@Resource
	private UserDriveLicenceMapper userDriveLicenceMapper;

	@Resource
	private LoanApplyCarMapper loanApplyCarMapper;

	@Resource
	private UserBasicService userBasicService;

	@Resource
	private CarSalesOrganizationMapper carSalesOrganizationMapper;

	@Value("${app.upload.download}")
	private String downloadImgUrl;

	//crm系统域名
	@Value("${app.remote.domain.crm}")
	private String	remoteDomainCrm;

	@Value("${app.remote.domain.auditService}")
	private String auditProcessServiceUrl;



	@Override
	public void saveFaceAuthResult(LoanFaceAuth authResult) {
		loanFaceAuthMapper.insertSelective(authResult);
	}

	@Override
	public Integer addLoanApply(LoanApply loanApply) {
		if(loanApply.getProductId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_PRODUCT);
		}
		if(StringUtils.isEmpty(loanApply.getProvince())){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_PROVINCE);
		}
		if(StringUtils.isEmpty(loanApply.getCity())) {
			throw new CheckException(ErrorMessageConstant.ERR_NULL_CITY);
		}
		if(StringUtils.isEmpty(loanApply.getDistrict())){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_DISTRICT);
		}
		if(StringUtils.isEmpty(loanApply.getName())) {
			throw new CheckException(ErrorMessageConstant.ERR_NULL_NAME);
		}
		if(loanApply.getMobile()==null){
			throw new CheckException(ErrorMessageConstant.ERR_MOBILE_NULL);
		}
		if(StringUtils.isEmpty(loanApply.getIdentityId())) {
			throw new CheckException(ErrorMessageConstant.ERR_NULL_IDENTITY_ID);
		}
		if(loanApply.getAmount()==null) {
			throw new CheckException(ErrorMessageConstant.ERR_NULL_AMOUNT);
		}
		LoanProduct product = loanProductMapper.selectByPrimaryKey(loanApply.getProductId());
		String productCode = product.getProductCode();
		if(loanApply.getAmount().compareTo(new BigDecimal(product.getLimitScopeFrom()))==-1){
			throw new CheckException(ErrorMessageConstant.AMOUNT_IS_MINUS_INVALID);
		}
		if (loanApply.getAmount().compareTo(new BigDecimal(product.getLimitScopeTo()))==1) {
			throw new CheckException(ErrorMessageConstant.AMOUNT_IS_INVALID);
		}
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
		loanApplyCriteria.createCriteria().andUidEqualTo(uid).andProductIdEqualTo(loanApply.getProductId()).andIsValidEqualTo(Byte.parseByte("1"));
		List<LoanApply> loanApplies = loanApplyMapper.selectByCriteria(loanApplyCriteria);
		if(loanApplies!=null && !loanApplies.isEmpty()){
			throw new CheckException(ErrorMessageConstant.IS_HAVE_VALID_APPLY_ERROR);
		}
		if(product!=null) {
			String applyId= redisNumberGenerator.generateNumber("SQ"+product.getProductCode(), 5);
			loanApply.setApplyId(applyId);
			loanApply.setProductName(product.getName());
		}
		loanApply.setIsValid(Status.STATUS_APPLY_INVALID);
		loanApply.setTenant("jkWeb");
		return loanApplyMapper.insertSelective(loanApply);
	}





	@Override
	public PageList<LoanRepayPlanBean> getLoanRepayPlansByDate(Integer uid,Integer status,String date,PageBounds pageBounds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("status", status);
		paramMap.put("date", date);
		PageList<LoanRepayPlanBean> dataList=(PageList<LoanRepayPlanBean>) loanRepayPlanMapper.getLoanRepayPlansByDate(paramMap,pageBounds);
		return dataList;
	}

	@Override
	public int addLoanApplyContactsInfo(List<UserContacts> contacts, Integer loanApplyId) {
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		LOG.info(String.format("参数：contacts=%s,loanApplyId=%s,uid=%s",contacts.toString(),loanApplyId,uid));
		LoanApplyContactsCriteria criteria = new LoanApplyContactsCriteria();
		criteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
		//清除旧的联系人记录
		loanApplyContactsMapper.deleteByCriteria(criteria);

		for(UserContacts contact:contacts) {
			contact.setUid(uid);
			if(contact.getMobile()!=null && (contact.getMobile().startsWith("86") ||contact.getMobile().startsWith("+86"))){
				contact.setMobile(contact.getMobile().substring(contact.getMobile().indexOf("6")+1, contact.getMobile().length()));
			}
			if(!contact.getMobile().matches(RegexContext.MOBILE_REGEX)){
				throw new CheckException(ErrorMessageConstant.ERR_MOBILE_ERROR);
			}
			LoanApplyContacts loanApply = new LoanApplyContacts();
			loanApply.setUid(contact.getUid());
			loanApply.setLoanApplyId(loanApplyId);
			loanApply.setContactId(contact.getId());
			loanApply.setMobile(contact.getMobile());
			loanApply.setName(contact.getName());
			loanApply.setRelation(contact.getRelation());
			loanApplyContactsMapper.insertSelective(loanApply);
			//修改用户联系人信息
			if(contact.getId()==null){
				UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
				userContactsCriteria.createCriteria().andUidEqualTo(contact.getUid()).andMobileEqualTo(contact.getMobile());
				List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
				if(userContactsList!=null && !userContactsList.isEmpty()){
					throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
				}
				userContactsMapper.insertSelective(contact);
			}else{
				UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
				userContactsCriteria.createCriteria().andUidEqualTo(contact.getUid()).andMobileEqualTo(contact.getMobile()).andIdNotEqualTo(contact.getId());
				List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
				if(userContactsList!=null && !userContactsList.isEmpty()){
					throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
				}
				userContactsMapper.updateByPrimaryKeySelective(contact);
			}
		}
		LoanApply apply = new LoanApply();
		apply.setId(loanApplyId);
		apply.setStep(Status.STEP_CONTACT);


		return loanApplyMapper.updateByPrimaryKeySelective(apply);
	}

	@Override
	public void updateLoanApply(LoanApply apply) {


		loanApplyMapper.updateByPrimaryKeySelective(apply);
	}

	@Override
	public Integer addLoanApplyProfessionInfo(UserProfession userProfession,Integer loanApplyId) {
		if(StringUtil.isNotEmpty(userProfession.getCorpEmail())){
			if(!userProfession.getCorpEmail().matches(RegexContext.EMAIL_REGEX)){
				throw new CheckException(ErrorMessageConstant.ERR_EMAIL_ERROR);
			}
		}
		if(!userProfession.getCorpTel().matches(RegexContext.TEL_REGEX)){
			throw new CheckException(ErrorMessageConstant.ERR_TEL_ERROR);
		}
		//清除旧的联系人记录
		LoanApplyProfessionCriteria criteria = new LoanApplyProfessionCriteria();
		criteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
		loanApplyProfessionMapper.deleteByCriteria(criteria);
		LoanApplyProfession loanApplyProfession = new LoanApplyProfession();
		loanApplyProfession.setUid(userProfession.getUid());
		loanApplyProfession.setLoanApplyId(loanApplyId);
		loanApplyProfession.setProfessionId(userProfession.getId());
		loanApplyProfession.setCorpName(userProfession.getCorpName());
		loanApplyProfession.setIndustry(userProfession.getIndustry());
		loanApplyProfession.setCorpProvince(userProfession.getCorpProvince());
		loanApplyProfession.setCorpCity(userProfession.getCorpCity());
		loanApplyProfession.setCorpDistrict(userProfession.getCorpDistrict());
		loanApplyProfession.setCorpAddress(userProfession.getCorpAddress());
		loanApplyProfession.setDepartment(userProfession.getDepartment());
		loanApplyProfession.setPosition(userProfession.getPosition());
		loanApplyProfession.setCorpTel(userProfession.getCorpTel());
		loanApplyProfession.setSalaryDay(userProfession.getSalaryDay());
		loanApplyProfession.setSalaryScope(userProfession.getSalaryScope());
		loanApplyProfession.setCorpEmail(userProfession.getCorpEmail());
		loanApplyProfession.setStatus(Byte.parseByte("0"));
		loanApplyProfessionMapper.insertSelective(loanApplyProfession);
		//修改用户职业信息
		if(userProfession.getId()==null){
			userProfessionMapper.insert(userProfession);
		}else{
			userProfessionMapper.updateByPrimaryKeySelective(userProfession);
		}
		LoanApply apply = new LoanApply();
		apply.setId(loanApplyId);
		apply.setStep(Status.STEP_PROFESSIOIN);
		return loanApplyMapper.updateByPrimaryKeySelective(apply);
	}

	@Override
	public Map<String, Object> getIdentityAuthInfo(Integer uid) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		dataMap.put("is_identity_auth", userBasic.getIsIdentityAuth());
		if(UtilConstant.AUTHENTICATION_STATUS_2!=userBasic.getIsIdentityAuth() || userBasic.getIdentityId()==null){
			return dataMap;
		}else{
			dataMap.put("identity_auth_type", userBasic.getIdentityAuthType());
			UserExtCriteria userExtCriteria = new UserExtCriteria();
			userExtCriteria.createCriteria().andUidEqualTo(uid);
			List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
			UserExt userExt = userExts.get(0);
			if(userExts!=null&&!userExts.isEmpty()){
				if(userExt!=null){
					dataMap.put("name",userExt.getName());
					dataMap.put("identity_type", userExt.getIdentityType());
					dataMap.put("identity_id", userExt.getIdentityId());
				}
			}else{
				throw new CheckException(ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
			}
			if(UtilConstant.IDENTITY_AUTH_TYPE_0==userBasic.getIdentityAuthType()){
					if(userExt.getIdentityCardFrontImageId()!=null){
						Attachment attachment1 = attachmentMapper.selectByPrimaryKey(userExt.getIdentityCardFrontImageId());
						if(attachment1!=null){
							dataMap.put("identityCardFrontImageUrl", downloadImgUrl+attachment1.getAttachPath()+attachment1.getSaveName());
						}
					if(userExt.getIdentityCardOppositeImageId()!=null){
						Attachment attachment2 = attachmentMapper.selectByPrimaryKey(userExt.getIdentityCardOppositeImageId());
						if(attachment2!=null){
							dataMap.put("identityCardOppositeImageUrl", downloadImgUrl+attachment2.getAttachPath()+attachment2.getSaveName());
						}
					}
					if(userExt.getLivingBodyImageId()!=null){
						Attachment attachment3 = attachmentMapper.selectByPrimaryKey(userExt.getLivingBodyImageId());
						if(attachment3!=null){
							dataMap.put("livingBodyImageUrl", downloadImgUrl+attachment3.getAttachPath()+attachment3.getSaveName());
						}
					}
					if(userExt.getHeadImageId()!=null){
						Attachment attachment4 = attachmentMapper.selectByPrimaryKey(userExt.getHeadImageId());
						if(attachment4!=null){
							dataMap.put("HeadImageUrl", downloadImgUrl+attachment4.getAttachPath()+attachment4.getSaveName());
						}
					}
				}
			}
		}
		return dataMap;
	}



	@Override
	public void finish(Integer applyId, Integer uid) {
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		LoanFaceAuthCriteria loanFaceAuthCriteria = new LoanFaceAuthCriteria();
		loanFaceAuthCriteria.createCriteria().andApplyIdEqualTo(applyId);
		List<LoanFaceAuth> loanFaceAuths = loanFaceAuthMapper.selectByCriteria(loanFaceAuthCriteria);
		//检测提交借款申请时的人脸识别附件是否备份（jk_loan_face_auth）
		if(loanFaceAuths==null || loanFaceAuths.isEmpty()){
			throw new CheckException("借款申请没有对应人脸识别附件资料！");
		}else {
			if(loanFaceAuths.get(0).getIdentityCardFrontImageId()==null || loanFaceAuths.get(0).getIdentityCardOppositeImageId()==null || loanFaceAuths.get(0).getLivingBodyImageId()==null || loanFaceAuths.get(0).getHeadImageId()==null){
				throw new CheckException("借款申请对应人脸识别附件资料不完整！");
			}
		}
		LOG.info(String.format("finish接口在调传入借款申请对应人脸识别附件id绑定记录：loanFaceAuth：%s",loanFaceAuths.get(0).toString()));
		//检车地址信息时否同步到和借款相关联（jk_loan_apply_address（只允许有一条和该借款相关联的地址信息数据））
		LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
		loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
		List<LoanApplyAddress> loanApplyAddresseList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
		if(loanApplyAddresseList==null||loanApplyAddresseList.isEmpty()){
			if(loanApplyAddresseList!=null && !loanApplyAddresseList.isEmpty() ){
				LoanApplyAddress loanApplyAddress = loanApplyAddresseList.get(0);
				loanApplyAddress.setFamilyAddress(userExt.getFamilyAddress());
				loanApplyAddress.setFamilyDistrict(userExt.getFamilyDistrict());
				loanApplyAddress.setFamilyCity(userExt.getFamilyCity());
				loanApplyAddress.setFamilyProvince(userExt.getFamilyProvince());
				loanApplyAddress.setResidentialAddress(userExt.getResidentialAddress());
				loanApplyAddress.setResidentialDistrict(userExt.getResidentialDistrict());
				loanApplyAddress.setResidentialCity(userExt.getResidentialCity());
				loanApplyAddress.setResidentialProvince(userExt.getResidentialProvince());
				loanApplyAddress.setDecorationAddress(userExt.getDecorationAddress());
				loanApplyAddress.setDecorationDistrict(userExt.getDecorationDistrict());
				loanApplyAddress.setDecorationCity(userExt.getDecorationCity());
				loanApplyAddress.setDecorationProvince(userExt.getDecorationProvince());
				loanApplyAddressMapper.updateByPrimaryKeySelective(loanApplyAddress);
			}else{
				LoanApplyAddress loanApplyAddress = new LoanApplyAddress();
				loanApplyAddress.setUid(uid);
				loanApplyAddress.setLoanApplyId(applyId);
				loanApplyAddress.setFamilyAddress(userExt.getFamilyAddress());
				loanApplyAddress.setFamilyDistrict(userExt.getFamilyDistrict());
				loanApplyAddress.setFamilyCity(userExt.getFamilyCity());
				loanApplyAddress.setFamilyProvince(userExt.getFamilyProvince());
				loanApplyAddress.setResidentialAddress(userExt.getResidentialAddress());
				loanApplyAddress.setResidentialDistrict(userExt.getResidentialDistrict());
				loanApplyAddress.setResidentialCity(userExt.getResidentialCity());
				loanApplyAddress.setResidentialProvince(userExt.getResidentialProvince());
				loanApplyAddress.setDecorationAddress(userExt.getDecorationAddress());
				loanApplyAddress.setDecorationDistrict(userExt.getDecorationDistrict());
				loanApplyAddress.setDecorationCity(userExt.getDecorationCity());
				loanApplyAddress.setDecorationProvince(userExt.getDecorationProvince());
				loanApplyAddressMapper.insert(loanApplyAddress);
			}
		}
		LoanApply apply=loanApplyMapper.selectByPrimaryKey(applyId);
		LoanProduct product = loanProductMapper.selectByPrimaryKey(apply.getProductId());
		//如果是购车宝产品需要将用户的驾驶证信息同步到和借款申请关联的数据表中（jk_loan_apply_drive_licence）
		if(UtilConstant.LOAN_PRODUCTCODE_C1.equals(product.getProductCode())){//购车宝
			UserDriveLicence userDriveLicence = null;
			UserDriveLicenceCriteria userDriveLicenceCriteria = new UserDriveLicenceCriteria();
			userDriveLicenceCriteria.createCriteria().andUidEqualTo(uid);
			List<UserDriveLicence> userDriveLicenceList = userDriveLicenceMapper.selectByCriteria(userDriveLicenceCriteria);
			if(userDriveLicenceList!=null && !userDriveLicenceList.isEmpty()){
				userDriveLicence = userDriveLicenceList.get(0);
			}
			LoanApplyDriveLicenceCriteria loanApplyDriveLicenceCriteria = new LoanApplyDriveLicenceCriteria();
			loanApplyDriveLicenceCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
			List<LoanApplyDriveLicence> loanApplyDriveLicenceList = loanApplyDriveLicenceMapper.selectByCriteria(loanApplyDriveLicenceCriteria);
			if(loanApplyDriveLicenceList!=null && !loanApplyDriveLicenceList.isEmpty()){
				LoanApplyDriveLicence loanApplyDriveLicence = loanApplyDriveLicenceList.get(0);
				if(userDriveLicence!=null){
					loanApplyDriveLicence.setDriverName(userDriveLicence.getDriverName());
					loanApplyDriveLicence.setDriveLicence(userDriveLicence.getDriveLicence());
					loanApplyDriveLicence.setDriveLicenceFile(userDriveLicence.getDriveLicenceFile());
					loanApplyDriveLicence.setAttachId(userDriveLicence.getAttachId());
					loanApplyDriveLicenceMapper.updateByPrimaryKeySelective(loanApplyDriveLicence);
				}
			}else{
				LoanApplyDriveLicence loanApplyDriveLicence = new LoanApplyDriveLicence();
				loanApplyDriveLicence.setLoanApplyId(applyId);
				loanApplyDriveLicence.setUid(uid);
				loanApplyDriveLicence.setDriverName(userDriveLicence.getDriverName());
				loanApplyDriveLicence.setDriveLicence(userDriveLicence.getDriveLicence());
				loanApplyDriveLicence.setDriveLicenceFile(userDriveLicence.getDriveLicenceFile());
				loanApplyDriveLicence.setAttachId(userDriveLicence.getAttachId());
				loanApplyDriveLicenceMapper.insertSelective(loanApplyDriveLicence);
			}
		}

		//创建信审
		LoanAuditCriteria criteria = new LoanAuditCriteria();
		criteria.createCriteria().andApplyLoanIdEqualTo(applyId);
		List<LoanAudit> existAudits = loanAuditMapper.selectByCriteria(criteria);
		if(existAudits!=null && existAudits.size()>0) {
			throw new CheckException("请勿重复提交借款申请！");
		}
		if(Status.NEED_AUDIT.equals(product.getIsNeedAudit())){
			LoanAudit loanAudit = new LoanAudit();
			loanAudit.setApplyLoanId(applyId);
			loanAudit.setUid(uid);
			loanAudit.setRepayType(Constant.REPAY_TYPE_MYDEHK);
			loanAuditMapper.insertSelective(loanAudit);
		}else{
			LoanAudit loanAudit = new LoanAudit();
			loanAudit.setApplyLoanId(applyId);
			loanAudit.setUid(uid);
			loanAudit.setRepayType(Constant.REPAY_TYPE_MYDEHK);
			loanAudit.setStatus(Status.STEP_WAIT_SCORE);
			loanAuditMapper.insertSelective(loanAudit);
		}
		apply.setStatus(Status.STATUS_APPLY_SUBMIT);
		apply.setStep(Status.STEP_DONE);
		apply.setIsValid(Status.STATUS_APPLY_VALID);
		loanApplyMapper.updateByPrimaryKeySelective(apply);
	}


	@Override
	public LoanApply selectByPrimaryKey(Integer uid){
		return loanApplyMapper.selectByPrimaryKey(uid);
	}

	@Override
	public void addLoanApplyAddress(LoanApplyAddress loanApplyAddress) {
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		loanApplyAddress.setUid(uid);
		Integer applyId = loanApplyAddress.getLoanApplyId();
		LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
		loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
		List<LoanApplyAddress> loanApplyAddressList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
		if(loanApplyAddressList!=null && !loanApplyAddressList.isEmpty()){
			LoanApplyAddress addresse = loanApplyAddressList.get(0);
			addresse.setFamilyAddress(loanApplyAddress.getFamilyAddress());
			addresse.setFamilyDistrict(loanApplyAddress.getFamilyDistrict());
			addresse.setFamilyCity(loanApplyAddress.getFamilyCity());
			addresse.setFamilyProvince(loanApplyAddress.getFamilyProvince());
			addresse.setResidentialAddress(loanApplyAddress.getResidentialAddress());
			addresse.setResidentialDistrict(loanApplyAddress.getResidentialDistrict());
			addresse.setResidentialCity(loanApplyAddress.getResidentialCity());
			addresse.setResidentialProvince(loanApplyAddress.getResidentialProvince());
			addresse.setDecorationAddress(loanApplyAddress.getDecorationAddress());
			addresse.setDecorationDistrict(loanApplyAddress.getDecorationDistrict());
			addresse.setDecorationCity(loanApplyAddress.getDecorationCity());
			addresse.setDecorationProvince(loanApplyAddress.getDecorationProvince());
			loanApplyAddressMapper.updateByPrimaryKeySelective(addresse);
		}else{
			loanApplyAddressMapper.insert(loanApplyAddress);
		}
		//更新用户表地址信息数据
		if(StringUtil.isNotEmpty(loanApplyAddress.getFamilyAddress())){
			userExt.setFamilyAddress(loanApplyAddress.getFamilyAddress());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getFamilyDistrict())){
			userExt.setFamilyDistrict(loanApplyAddress.getFamilyDistrict());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getFamilyCity())){
			userExt.setFamilyCity(loanApplyAddress.getFamilyCity());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getFamilyProvince())){
			userExt.setFamilyProvince(loanApplyAddress.getFamilyProvince());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getResidentialAddress())){
			userExt.setResidentialAddress(loanApplyAddress.getResidentialAddress());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getResidentialDistrict())){
			userExt.setResidentialDistrict(loanApplyAddress.getResidentialDistrict());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getResidentialCity())){
			userExt.setResidentialCity(loanApplyAddress.getResidentialCity());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getResidentialProvince())){
			userExt.setResidentialProvince(loanApplyAddress.getResidentialProvince());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getDecorationAddress())){
			userExt.setDecorationAddress(loanApplyAddress.getDecorationAddress());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getDecorationDistrict())){
			userExt.setDecorationDistrict(loanApplyAddress.getDecorationDistrict());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getDecorationCity())){
			userExt.setDecorationCity(loanApplyAddress.getDecorationCity());
		}
		if(StringUtil.isNotEmpty(loanApplyAddress.getDecorationProvince())){
			userExt.setDecorationProvince(loanApplyAddress.getDecorationProvince());
		}
		userExtMapper.updateByPrimaryKeySelective(userExt);
		if(StringUtil.isNotEmpty(userExt.getFamilyProvince())&&StringUtil.isNotEmpty(userExt.getFamilyCity())&&StringUtil.isNotEmpty(userExt.getFamilyDistrict())&&StringUtil.isNotEmpty(userExt.getFamilyAddress())
				&&StringUtil.isNotEmpty(userExt.getResidentialProvince())&&StringUtil.isNotEmpty(userExt.getResidentialCity())&&StringUtil.isNotEmpty(userExt.getResidentialDistrict())&&StringUtil.isNotEmpty(userExt.getResidentialAddress()) ){
			userBasic.setIsAddressFill(Byte.parseByte("1"));
			userBasicMapper.updateByPrimaryKey(userBasic);
		}
	}

	@Override
	public void addLoanApplyCar(LoanApplyCar loanApplyCar) {
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		Integer applyId = loanApplyCar.getLoanApplyId();
		LoanApplyCarCriteria loanApplyCarCriteria = new LoanApplyCarCriteria();
		loanApplyCarCriteria.createCriteria().andLoanApplyIdEqualTo(applyId);
		List<LoanApplyCar> loanApplyCarList = loanApplyCarMapper.selectByCriteria(loanApplyCarCriteria);
		if(loanApplyCarList!=null && !loanApplyCarList.isEmpty()){
			LoanApplyCar car = loanApplyCarList.get(0);
			car.setSalesOrganization(loanApplyCar.getSalesOrganization());
			car.setBrand(loanApplyCar.getBrand());
			car.setModel(loanApplyCar.getModel());
			car.setNakedBikeAmount(loanApplyCar.getNakedBikeAmount());
			car.setAttachId(loanApplyCar.getAttachId());
			loanApplyCarMapper.updateByPrimaryKeySelective(car);
		}else{
			loanApplyCar.setUid(uid);
			loanApplyCarMapper.insertSelective(loanApplyCar);
		}

	}

	@Override
	public Map<String, Object> getLoanApplyCar(Integer loanApplyId) {
		Map<String, Object> result = null;
		LoanApplyCar loanApplyCar = null;
		LoanApplyCarCriteria loanApplyCarCriteria = new LoanApplyCarCriteria();
		loanApplyCarCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
		List<LoanApplyCar> loanApplyCars = loanApplyCarMapper.selectByCriteria(loanApplyCarCriteria);
		if(loanApplyCars!=null && !loanApplyCars.isEmpty()){
			result = new HashMap<String,Object>();
			List<String> imgUrls = new ArrayList<String>();
			loanApplyCar = loanApplyCars.get(0);
			if(loanApplyCar.getAttachId()!=null){
				List<String> attachIds = Arrays.asList(loanApplyCar.getAttachId().split(","));
				for(String attachId:attachIds){
					Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(attachId.trim()));
					if(attachment!=null && attachment.getAttachPath()!=null && attachment.getSaveName()!=null){
						imgUrls.add(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
					}
				}
			}
			result.put("id", loanApplyCar.getId());
			result.put("uid", loanApplyCar.getUid());
			result.put("loanApplyId", loanApplyCar.getLoanApplyId());
			result.put("brand", loanApplyCar.getBrand());
			result.put("model", loanApplyCar.getModel());
			result.put("nakedBikeAmount", loanApplyCar.getNakedBikeAmount());
			result.put("imgUrls", imgUrls);
			result.put("attachId", loanApplyCar.getAttachId());
			if(loanApplyCar.getSalesOrganization()!=null){
				result.put("salesOrganization", loanApplyCar.getSalesOrganization());
				CarSalesOrganization carSalesOrganization = carSalesOrganizationMapper.selectByPrimaryKey(loanApplyCar.getSalesOrganization());
				if(carSalesOrganization!=null){
					result.put("cityName", carSalesOrganization.getCityName());
					result.put("cityCode", carSalesOrganization.getCityCode());
					result.put("soName", carSalesOrganization.getName());
				}
			}
		}

		return result;
	}

	@Override
	public Map<String, Object> getBuyCarInfo(Integer loanApplyId,Integer uid) {
		boolean isQualify=true;
		Map<String, Object> result = new HashMap<>();
		List<CertficationInfo>  buyCarInfoList = new ArrayList<CertficationInfo>();
		CertficationInfo  buyCarInfo = new CertficationInfo();
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		buyCarInfo.setName("订车信息");
		buyCarInfo.setIsFill(Byte.parseByte("1"));//1:必填
		LoanApplyCarCriteria loanApplyCarCriteria = new LoanApplyCarCriteria();
		loanApplyCarCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
		List<LoanApplyCar> cars = loanApplyCarMapper.selectByCriteria(loanApplyCarCriteria);
		if(cars!=null && !cars.isEmpty()){
			buyCarInfo.setResult("已填写");
		}else{
			buyCarInfo.setResult("未填写");
			isQualify=false;
		}
		buyCarInfoList.add(buyCarInfo);
		result.put("buyCarInfo", buyCarInfoList);
		List<CertficationInfo>  driveLicenceInfoList = new ArrayList<CertficationInfo>();
		CertficationInfo driveLicenceInfo = new CertficationInfo();
		driveLicenceInfo.setName("驾照信息");
		driveLicenceInfo.setIsFill(Byte.parseByte("1"));//1:必填
		if(userBasic.getIsDriveLicenceFill()==1){
			driveLicenceInfo.setResult("已填写");
		}else{
			driveLicenceInfo.setResult("未填写");
			isQualify=false;
		}
		driveLicenceInfoList.add(driveLicenceInfo);
		result.put("driveLicenceInfo", driveLicenceInfoList);
		result.put("isQualify", isQualify);
		return result;
	}

	@Override
	public List<Map<String, Object>> getSoCity() {
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		List<CarSalesOrganization> list = carSalesOrganizationMapper.getSoCity();
		if(list!=null&&!list.isEmpty()){
			for(CarSalesOrganization carSalesOrganization:list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cityCode", carSalesOrganization.getCityCode());
				map.put("cityName", carSalesOrganization.getCityName());
				resultlist.add(map);
			}
		}
		return resultlist;
	}

	@Override
	public List<Map<String, Object>> getSalesOrganization(String cityCode) {
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		CarSalesOrganizationCriteria criteria = new CarSalesOrganizationCriteria();
		criteria.createCriteria().andCityCodeEqualTo(cityCode).andStatusEqualTo(Byte.parseByte("1"));
		List<CarSalesOrganization> list = carSalesOrganizationMapper.selectByCriteria(criteria);
		if(list!=null && !list.isEmpty()){
			for(CarSalesOrganization carSalesOrganization:list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("soId", carSalesOrganization.getId());
				map.put("soName", carSalesOrganization.getName());
				resultlist.add(map);
			}
		}
		return resultlist;
	}

	@Override
	public List<LoanApply> queryLoanApply(Integer uid,Integer status) {
		LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
		loanApplyCriteria.setOrderByClause("create_time desc");
		loanApplyCriteria.createCriteria().andUidEqualTo(uid).andStatusEqualTo(status.byteValue());
		return loanApplyMapper.selectByCriteria(loanApplyCriteria);
	}

	@Override
	public Boolean saveLoanApply(LoanApplyReq loanApplyReq, Integer uid) {
		ValidatorUtils.validateEntity(loanApplyReq);
		LoanProduct loanProduct = loanProductMapper.selectByPrimaryKey(loanApplyReq.getProductId());
		//申请金额校验
		Assert.isTrue((loanApplyReq.getAmount().compareTo(new BigDecimal(loanProduct.getLimitScopeFrom()))==-1),"申请金额小于最小可借额度");
		Assert.isTrue((loanApplyReq.getAmount().compareTo(new BigDecimal(loanProduct.getLimitScopeTo()))==1),"申请金额大于最大可借额度");
		//申请城市校验
		if(UtilConstant.LOAN_PRODUCTCODE_C9.equals(loanProduct.getProductCode())){//工薪贷申请城市只能是杭州
			Assert.isTrue(!UtilConstant.C9_OPEN_CITY.contains(loanApplyReq.getCity()),"区域未开放");
		}
		//历史申请检验
		LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
		loanApplyCriteria.createCriteria().andUidEqualTo(uid).andProductIdEqualTo(loanApplyReq.getProductId()).andIsValidEqualTo(Byte.parseByte("1"));
		List<LoanApply> loanApplies = loanApplyMapper.selectByCriteria(loanApplyCriteria);
		Assert.isTrue(CollectionUtils.isNotEmpty(loanApplies),"已经存在有效的该产品借款申请");
		//构建借款申请模型
		LoanApply loanApply  = ObjectConvert.convertObject( loanApplyReq, LoanApply.class);
		String applyId= redisNumberGenerator.generateNumber("SQ"+loanProduct.getProductCode(), 5);
		loanApply.setApplyId(applyId);
		loanApply.setUid(uid);
		loanApply.setProductName(loanProduct.getName());
		loanApply.setIsValid(Status.STATUS_APPLY_INVALID);
		loanApply.setTenant("jkWeb");
		Integer sum = loanApplyMapper.insertSelective(loanApply);
		Assert.isTrue((sum < 0),"新增借款失败");
		return  true;
	}


	@Override
	public int addLoanApplyContactsInfo(List<UserContacts> contacts, Integer loanApplyId,Integer uid,String stepCode) {
		LOG.info(String.format("参数：contacts=%s,loanApplyId=%s,uid=%s",contacts.toString(),loanApplyId,uid));
		//清除旧的联系人记录
		LoanApplyContactsCriteria criteria = new LoanApplyContactsCriteria();
		criteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
		loanApplyContactsMapper.deleteByCriteria(criteria);
		for(UserContacts contact:contacts) {
			contact.setUid(uid);
			if(contact.getMobile()!=null && (contact.getMobile().startsWith("86") ||contact.getMobile().startsWith("+86"))){
				contact.setMobile(contact.getMobile().substring(contact.getMobile().indexOf("6")+1, contact.getMobile().length()));
			}
			if(!contact.getMobile().matches(RegexContext.MOBILE_REGEX)){
				throw new CheckException(ErrorMessageConstant.ERR_MOBILE_ERROR);
			}
			LoanApplyContacts loanApply = new LoanApplyContacts();
			loanApply.setUid(contact.getUid());
			loanApply.setLoanApplyId(loanApplyId);
			loanApply.setContactId(contact.getId());
			loanApply.setMobile(contact.getMobile());
			loanApply.setName(contact.getName());
			loanApply.setRelation(contact.getRelation());
			loanApplyContactsMapper.insertSelective(loanApply);
			//修改用户联系人信息
			if(contact.getId()==null){
				UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
				userContactsCriteria.createCriteria().andUidEqualTo(contact.getUid()).andMobileEqualTo(contact.getMobile());
				List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
				if(userContactsList!=null && !userContactsList.isEmpty()){
					throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
				}
				userContactsMapper.insertSelective(contact);
			}else{
				UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
				userContactsCriteria.createCriteria().andUidEqualTo(contact.getUid()).andMobileEqualTo(contact.getMobile()).andIdNotEqualTo(contact.getId());
				List<UserContacts> userContactsList = userContactsMapper.selectByCriteria(userContactsCriteria);
				if(userContactsList!=null && !userContactsList.isEmpty()){
					throw new CheckException(ErrorMessageConstant.ERR_DATA_ERROR);
				}
				userContactsMapper.updateByPrimaryKeySelective(contact);
			}
		}
		LoanApply apply = new LoanApply();
		apply.setId(loanApplyId);
		apply.setStep(Status.STEP_CONTACT);
		Integer updateNum = loanApplyMapper.updateByPrimaryKeySelective(apply);

		return updateNum;
	}
	@Override
	public Boolean saveLoanApplyProfessionInfo(UserProfessionReq userProfessionReq) {
		ValidatorUtils.validateEntity(userProfessionReq);
		//清除旧的联系人记录
		LoanApplyProfessionCriteria criteria = new LoanApplyProfessionCriteria();
		criteria.createCriteria().andLoanApplyIdEqualTo(userProfessionReq.getLoanApplyId());
		loanApplyProfessionMapper.deleteByCriteria(criteria);
		//构建申请借款公司信息
		LoanApplyProfession loanApplyProfession = ObjectConvert.convertObject(userProfessionReq,LoanApplyProfession.class);
		loanApplyProfession.setLoanApplyId(userProfessionReq.getLoanApplyId());
		loanApplyProfession.setStatus(Byte.parseByte("0"));
		Integer insertNum = loanApplyProfessionMapper.insertSelective(loanApplyProfession);
		UserProfession userProfession  = ObjectConvert.convertObject(userProfessionReq,UserProfession.class);
		if(userProfessionReq.getId() == null){
			userProfessionMapper.insert(userProfession);
		}else{
			userProfessionMapper.updateByPrimaryKeySelective(userProfession);
		}
		return true;
	}

	public LoanLimit getLoanLimitByApplyId(Integer applyId) {
		LoanLimitCriteria criteria = new LoanLimitCriteria();
		criteria.createCriteria().andLoanApplyIdEqualTo(applyId);
		List<LoanLimit> list = loanLimitMapper.selectByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}

	public LoanApply getLoanApplyByApplyId(String applyId) {
		LoanApplyCriteria criteria = new LoanApplyCriteria();
		criteria.createCriteria().andApplyIdEqualTo(applyId);
		List<LoanApply> list = loanApplyMapper.selectByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}

	public LoanApply getLoanApplyByOrderNo(Integer uid,String orderNo) {
		LoanApplyCriteria criteria = new LoanApplyCriteria();
		criteria.createCriteria().andOrderNoEqualTo(orderNo).andUidEqualTo(uid);
		List<LoanApply> list = loanApplyMapper.selectByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}

	public LoanProduct getLoanProductByCode(String productCode) {
		LoanProductCriteria loanProductCriteria = new LoanProductCriteria();
		loanProductCriteria.createCriteria().andProductCodeEqualTo(productCode);
		List<LoanProduct> productList = loanProductMapper.selectByCriteria(loanProductCriteria);
		return productList.isEmpty() ? null : productList.get(0);
	}






}
