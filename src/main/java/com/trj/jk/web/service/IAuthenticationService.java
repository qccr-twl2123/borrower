package com.trj.jk.web.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.UserCertfication;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserFaceLog;
import com.trj.jk.web.domain.entity.authentication.AuthenticationBean;
import com.trj.jk.web.domain.entity.authentication.AuthenticationResultBean;
import com.trj.jk.web.domain.entity.authentication.bi.BiRetResult;
import com.trj.jk.web.domain.entity.authentication.bi.CertResult;
import com.trj.jk.web.domain.entity.authentication.bi.OperatorResetPwdBean;

public interface IAuthenticationService {
	/**
	 * 保存认证提交信息
	 * @param bean
	 * @param uid
	 * @param type
	 * @return
	 */
	public Integer saveAuthentication(AuthenticationBean bean,int uid,Byte type);
	
	/**
	 * 调用服务进行认证
	 * @param bean
	 * @param url
	 * @param id
	 * @param uid
	 * @param type
	 * @return
	 */
	public CertResult certificate(Object bean, String url, Integer id, Integer uid, Byte type);
	
	/**
	 * 保存三像比对人脸结果
	 * @param ext
	 * @param uid
	 * @param applyId
	 */
	public void saveFaceAuthResult(UserExt ext,Integer uid,Integer applyId);
	
	/**
	 * 公积金，社保认证wap页面调用地区信息接口
	 * @return
	 */
	public Map<String, Object> getDistrict();


	/**
	 * 单独获取执门城市
	 * @return
     */
	public List<City> getHotCity();
	
	/**
	 *  公积金，社保认证wap页面调用城市信息接口
	 * @param provinceCode
	 * @param searchKey
	 * @return
	 */
	public List<City> selectCity(String provinceCode,String searchKey);
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String pythonCall() throws IOException;
	
	/**
	 * 获取用户认证信息
	 * @param uid
	 * @return
	 */
	public AuthenticationResultBean getAuthenticationInfo(Integer uid);
	
	/**
	 * 三像比对
	 * @param
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public boolean faceAuthentication(List<MultipartFile> files,UserExt ext)throws Exception;
	
	/**
	 * 更新三像比对结果失败
	 * @param uid
	 */
	public void updateIsNotpassFaceAuth(Integer uid);
	
	/**
	 * 认证结果回调接口（包含即时认证回调，认证过期回调）
	 * @param userCertfication
	 * @param confirmType
	 */
	public void authConfirm(UserCertfication userCertfication,Byte confirmType);

	/**
	 * 省份证抠像比对
	 * @param multipartFile：省份证抠像
	 * @param ext：姓名，身份证号码
	 * @return
	 */
	public boolean identityAuth(MultipartFile file, UserExt ext);

	/**
	 * 活体比对
	 * @param file
	 * @return
	 */
	public boolean livingBodyAuth(MultipartFile file);
	public boolean livingBodyAuth(Integer uid,MultipartFile file);

	/**
	 * 保存身份信息
	 * @param ext
	 */
	public void identitySave(UserExt ext);
	

	/**
	 * 活体比对后的信息保存
	 * @param loanApplyId：申请Id
	 * @param livingBodyImageId：活体照
	 */
	public void livingBodySave(Integer loanApplyId, Integer livingBodyImageId);
	public void livingBodySave(Integer uid, Integer loanApplyId, Integer livingBodyImageId);

	/**
	 * 重置运营商服务密码短信验证码获取
	 * @param mobile
	 */
	public BiRetResult getVerifyCode(String mobile);

	/**
	 * 重置运营商服务密码
	 * @param bean
	 */
	public CertResult modifyPassword(OperatorResetPwdBean bean);
	



	
	
}
