package com.trj.jk.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.alibaba.fastjson.JSON;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.authentication.bi.SoinsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.MobileValidateCode;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.user.UserBean;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.jms.IQueueService;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.mapper.LoanAuditMapper;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.mapper.LoanProductMapper;
import com.trj.jk.web.mapper.UserBankCardMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.jdbc.JdbcDao;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.RedisNumberGenerator;
import com.trj.jk.web.task.async.ContractPublishTask;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.UtilConstant;
import com.trj.message.service.api.SmsSendParam;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final java.util.Random random = new java.util.Random();

	//crm系统域名
	@Value("${app.remote.domain.crm}")
	private String	remoteDomainCrm;

	//crm个人用户e签宝签章开户url
	@Value("${app.remote.domain.esOpenAccountUrl}")
	private String esOpenAccountUrl;

	//调用crm系统e签宝发送签署短信验证码接口url
	@Value("${app.remote.domain.signatureAccreditUrl}")
	private String signatureAccreditUrl;

	//crm生成借款合同信息和编号并电子签章授权协议签章url
	@Value("${app.remote.domain.generateContractAndSignAccreditUrl}")
	private String generateContractAndSignAccreditUrl;

	//免息购crm生成借款合同信息和编号并电子签章授权协议签章url
	@Value("${app.remote.domain.interestFreeGenerateContract}")
	private String freeInterestGenerateContract;	
	
	
	//crm合同发布url
	@Value("${app.remote.domain.contractPublishUrl}")
	private String contractPublishUrl;

	@Autowired
	private UserBasicMapper userBasicMapper;

	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private LoanApplyMapper loanApplyMapper;

	@Autowired
	private LoanProductMapper loanProductMapper;

	@Autowired
	private CodeMapper codeMapper;

	@Autowired
	private IThreadTaskService threadTaskService;

	@Autowired
	private RedisNumberGenerator redisNumberGenerator;

	@Autowired
	private  LoanLimitMapper loanLimitMapper;

	@Autowired
	private  LoanAuditMapper loanAuditMapper;

	@Autowired
	private  IQueueService queueService;

	@Autowired
	private  JdbcDao JdbcDao;

	@Autowired
	private UserBankCardMapper userBankCardMapper;

	@Override
	public void modifyPassword(UserBean userBean) {
		if(userBean.getPassword()==null){
			throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_NULL);
		}
		if(userBean.getOldPassword()==null){
			throw new CheckException(ErrorMessageConstant.ERR_OLD_PASSWORD_NULL);
		}
		String oldPassword = userBean.getOldPassword();
		UserBasic retUser =userBasicMapper.selectByPrimaryKey(userBean.getId());

		String md5Pwd=DigestUtil.getMD5(oldPassword);
		if(!md5Pwd.equals(retUser.getPassword())){
			throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_VERIFY);
		}
		String newMd5Pwd = DigestUtil.getMD5(userBean.getPassword());
		userBean.setPassword(newMd5Pwd);
		userBasicMapper.updateByPrimaryKeySelective(userBean);
	}



	public void checkUserInfo(UserBean userBean){

		if(userBean.getId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_ID);
		}


		if(Constant.CHANGE_PW_BY_OLDPW.equals(userBean.getModifyPassowrdWay())){
			if(userBean.getPassword()==null){
				throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_NULL);
			}

			if(userBean.getOldPassword()==null){
				throw new CheckException(ErrorMessageConstant.ERR_OLD_PASSWORD_NULL);
			}
			String oldPassword = userBean.getOldPassword();

			UserBasic retUser =userBasicMapper.selectByPrimaryKey(userBean.getId());

			String md5Pwd=DigestUtil.getMD5(oldPassword);

			if(!md5Pwd.equals(retUser.getPassword())){
				throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_VERIFY);
			}

		}else if(Constant.CHANGE_PW_BY_NAMECERT.equals(userBean.getModifyPassowrdWay())) {
			if(userBean.getName()==null){
				throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_NULL);
			}
			if(userBean.getIdentityId()==null) {
				throw new CheckException(ErrorMessageConstant.ERR_NULL_IDENTITY_ID);
			}
			UserBean user = userExtMapper.getUserInfo(userBean.getId());
			if(!userBean.getName().equals(user.getName()) || !userBean.getIdentityId().equals(user.getIdentityId())){
				throw new CheckException(ErrorMessageConstant.ERR_REAL_NAME_VERIFY);
			}

		}else if(Constant.CHANGE_PW_BY_QUESTION.equals(userBean.getModifyPassowrdWay())){

		}else {
			throw new CheckException(ErrorMessageConstant.ERR_PARAM_ERROR);
		}
	}



	@Override
	public Map<String, Object> verifyCode(String mobile,String verifyCode,Integer bankCardId) {
		Map<String, Object> result =new HashMap<String, Object>();
		if(bankCardId!=0){
			mobile = userBankCardMapper.selectByPrimaryKey(bankCardId).getMediaId();
		}
		String status=JdbcDao.getTrbsVerifyCodeStatus(mobile, verifyCode);
		if(status==null){
			result.put("isValid", false);
			result.put("message", ErrorMessageConstant.ERR_VERIFY_INCORRECT);
		}else if(Status.VERIFY_EXPIRE.equals(status)){
			result.put("isValid", false);
			result.put("message", ErrorMessageConstant.ERR_VERIFY_INVALID);
		}else{
			result.put("isValid", true);
		}
		return result;
	}



	@Override
	public String getName(Integer uid) {
		UserExtCriteria criteria = new UserExtCriteria();
		criteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> extList = userExtMapper.selectByCriteria(criteria);
		if(extList!=null && extList.size() >0){
			UserExt ext = extList.get(0);
			return ext.getName();
		}
		return null;
	}



	@Override
	public String getIdentityId(Integer uid) {
		UserBasic basic = new UserBasic();
		basic.setId(uid);
		UserBasic data = userBasicMapper.selectByPrimaryKey(uid);
		return data.getIdentityId();
	}


/**
 * 电子签章授权
 * @param uid
 * @return
 */
	@Override
	public boolean signatureAccredit(Integer uid) {
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		if(userBasic.getEsAccountId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NO_ES_EXIST_ACCOUNT);
		}
		//调用crm系统e签宝发送签署短信验证码接口
		Map<String, String> param = new HashMap<String, String>();
		param.put("jkWebEsAccountId", String.valueOf(userBasic.getEsAccountId()));
		LOG.info("crm系统e签宝发送签署短信验证码接口服务url："+(remoteDomainCrm+signatureAccreditUrl));
		LOG.info("crm系统e签宝发送签署短信验证码接口服务服务参数："+param.toString());
		String body = HttpClientUtils.httpPost((remoteDomainCrm+signatureAccreditUrl), null, param, null, null);
		Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(body, Map.class);
		LOG.info("crm系统e签宝发送签署短信验证码接口服务返回："+result.toString());
		if(!"0".equals(String.valueOf(result.get("isSuccess")))){
			throw new RuntimeException("e签宝发送签署短信验证码异常！");
		}
		return true;

	}


	/**
	 * 电子签章
	 * @param uid
	 * @param verifyCode
	 * @param loanLimit
	 * @throws Exception
	 */
	@Override
	public void electronSignature(Integer uid, LoanLimit loanLimit,String verifyCode) throws Exception {
		//校验额度批复信息
		if(loanLimit.getLoanApplyId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_LOAN_APPLY_ID_ERROR);
		}
		if(loanLimit.getProductId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_PRODUCT_ID_ERROR);
		}
		if(loanLimit.getUseAmount()==null||new BigDecimal("0.00").compareTo(loanLimit.getUseAmount())==1 ){
			throw new CheckException(ErrorMessageConstant.ERR_AMOUNT_NULL_ERROR);
		}
		if(new BigDecimal("1000.00").compareTo(loanLimit.getUseAmount())==1 ){
			throw new CheckException(ErrorMessageConstant.ERR_USERAMOUNT_INVALID_ERROR);
		}
		if(loanLimit.getRepayType()==null){
			throw new CheckException(ErrorMessageConstant.ERR_REPAYTYPE_ERROR);
		}
		if(loanLimit.getTerm()==null){
			throw new CheckException(ErrorMessageConstant.ERR_TERM_ERROR);
		}
		if(loanLimit.getTermUnit()==null){
			throw new CheckException(ErrorMessageConstant.ERR_TERM_UNIT_ERROR);
		}
		if(loanLimit.getBankCardId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_BANKCARDID_ERROR);
		}
		if(loanLimit.getInterest()==null){
			throw new CheckException(ErrorMessageConstant.ERR_INTEREST_ERROR);
		}
		if(loanLimit.getExpectRepayAmount()==null){
			throw new CheckException(ErrorMessageConstant.ERR_EXPECTREPAYAMOUNT_ERROR);
		}

		//额度校验
		BigDecimal useAmount = loanLimit.getUseAmount();
		BigDecimal cutCharge = loanLimit.getCutCharge();
		Integer loanApplyId = loanLimit.getLoanApplyId();
		LoanAuditCriteria loanAuditCriteria = new LoanAuditCriteria();
		loanAuditCriteria.createCriteria().andApplyLoanIdEqualTo(loanApplyId);
		LoanAudit loanAudit = loanAuditMapper.selectByCriteria(loanAuditCriteria).get(0);
		if(loanAudit.getRestLimit().compareTo(loanLimit.getUseAmount())==-1){
			throw new CheckException(ErrorMessageConstant.ERR_AMOUNT_ERROR);
		}
		//保存额度批复提交信息
		LoanProduct product = loanProductMapper.selectByPrimaryKey(loanLimit.getProductId());
		if(product!=null) {
			String receiptId= redisNumberGenerator.generateNumber("JK"+product.getProductCode(), 5);
			loanLimit.setLoanReceiptId(receiptId);
		}
		loanLimit.setIsContractPublish(Byte.parseByte("0"));

		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		loanLimit.setName(userExt.getName());
		loanLimit.setMobile(userBasic.getMobile());
		loanLimit.setInterest(loanLimit.getInterest().multiply(new BigDecimal("100.00")));
		loanLimit.setTenant(UtilConstant.TENANT_JKWEB);
		loanLimitMapper.insertSelective(loanLimit);
		//可用额度减少
		loanAudit.setRestLimit(loanAudit.getRestLimit().subtract(useAmount));
		loanAudit.setCutCharge(cutCharge);
		loanAuditMapper.updateByPrimaryKey(loanAudit);
		LoanApply loanApply = loanApplyMapper.selectByPrimaryKey(loanApplyId);
		if(loanApply!=null && loanApply.getProductId()!=null){
			String productCode = loanProductMapper.selectByPrimaryKey(loanApply.getProductId()).getProductCode();
			if(UtilConstant.LOAN_PRODUCTCODE_C1.equals(productCode)){//如果是购车宝只允许用户使用一次额度使用
				loanApply.setIsValid(Byte.parseByte("0"));
				loanApplyMapper.updateByPrimaryKey(loanApply);
			}
			//调用crm系统生成合同服务并签署电子签章授权协议
			CodeCriteria codeCriteria = new CodeCriteria();
			codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.PRODUCT_CONTRACT_KEY).andCodeNoEqualTo(productCode);
			List<Code> codes = codeMapper.selectByCriteria(codeCriteria);
			if(codes!=null && !codes.isEmpty()){
				String codeName = codes.get(0).getCodeName();
				//生成合同数据
				//调用crm系统生成合同服务并签署电子签章授权协议
				generateContractAndSignAccredit(uid,loanLimit,verifyCode,codeName);
				//异步调用crm合同发布接口
				Map<String, String> param = new HashMap<String, String>();
				param.put("jkWebUid", String.valueOf(uid));
				param.put("jkWebLoanLimit", JsonUtils.objectToJsonString(loanLimit));
				threadTaskService.asyncExecute(new ContractPublishTask((remoteDomainCrm+contractPublishUrl),param));
			}else{
				throw new CheckException("借款申请产品合同配置不可以为空！");
			}
			//调取crm系统服务进行电子签章业务
		}else{
			throw new CheckException("借款申请产品Id不可以为空！");
		}
	}

	/**
	 *
	 * @param uid(用户Id)
	 * @param loanLimit(额度使用Id)
	 * @param verifyCode(签署电子签章协议的验证码)
	 * @param contractsTplNo(要生成合同数据的模板编号例如：工薪贷 CT009,CT010,CT011,CT012)
	 */
	public void generateContractAndSignAccredit(Integer uid,LoanLimit loanLimit,String verifyCode,String contractsTplNo){
		Integer esAccountId = userBasicMapper.selectByPrimaryKey(uid).getEsAccountId();
		if(esAccountId==null){
			throw new CheckException("用户还未e签宝开户！");
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("jkWebUid", String.valueOf(uid));
		param.put("jkWebLoanLimit", JsonUtils.objectToJsonString(loanLimit));
		param.put("jkWebSignAccreditVerifyCode", String.valueOf(verifyCode));
		param.put("jkWebContractsTplNo", String.valueOf(contractsTplNo));
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务url："+(remoteDomainCrm+generateContractAndSignAccreditUrl));
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务参数："+param.toString());
		String body = HttpClientUtils.httpPost((remoteDomainCrm+generateContractAndSignAccreditUrl), null, param, null, null);
		Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(body, Map.class);
		if(result == null || !"0".equals(String.valueOf(result.get("isSuccess")))){
			throw new RuntimeException("电子签章授权协议异常！"+String.valueOf(result.get("message")));
		}
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务返回："+result.toString());
	}


	@Override
	public String doContractPublish(String url,Map<String, String> param) {
		String loanLimitStr = param.get("jkWebLoanLimit");
		LoanLimit loanLimit = (LoanLimit)JsonUtils.string2Object(loanLimitStr, LoanLimit.class);
		LOG.info("crm合同发布服务url："+url);
		LOG.info("crm合同发布服务参数："+param.toString());
		String body = HttpClientUtils.httpPost(url, null, param, null, null);
		LOG.info("crm合同发布服务返回信息"+body);
		Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(body, Map.class);
		if("0".equals(String.valueOf(result.get("isSuccess")))){
			loanLimit.setIsContractPublish(Byte.parseByte("1"));
			loanLimit.setPublishContractId(String.valueOf(result.get("data")));
			loanLimitMapper.updateByPrimaryKeySelective(loanLimit);
		};
		return body;
	}



	@Override
	public void esOpenAccount(Integer uid) {
		UserBasic userBasic=userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		if(userExts==null||userExts.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
		}
		UserExt userExt = userExts.get(0);
		//e签宝签章开户逻辑
		if(userBasic.getEsAccountId()==null){//第一次借款用户还未电子签章开户
			if(userBasic.getIsIdentityAuth()!=2||userExt.getName()==null||userExt.getIdentityId()==null){//用户还未身份认证成功
				throw new CheckException(ErrorMessageConstant.ERR_NO_IDENTITY_AUTH_ERROR);
			}
			//调用crm系统e签宝个人签章开户
			Map<String, String> param = new HashMap<String, String>();
			param.put("jkWebUid", String.valueOf(uid));
			LOG.info("crm系统e签宝个人签章开户服务url："+(remoteDomainCrm+esOpenAccountUrl));
			LOG.info("crme系统签宝个人签章开户服务参数："+param.toString());
			String responseBody = HttpClientUtils.httpPost((remoteDomainCrm+esOpenAccountUrl), null, param, null, null);
			Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(responseBody, Map.class);
			LOG.info("crme系统签宝个人签章开户服务返回结果："+result.toString());
			if(!"0".equals(String.valueOf(result.get("isSuccess")))){
				throw new RuntimeException(ErrorMessageConstant.ERR_ES_ADD_ACCOUNT);
			}
		}
		
	}


	@Override
	public boolean checkPassword(UserBasic userBasic) {
		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andMobileEqualTo(userBasic.getMobile()).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> userBasics = userBasicMapper.selectByCriteria(userBasicCriteria);
		if(userBasics==null||userBasics.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_USERBASIC_ERROR);
		}
		UserBasic user = userBasics.get(0);
		if(user.getPassword().equals( DigestUtil.getMD5(userBasic.getPassword()))){
			return true;
		}else{
			
			return false;
		}
	}



	@Override
	public void getVerifyCode(Integer uid,String mobile, Byte mtype,Integer bankCardId) {
		if(mtype==2){//修改绑定手机号获取动态码
			//对手机号进行是否被注册校验
			UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
			userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
			List<UserBasic> userBasics = userBasicMapper.selectByCriteria(userBasicCriteria);
			if(userBasics!=null&&!userBasics.isEmpty()){
				throw new CheckException(ErrorMessageConstant.ERR_MOBILE_EXIST_ERROR);
			}
		}
		String verifyCode="";
		for(int i=0;i<6;i++){ 
			verifyCode+=random.nextInt(10); 
		}
		Map<String, Object> map= (Map<String, Object>)JdbcDao.getSmstplByMtype(mtype);
		if(map!=null){
			String content = "";
			if(bankCardId!=0){
				UserBankCard userBankCard = userBankCardMapper.selectByPrimaryKey(bankCardId);
				mobile = userBankCard.getMediaId();
				content = String.format(String.valueOf(map.get("content")),verifyCode,userBankCard.getBankName(),userBankCard.getCardId().substring(userBankCard.getCardId().length()-4,userBankCard.getCardId().length()));
			}else{
				content = String.format(String.valueOf(map.get("content")),verifyCode);
			}
			queueService.sendSms(new SmsSendParam(mobile, content));
			int tplId = Integer.parseInt(String.valueOf(map.get("id")));
			MobileValidateCode mobileValidateCode=new MobileValidateCode();
			if(uid!=null){
				mobileValidateCode.setUid(uid);
			}else{
				mobileValidateCode.setUid(0);
			}
			mobileValidateCode.setSmsId(tplId);
			mobileValidateCode.setCode(verifyCode);
			mobileValidateCode.setMobile(mobile);
			mobileValidateCode.setStatus(Byte.parseByte("1"));
			mobileValidateCode.setMiNo(UtilConstant.TENANT_JKWEB);
			JdbcDao.saveVerifyCode(mobileValidateCode);
		}else{
			throw new CheckException(ErrorMessageConstant.ERR_SMSTPL_ERROR);
		}

	}

   public String getUserInfo(Integer uid){
        UserBasic userBasic=userBasicMapper.selectByPrimaryKey(uid);
        SoinsBean bean =  new SoinsBean(userBasic.getName(),userBasic.getIdentityId(),userBasic.getMobile());
        return JSON.toJSONString(bean);
	}

	@Override
	public Map<String, Object> getUserInfoByTrjUid(Integer uid) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		/*UserBasicCriteria userBasicCriteria=new UserBasicCriteria();
		userBasicCriteria.createCriteria().andTrjtzUidEqualTo(trjUid);
		List<UserBasic> userBasicList=userBasicMapper.selectByCriteria(userBasicCriteria);*/
		UserBasic userBasic=userBasicMapper.selectByPrimaryKey(uid);
		List<Map<String, Object>> userBankCardList=null;
		UserExt userExt=null;
		Integer hasCard=0;//0未进行身份验证  1进行了身份验证
		if(null!=userBasic){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			PageBounds pageBounds=new PageBounds();
			paramMap.put("uid", userBasic.getId());
			paramMap.put("cardBindBank", UtilConstant.CARD_BIND_BANK);
			paramMap.put("cardType",UtilConstant.CARD_TYPE);
			paramMap.put("bankBanner",UtilConstant.BANK_BANNER);
			userBankCardList= userBankCardMapper.selectBankCardInfoByUid(paramMap,pageBounds);
			UserExtCriteria userExtCriteria=new UserExtCriteria();
			userExtCriteria.createCriteria().andUidEqualTo(userBasic.getId());
			List<UserExt> userExtList=new ArrayList<UserExt>();
			userExtList=userExtMapper.selectByCriteria(userExtCriteria);
			if(null!=userExtList&&userExtList.size()>0){
				userExt=userExtList.get(0);
				if((null!=userExt.getIdentityCardFrontImageId()&&userExt.getIdentityCardFrontImageId()>0)&&(null!=userExt.getIdentityCardOppositeImageId()&&userExt.getIdentityCardOppositeImageId()>0)){
					hasCard=1;
				}
			}
		}
		//resultMap.put("userbasic",userBasic);
		resultMap.put("bankcard",userBankCardList);
		resultMap.put("hasCard",hasCard);
		resultMap.put("esAccountId",userBasic.getEsAccountId());
		return resultMap;
	}

    @Override
    public UserBasic getUserBasicByMobile(String mobile) {
        UserBasicCriteria userBasicCriteria=new UserBasicCriteria();
        userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
        List<UserBasic> list=userBasicMapper.selectByCriteria(userBasicCriteria);
        UserBasic userBasic=null;
        if(null!=list&&list.size()>0){
            userBasic=list.get(0);
        }
        return userBasic;
    }

    /**
    * @description: 免息贷合同签章
    * @author: zhangxin
    * @Time: 15:24 2017/6/23
    *
    */
	@Override
	public void doInterestFreeSignature(Integer uid, Integer orderId, String verifyCode) {
		//调用crm系统生成合同服务并签署电子签章授权协议
		String productCode = loanProductMapper.searchProductByOrderId(orderId).getProductCode();
		CodeCriteria codeCriteria = new CodeCriteria();
		codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.PRODUCT_CONTRACT_KEY).andCodeNoEqualTo(productCode);
		List<Code> codes = codeMapper.selectByCriteria(codeCriteria);
		if(codes!=null && !codes.isEmpty()){
			String codeName = codes.get(0).getCodeName();
			//生成合同数据
			//调用crm系统生成合同服务并签署电子签章授权协议
			freeContractAndSignAccredit(uid,orderId,verifyCode,codeName);
			//异步调用crm合同发布接口
			Map<String, String> param = new HashMap<String, String>();
			param.put("jkWebUid", String.valueOf(uid));
			param.put("jkWebOrderId", String.valueOf(orderId));
			threadTaskService.asyncExecute(new ContractPublishTask((remoteDomainCrm+contractPublishUrl),param));
		}else{
			throw new CheckException("借款申请产品合同配置不可以为空！");
		}

	}



	/**
	 *
	 * @param uid(用户Id)
	 * @param orderId(订单表id)
	 * @param verifyCode(签署电子签章协议的验证码)
	 * @param contractsTplNo(要生成合同数据的模板编号例如：工薪贷 CT009,CT010,CT011,CT012)
	 */
	public void freeContractAndSignAccredit(Integer uid,Integer orderId,String verifyCode,String contractsTplNo){
		Integer esAccountId = userBasicMapper.selectByPrimaryKey(uid).getEsAccountId();
		if(esAccountId==null){
			throw new CheckException("用户还未e签宝开户！");
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("jkWebUid", String.valueOf(uid));
		param.put("jkWebOrderId", String.valueOf(orderId));
		param.put("jkWebSignAccreditVerifyCode", String.valueOf(verifyCode));
		param.put("jkWebContractsTplNo", String.valueOf(contractsTplNo));
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务url："+(remoteDomainCrm+freeInterestGenerateContract));
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务参数："+param.toString());
		String body = HttpClientUtils.httpPost((remoteDomainCrm+freeInterestGenerateContract), null, param, null, null);
		Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(body, Map.class);
		LOG.info("crm系统生成借款合同数据信息和编号并且签章电子签章授权协议服务返回："+result.toString());
		if(!"0".equals(String.valueOf(result.get("isSuccess")))){
			throw new RuntimeException("电子签章授权协议异常！"+String.valueOf(result.get("message")));
		}
	}

	public void updateUserHouseFundCertStatus(Integer uid, boolean success) {
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		userBasic.setHouseFundCertStatus(success ? Status.STATUS_CERT_SUCCESS : Status.STATUS_CERT_FAIL);
		userBasicMapper.updateByPrimaryKey(userBasic);
	}


}
