package com.trj.jk.web.service.impl;

import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.domain.entity.constant.Status;
import com.trj.jk.web.enums.LoanApplyStatusEnum;
import com.trj.jk.web.enums.LoanApplyStepEnum;
import com.trj.jk.web.enums.LoanProductEnum;
import com.trj.jk.web.enums.TenantEnum;
import com.trj.jk.web.mapper.*;
import com.trj.jk.web.model.request.OrderFinishReq;
import com.trj.jk.web.service.IApplyLoanService;
import com.trj.jk.web.service.LoanApplyService;
import com.trj.jk.web.service.RedisNumberGenerator;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xierongli on 17/8/10.
 */
@Service
public class LoanApplyServiceImpl implements LoanApplyService {

    @Autowired
    private LoanApplyMapper loanApplyMapper;
    @Autowired
    private LoanProductMapper loanProductMapper;
    @Autowired
    private RedisNumberGenerator redisNumberGenerator;
    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private LoanAuditMapper loanAuditMapper;

    @Autowired
    private IApplyLoanService iApplyLoanService;
    @Autowired
    private UserContactsMapper userContactsMapper;
    @Autowired
    private LoanApplyContactsMapper loanApplyContactsMapper;
    @Autowired
    private UserProfessionMapper userProfessionMapper;
    @Autowired
    private LoanApplyProfessionMapper loanApplyProfessionMapper;

    public Integer addLoanApply(LoanApply loanApply){
        LoanProduct product = loanProductMapper.selectByPrimaryKey(loanApply.getProductId());
        UserBasic userBasic =userBasicMapper.selectByPrimaryKey(loanApply.getUid());
        Assert.isNull(userBasic,"用户不存在");
        Assert.isNull(product,"产品不存在");
        //可立即申请时vaild_date=null
        //30天后可重新申请
        //90天后可重新申请
        //永久不能再申请
        LoanApply loanApplyRefuse = loanApplyMapper.queryRefuseApplyByVaildDate(loanApply.getUid(), loanApply.getProductId());
        String tipMsg = "亲,感谢您的支持!您只有在上次申请30天后才能重新申请哦!";
        if (null != loanApplyRefuse && StringUtils.isNotEmpty(loanApplyRefuse.getRejectDayInfo())) {
            tipMsg = "亲,感谢您的支持!" + loanApplyRefuse.getRejectDayInfo() + "!";
        }
        Assert.isTrue(loanApplyRefuse != null, tipMsg);

        LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
        loanApplyCriteria.createCriteria().andUidEqualTo(loanApply.getUid()).andProductIdEqualTo(loanApply.getProductId()).andIsValidEqualTo(Byte.parseByte("1"));
        List<LoanApply> loanApplies = loanApplyMapper.selectByCriteria(loanApplyCriteria);
        Assert.isTrue(CollectionUtils.isNotEmpty(loanApplies),ErrorMessageConstant.IS_HAVE_VALID_APPLY_ERROR);
        // 金鸡贷产品与工薪贷和购车宝互斥关系
        validApplyProductExist(loanApply.getUid(), product.getProductCode());

        if(product!=null) {
            String applyId= redisNumberGenerator.generateNumber("SQ"+product.getProductCode(), 5);
            loanApply.setApplyId(applyId);
            loanApply.setProductName(product.getName());
        }
        loanApply.setIsValid(Status.STATUS_APPLY_INVALID);
        loanApply.setTenant("jkWeb");
        loanApply.setName(StringUtils.isBlank(userBasic.getName())?"":userBasic.getName());
        loanApply.setMobile(StringUtils.isBlank(userBasic.getMobile())?"":userBasic.getMobile());
        loanApply.setIdentityId(StringUtils.isBlank(userBasic.getIdentityId())?"":userBasic.getIdentityId());
        return loanApplyMapper.insertSelective(loanApply);
    }

    @Override
    public LoanApply getByOrderNo(Integer uid,String orderNo) {
        LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
        loanApplyCriteria.createCriteria().andOrderNoEqualTo(orderNo).andUidEqualTo(uid);
        List<LoanApply> loanApplyList = loanApplyMapper.selectByCriteria(loanApplyCriteria);
        if(CollectionUtils.isNotEmpty(loanApplyList)){return loanApplyList.get(0);}
        return null;
    }

    @Transactional
    public void orderFinish(OrderFinishReq orderFinishReq,Integer uid) {
        //生成借款申请记录
        LoanApply loanApply = ObjectConvert.convertObject(orderFinishReq,LoanApply.class);
        LoanProduct product = iApplyLoanService.getLoanProductByCode(orderFinishReq.getProductCode());
        Assert.isNull(product,"产品代码不合法");
        loanApply.setProductId(product.getId());
        loanApply.setProductName(product.getName());
        loanApply.setProductName(product.getName());
        loanApply.setTenant(TenantEnum.CHANG_FU_DAI.getDescription());
        loanApply.setStatus(LoanApplyStatusEnum.RUNNING.getCode().byteValue());
        loanApply.setStep(LoanApplyStepEnum.FILL_FACE_AUTH.getCode().byteValue());
        loanApply.setIsValid(Status.STATUS_APPLY_VALID);

        LoanApplyCriteria loanApplyUpdateCriteria = new LoanApplyCriteria();
        loanApplyUpdateCriteria.createCriteria().andOrderNoEqualTo(orderFinishReq.getOrderNo());

        int loanApplyNum = loanApplyMapper.updateByCriteriaSelective(loanApply,loanApplyUpdateCriteria);
        Assert.isTrue(loanApplyNum < 0,"更新借款申请失败");

        //清除旧的联系人记录
        LoanApply newLoanApply = null;
        LoanApplyCriteria loanApplyCriteria1 = new LoanApplyCriteria();
        loanApplyCriteria1.createCriteria().andOrderNoEqualTo(orderFinishReq.getOrderNo()).andUidEqualTo(uid);
        newLoanApply = loanApplyMapper.selectByCriteria(loanApplyCriteria1).get(0);

        Integer loanApplyId =newLoanApply.getId();
        List<UserContacts> userContactsList = ObjectConvert.convertList(orderFinishReq.getContacts(),UserContacts.class);
        if(CollectionUtils.isNotEmpty(userContactsList)){
            for(UserContacts userContacts : userContactsList){
                userContacts.setUid(uid);

                //将用户联系人克隆存储到userContacts
                UserContactsCriteria userContactsCriteria = new UserContactsCriteria();
                userContactsCriteria.createCriteria().andUidEqualTo(userContacts.getUid()).andMobileEqualTo(userContacts.getMobile());
                List<UserContacts> userContacts1 = userContactsMapper.selectByCriteria(userContactsCriteria);
                if(CollectionUtils.isNotEmpty(userContacts1)){
                    userContacts.setId(userContacts1.get(0).getId());
                    userContactsMapper.updateByPrimaryKeySelective(userContacts);
                }else {
                    int num = userContactsMapper.insertSelective(userContacts);
                    Assert.isTrue(num<0,"生成用户联系人失败");
                }
                //保存到借款联系人表
                LoanApplyContacts loanApplyContact = ObjectConvert.convertObject(userContacts,LoanApplyContacts.class);
                loanApplyContact.setLoanApplyId(loanApplyId);
                loanApplyContact.setContactId(userContacts.getId());
                int num2 = loanApplyContactsMapper.insertSelective(loanApplyContact);
                Assert.isTrue(num2<0,"生成借款联系人失败");
            }
        }
        //保存公司信息loanApplyProfession
        if(StringUtils.isNotBlank(orderFinishReq.getCorpName())){
            UserProfessionCriteria userProfessionCriteria = new UserProfessionCriteria();
            userProfessionCriteria.createCriteria().andCorpNameEqualTo(orderFinishReq.getCorpName()).andUidEqualTo(uid);
            List<UserProfession> userProfessionList = userProfessionMapper.selectByCriteria(userProfessionCriteria);
            Integer professionId = 0;
            if(CollectionUtils.isEmpty(userProfessionList)){
                UserProfession userProfession = new UserProfession();
                userProfession.setCorpName(orderFinishReq.getCorpName());
                userProfession.setUid(uid);
                int userProfessionNum = userProfessionMapper.insertSelective(userProfession);
                Assert.isTrue(userProfessionNum<0,"保存公司信息失败");
                professionId = userProfession.getId();
            }else{
                professionId = userProfessionList.get(0).getId();
            }

            LoanApplyProfession loanApplyProfession = new LoanApplyProfession();
            loanApplyProfession.setLoanApplyId(loanApplyId);
            loanApplyProfession.setUid(uid);
            loanApplyProfession.setProfessionId(professionId);
            loanApplyProfession.setCorpName(orderFinishReq.getCorpName());
            loanApplyProfession.setStatus(new Byte("0"));
            int loanApplyProfessionNum = loanApplyProfessionMapper.insertSelective(loanApplyProfession);
            Assert.isTrue(loanApplyProfessionNum<0,"保存公司信息失败");
        }

        //完成借款申请后续工作(创建借款审核记录)
        LoanAuditCriteria loanAuditCriteria = new LoanAuditCriteria();
        loanAuditCriteria.createCriteria().andApplyLoanIdEqualTo(newLoanApply.getId());
        Assert.isTrue(CollectionUtils.isNotEmpty(loanAuditMapper.selectByCriteria(loanAuditCriteria)),"请勿重复提交借款申请");

        LoanProduct loanProduct = loanProductMapper.selectByPrimaryKey(loanApply.getProductId());
        // 金鸡贷产品与工薪贷和购车宝互斥关系
        validApplyProductExist(uid, loanProduct.getProductCode());
        LoanAudit loanAudit = new LoanAudit();
        if(Status.NEED_AUDIT.equals(loanProduct.getIsNeedAudit())){
            loanAudit.setApplyLoanId(newLoanApply.getId());
            loanAudit.setUid(uid);
            loanAudit.setRepayType(Constant.REPAY_TYPE_MYDEHK);
        }else{
            loanAudit.setApplyLoanId(newLoanApply.getId());
            loanAudit.setUid(uid);
            loanAudit.setRepayType(Constant.REPAY_TYPE_MYDEHK);
            loanAudit.setStatus(Status.STEP_WAIT_SCORE);
        }
        loanAuditMapper.insertSelective(loanAudit);
        //更新借款申请状态
        loanApplyMapper.updateByPrimaryKeySelective(loanApply);
    }

    @Override
    public void createLoanApply(Integer uid, String orderNo) {
        LoanApply loanApply = getByOrderNo(uid, orderNo);
        LoanProductCriteria loanProductCriteria = new LoanProductCriteria();
        loanProductCriteria.createCriteria().andProductCodeEqualTo(LoanProductEnum.JIN_JI_DAI.getProductCode());
        List<LoanProduct> loanProducts = loanProductMapper.selectByCriteria(loanProductCriteria);

        if (loanApply != null && CollectionUtils.isNotEmpty(loanProducts)) {
            //验证用户是否可以提交申请(存在有效的申请or未结清的还款计划)
            LoanApplyCriteria loanApplyCriteria = new LoanApplyCriteria();
            loanApplyCriteria.createCriteria().andUidEqualTo(uid).andProductIdEqualTo(loanProducts.get(0).getId()).andIsValidEqualTo(Byte.parseByte("1"));
            List<LoanApply> loanApplyList = loanApplyMapper.selectByCriteria(loanApplyCriteria);
            Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyList), ErrorMessageConstant.IS_HAVE_VALID_APPLY_ERROR);
        }
        if (loanApply == null) {
            //生成订单,loanApply
            loanApply = new LoanApply();
            loanApply.setOrderNo(orderNo);
            loanApply.setUid(uid);
            loanApply.setIsValid(Status.STATUS_APPLY_INVALID);
            String applyId = redisNumberGenerator.generateNumber("SQ" + LoanProductEnum.JIN_JI_DAI.getProductCode(), 5);
            loanApply.setApplyId(applyId);
            loanApplyMapper.insertSelective(loanApply);
        }
    }

    /**
     * 金鸡贷产品与工薪贷和购车宝互斥关系；
     * 工薪贷与金鸡贷互斥（只要有其中一款产品在贷状态就不可以再次申请另外一款产品）
     * 购车宝与金鸡贷互斥（只要有其中一款产品在贷状态就不可以再次申请另外一款产品）
     *
     * @param uid
     * @param productCode
     */
    public void validApplyProductExist(Integer uid, String productCode){
        if (productCode.equals(LoanProductEnum.JIN_JI_DAI.getProductCode())) {
            Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.GONG_XIN_DAI.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
            Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.GOU_CHE_BAO.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
        } else {
            Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.JIN_JI_DAI.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
        }
    }
}
