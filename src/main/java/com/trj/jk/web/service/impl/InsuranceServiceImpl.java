package com.trj.jk.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.LoanLimitInsurance;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.mapper.LoanLimitInsuranceMapper;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.model.dto.InsuranceApplyDTO;
import com.trj.jk.web.model.request.InsuranceApplyReq;
import com.trj.jk.web.properties.InsuranceProperties;
import com.trj.jk.web.service.ILoanInfoService;
import com.trj.jk.web.service.InsuranceService;
import com.trj.jk.web.util.*;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/3 下午3:31 mark1xie Exp $$
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {
    public static final Logger logger = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    @Autowired
    private InsuranceProperties insuranceProperties;
    @Autowired
    private LoanLimitInsuranceMapper loanLimitInsuranceMapper;
    @Autowired
    private LoanLimitMapper loanLimitMapper;
    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private ILoanInfoService loanInfoService;

    @Override
    public void apply(Integer loanLimitId) {
        logger.info("兴民保单申请 loanLimitId:{}",loanLimitId);
        try{
            LoanLimit loanLimit= loanLimitMapper.selectByPrimaryKey(loanLimitId);
            Assert.isNull(loanLimit,"借款申请不存在");
            UserBasic userBasic = userBasicMapper.selectByPrimaryKey(loanLimit.getUid());
            Assert.isNull(userBasic,"用户不存在");

            InsuranceApplyReq insuranceApplyReq = new InsuranceApplyReq();
            insuranceApplyReq.setIdType("0");
            insuranceApplyReq.setIdNo(userBasic.getIdentityId());
            insuranceApplyReq.setApplyDate(DateUtil.formatDateTime(new Date()));
            insuranceApplyReq.setName(userBasic.getName());
            insuranceApplyReq.setPhone(userBasic.getMobile());
            insuranceApplyReq.setTotalPremium(loanInfoService.calculateInsurance(Integer.parseInt(loanLimit.getTerm()),loanLimit.getUseAmount()).toString());
            insuranceApplyReq.setInsurancePeriodNum(loanLimit.getTerm());

            //生成平台编号
            String platformApplyId = RandomUtil.getSequeue();
            insuranceApplyReq.setPlatformApplyId(platformApplyId);
            insuranceApplyReq.setUserName(insuranceProperties.getUserName());
            insuranceApplyReq.setPassword(insuranceProperties.getPassword());
            insuranceApplyReq.setProductCode(insuranceProperties.getProductCode());
            //验证参数
            ValidatorUtils.validateEntity(insuranceApplyReq);
            Map<String,Object> paramMap = BeanUtil.beanToMap(insuranceApplyReq);
            String param = JSON.toJSONString(paramMap);
            Map<String,String> headPostParam = Maps.newHashMap();
            RSAHelper.peerPubKey = RSAHelper.getPublicKey(insuranceProperties.getPublicKey());
            byte[] chiper = RSAHelper.encryptRSA(param.getBytes(),true,"utf-8");
            headPostParam.put("data_content", URLEncoder.encode(new String(chiper,"ISO-8859-1"),"utf-8"));
            //开始请求
            String postString = HttpClientUtils.httpPost(insuranceProperties.getPolicyReqUrl(), null,headPostParam,null,null);
            postString = URLDecoder.decode(postString,"utf-8");
            //解密
            RSAHelper.localPrivKey = RSAHelper.getPrivateKey(insuranceProperties.getPrivateKey());
            byte[] temp = RSAHelper.decryptRSA(postString.getBytes("ISO-8859-1"), true, "utf-8");
            postString = new String(temp);
            logger.info("兴民保单申请result:{}",postString);
            InsuranceApplyDTO insuranceApplyDTO = JSON.parseObject(postString,InsuranceApplyDTO.class);
            //生成借款保险记录
            LoanLimitInsurance loanLimitInsurance = new LoanLimitInsurance();
            loanLimitInsurance.setApplyStatus(insuranceApplyDTO.getApplyStatus().byteValue());
            loanLimitInsurance.setApplyRemark(insuranceApplyDTO.getResp_msg());
            loanLimitInsurance.setApplyTime(DateUtil.parse(insuranceApplyReq.getApplyDate()));
            loanLimitInsurance.setLoanLimitId(loanLimitId);
            loanLimitInsurance.setPlatformApplyId(platformApplyId);
            Money totalFee =  Money.ofYuan(new Double(insuranceApplyReq.getTotalPremium()));
            loanLimitInsurance.setTotalPremium(totalFee.getCent());
            loanLimitInsurance.setInsurancePeriodNum(new Byte(insuranceApplyReq.getInsurancePeriodNum()));
            loanLimitInsurance.setProductCode(insuranceProperties.getProductCode());
            loanLimitInsuranceMapper.insertSelective(loanLimitInsurance);

        }catch (Exception e){
            logger.error("兴民保单申请异常",e);
        }
    }
}
