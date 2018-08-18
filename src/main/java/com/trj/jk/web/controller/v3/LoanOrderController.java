package com.trj.jk.web.controller.v3;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.*;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.model.request.CutInterestReq;
import com.trj.jk.web.model.request.OrderFinishReq;
import com.trj.jk.web.model.request.SignatureReq;
import com.trj.jk.web.model.response.CutInterestRes;
import com.trj.jk.web.service.*;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 融资申请相关业务controller类
 *
 * @author l46001
 */
@RestController("LoanOrderController_v3")
@RequestMapping(value = {"/v3/order"})
public class LoanOrderController extends BaseController {
    @Value("${server.url}")
    private String serverUrl;

    @Resource
    private ILoanInfoService loanInfoService;
    @Autowired
    private IUserService userService;
    @Resource
    private IApplyLoanService applyLoanService;
    @Autowired
    private UserBasicService userBasicService;
    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private LoanAuditService loanAuditService;
    @Autowired
    private ILoanProductService loanProductService;
    @Autowired
    private InsuranceService insuranceService;


    @RequestMapping("queryInsuranceContractTpl")
    public Result<Map<String,String>> queryInsuranceContractTpl(){
        logger.info("查询保险协议合同");
        Map<String,String> map = Maps.newHashMap();
        map.put("个人意外伤害保险条款",serverUrl+"/images/templates/保险条款.pdf");
        map.put("投保须知",serverUrl+"/images/templates/投保须知.pdf");
        return  Results.newSuccessResult(map);
    }

    @RequestMapping("queryLoanUseTypeList")
    public Result<List<String>> queryLoanUseTypeList(){
        logger.info("查询借款用途");
        List<String> loanUseTypeList = Lists.newArrayList();
        loanUseTypeList.add("婚庆服务");
        loanUseTypeList.add("旅游消费");
        loanUseTypeList.add("购车消费");
        loanUseTypeList.add("助学进修");
        loanUseTypeList.add("百货家电");
        loanUseTypeList.add("医疗服务");
        loanUseTypeList.add("装修建材");
        return Results.newSuccessResult(loanUseTypeList);
    }

    /**
     * 借款流程借款申请最后完成接口
     * @param orderFinishReq
     */
    @RequestMapping(value = {"/finish"}, method = RequestMethod.POST)
    public Result<Boolean> finish(@RequestBody OrderFinishReq orderFinishReq) {
        logger.info("借款流程借款申请最后完成接口开始执行....");
        ValidatorUtils.validateEntity(orderFinishReq);
        Assert.isTrue(orderFinishReq.getContacts().size() !=2,"请传入2组联系人");

        Integer userId = getCurrentUid();
        Assert.isNull(userId,"用户ID为空");
        LoanApply loanApply = loanApplyService.getByOrderNo(userId,orderFinishReq.getOrderNo());
        Assert.isNull(loanApply,"订单号不合法");

        UserBasic userBasic = userBasicService.getById(userId);
        Assert.isTrue(UtilConstant.AUTHENTICATION_STATUS_2 != userBasic.getIsIdentityAuth(),"用户未实名认证");
        loanApplyService.orderFinish(orderFinishReq,userId);

        return Results.newSuccessResult(true);
    }


    /**
     * 额度批复列表获取接口
     * @param pageBean
     */
    @RequestMapping(value = {"/getLimitAuditList"}, method = RequestMethod.GET)
    public Result<Map<String, Object>> getLimitAuditList(PageBean pageBean,String productCode) {
        logger.info("额度批复列表获取接口开始执行....");
        Assert.isNull(loanProductService.getByProductCode(productCode),"产品代码不合法");

        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer uid = getCurrentUid();
        Assert.isNull(uid,"用户Id为空");
        PageBounds pageBounds = getPageBounds(pageBean);
        PageList<LimitAuditBean> limitAuditBeanPageList = loanAuditService.getLimitAuditListByProduct(uid, productCode, pageBounds);
        Assert.isNull(limitAuditBeanPageList,"没有记录");

        dataMap.put("list", limitAuditBeanPageList);
        dataMap.put("limit", limitAuditBeanPageList.getPaginator().getLimit());
        dataMap.put("totalPages", limitAuditBeanPageList.getPaginator().getTotalPages());
        dataMap.put("page", limitAuditBeanPageList.getPaginator().getPage());
        return Results.newSuccessResult(dataMap);
    }


    /**
     * 还款试算接口（包括砍头息）
     *
     * @param cutInterestReq
     * @return
     */
    @RequestMapping(value = {"/tryDoRepayAmount"}, method = RequestMethod.GET)
    public Result<CutInterestRes> tryDoRepayAmount(CutInterestReq cutInterestReq) {
        logger.info("砍头息还款试算接口开始执行....");
        ValidatorUtils.validateEntity(cutInterestReq);
        Assert.isTrue( !(cutInterestReq.getTerm() == 6 || cutInterestReq.getTerm() == 12),"还款期限只能为6或12期");
        CutInterestRes cutInterestRes = loanInfoService.getLoanProductRateTry(cutInterestReq);
        return Results.newSuccessResult(cutInterestRes);
    }


    /**
     * 合同签章接口
     * @param
     * @return
     * @author xierongli
     * @date 17/9/15 上午11:18
     */
    @RequestMapping(value = {"/doSignature"}, method = RequestMethod.POST)
    public Result<Boolean> doSignature(SignatureReq signatureReq) {
        logger.info("合同签章接口开始执行....");
        try{
            ValidatorUtils.validateEntity(signatureReq);
            Integer uid = getCurrentUid();
            Assert.isNull(uid,"用户ID不能为空");
            LoanApply loanApply = applyLoanService.getLoanApplyByOrderNo(uid,signatureReq.getOrderNo());
            Assert.isNull(loanApply, "订单号无效");
            final Integer loanLimitId = userBasicService.doElectronSignature(signatureReq,uid,loanApply);
            //开启线程进行保单申请
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    insuranceService.apply(loanLimitId);
                }
            });
            executorService.shutdown();

        }catch (RuntimeException e){
            throw new RRException(e.getMessage());
        }

        return Results.newSuccessResult(true);
    }

    /**
     * 还款计划列表信息获取接口
     *
     * @param pageBean
     * @return
     */
    @RequestMapping(value = {"/getRepayPlans"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getRepayPlans( String orderNo, PageBean pageBean) {
        logger.info("还款计划列表信息获取执行....");
        final Result<Object> result = new Result<Object>();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        Assert.isBlank(orderNo,"订单号不能空");
        Integer userId = getCurrentUid();
        Assert.isNull(userId,"用户ID为空");
        LoanApply loanApply = applyLoanService.getLoanApplyByOrderNo(userId,orderNo);
        Assert.isNull(loanApply,"订单号无效");

        LoanLimit loanLimit = applyLoanService.getLoanLimitByApplyId(loanApply.getId());
        Assert.isNull(loanLimit,"数据为空");
        PageBounds pageBounds = getPageBounds(pageBean);
        pageBounds.setLimit(50);
        PageList<RepayPlanInfoBean> loanRepayPlans = loanInfoService.getRepayPlans(loanLimit.getId(), pageBounds);
        Assert.isNull(loanRepayPlans,"没有还款数据");

        dataMap.put("list", loanRepayPlans);
        dataMap.put("limit", loanRepayPlans.getPaginator().getLimit());
        dataMap.put("totalPages", loanRepayPlans.getPaginator().getTotalPages());
        dataMap.put("page", loanRepayPlans.getPaginator().getPage());

        result.setSuccess(true);
        result.setMessage(ResultMessageConstant.GET_REPAY_PLAN_LIST_SUCCESS);
        result.setData(dataMap);
        logger.info(String.format("还款计划列表信息获取接口返回：%s", result.toString()));
        return result;
    }

    /**
     * 合同签章授权接口（即电子签章发送验证码接口）
     */
    @RequestMapping(value = {"/signatureAccredit"}, method = RequestMethod.GET)
    public Result<Boolean> signatureAccredit() {
        logger.info("电子签章发送验证码接口开始执行....");

        Integer uid = getCurrentUid();
        Assert.isNull(uid,"用户ID为不能空");
        try {
            userService.esOpenAccount(uid);
            userService.signatureAccredit(uid);
        } catch (CheckException e) {
            logger.error(e.getMessage(), e);
           return Results.newFailedResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Results.newFailedResult(ErrorMessageConstant.ERR_OCCURS);
        }
        return Results.newSuccessResult(true);
    }
}
