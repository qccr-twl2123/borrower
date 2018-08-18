package com.trj.jk.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.trj.jk.web.util.UtilConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.BankBranchCriteria;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.CityCriteria;
import com.trj.jk.web.domain.Province;
import com.trj.jk.web.domain.ProvinceCriteria;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.domain.exception.LoginException;
import com.trj.jk.web.mapper.BankBranchMapper;
import com.trj.jk.web.mapper.CityMapper;
import com.trj.jk.web.mapper.ProvinceMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.jdbc.JdbcDao;
import com.trj.jk.web.service.IConfigService;
import com.trj.jk.web.util.DigestUtil;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;

@Service
@Transactional
public class ConfigServiceImpl implements IConfigService{
	
	@Autowired
	private UserBasicMapper userBasicMapper;
	
	@Autowired
	private ProvinceMapper provinceMapper;
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private BankBranchMapper bankBranchMapper;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	//crm系统域名
	@Value("${app.remote.domain.crm}")
	private String	remoteDomainCrm;
	
	//crm修改e签宝账户联系信息url
	@Value("${app.remote.domain.updateEsAccountContactUrl}")
	private String updateEsAccountContactUrl;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);

	@Override
	public void changeMobile(Integer uid,String mobile,String pwd,String verifyCode) {
		//检验修改的新手机号是否被注册
		UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
		userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
		List<UserBasic> userBasics = userBasicMapper.selectByCriteria(userBasicCriteria);
		if(userBasics!=null&&!userBasics.isEmpty()){
			throw new CheckException(ErrorMessageConstant.ERR_MOBILE_EXIST_ERROR);
		}
		//验证密码是否正确
		UserBasic user = userBasicMapper.selectByPrimaryKey(uid);
		if(!user.getPassword().equals(DigestUtil.getMD5(pwd))){
			throw new CheckException(ErrorMessageConstant.ERR_PASSWORD_VERIFY);
		};
		//验证短信验证码
		String status=jdbcDao.getTrbsVerifyCodeStatus(mobile, verifyCode);
		if(status==null){
			throw new CheckException(ErrorMessageConstant.ERR_VERIFY_INCORRECT);
		}
		if("0".endsWith(status)){
			throw new CheckException(ErrorMessageConstant.ERR_VERIFY_INVALID);
		}
		//如果用户已经e签宝开户，调用crm系统修改e签宝账户联系信息
		if(user.getEsAccountId()!=null){
			LOG.info(String.format("调用crm系统修改e签宝账户联系信息服务接口<<<<<<<：参数：jkWebEsAccountId:%s",user.getEsAccountId()));
			Map<String, String> param = new HashMap<String, String>();
			param.put("jkWebEsAccountId", String.valueOf(user.getEsAccountId()));
			param.put("newMobile", String.valueOf(mobile));
			LOG.info("调用crm系统修改e签宝账户联系信息服务接口服务url："+(remoteDomainCrm+updateEsAccountContactUrl));
			LOG.info("调用crm系统修改e签宝账户联系信息服务接口服务参数："+param.toString());
			String body = HttpClientUtils.httpPost((remoteDomainCrm+updateEsAccountContactUrl), null, param, null, null);
			Map<String, Object> result = (Map<String, Object>)JsonUtils.stringToObject(body, Map.class);
			LOG.info("crm系统e签宝发送签署短信验证码接口服务返回："+result.toString());
			if(!"0".equals(String.valueOf(result.get("isSuccess")))){
				throw new RuntimeException("修改e签宝账户联系信息异常！");
			}
		}
		user.setMobile(mobile);
		userBasicMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Province> getProvinces() {
		List<Province> provinceList = provinceMapper.selectByCriteria(new ProvinceCriteria());
		return provinceList;
	}

	@Override
	public List<City> getCitiesByProvinceCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		CityCriteria criteria = new CityCriteria();
		criteria.createCriteria().andProvinceCodeEqualTo(code);
		List<City> citys=cityMapper.selectByCriteria(criteria);
		return citys;
	}

	@Override
	public List<BankBranch> getBankBranchesByCity(String city, String bankCode) {
		CityCriteria criteria = new CityCriteria();
		criteria.createCriteria().andNameLike(city+"%");
		List<City> cities=cityMapper.selectByCriteria(criteria);
		if(cities!=null && cities.size()>0){
			String cityCode = cities.get(0).getCode();
			BankBranchCriteria branchCriteria = new BankBranchCriteria();
			branchCriteria.createCriteria().andCityCodeEqualTo(cityCode).andBankCodeEqualTo(bankCode);
			List<BankBranch> banks = bankBranchMapper.selectByCriteria(branchCriteria);
			return banks;

		}else{
			return null;
		}

	}

	@Override
	public List<BankBranch> getBankBranchesByCityCode(String cityCode, String bankCode) {
		BankBranchCriteria branchCriteria = new BankBranchCriteria();
		branchCriteria.createCriteria().andCityCodeEqualTo(cityCode).andBankCodeEqualTo(bankCode);
		return  bankBranchMapper.selectByCriteria(branchCriteria);
	}

}
