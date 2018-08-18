package com.trj.jk.web.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.CityCriteria;
import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.CodeCriteria;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.LoanFaceAuth;
import com.trj.jk.web.domain.LoanFaceAuthCriteria;
import com.trj.jk.web.domain.Province;
import com.trj.jk.web.domain.ProvinceCriteria;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.UserCertfication;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.authentication.AuthenticationBean;
import com.trj.jk.web.domain.entity.authentication.AuthenticationResultBean;
import com.trj.jk.web.domain.entity.authentication.CheckFaceImgInfo;
import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.JDBean;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.domain.entity.regex.RegexContext;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.domain.exception.InvokeException;
import com.trj.jk.web.domain.exception.ServiceException;
import com.trj.jk.web.mapper.AttachmentMapper;
import com.trj.jk.web.mapper.CityMapper;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.mapper.LoanFaceAuthMapper;
import com.trj.jk.web.mapper.ProvinceMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserCertficationMapper;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.mapper.UserFaceLogMapper;
import com.trj.jk.web.service.IAuthenticationService;
import com.trj.jk.web.service.face.IFaceService;
import com.trj.jk.web.service.operator.IOperatorService;
import com.trj.jk.web.util.FileUtil;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.util.UtilConstant;

@Service("authenticationService")
@Transactional
public class AuthenticationServiceImpl implements IAuthenticationService{
	private Gson gson = new Gson();

	@Value("${app.upload.path}")
	private String uploadPath;

	@Value("${app.upload.download}")
	private String downloadImgUrl;

	@Value("${app.file.tmp.path}")
	private String fileTmpPath;

	@Resource
	private UserCertficationMapper userCertficationMapper;

	@Resource
	private UserBasicMapper userBasicMapper;

	@Resource
	private UserExtMapper userExtMapper;

	@Resource
	private CityMapper cityMapper;

	@Resource
	private ProvinceMapper provinceMapper;

	@Resource
	private CodeMapper codeMapper;

	@Resource
	private UserCertficationMapper certMapper;

	@Resource
	private IFaceService faceService;

	@Resource
	private IOperatorService operatorService;

	@Resource
	private LoanFaceAuthMapper loanFaceAuthMapper;

	@Resource
	private AttachmentMapper attachmentMapper;

	@Resource
	private LoanApplyMapper loanApplyMapper;

	@Resource
	private UserFaceLogMapper userFaceLogMapper;

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);


	@Override
	public Integer saveAuthentication(AuthenticationBean bean,int uid,Byte type) {
		//返回结果对象
		if(bean == null){
			throw new ServiceException(ErrorMessageConstant.ERR_PARAM_ERROR);
		}
		UserCertfication userCertfication = new UserCertfication();
		userCertfication.setUid(uid);
		if(bean.getLoanApplyId()!=null){
			userCertfication.setLoanApplyId(bean.getLoanApplyId());
		}
		userCertfication.setType(type);
		userCertfication.setCertficationStatus(Byte.parseByte("0"));
		userCertfication.setContent(JsonUtils.objectToJsonString(bean));
		Integer id = userCertficationMapper.insert(userCertfication);
		//更新认证结果
		UserBasic user = new UserBasic();
		user.setId(uid);
		if(type==Constant.CERTIFICATION_TYPE_GJJ) {
			user.setHouseFundCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_SOCIAL){
			user.setSocialInsuranceCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_CREDIT) {
			user.setCreditInformationCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_CREDITCARD) {
			user.setCreditCardCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_JD){
			user.setEcommenceCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_OPERATOR){
			user.setMobileCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_PHONERZ){
			user.setMobileReallynameCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_DIPLOMA){
			user.setDiplomaCertStatus(Status.STATUS_CERT_ING);
		}else if(type==Constant.CERTIFICATION_TYPE_OPERATOR_STEP2){
			user.setMobileCertStatus(Status.STATUS_CERT_ING);
		}

		userBasicMapper.updateByPrimaryKeySelective(user);

		return userCertfication.getId();

	}

	@Override
	public void saveFaceAuthResult(UserExt ext,Integer uid,Integer applyId) {
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		if(userExts==null || userExts.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
		}
		UserExt userExt = userExts.get(0);
		if(applyId==null||applyId<0){//非流程三照比对
			if(userBasic.getIsPassFaceAuth()==1){//三像比对识别通过
				LOG.info(String.format("非流程三照比对成功结果保存：参数：applyId:%s ext:%s",applyId,ext.toString()));
				userExt.setName(ext.getName());
				userExt.setIdentityId(ext.getIdentityId());
				userExt.setIdentityCardFrontImageId(ext.getIdentityCardFrontImageId());
				userExt.setIdentityCardOppositeImageId(ext.getIdentityCardOppositeImageId());
				userExt.setLivingBodyImageId(ext.getLivingBodyImageId());
				userExt.setHeadImageId(ext.getHeadImageId());
				userExt.setIdentityAddress(ext.getIdentityAddress());
				userExtMapper.updateByPrimaryKey(userExt);
				userBasic.setIdentityId(ext.getIdentityId());
				userBasic.setIsFaceAuth(UtilConstant.AUTHENTICATION_STATUS_2);
				userBasic.setIsIdentityAuth(UtilConstant.AUTHENTICATION_STATUS_2);
				userBasic.setIdentityAuthType(Byte.parseByte("0"));
				userBasic.setName(ext.getName());
				userBasicMapper.updateByPrimaryKey(userBasic);
			}
			//非流程比对失败数据不保存;
		}else{//借款流程比对
			if (userBasic.getIsPassFaceAuth()==1) {//三像比对识别通过
				//申请是否人工审核
				LoanApply loanApply = loanApplyMapper.selectByPrimaryKey(applyId);
				loanApply.setIsManualAudit(Byte.parseByte("0"));
				loanApplyMapper.updateByPrimaryKey(loanApply);
				if(userBasic.getIsFaceAuth()==2){//sdk实名成功
					LOG.info(String.format("流程三照比对结果成功非流程三照比对也成功结果保存：参数：applyId:%s ext:%s",applyId,ext.toString()));
					//用户表实名认证附件信息已经不用更新，只需要同步用户表实名认证附件信息到jk_loan_face_auth表
					//更新用户活体照
					userExt.setLivingBodyImageId(ext.getLivingBodyImageId());
					userExtMapper.updateByPrimaryKey(userExt);
					//同步jk_loan_face_auth表数据
					LoanFaceAuth loanFaceAuth = new LoanFaceAuth();
					loanFaceAuth.setUid(uid);
					loanFaceAuth.setApplyId(applyId);
					loanFaceAuth.setStatus(Byte.parseByte("0"));
					loanFaceAuth.setIdentityCardFrontImageId(userExt.getIdentityCardFrontImageId());
					loanFaceAuth.setIdentityCardOppositeImageId(userExt.getIdentityCardOppositeImageId());
					loanFaceAuth.setIdentityAddress(userExt.getIdentityAddress());
					loanFaceAuth.setLivingBodyImageId(ext.getLivingBodyImageId());
					loanFaceAuth.setHeadImageId(userExt.getHeadImageId());
					loanFaceAuthMapper.insert(loanFaceAuth);

				}else{//银行卡实名
					LOG.info(String.format("流程三照比对结果成功非流程三照比对未成功结果保存：参数：applyId:%s ext:%s",applyId,ext.toString()));
					//用户身份信息通过银行绑卡实名，流程sdk比对成功
					//更新用户表实名认证附件信息，并把实名认证附件信息同步到jk_loan_face_auth表
					userExt.setIdentityCardFrontImageId(ext.getIdentityCardFrontImageId());
					userExt.setIdentityCardOppositeImageId(ext.getIdentityCardOppositeImageId());
					userExt.setIdentityAddress(ext.getIdentityAddress());
					userExt.setLivingBodyImageId(ext.getLivingBodyImageId());
					userExt.setHeadImageId(ext.getHeadImageId());
					userExtMapper.updateByPrimaryKey(userExt);
					LoanFaceAuth loanFaceAuth = new LoanFaceAuth();
					loanFaceAuth.setUid(uid);
					loanFaceAuth.setApplyId(applyId);
					loanFaceAuth.setStatus(Byte.parseByte("0"));
					loanFaceAuth.setIdentityCardFrontImageId(ext.getIdentityCardFrontImageId());
					loanFaceAuth.setIdentityCardOppositeImageId(ext.getIdentityCardOppositeImageId());
					loanFaceAuth.setIdentityAddress(ext.getIdentityAddress());
					loanFaceAuth.setLivingBodyImageId(ext.getLivingBodyImageId());
					loanFaceAuth.setHeadImageId(ext.getHeadImageId());
					loanFaceAuthMapper.insert(loanFaceAuth);
				}
				userBasic.setIsFaceAuth(UtilConstant.AUTHENTICATION_STATUS_2);
				userBasic.setIsIdentityAuth(UtilConstant.AUTHENTICATION_STATUS_2);
				userBasic.setIdentityAuthType(Byte.parseByte("0"));
				userBasicMapper.updateByPrimaryKey(userBasic);
			}else{//流程三像比对识别不通过
				//申请是否人工审核
				LoanApply loanApply = loanApplyMapper.selectByPrimaryKey(applyId);
				loanApply.setIsManualAudit(Byte.parseByte("1"));
				loanApplyMapper.updateByPrimaryKeySelective(loanApply);
				if(userBasic.getIsFaceAuth()==2){//sdk实名成功
					//用户表实名认证附件信息已经不用更新，只需要同步用户表实名认证附件信息到jk_loan_face_auth表
					//同步jk_loan_face_auth表数据
					LOG.info(String.format("流程三照比对结果不成功非流程三照比对成功结果保存：参数：applyId:%s ext:%s",applyId,ext.toString()));
					LoanFaceAuth loanFaceAuth = new LoanFaceAuth();
					loanFaceAuth.setUid(uid);
					loanFaceAuth.setApplyId(applyId);
					loanFaceAuth.setStatus(Byte.parseByte("1"));
					loanFaceAuth.setIdentityCardFrontImageId(userExt.getIdentityCardFrontImageId());
					loanFaceAuth.setIdentityCardOppositeImageId(userExt.getIdentityCardOppositeImageId());
					loanFaceAuth.setLivingBodyImageId(ext.getLivingBodyImageId());
					loanFaceAuth.setHeadImageId(userExt.getHeadImageId());
					loanFaceAuth.setIdentityAddress(userExt.getIdentityAddress());
					loanFaceAuthMapper.insert(loanFaceAuth);
				}else{//银行卡实名
					//用户身份信息通过银行绑卡实名，流程sdk比对成功
					//更新用户表实名认证附件信息，并把实名认证附件信息同步到jk_loan_face_auth表
					LOG.info(String.format("流程三照比对结果不成功非流程三照比对也不成功结果保存：参数：applyId:%s ext:%s",applyId,ext.toString()));
					LoanFaceAuth loanFaceAuth = new LoanFaceAuth();
					loanFaceAuth.setUid(uid);
					loanFaceAuth.setApplyId(applyId);
					loanFaceAuth.setStatus(Byte.parseByte("1"));
					loanFaceAuth.setIdentityCardFrontImageId(ext.getIdentityCardFrontImageId());
					loanFaceAuth.setIdentityCardOppositeImageId(ext.getIdentityCardOppositeImageId());
					loanFaceAuth.setIdentityAddress(ext.getIdentityAddress());
					loanFaceAuth.setLivingBodyImageId(ext.getLivingBodyImageId());
					loanFaceAuth.setHeadImageId(ext.getHeadImageId());
					loanFaceAuthMapper.insert(loanFaceAuth);
				}

		}
	}

}

	@Override
	public Map<String, Object> getDistrict() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		CityCriteria cityCriteria = new CityCriteria();
		cityCriteria.createCriteria().andIsHotEqualTo(Byte.parseByte("1"));
		List<City> hotCitys = cityMapper.selectByCriteria(cityCriteria);
		dataMap.put("hotCity", hotCitys);
		List<Province> provinces = provinceMapper.selectByCriteria(new ProvinceCriteria());
		dataMap.put("province", provinces);
		return dataMap;
	}

	@Override
	public List<City> getHotCity() {
		CityCriteria cityCriteria = new CityCriteria();
		cityCriteria.createCriteria().andIsHotEqualTo(Byte.parseByte("1"));
		return cityMapper.selectByCriteria(cityCriteria);
	}

	@Override
	public List<City> selectCity(String provinceCode, String searchKey) {
		List<City> cities = null;
		if(provinceCode!=null){
			CityCriteria provinceCriteria = new CityCriteria();
			provinceCriteria.createCriteria().andProvinceCodeEqualTo(provinceCode);
			cities = cityMapper.selectByCriteria(provinceCriteria);
			return cities;
		}else{
			if(searchKey!=null){
				CityCriteria cityCriteria = new CityCriteria();
				cityCriteria.createCriteria().andNameLike("%"+searchKey+"%");
				cityCriteria.or().andSearchKeyLike(searchKey.toLowerCase()+"%");
				cities = cityMapper.selectByCriteria(cityCriteria);
				return cities;
			}
		}
		return cities;
	}

	@Override
	@Deprecated
	public  String pythonCall() throws IOException{
//		String cmd = "python " + scriptPath + " -s " +session+ " -H " + host + " " + imagePath;
		String cmd= "python /home/vobile/project/tourongjia/长富贷/python/Get_Token.py";
	    Process pr = null;
	    try {
	    	pr = Runtime.getRuntime().exec(cmd);
	    	String resultLine = IOUtils.toString(pr.getInputStream());

	        if (StringUtils.isNotBlank(resultLine)) {
	        	LOG.info("exec `" + cmd + "` response: " + resultLine);
//	            VSyncMatchResponse response = gson.fromJson(resultLine, VSyncMatchResponse.class);
//	                if (response.getMatch() == null || response.getMatch().size() == 0) {
//	                    throw new VSyncNomatchExcpetion();
//	                }
//
//	                return response;
//	            }
	        }
	        LOG.error(IOUtils.toString(pr.getErrorStream()));
	        throw new InvokeException(IOUtils.toString(pr.getErrorStream()));
	    } finally {
	        if (pr != null) {
	            pr.destroy();
	        }
	    }
	}

	@Override
	public AuthenticationResultBean getAuthenticationInfo(Integer uid) {
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		AuthenticationResultBean authenticationResultBean = new AuthenticationResultBean();
		if(userBasic!=null){
			//身份
			if(userBasic.getIsIdentityAuth()==UtilConstant.AUTHENTICATION_STATUS_2){
				UserExtCriteria userExtCriteria = new UserExtCriteria();
				userExtCriteria.createCriteria().andUidEqualTo(uid);
				UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
				String newString = "";
				for(int i=0;i<userExt.getName().length()-1;i++){
					newString+="*";
				}
				authenticationResultBean.setIsIdentityAuth(userExt.getName().replace(userExt.getName().substring(1,userExt.getName().length()), newString));
			}else{
				CodeCriteria codeCriteria = new CodeCriteria();
				codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getIsIdentityAuth()));
				authenticationResultBean.setIsIdentityAuth(codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName());
			}
			//运营商
			CodeCriteria codeCriteria1 = new CodeCriteria();
			codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getMobileCertStatus()));
			List<Code> codes = codeMapper.selectByCriteria(codeCriteria1);
			authenticationResultBean.setMobileCertStatus(codes.get(0).getCodeName());
			//学历
			CodeCriteria codeCriteria2 = new CodeCriteria();
			codeCriteria2.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getDiplomaCertStatus()));
			authenticationResultBean.setDiplomaCertStatus(codeMapper.selectByCriteria(codeCriteria2).get(0).getCodeName());
			//公积金
			CodeCriteria codeCriteria3 = new CodeCriteria();
			codeCriteria3.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getHouseFundCertStatus()));
			authenticationResultBean.setHouseFundCertStatus(codeMapper.selectByCriteria(codeCriteria3).get(0).getCodeName());
			//社保
			CodeCriteria codeCriteria4 = new CodeCriteria();
			codeCriteria4.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getSocialInsuranceCertStatus()));
			authenticationResultBean.setSocialInsuranceCertStatus(codeMapper.selectByCriteria(codeCriteria4).get(0).getCodeName());
			//支付宝
			CodeCriteria codeCriteria5 = new CodeCriteria();
			codeCriteria5.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getAlipayCertStatus()));
			authenticationResultBean.setAlipayCertStatus(codeMapper.selectByCriteria(codeCriteria5).get(0).getCodeName());
			//电商
			CodeCriteria codeCriteria6 = new CodeCriteria();
			codeCriteria6.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getEcommenceCertStatus()));
			authenticationResultBean.setEcommenceCertStatus(codeMapper.selectByCriteria(codeCriteria6).get(0).getCodeName());
			//职业
			CodeCriteria codeCriteria7 = new CodeCriteria();
			codeCriteria7.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getProfessionCertStatus()));
			authenticationResultBean.setProfessionCertStatus(codeMapper.selectByCriteria(codeCriteria7).get(0).getCodeName());
			//运营商实名
			CodeCriteria codeCriteria8 = new CodeCriteria();
			codeCriteria8.createCriteria().andCodeKeyEqualTo(UtilConstant.AUTHENTICATION_STATUS_KEY).andCodeNoEqualTo(String.valueOf(userBasic.getMobileReallynameCertStatus()));
			authenticationResultBean.setMobileReallynameCertStatus(codeMapper.selectByCriteria(codeCriteria8).get(0).getCodeName());
	}
		return authenticationResultBean;
	}



	@Override
	public CertResult certificate(Object bean,String url,Integer id, Integer uid, Byte type) {
		Map<String, String> reqHeadMap = new HashMap<String, String>();
		reqHeadMap.put("Content-Type", "application/json");
		String postData="";
		if(bean instanceof String ){
			postData = (String) bean;
		}else {
			postData = gson.toJson(bean);
		}
		LOG.info("认证调用的url值："+url);
		LOG.debug("认证调用的url值："+url);
		LOG.info(postData);
		LOG.debug(postData);
		LOG.info("认证调用的reqHeadMap值："+reqHeadMap);
		LOG.debug("认证调用的reqHeadMap值："+reqHeadMap);
		String body = HttpClientUtils.httpPost(url, postData, null, reqHeadMap, null);
		LOG.info(body);
		LOG.debug(body);
		CertResult result=handleCertificationResult(body,uid,id,type, bean);
		return result;
	}

	private CertResult handleCertificationResult(String response,Integer uid, Integer id, Byte type, Object bean) {
		CertResult result=gson.fromJson(response, CertResult.class);
		if(result!=null ){
			Integer retCode = result.getRetcode();
			UserBasic user = new UserBasic();
			user.setId(uid);
			UserCertfication cert = new UserCertfication();
			cert.setId(id);

			if(type==Constant.CERTIFICATION_TYPE_JD){
				if(Status.STATUS_PROCESS_10001==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("再次输入短信验证码");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10002==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("输入短信验证码");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10003==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("密码错误");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10004==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("短信验证码错误");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10006==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("短信验证码失效系统已自动重新下发");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10007==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("简单密码或初始密码无法登录");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else if(Status.STATUS_PROCESS_10008==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("开始采集行为数据");
					user.setEcommenceCertStatus(Status.STATUS_CERT_SUCCESS);
					cert.setCertficationStatus(Status.STATUS_CERT_TRUE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}else{
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("错误信息");
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}
			}
			LOG.info("认证返回的type值："+type);
			LOG.debug("认证返回的type值："+type);
			if(type==Constant.CERTIFICATION_TYPE_OPERATOR||type==Constant.CERTIFICATION_TYPE_OPERATOR_STEP2){
				LOG.info("认证返回的retCode值："+retCode);
				LOG.debug("认证返回的retCode值："+retCode);
				if(Status.STATUS_PROCESS_10001==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("再次输入短信验证码");
				}else if(Status.STATUS_PROCESS_10002==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("输入短信验证码");
				}else if(Status.STATUS_PROCESS_10003==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("密码错误");
				}else if(Status.STATUS_PROCESS_10004==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("短信验证码错误");
				}else if(Status.STATUS_PROCESS_10006==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("短信验证码失效系统已自动重新下发");
				}else if(Status.STATUS_PROCESS_10007==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("简单密码或初始密码无法登录");
				}else if(Status.STATUS_PROCESS_10008==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("开始采集行为数据");
					user.setMobileCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(Status.STATUS_PROCESS_10017==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("请用本机发送CXXD至10001获取查询详单的验证码");
				}else if(Status.STATUS_PROCESS_10018==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("短信码失效请用本机发送CXXD至10001获取查询详单的验证码");
				}else if(Status.STATUS_PROCESS_10022==retCode){
					result.setSuccess(true);
					result.setRetcode(retCode);
					result.setRetmessage("输入查询密码");
				}else if(Status.STATUS_PROCESS_10023==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("查询密码错误");
				}else if(Status.STATUS_PROCESS_30000==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("错误信息");
				}else if(Status.STATUS_RETURN_SUCCESS==retCode){
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("采集请求超时");
				}else{
					result.setSuccess(false);
					result.setRetcode(retCode);
					result.setRetmessage("系统异常请稍后重试");
				}

				if(Status.STATUS_PROCESS_10008==retCode){
					cert.setCertficationStatus(Status.STATUS_CERT_TRUE);
					cert.setRemark(result.getRetmessage());
					cert.setType((byte)9);
					certMapper.updateByPrimaryKeySelective(cert);
				}else{
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
					return result;
				}
			}

			if(Status.STATUS_RETURN_SUCCESS==retCode){
				result.setSuccess(true);

				if(type==Constant.CERTIFICATION_TYPE_GJJ) {
					user.setHouseFundCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(type==Constant.CERTIFICATION_TYPE_SOCIAL){
					user.setSocialInsuranceCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(type==Constant.CERTIFICATION_TYPE_CREDIT) {
					user.setCreditInformationCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(type==Constant.CERTIFICATION_TYPE_CREDITCARD) {
					user.setCreditCardCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(type==Constant.CERTIFICATION_TYPE_JD){
					JDBean jd = (JDBean)bean;
					if(!StringUtils.isEmpty(jd.getCaptcha())) {
						user.setEcommenceCertStatus(Status.STATUS_CERT_SUCCESS);
					}else{
						cert.setCertficationStatus(Status.STATUS_CERT_TRUE);
						cert.setRemark(result.getRetmessage());
						certMapper.updateByPrimaryKeySelective(cert);
						return result;
					}
				//运营商认证只有在最后一部才改状态
				}/*else if(type==Constant.CERTIFICATION_TYPE_OPERATOR){
					return result;
				}else if(type==Constant.CERTIFICATION_TYPE_OPERATOR_STEP2  ){
					user.setMobileCertStatus(Status.STATUS_CERT_SUCCESS);
				}*/else if(type==Constant.CERTIFICATION_TYPE_PHONERZ){
					user.setMobileReallynameCertStatus(Status.STATUS_CERT_SUCCESS);
				}else if(type==Constant.CERTIFICATION_TYPE_DIPLOMA){
					user.setDiplomaCertStatus(Status.STATUS_CERT_SUCCESS);
				}
				if(type!=Constant.CERTIFICATION_TYPE_OPERATOR&&type!=Constant.CERTIFICATION_TYPE_OPERATOR_STEP2&&type!=Constant.CERTIFICATION_TYPE_JD){
					cert.setCertficationStatus(Status.STATUS_CERT_TRUE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}


			}else {
				if(type==Constant.CERTIFICATION_TYPE_GJJ) {
					user.setHouseFundCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_SOCIAL){
					user.setSocialInsuranceCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_CREDIT) {
					user.setCreditInformationCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_CREDITCARD) {
					user.setCreditCardCertStatus(Status.STATUS_CERT_FAIL);
				}/*else if(type==Constant.CERTIFICATION_TYPE_JD){
					user.setEcommenceCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_OPERATOR){
					user.setMobileCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_OPERATOR_STEP2){
					user.setMobileCertStatus(Status.STATUS_CERT_FAIL);
				}*/else if(type==Constant.CERTIFICATION_TYPE_PHONERZ){
					user.setMobileReallynameCertStatus(Status.STATUS_CERT_FAIL);
				}else if(type==Constant.CERTIFICATION_TYPE_DIPLOMA){
					user.setDiplomaCertStatus(Status.STATUS_CERT_FAIL);
				}
				if(type!=Constant.CERTIFICATION_TYPE_OPERATOR&&type!=Constant.CERTIFICATION_TYPE_OPERATOR_STEP2&&type!=Constant.CERTIFICATION_TYPE_JD){
					cert.setCertficationStatus(Status.STATUS_CERT_FALSE);
					cert.setRemark(result.getRetmessage());
					certMapper.updateByPrimaryKeySelective(cert);
				}
			}

			userBasicMapper.updateByPrimaryKeySelective(user);
		}

		return result;
	}

	@Override
	public boolean faceAuthentication(List<MultipartFile> files, UserExt ext) throws Exception {
		CheckFaceImgInfo checkFaceImgInfo = new CheckFaceImgInfo();
		Integer uid=ext.getUid();
		if(ext.getUid()==null){
			uid=(Integer)SessionUtil.getUserLogonInfo();
		}
		if(uid==null){
			throw new CheckException("用户不存在！");
		}
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		List<UserExt> userExts = userExtMapper.selectByCriteria(userExtCriteria);
		if(userExts==null||userExts.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
		}
		UserExt userExt = userExts.get(0);
		if(userBasic.getIsIdentityAuth()==UtilConstant.AUTHENTICATION_STATUS_2 && userBasic.getIdentityAuthType()==0){//二次人脸识别只取活体照
			if(userExt.getName()==null || userExt.getIdentityId()==null || userExt.getHeadImageId()==null){
				throw new CheckException("二次人脸识别所需三照比对信息不能为空！");
			}
			checkFaceImgInfo.setName(userExt.getName());
			checkFaceImgInfo.setIdentityId(userExt.getIdentityId());
			File livingBodyImage=FileUtil.saveFile(files.get(0),uploadPath);
			checkFaceImgInfo.setLivingBodyImage(livingBodyImage);
			Attachment attachment = attachmentMapper.selectByPrimaryKey(userExt.getHeadImageId());
			if(attachment==null){
				throw new CheckException("二次人脸识别所需身份证人像照比对信息未找到！");
			}
			String headImagePath =  download(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName(),uploadPath+File.separator +attachment.getSaveName());
			File headImage= new File(headImagePath);
			checkFaceImgInfo.setHeadImage(headImage);
		}else if(userBasic.getIsIdentityAuth()==UtilConstant.AUTHENTICATION_STATUS_2 && userBasic.getIdentityAuthType()==1){
			if(userExt.getName()==null || userExt.getIdentityId()==null){
				throw new CheckException("绑卡认定身份信息时身份信息不能为空！");
			}
			checkFaceImgInfo.setName(userExt.getName());
			checkFaceImgInfo.setIdentityId(userExt.getIdentityId());
			checkFaceImgInfo.setLivingBodyImage(FileUtil.saveFile(files.get(0),uploadPath));
			checkFaceImgInfo.setHeadImage(FileUtil.saveFile(files.get(1),uploadPath));
		}else{
			checkFaceImgInfo.setName(ext.getName());
			checkFaceImgInfo.setIdentityId(ext.getIdentityId());
			checkFaceImgInfo.setLivingBodyImage(FileUtil.saveFile(files.get(0),uploadPath));
			checkFaceImgInfo.setHeadImage(FileUtil.saveFile(files.get(1),uploadPath));
		}
		LOG.info("三照比对接口信息："+checkFaceImgInfo.toString());
		boolean falg = false;
		try {
			falg =  faceService.checkFaceEx(uid,checkFaceImgInfo.getIdentityId(), checkFaceImgInfo.getName(), checkFaceImgInfo.getLivingBodyImage(), checkFaceImgInfo.getHeadImage());
			if(falg){
				userBasic.setIsPassFaceAuth(Byte.parseByte("1"));
			}else{
				userBasic.setIsPassFaceAuth(Byte.parseByte("0"));
			}
			userBasicMapper.updateByPrimaryKey(userBasic);
		} catch (Exception e) {
			LOG.info("三照比对接口发生异常："+e.getMessage());
			userBasic.setIsPassFaceAuth(Byte.parseByte("0"));
			userBasicMapper.updateByPrimaryKey(userBasic);
		}
		return falg;
	}

	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   * @throws Exception
	   *           各种异常
	   */
	public String download(String urlString, String filename) throws Exception {
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	    return filename;
	}




	@Override
	public void updateIsNotpassFaceAuth(Integer uid) {
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		userBasic.setIsPassFaceAuth(Byte.parseByte("0"));
		userBasicMapper.updateByPrimaryKey(userBasic);
	}

	@Override
	public void authConfirm(UserCertfication userCertfication,Byte confirmType) {
		//参数验证
		if(userCertfication.getUid()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_UID);
		}
		if(confirmType==0&&userCertfication.getId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_NULL_CERTFICATIONID);
		}
		if(userCertfication.getType()==null){
			throw new CheckException(ErrorMessageConstant.ERR_AUTH_TYPE);
		}
		if(userCertfication.getCertficationStatus()==null){
			throw new CheckException(ErrorMessageConstant.ERR_AUTH_STATUS_NULL);
		}
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userCertfication.getUid());
		if(userBasic==null){
			throw new CheckException(ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
		}

		if(confirmType==0){//认证回调   如果是认证回调就需要更新当时提交的认证信息的那条记录数据状态
			UserCertfication certfication = userCertficationMapper.selectByPrimaryKey(userCertfication.getId());
			if(certfication==null){
				throw new CheckException(ErrorMessageConstant.ERR_NULL_USER_CERTFICATION);
			}
			//更新认证信息记录状态
			if(UtilConstant.AUTHENTICATION_STATUS_2==userCertfication.getCertficationStatus()){//成功
				certfication.setCertficationStatus(Byte.parseByte("1"));
			}else{//失败
				certfication.setCertficationStatus(Byte.parseByte("0"));
			}
			userCertficationMapper.updateByPrimaryKey(certfication);
		}

		//更新用户对应的认证状态
		switch (userCertfication.getType()) {
		case 0://学历认证
				userBasic.setDiplomaCertStatus(userCertfication.getCertficationStatus());
			break;

		case 1://支付宝认证
				userBasic.setAlipayCertStatus(userCertfication.getCertficationStatus());
			break;

		case 2://公积金认证
				userBasic.setHouseFundCertStatus(userCertfication.getCertficationStatus());
			break;

		case 3://社保认证
				userBasic.setSocialInsuranceCertStatus(userCertfication.getCertficationStatus());
			break;

		case 4://聚信立征信认证
				userBasic.setCreditInformationCertStatus(userCertfication.getCertficationStatus());
			break;

		case 5://聚信立信用卡认证
				userBasic.setCreditCardCertStatus(userCertfication.getCertficationStatus());
			break;

		case 6://聚信立京东认证
				userBasic.setEcommenceCertStatus(userCertfication.getCertficationStatus());
			break;

		case 7://聚信立运营商认证
				userBasic.setMobileCertStatus(userCertfication.getCertficationStatus());
			break;

		case 8://运营商实名认证
				userBasic.setMobileReallynameCertStatus(userCertfication.getCertficationStatus());
			break;

		default:
			break;
		}
		userBasicMapper.updateByPrimaryKeySelective(userBasic);

	}

	@Override
	public boolean identityAuth(MultipartFile file, UserExt ext) {
		Integer uid = ext.getUid();
		if(null == uid){
			uid = (Integer)SessionUtil.getUserLogonInfo();
		}
		Assert.notNull(uid, "uid获取为空");
		//校验身份信息是否已被实名认证过
		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andIdentityIdEqualTo(ext.getIdentityId()).andIdNotEqualTo(uid);
		List<UserBasic> userBasics = userBasicMapper.selectByCriteria(userBasicCriteria);
		if (userBasics != null && !userBasics.isEmpty()) {
			throw new CheckException(ErrorMessageConstant.ERR_IDENTITYID_EXIST);
		}
		boolean flag = faceService.checkFaceImg(uid, ext.getIdentityId(), ext.getName(), FileUtil.saveFile(file, uploadPath));
		return flag;
	}

	@Override
	public void identitySave(UserExt ext) {
		Integer uid = ext.getUid();
		if(null == uid){
			uid = (Integer)SessionUtil.getUserLogonInfo();
		}
		Assert.notNull(uid, "uid获取为空");
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		Assert.notNull(userBasic, "userBasic获取为空");
		Assert.notNull(userExt, "userExt获取为空");
			//更新userBasic表中的身份信息
			userBasic.setName(ext.getName());
			userBasic.setIdentityId(ext.getIdentityId());
		userBasic.setIsIdentityAuth(UtilConstant.AUTHENTICATION_STATUS_2);
			userBasic.setIdentityAuthType(UtilConstant.IDENTITY_AUTH_TYPE_2);
			userBasicMapper.updateByPrimaryKey(userBasic);
			//更新userExt表中的身份信息
			userExt.setName(ext.getName());
			userExt.setIdentityId(ext.getIdentityId());
			userExt.setIdentityAddress(ext.getIdentityAddress());
			userExt.setIdentityCardFrontImageId(ext.getIdentityCardFrontImageId());
			userExt.setIdentityCardOppositeImageId(ext.getIdentityCardOppositeImageId());
			userExt.setHeadImageId(ext.getHeadImageId());
			userExtMapper.updateByPrimaryKey(userExt);

		}



	@Override
	public boolean livingBodyAuth(MultipartFile file1) {
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		return livingBodyAuth(uid,file1);
	}

	@Override
	public boolean livingBodyAuth(Integer uid, MultipartFile file1) {
		Assert.notNull(uid, "uid获取为空！");
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		Assert.notNull(userBasic, "userBasic获取为空！");
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		Assert.notNull(userExt, "userExt获取为空！");
		Assert.notNull(userExt.getHeadImageId(), "身份证抠图获取为空！");
		Attachment attachment = attachmentMapper.selectByPrimaryKey(userExt.getHeadImageId());
		Assert.notNull(attachment, "身份证抠图获取为空！");
		File file2=null;
		try {
			file2=new File(FileUtil.getFileByUrl(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName(),fileTmpPath));
		}catch (Exception e) {
			LOG.error("获取身份证抠图照错误！"+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("获取身份证抠图照错误！"+e.getMessage());
		}
		boolean flag = faceService.compareFace(uid, FileUtil.saveFile(file1, uploadPath), file2);
		if(flag){
			userBasic.setIsFaceAuth(UtilConstant.AUTHENTICATION_STATUS_2);
			userBasicMapper.updateByPrimaryKey(userBasic);
		}
		return flag;
	}

	@Override
	public void livingBodySave(Integer loanApplyId, Integer livingBodyImageId) {
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		livingBodySave(uid,loanApplyId,livingBodyImageId);
	}

	@Override
	public void livingBodySave(Integer uid, Integer loanApplyId, Integer livingBodyImageId) {
		Assert.notNull(uid, "uid获取为空");
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		Assert.notNull(userBasic, "userBasic获取为空");
		UserExtCriteria userExtCriteria = new UserExtCriteria();
		userExtCriteria.createCriteria().andUidEqualTo(uid);
		UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
		Assert.notNull(userExt, "userExt获取为空");
		if(null ==livingBodyImageId){
			livingBodyImageId = userExt.getLivingBodyImageId();
		}

		LoanApply loanApply = loanApplyMapper.selectByPrimaryKey(loanApplyId);
		Assert.notNull(loanApply, "loanApply获取为空");

		LoanFaceAuthCriteria loanFaceAuthCriteria = new LoanFaceAuthCriteria();
		loanFaceAuthCriteria.createCriteria().andApplyIdEqualTo(loanApplyId);
		List<LoanFaceAuth> loanFaceAuths = loanFaceAuthMapper.selectByCriteria(loanFaceAuthCriteria);

		//冗余一份借款申请相关的认证信息
		if(CollectionUtils.isNotEmpty(loanFaceAuths)){
			LoanFaceAuth loanFaceAuth=loanFaceAuths.get(0);
			loanFaceAuth.setIdentityCardFrontImageId(userExt.getIdentityCardFrontImageId());
			loanFaceAuth.setIdentityCardOppositeImageId(userExt.getIdentityCardOppositeImageId());
			loanFaceAuth.setIdentityAddress(userExt.getIdentityAddress());
			loanFaceAuth.setLivingBodyImageId(livingBodyImageId);
			loanFaceAuth.setHeadImageId(userExt.getHeadImageId());
			if(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsFaceAuth()){//未通过活体比对
				loanFaceAuth.setStatus(Byte.parseByte("0"));
			}else{
				loanFaceAuth.setStatus(Byte.parseByte("1"));
			}
			loanFaceAuthMapper.updateByPrimaryKey(loanFaceAuth);
		}else{
			LoanFaceAuth loanFaceAuth=new LoanFaceAuth();
			loanFaceAuth.setUid(uid);
			loanFaceAuth.setApplyId(loanApplyId);
			loanFaceAuth.setIdentityCardFrontImageId(userExt.getIdentityCardFrontImageId());
			loanFaceAuth.setIdentityCardOppositeImageId(userExt.getIdentityCardOppositeImageId());
			loanFaceAuth.setIdentityAddress(userExt.getIdentityAddress());
			loanFaceAuth.setLivingBodyImageId(livingBodyImageId);
			loanFaceAuth.setHeadImageId(userExt.getHeadImageId());
			if(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsFaceAuth()){//未通过活体比对
				loanFaceAuth.setStatus(Byte.parseByte("0"));
			}else{
				loanFaceAuth.setStatus(Byte.parseByte("1"));
			}
			loanFaceAuthMapper.insert(loanFaceAuth);
		}
		if(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsFaceAuth()){//未通过活体比对
			//跟新借款申请表数据
			loanApply.setIsManualAudit(Byte.parseByte("0"));
			//如果活体比对成功，需要将活体照更新userExt表
			userExt.setLivingBodyImageId(livingBodyImageId);
		}else{
			//跟新借款申请表数据
			loanApply.setIsManualAudit(Byte.parseByte("1"));
		}
		loanApplyMapper.updateByPrimaryKey(loanApply);
		userExtMapper.updateByPrimaryKey(userExt);
	}

	@Override
	public BiRetResult getVerifyCode(String mobile) {
		OperatorResetPwdBean bean = new OperatorResetPwdBean();
		Integer uid = (Integer)SessionUtil.getUserLogonInfo();
		Assert.notNull(uid, "uid获取为空");
		UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
		Assert.notNull(userBasic, "userBasic获取为空");
		Assert.notNull(userBasic.getName(), "未实名认证！姓名为空！");
		Assert.notNull(userBasic.getIdentityId(), "未实名认证！身份证号码为空！");
		bean.setName(userBasic.getName());
		bean.setIdentityId(userBasic.getIdentityId());
		bean.setMobile(mobile);
		BiRetResult result = operatorService.getVerifyCode(bean);
		return result;
	}

	@Override
	public CertResult modifyPassword(OperatorResetPwdBean bean) {
		if(bean.getVerifyCode()==null){
			throw new CheckException("手机验证码不可以为空！");
		}
		if(!bean.getVerifyCode().matches(RegexContext.SIX_NUMBER)){
			throw new CheckException("手机验证码格式应为6位数字！");
		}
		if(bean.getPassword()==null){
			throw new CheckException("新服务密码不可以为空！");
		}
		if(!bean.getPassword().matches(RegexContext.SIX_NUMBER)){
			throw new CheckException("新服务密码格式应为6位数字！");
		}
		if(bean.getMobile()==null){
			throw new CheckException("手机号码不可以为空！");
		}
		if(bean.getWebsite()==null){
			throw new CheckException("Website不可以为空！");
		}
		if(bean.getToken()==null){
			throw new CheckException("token不可以为空！");
		}
		CertResult certResult = operatorService.resetPwd(bean);
		return certResult;
	}



	
}
