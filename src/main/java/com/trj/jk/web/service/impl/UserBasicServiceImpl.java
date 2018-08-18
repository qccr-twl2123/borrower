package com.trj.jk.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.enums.DateUnitEnum;
import com.trj.jk.web.enums.LoanLimitStatusEnum;
import com.trj.jk.web.enums.LoanProductEnum;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.mapper.*;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.model.request.SignatureReq;
import com.trj.jk.web.service.*;
import com.trj.jk.web.service.face.IFaceService;
import com.trj.jk.web.task.async.ContractPublishTask;
import com.trj.jk.web.util.FileUtil;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xierongli on 17/8/3.
 */
@Service
public class UserBasicServiceImpl implements UserBasicService {

    public static final Logger logger = LoggerFactory.getLogger(UserBasicServiceImpl.class);


    @Value("${app.upload.path}")
    private String uploadPath;
    @Value("${app.upload.download}")
    private String downloadImgUrl;
    @Value("${app.file.tmp.path}")
    private String fileTmpPath;

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
    private FileService fileService;
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private IFaceService faceService;
    @Autowired
    private LoanApplyMapper loanApplyMapper;
    @Autowired
    private LoanFaceAuthMapper loanFaceAuthMapper;
    @Autowired
    private LoanAuditMapper loanAuditMapper;
    @Autowired
    private LoanProductMapper loanProductMapper;
    @Autowired
    private RedisNumberGenerator redisNumberGenerator;
    @Autowired
    private LoanLimitMapper loanLimitMapper;
    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private IThreadTaskService threadTaskService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserBankCardMapper userBankCardMapper;
    @Autowired
    private LoanApplyService loanApplyService;


    @Transactional
    public void insert(UserBasic userBasic) {
        int userBasicNum = userBasicMapper.insertSelective(userBasic);
        UserExt userExt = new UserExt();
        userExt.setUid(userBasic.getId());
        int userExtNum = userExtMapper.insertSelective(userExt);
    }

    @Override
    public UserBasic queryByMobile(String mobile) {
        UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
        userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
        List<UserBasic>  userBasicList = userBasicMapper.selectByCriteria(userBasicCriteria);
        if(CollectionUtils.isNotEmpty(userBasicList)){return userBasicList.get(0);}
        return null;
    }

    @Transactional
    public void saveUserIdentity(UserExt userExt) {
        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userExt.getUid());
        Assert.isNull(userBasic,"uid 不合法");
        UserExtCriteria userExtCriteria = new UserExtCriteria();
        userExtCriteria.createCriteria().andUidEqualTo(userExt.getUid());
        Assert.isNull(userExtMapper.selectByCriteria(userExtCriteria).get(0),"userExt不存在");
        Assert.isTrue(!(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsIdentityAuth()),"用户身份证比对未成功！");

        //更新userBasic表中的身份信息
        userBasic.setName(userExt.getName());
        userBasic.setIdentityId(userExt.getIdentityId());
        userBasic.setIdentityAuthType(UtilConstant.IDENTITY_AUTH_TYPE_2);
        userBasic.setModifyTime(new Date());
        int updateBasicNum = userBasicMapper.updateByPrimaryKey(userBasic);
        Assert.isTrue(updateBasicNum < 0,"用户基础信息更新失败");
        //更新userExt表中的身份信息
        userExt.setModifyTime(new Date());
        int updateExtNum = userExtMapper.updateByCriteriaSelective(userExt,userExtCriteria);
        Assert.isTrue(updateExtNum < 0,"身份信息更新失败");
    }

    @Override
    public UserBasic getByIdentityId(String identityId) {
        UserBasicCriteria userBasicCriteria = new UserBasicCriteria();
        userBasicCriteria.createCriteria().andIdentityIdEqualTo(identityId).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
        List<UserBasic> userBasicList = userBasicMapper.selectByCriteria(userBasicCriteria);
        if(CollectionUtils.isNotEmpty(userBasicList)){return userBasicList.get(0);}
        return null;
    }

    @Override
    public UserBasic getById(Integer id) {
        return userBasicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UserBasic userBasic) {
        return userBasicMapper.updateByPrimaryKey(userBasic);
    }


    @Transactional
    public void doIdentityAuth(final Integer uid, final List<MultipartFile> files, final IdentityReq identityReq){
        //更新用户认证状态
        UserBasic userBasic= getById(uid);
        userBasic.setIsIdentityAuth(UtilConstant.AUTHENTICATION_STATUS_2);
        userBasic.setName(identityReq.getName());
        userBasic.setIdentityId(identityReq.getIdentityId());
        Assert.isTrue(updateByPrimaryKey(userBasic) < 0,"身份认证失败");
        //上传图片,更新userExt, 正面->反面
        int num1 = fileService.asyncUploadImage(1,uid,files.get(0),identityReq);
        int num2 =fileService.asyncUploadImage(2,uid,files.get(1),identityReq);
        Assert.isTrue(num1 <0,"身份证正面照上传失败,请重试");
        Assert.isTrue(num2 <0,"身份证反面照上传失败,请重试");
    }

    @Transactional
    public void doIdentityAuth(final Integer uid, File file1, File file2, final IdentityReq identityReq,String orderNo) {
        loanApplyService.createLoanApply(uid,orderNo);
        //更新用户认证状态
        UserBasic userBasic= getById(uid);
        userBasic.setIsIdentityAuth(UtilConstant.AUTHENTICATION_STATUS_2);
        userBasic.setName(identityReq.getName());
        userBasic.setIdentityId(identityReq.getIdentityId());
        Assert.isTrue(updateByPrimaryKey(userBasic) < 0,"身份认证失败");
        //上传图片,更新userExt, 正面->反面
        int num1 = fileService.asyncUploadImage(1,uid,file1,identityReq);
        int num2 =fileService.asyncUploadImage(2,uid,file2,identityReq);
        Assert.isTrue(num1 <0,"身份证正面照上传失败,请重试");
        Assert.isTrue(num2 <0,"身份证反面照上传失败,请重试");


    }

    @Transactional
    public void liveBodyAuthAndSave(Integer uid,Integer loanApplyId) {
        LoanApply loanApply = loanApplyMapper.selectByPrimaryKey(loanApplyId);
        Assert.isNull(loanApply, "借款申请不存在");
        //获取UserBasic,UserExt
        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
        Assert.isNull(userBasic, "用户不存在");
        UserExtCriteria userExtCriteria = new UserExtCriteria();
        userExtCriteria.createCriteria().andUidEqualTo(uid);
        UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
        Assert.isNull(userExt, "用户不存在");

        //借款人脸认证
        LoanFaceAuthCriteria loanFaceAuthCriteria = new LoanFaceAuthCriteria();
        loanFaceAuthCriteria.createCriteria().andApplyIdEqualTo(loanApplyId);
        List<LoanFaceAuth> loanFaceAuthList = loanFaceAuthMapper.selectByCriteria(loanFaceAuthCriteria);

        //构建数据模型 LoanFaceAuth
        LoanFaceAuth loanFaceAuth = ObjectConvert.convertObject(userExt,LoanFaceAuth.class);
        loanFaceAuth.setApplyId(loanApplyId);
        if(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsFaceAuth()){
            loanFaceAuth.setStatus(Byte.parseByte("0"));
        }else{
            loanFaceAuth.setStatus(Byte.parseByte("1"));
        }
        if(CollectionUtils.isNotEmpty(loanFaceAuthList)){
            loanFaceAuthMapper.updateByPrimaryKey(loanFaceAuth);
        }else{
            loanFaceAuthMapper.insert(loanFaceAuth);
        }
        //设置借款申请是否人工审核
        if(UtilConstant.AUTHENTICATION_STATUS_2==userBasic.getIsFaceAuth()){
            loanApply.setIsManualAudit(Byte.parseByte("0"));
        }else{
            loanApply.setIsManualAudit(Byte.parseByte("1"));
        }
        loanApplyMapper.updateByPrimaryKey(loanApply);
    }

    @Override
    public Boolean compareTrueFaceAndHeadImage(Integer uid,MultipartFile file) {
        UserExtCriteria userExtCriteria = new UserExtCriteria();
        userExtCriteria.createCriteria().andUidEqualTo(uid);
        UserExt userExt = userExtMapper.selectByCriteria(userExtCriteria).get(0);
        Assert.isNull(userExt,"用户不存在");
        Assert.isNull(userExt.getHeadImageId(),"用户身份证抠图为空");

        Attachment attachment = attachmentMapper.selectByPrimaryKey(userExt.getHeadImageId());
        Assert.isNull(attachment,"身份抠图不存在");
        File headImage=null;
        try {
            headImage = new File(FileUtil.getFileByUrl(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName(),fileTmpPath));
        }catch (Exception e) {
            throw new RRException("获取身份证抠图照错误");
        }
        boolean flag = faceService.compareFace(uid, FileUtil.saveFile(file, uploadPath), headImage);
        if(flag){
            UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
            userBasic.setIsFaceAuth(UtilConstant.AUTHENTICATION_STATUS_2);
            userBasicMapper.updateByPrimaryKey(userBasic);
        }

        return flag;
    }

    @Transactional
    public Integer doElectronSignature(SignatureReq signatureReq,Integer uid,LoanApply loanApply)  {
        //验证放款银行卡ID是否合法
        UserBankCard userBankCard = userBankCardMapper.selectByPrimaryKey(signatureReq.getBankCardId());
        Assert.isTrue(userBankCard== null,"异常银行卡");
        Assert.isTrue( !userBankCard.getUid().equals(uid),"非法操作,请提供正确的银行卡");
        //验证利率是否合法
        LoanAuditCriteria loanAuditCriteria = new LoanAuditCriteria();
        loanAuditCriteria.createCriteria().andApplyLoanIdEqualTo(loanApply.getId()).andUidEqualTo(uid);
        List<LoanAudit> loanAuditList =loanAuditMapper.selectByCriteria(loanAuditCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(loanAuditList),"没有审批记录");
        LoanAudit loanAudit = loanAuditList.get(0);
        Assert.isNull(loanAudit,"没有借款审核记录");
        BigDecimal interest = signatureReq.getInterest().multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_UP);
        Assert.isTrue(interest.compareTo(loanAudit.getInterest()) != 0, "借款利率与审核数据不符");
        //检测剩余额度是否合法
        Assert.isTrue(loanAudit.getRestLimit().compareTo(signatureReq.getUseAmount())==-1,"用款金额不能大于可用额度");

        //构建借款额度记录
        LoanLimit loanLimit = ObjectConvert.convertObject(signatureReq,LoanLimit.class);
        loanLimit.setUid(uid);
        loanLimit.setLoanApplyId(loanApply.getId());
        loanLimit.setProductId(loanApply.getProductId());
        loanLimit.setRepayType(UtilConstant.REPAY_TYPE_PERMONTH);
        loanLimit.setTermUnit(DateUnitEnum.MONTH.getEnglish());
        loanLimit.setStatus(LoanLimitStatusEnum.INIT.getCode().byteValue());

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
        //借款额度数据落地
        int loanLimitNum = loanLimitMapper.insertSelective(loanLimit);
        Assert.isTrue(loanLimitNum < 0,"借款额度新增失败");

        //借款审批表修改
        loanAudit.setRestLimit(loanAudit.getRestLimit().subtract(signatureReq.getUseAmount()));
        loanAudit.setCutCharge(signatureReq.getCutCharge());
        int loanAuditNum = loanAuditMapper.updateByPrimaryKey(loanAudit);
        Assert.isTrue(loanAuditNum < 0,"借款审批更新失败");

        //如果是购车宝,设置只能申请一次额度
        String productCode = loanProductMapper.selectByPrimaryKey(loanApply.getProductId()).getProductCode();
        if(UtilConstant.LOAN_PRODUCTCODE_C1.equals(productCode)){
            loanApply.setIsValid(Byte.parseByte("0"));
            loanApplyMapper.updateByPrimaryKey(loanApply);
        }

        //调用crm系统生成合同服务并签署电子签章授权协议
        CodeCriteria codeCriteria = new CodeCriteria();
        codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.PRODUCT_CONTRACT_KEY).andCodeNoEqualTo(productCode);
        List<Code> codes = codeMapper.selectByCriteria(codeCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(codes),"借款申请产品合同配置不可以为空");

        String codeName = codes.get(0).getCodeName();
        //生成合同数据
        //调用crm系统生成合同服务并签署电子签章授权协议
        logger.info("调用crm系统生成合同服务并签署电子签章授权协议,uid={},loanLimit={},varifyCode={},codeName={}",uid,JSON.toJSONString(loanLimit),signatureReq.getVerifyCode(),codeName);
        iUserService.generateContractAndSignAccredit(uid,loanLimit,signatureReq.getVerifyCode(),codeName);
        //异步调用crm合同发布接口
        Map<String, String> param = new HashMap<String, String>();
        param.put("jkWebUid", String.valueOf(uid));
        param.put("jkWebLoanLimit", JsonUtils.objectToJsonString(loanLimit));
        logger.info("调用crm合同发布接口,jkWebUid={},jkWebLoanLimit={}",uid, JSON.toJSONString(loanLimit));
        threadTaskService.asyncExecute(new ContractPublishTask((remoteDomainCrm+contractPublishUrl),param));
        return loanLimit.getId();
    }


}
