package com.trj.jk.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.trj.commons.result.Results;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.model.result.SocialResult;
import com.trj.jk.web.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.entity.authentication.AuthenticationBean;
import com.trj.jk.web.domain.entity.authentication.AuthenticationResultBean;
import com.trj.jk.web.domain.entity.authentication.bi.BiParamBase;
import com.trj.jk.web.domain.entity.authentication.bi.BiParamBeanFactory;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.CityResult;
import com.trj.jk.web.domain.entity.authentication.bi.CityRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.SoinsResult;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.face.IFaceService;
import com.trj.jk.web.task.async.CertificateTask;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.SessionUtil;

/**
 * 认证管理controller类
 * @author l46001
 *
 */
@RestController
@RequestMapping(value={"/authentication"})
public class AuthenticationController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Value("${app.service.path}")
	private String servicePath;
	@Resource
	private IAuthenticationService authenticationService;
	@Autowired
	private ICodeService codeService;
	@Autowired
	private IThreadTaskService threadTaskService;
	@Autowired
	private IFaceService faceService;
    @Autowired
	private IUserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserBasicService userBasicService;
    @Autowired
    private UserFaceLogService userFaceLogService;


	/**
	 * 认证管理页面获取认证信息接口
	 * @return
	 */
	@RequestMapping(value={"/getAuthenticationInfo"},method=RequestMethod.GET)
	@ResponseBody
	public Result<Object> getAuthenticationInfo() {
		Result<Object> result = new Result<Object>();
		LOG.info("认证管理页面获取认证信息接口开始执行...");
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			LOG.info(String.format("参数：uid=%s", uid));
			if(uid!=null){
				AuthenticationResultBean authenticationResultBea = authenticationService.getAuthenticationInfo(uid);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.GET_AUTHENTICATION_SUCCESS);
				result.setData(authenticationResultBea);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("认证管理页面获取认证信息接口返回：%s",result.toString()));
		return result;
	}

	/**
	 * 认证管理做认证方法
	 * @param bean:认证信息Bean
	 * @param uid：对应用户id
	 * @param type：认证信息的类型
	 * @return
	 */
	@RequestMapping(value={"/doAuthentication"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> doAuthentication(AuthenticationBean bean,Byte type) {
		Result<Object> result = new Result<Object>();
		LOG.info("认证接口开始执行....");
		try {
			if(bean!=null && type!=null){
				LOG.info(String.format("参数：bean=%s,type=%s",bean.toString(),type));
				CertResult certificateResult = null;
				Integer uid = (Integer)SessionUtil.getUserLogonInfo();
				Code code=codeService.getCodeByKeyAndNo(Constant.CODE_CERT_SERVICE, type.toString());
				
				Integer id=authenticationService.saveAuthentication(bean,uid,type);
				
				if(code !=null) {
					String scriptPath = code.getCodeName();
					String fullScriptPath = servicePath + scriptPath;
					BiParamBase obj=BiParamBeanFactory.getBean(type, bean,uid);
					obj.setUid(uid);
					obj.setCertficationId(id);
					LOG.info(String.format("调用Bi认证Url：%s", fullScriptPath));
					LOG.info(String.format("调用Bi参数实体：%s",obj.toString()));
                    //社保特殊处理
                    if(type==Constant.CERTIFICATION_TYPE_SOCIAL) {
                        certificateResult = authenticationService.certificate(bean.getSoinsBean(), fullScriptPath, id, uid,type);
                        result.setSuccess(true);
                        result.setMessage(certificateResult.getRetmessage());
                        result.setData(certificateResult);
                    }else if(bean.getSource()==Constant.CERT_SOURCE_APPLY) {
						certificateResult = authenticationService.certificate(obj, fullScriptPath, id, uid,type);
						result.setSuccess(true);
						result.setMessage(certificateResult.getRetmessage());					
						
						result.setData(certificateResult);
					}else {
						threadTaskService.asyncExecute(new CertificateTask(fullScriptPath,obj,id, uid,type));		
						result.setMessage("提交成功");
						result.setSuccess(true);
					}
				}			
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_OCCURS);
			}
			

		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("认证接口返回：%s",result.toString()));
		return result;
	}

	@PostMapping("socialAuthentication")
	public Map<String,Object> socialAuthentication(String soinsBean){
		com.trj.jk.web.validator.Assert.isTrue(StringUtils.isBlank(soinsBean),"社保认证参数不能为空");
        Code code=codeService.getCodeByKeyAndNo(Constant.CODE_CERT_SERVICE, "3");
        String url = servicePath + code.getCodeName();
        LOG.info("调用博士盾社保认证url:{} param:{}",url,soinsBean);
		String result = HttpClientUtils.httpPost(url, soinsBean, null, null, null);

		SocialResult socialResult = JSON.parseObject(result, SocialResult.class);

		Map<String,Object> resultMap = Maps.newHashMap();
		if(socialResult.getRetcode() == -1){
			resultMap.put("success",false);
			resultMap.put("message",socialResult.getRetmessage());
		}else if(socialResult.getRetcode()== 0){
			resultMap.put("success",true);
			resultMap.put("message","成功");
			if(StringUtils.isNotBlank(socialResult.getAreaList())){
				resultMap.put("data",socialResult.getAreaList());
			}else{
				Integer uid = (Integer)SessionUtil.getUserLogonInfo();
				UserBasic userBasic = userBasicService.getById(uid);
				userBasic.setSocialInsuranceCertStatus(Status.STATUS_CERT_SUCCESS);

				int num = userBasicService.updateByPrimaryKey(userBasic);
				com.trj.jk.web.validator.Assert.isTrue(num<0,"认证失败");
				resultMap.put("data","");
			}
		}
		return resultMap;
	}

	
	/**
	 * 认证回调接口
	 * @param uid：用户uid
	 * @param loanApplyId：借款申请id
	 * @return
	 */
	@RequestMapping(value={"/confirm"},method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> confirm(UserCertfication userCertfication,Integer certficationId,Byte confirmType) {
		Result<Object> result = new Result<Object>();
		LOG.info("认证回调接口开始执行...");
		try {
			if(userCertfication!=null){
				LOG.info(String.format("参数：userCertfication=%s,certficationId=%s,confirmType=%s",userCertfication.toString(),certficationId,confirmType));
				if(certficationId!=null){					
					userCertfication.setId(certficationId);
				}
				authenticationService.authConfirm(userCertfication,confirmType);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.AUTH_CONFIRM_SUCCESS);
			}else{
				result.setSuccess(true);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException ex){
			LOG.info(ex.getMessage(),ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("认证回调接口返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 身份证识别接口
	 * @param idcardBase64Img
	 * @return
	 */
	@PostMapping("/idCardOcr")
	@ResponseBody
	public Result<Map<String, String>> idCardOcr(@RequestParam String idcardBase64Img) {
		LOG.info("身份证识别接口开始执行...");
		LOG.info("idcardBase64Img={}",idcardBase64Img);
		Integer uid = (Integer) SessionUtil.getUserLogonInfo();
		Result<Map<String, String>> result = new Result<Map<String, String>>();
		//调用发生异常失败数据信息入库
		UserFaceLog userFaceLog = new UserFaceLog();
		userFaceLog.setType((byte)0);
		userFaceLog.setUid(uid);
		userFaceLog.setStatus((byte)0);
		try {
			Map<String,String> data = faceService.idCardOcr(uid,idcardBase64Img);
			if(data == null || data.size() == 0){
				return Results.newFailedResult("身份识别失败");
			}
			result.setData(data);
		} catch (Exception ex) {
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}
		LOG.info(String.format("身份证识别接口返回：%s",result.toString()));
		return result;
	}
	
	
	
	/**
	 * 身份信息（姓名,身份证号，身份证抠像）比对匹配接口(已经废弃)
	 * @param idcardBase64Img
	 * @return
	 */
	@PostMapping(value={"/identity/auth"})
	public Result<Object> identityAuth(@RequestParam("file") List<MultipartFile> files,UserExt ext) {
		LOG.info(" ========身份信息（姓名,身份证号，身份证抠像）比对匹配接口开始执行=======");
		Integer uid = (Integer) SessionUtil.getUserLogonInfo();
		Result<Object> result = new Result<Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//调用发生异常失败数据信息入库
		UserFaceLog userFaceLog = new UserFaceLog();
		userFaceLog.setType((byte)1);
		userFaceLog.setUid(uid);
		userFaceLog.setStatus((byte)0);
		try {
			Assert.notNull(ext, "身份信息不能为空！");
			Assert.hasText(ext.getName(), "姓名不可以为空！");
			Assert.hasText(ext.getIdentityId(), "身份证号码不可以为空！");
			Assert.notEmpty(files, "比对图片不能为空！");
			LOG.info("name={},identityId={},files={}",ext.getName(),ext.getIdentityId(),files.toString());
			boolean isSuccess = authenticationService.identityAuth(files.get(0),ext);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.FACE_AUTH_SUCCESS);
			dataMap.put("authResult", isSuccess);
			result.setData(dataMap);
		}catch(IllegalArgumentException ex){
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch(CheckException ex){
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("身份证识别接口返回：%s",result.toString()));
		return result;
	}
	
	
	/**
	 * 身份信息（姓名,身份证号，身份证地址，身份证有效期，身份证抠像，身份证正面，身份证反面）保存接口
	 * @param idcardBase64Img
	 * @return
	 */
	@PostMapping("/identity/save")
	@ResponseBody
	public Result<Object> identitySave(UserExt ext) {
		LOG.info(" ========身份信息保存接口开始执行=======");
		Result<Object> result = new Result<Object>();
		try {
			Assert.notNull(ext, "身份信息不能为空！");
			Assert.notNull(ext.getName(), "姓名信息不能为空！");
			Assert.notNull(ext.getIdentityId(), "身份证号信息不能为空！");
			Assert.notNull(ext.getHeadImageId(), "身份证抠像图片信息不能为空！");
			Assert.notNull(ext.getIdentityCardFrontImageId(), "身份证正面图片信息不能为空！");
			Assert.notNull(ext.getIdentityCardOppositeImageId(), "身份证反面面图片信息不能为空！");
			authenticationService.identitySave(ext);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.SAVE_IDENTITY_SUCCESS);
		}catch(IllegalArgumentException ex){
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch(CheckException ex){
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("身份证识别接口返回：%s",result.toString()));
		return result;
	}
	
	
	
	
	/**
	 * （姓名,身份证号，活体）比对匹配接口
	 * @param idcardBase64Img
	 * @return
	 */
	@PostMapping("/livingBody/auth")
	@ResponseBody
	public Result<Object> livingBodyAuth(@RequestParam("file") List<MultipartFile> files) {
		LOG.info(" ========（姓名,身份证号，活体）比对匹配接口开始执行=======");
		Result<Object> result = new Result<Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Integer uid = (Integer) SessionUtil.getUserLogonInfo();
		//调用发生异常失败数据信息入库
		UserFaceLog userFaceLog = new UserFaceLog();
		userFaceLog.setType((byte)3);
		userFaceLog.setUid(uid);
		userFaceLog.setStatus((byte)0);
		try {
			Assert.notEmpty(files,"活体照不可以为空！");
			LOG.info("files={}",files.toString());
			boolean isSuccess = authenticationService.livingBodyAuth(files.get(0));
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.FACE_AUTH_SUCCESS);
			dataMap.put("authResult", isSuccess);
			result.setData(dataMap);
		}catch(IllegalArgumentException ex){
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch(CheckException ex){
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			userFaceLog.setRemark(ex.getMessage());
			userFaceLogService.insert(userFaceLog);
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}

		LOG.info(String.format("身份证识别接口返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * （活体）比对结果保存接口
	 * @param idcardBase64Img
	 * @return
	 */
	@PostMapping("/livingBody/save")
	@ResponseBody
	public Result<Object> livingBodySave(@RequestParam(value="loanApplyId",required=true) Integer loanApplyId,@RequestParam(value="livingBodyImageId",required=true) Integer livingBodyImageId) {
		LOG.info(" ========（活体）比对结果保存接口开始执行=======");
		Result<Object> result = new Result<Object>();
		try {
			Assert.notNull(livingBodyImageId,"活体照信息不可以为空！");
			Assert.notNull(loanApplyId,"申请Id不可以为空！");
			LOG.info("loanApplyId={},livingBodyImageId={}",loanApplyId,livingBodyImageId);
			authenticationService.livingBodySave(loanApplyId,livingBodyImageId);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.SAVE_LIVINGBODY_SUCCESS);
		}catch(IllegalArgumentException ex){
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch(CheckException ex){
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}catch (Exception ex) {
			LOG.info(ex.getMessage(), ex);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("身份证识别接口返回：%s",result.toString()));
		return result;
	}
	
	/**
	 * 人脸识别认证
	 * @return
	 */
	@RequestMapping(value="/face",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> faceAuthentication(@RequestParam("file") List<MultipartFile> files,UserExt ext) {
		Result<Object> result = new Result<Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Integer uid=(Integer)SessionUtil.getUserLogonInfo();
		LOG.info("三像比对接口开始执行》》》》》"+files.toString()+ext.toString());
		try{
			if(!files.isEmpty() && uid!=null){
				//测试账号
				boolean isSuccess = authenticationService.faceAuthentication(files,ext);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.FACE_AUTH_SUCCESS);
				dataMap.put("faceAuthResult", isSuccess);
				result.setData(dataMap);
			}else{
				//参数错误认为比对失败更新是否通过三照比对状态
				authenticationService.updateIsNotpassFaceAuth(uid);
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			//三照比对发生异常，认为比对失败更新是否通过三照比对状态
			authenticationService.updateIsNotpassFaceAuth(uid);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			//三照比对发生异常，认为比对失败更新是否通过三照比对状态
			authenticationService.updateIsNotpassFaceAuth(uid);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		
		return result;
	}
	
	
	
	/**
	 * 人脸识别成功结果保存
	 * @return
	 */
	@RequestMapping(value="/face/save",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> saveFaceAuthResult(UserExt ext,Integer applyId) {
		Result<Object> result = new Result<Object>();
		try{
			Integer uid=(Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null){
				LOG.info(String.format("三像结果附件save口开始执行》》》》》传入参数 applyId：%s ext：%s uid:%s",applyId,ext==null?"":ext.toString(),uid));
				authenticationService.saveFaceAuthResult(ext,uid,applyId);
				result.setSuccess(true);
				result.setMessage(ErrorMessageConstant.SAVE_SUCCESS);
			}else{
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}

		}catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}

		return result;
	}


	/**
	 * wap页面公积金认证，社保认证获取地区信息
	 * @return
	 */
	@RequestMapping(value="/getDistrict",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> getDistrict() {
		Result<Object> result = new Result<Object>();
		LOG.info("获取地区信息开始执行");
		try {
			Map<String, Object> dataMap = authenticationService.getDistrict();
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_DISTRICT_SUCCESS);
			result.setData(dataMap);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("获取地区信息返回：%s",result.toString()));
		return result;
	}


	/**
	 * wap页面公积金认证，社保认证查询城市信息
	 * @return
	 */
	@RequestMapping(value="/selectCity",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> selectCity(String provinceCode,String searchKey) {
		Result<Object> result = new Result<Object>();
		LOG.info("查询城市信息开始执行");
		try {
			List<City> dataList = authenticationService.selectCity(provinceCode,searchKey);
			result.setSuccess(true);
			result.setMessage(ResultMessageConstant.GET_DISTRICT_SUCCESS);
			result.setData(dataList);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("查询城市信息返回：%s",result.toString()));
		return result;
	}


    /**
     * wap页面公积金认证，社保认证表单字段
     *
     * @return
     */
    @RequestMapping(value = "/socialCity", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> socialCity(String searchKey) {
        Result<Object> result = new Result<Object>();
        LOG.info("获取社保地区单信息成功");
        try {
            String cityJson = stringRedisTemplate.opsForValue().get("social::city");
            if (cityJson == null || cityJson.equals("")) {
                String url = servicePath + "cfd_jxl_shebaoregion/";
                Map<String, String> reqHeadMap = new HashMap<String, String>();
                reqHeadMap.put("Content-Type", "application/json");
                cityJson = HttpClientUtils.httpPost(url, "", null, reqHeadMap, null);
                LOG.info(String.format("获取JXL社保地区单信息成功：%s", cityJson));
                stringRedisTemplate.opsForValue().set("social::city", cityJson, 10, TimeUnit.MINUTES);
            }
            CityResult cityResult = new Gson().fromJson(cityJson, CityResult.class);
            List<CityRetResult> list =cityResult.queryCity(searchKey);

            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<City> hotCity = authenticationService.getHotCity();
            List<CityRetResult> hotCityList  = cityResult.buildHotCity(hotCity);
            dataMap.put("hotCity", hotCityList);
            dataMap.put("city", list);

            result.setSuccess(true);
            result.setMessage(ResultMessageConstant.GET_SOCIAL_CITY_SUCCESS);
            result.setData(dataMap);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        LOG.info(String.format("获取社保地区单信息成功：%s", result.toString()));
        return result;
    }

    /**
     * wap页面公积金认证，社保认证表单字段
     *
     * @return
     */
    @RequestMapping(value = "/socialField", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> socialField(String fullgeocode) {
        Result<Object> result = new Result<Object>();
        LOG.info("查询社保认证表单字段");
        try {
            String url = servicePath + "cfd_jxl_shebaologintype/";
            Map<String, String> reqHeadMap = new HashMap<String, String>();
            reqHeadMap.put("Content-Type", "application/json");
            String body = HttpClientUtils.httpPost(url, "{\"fullgeocode\":\"" + fullgeocode + "\"}", null, reqHeadMap, null);
            result.setSuccess(true);
            result.setMessage(ResultMessageConstant.GET_SOCIAL_FIELD_SUCCESS);
            result.setData(body);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        LOG.info(String.format("查询社保认证表单字段返回：%s", result.toString()));
        return result;
    }


    /**
     * wap页面公积金认证，社保认证查询城市信息
     *
     * @return
     */
    @RequestMapping(value = "/gjj", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> gjj() {
        Result<Object> result = new Result<Object>();
        LOG.info("获取公积金H5查询页面");
        try {
            Integer uid = (Integer) SessionUtil.getUserLogonInfo();
            String userInfo = userService.getUserInfo(uid);
            String url = servicePath + "fus_gjj/";
            Map<String, String> reqHeadMap = new HashMap<String, String>();
            reqHeadMap.put("Content-Type", "application/json");
            String body = HttpClientUtils.httpPost(url, userInfo, null, reqHeadMap, null);
            LOG.info(String.format("博士盾:%s", body));
			SoinsResult soinsResult = new Gson().fromJson(body, SoinsResult.class);
			if (null != soinsResult && soinsResult.getRetcode() == 0) {
				LOG.info(String.format("公积用户ID写入缓存:%s", uid));
				//缓存记录，用于处理公积金回调
				stringRedisTemplate.opsForValue().set("gjj-get-uid::open_id::" + soinsResult.getRetresult().getOpen_id(), uid.toString(), 1, TimeUnit.DAYS);
			}
            result.setSuccess(true);
            result.setMessage(ResultMessageConstant.GET_GJJ_H5_SUCCESS);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("h5_url", soinsResult == null ? "" : soinsResult.getRetresult().getSuccess_url());
            result.setData(dataMap);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        LOG.info(String.format("获取公积金H5查询页面：%s", result.toString()));
        return result;
    }

}
