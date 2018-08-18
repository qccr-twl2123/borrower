package com.trj.jk.web.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.LoanProduct;
import com.trj.jk.web.domain.entity.*;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.model.request.MonthlyRepayReq;
import com.trj.jk.web.model.response.MonthlyRepayRes;
import com.trj.jk.web.model.response.MyLoanInfoRes;
import com.trj.jk.web.service.ILoanInfoService;
import com.trj.jk.web.service.LoanRepayGeneralService;
import com.trj.jk.web.util.SessionUtil;
import com.trj.jk.web.validator.ValidatorUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 融资申请相关业务controller类
 *
 * @author l46001
 */
@RestController
@RequestMapping(value = {"/applyLoan"})
public class LoanInfoController extends BaseController {

    @Resource
    private ILoanInfoService loanInfoService;
    @Autowired
    private LoanRepayGeneralService loanRepayGeneralService;

    /**
     * 我的贷款主页面信息获取接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/getMyLoansInfo"}, method = RequestMethod.GET)
    public Result<Map<String,Object>> getMyLoansInfo() {
        logger.info("我的贷款主页面信息获取接口开始执行....");
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        return Results.newSuccessResult(loanRepayGeneralService.getMyLoanInfo(uid));
    }


    /**
     * 额度批复列表获取接口
     * @param pageBean 分页对象
     * @param  isValid 0无效 1有效
     * @return
     */
    @RequestMapping(value = {"/getLimitAuditList"}, method = RequestMethod.GET)
    public Result<Object> getLimitAuditList(PageBean pageBean, Byte isValid) {
        logger.info("额度批复列表获取接口开始执行....");
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            Integer uid = (Integer) SessionUtil.getUserLogonInfo();

            if (uid != null) {
                PageBounds pageBounds = getPageBounds(pageBean);
                PageList<LimitAuditBean> loanApplys = loanInfoService.getLimitAuditList(uid, pageBounds, isValid);
                dataMap.put("list", loanApplys);
                dataMap.put("limit", loanApplys.getPaginator().getLimit());
                dataMap.put("totalPages", loanApplys.getPaginator().getTotalPages());
                dataMap.put("page", loanApplys.getPaginator().getPage());
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_LOAN_LIMIT_LIST_SUCCESS);
                result.setData(dataMap);
            } else {
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        logger.info(String.format("额度批复列表获取接口返回：%s", result.toString()));
        return result;
    }

    /**
     * 获取每月还款金额
     *
     * @return
     */
    @RequestMapping(value = {"/getMonthlyRepay"}, method = RequestMethod.GET)
    public Result<MonthlyRepayRes> getMonthlyRepay(MonthlyRepayReq monthlyRepayReq) {
        logger.info("获取每月还款金额接口开始执行....");
        ValidatorUtils.validateEntity(monthlyRepayReq);
        MonthlyRepayRes monthlyRepayRes = loanInfoService.getMonthlyRepay(monthlyRepayReq);
        return Results.newSuccessResult(monthlyRepayRes);
    }

    /**
     * 我的借款列表获取接口
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = {"/getMyLoans"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getMyLoans(PageBean bean, Byte isValid) {
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            Integer uid = (Integer) SessionUtil.getUserLogonInfo();

            logger.info("我的借款列表获取接口开始执行....");
            logger.info(String.format("参数：uid=%s,isValid=%s", uid, isValid));
            if (uid != null) {
                PageBounds pageBounds = getPageBounds(bean);
                PageList<LoanBean> loanApplys = loanInfoService.getMyLoansByUid(uid, pageBounds, isValid);
                dataMap.put("list", loanApplys);
                dataMap.put("limit", loanApplys.getPaginator().getLimit());
                dataMap.put("totalPages", loanApplys.getPaginator().getTotalPages());
                dataMap.put("page", loanApplys.getPaginator().getPage());
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_LOAN_APPLY_LIST_SUCCESS);
                result.setData(dataMap);
            } else {
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        logger.info(String.format("我的借款列表获取接口返回：%s", result.toString()));
        return result;
    }


    /**
     * 未结清借款详情获取接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/getUnFinishLoansDetails"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getUnFinishLoansDetails(Integer loanLimitId) {
        final Result<Object> result = new Result<Object>();
        try {
            logger.info("未结清借款详情获取接口开始执行....");
            logger.info(String.format("参数：loanLimitId=%s", loanLimitId));
            if (loanLimitId != null) {
                Object obj = loanInfoService.getUnFinishLoansDetails(loanLimitId);
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_LOAN_DETAILS_SUCCESS);
                result.setData(obj);
            } else {
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        logger.info(String.format("未结清借款详情获取接口返回：%s", result.toString()));
        return result;
    }

    /**
     * 已结清借款详情获取接口
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = {"/getFinishLoansDetails"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getFinishLoansDetails(Integer loanLimitId, PageBean bean) {
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        logger.info("已结清借款详情获取接口开始执行....");
        logger.info(String.format("参数：loanLimitId=%s", loanLimitId));
        try {
            if (loanLimitId != null) {
                PageBounds pageBounds = getPageBounds(bean);
                PageList<Map<String, Object>> data = loanInfoService.getFinishLoansDetails(loanLimitId, pageBounds);
                dataMap.put("list", data);
                dataMap.put("limit", data.getPaginator().getLimit());
                dataMap.put("totalPages", data.getPaginator().getTotalPages());
                dataMap.put("page", data.getPaginator().getPage());
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_LOAN_DETAILS_SUCCESS);
                result.setData(dataMap);
            } else {
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        logger.info(String.format("已结清借款详情获取接口返回：%s", result.toString()));
        return result;
    }

    /**
     * 还款计划列表信息获取接口
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = {"/getRepayPlans"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getRepayPlans(Integer loanLimitId, PageBean bean) {
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            logger.info("还款计划列表信息获取接口开始执行....");
            logger.info(String.format("参数：loanLimitId=%s", loanLimitId));
            if (loanLimitId != null) {
                PageBounds pageBounds = getPageBounds(bean);
                PageList<RepayPlanInfoBean> loanRepayPlans = loanInfoService.getRepayPlans(loanLimitId, pageBounds);
                dataMap.put("list", loanRepayPlans);
                dataMap.put("limit", loanRepayPlans.getPaginator().getLimit());
                dataMap.put("totalPages", loanRepayPlans.getPaginator().getTotalPages());
                dataMap.put("page", loanRepayPlans.getPaginator().getPage());
                result.setSuccess(true);
                result.setMessage(ResultMessageConstant.GET_REPAY_PLAN_LIST_SUCCESS);
                result.setData(dataMap);
            } else {
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        logger.info(String.format("还款计划列表信息获取接口返回：%s", result.toString()));
        return result;
    }

    /**
     * 根据用户id获取还款计划
     *
     * @param status,bean
     * @return
     */
    @RequestMapping(value = {"/getLoanRepayPlansByUid"}, method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> getLoanRepayPlansByUid(Integer status, PageBean bean) {
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();

        PageBounds pageBounds = getPageBounds(bean);
        PageList<LoanRepayPlanBean> datalist = (PageList<LoanRepayPlanBean>) loanInfoService.getLoanRepayPlansByUid(uid, status, pageBounds);
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("list", datalist);
        dataMap.put("limit", datalist.getPaginator().getLimit());
        dataMap.put("totalPages", datalist.getPaginator().getTotalPages());
        dataMap.put("page", datalist.getPaginator().getPage());
        result.setData(dataMap);
        result.setSuccess(true);
        result.setMessage("获取还款计划成功");

        return result;
    }


    /**
     * 根据用户和时间获取还款计划
     *
     * @param status,bean
     * @return
     */
    @RequestMapping(value = {"/getLoanRepayPlansByDate"}, method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> getLoanRepayPlansByDate(Integer status, String date, PageBean bean) {
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();

        PageBounds pageBounds = getPageBounds(bean);
        PageList<LoanRepayPlanBean> datalist = (PageList<LoanRepayPlanBean>) loanInfoService.getLoanRepayPlansByDate(uid, status, date, pageBounds);
        final Result<Object> result = new Result<Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("list", datalist);
        dataMap.put("limit", datalist.getPaginator().getLimit());
        dataMap.put("totalPages", datalist.getPaginator().getTotalPages());
        dataMap.put("page", datalist.getPaginator().getPage());
        result.setData(dataMap);
        result.setSuccess(true);
        result.setMessage("获取还款计划成功");
        return result;
    }

    /**
     * 获取最近借款成功的申请信息滚动展示
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = {"/getLoanApplyScrollingMessage"}, method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> getLoanApplyScrollingMessage(PageBean bean) {
        List<String> msgList = new ArrayList<String>();
        PageBounds pageBounds = getPageBounds(bean);
        PageList<LoanLimit> datalist = (PageList<LoanLimit>) loanInfoService.getLoanApplyScrollingMessage(pageBounds);
        for (LoanLimit loanLimit : datalist) {
            if (loanLimit.getMobile() == null || loanLimit.getUseAmount() == null) {
                continue;
            }
            String msg = loanLimit.getMobile().substring(0,3)+"****"+loanLimit.getMobile().substring(7,11) +"用户成功借款"+loanLimit.getUseAmount().add(new BigDecimal(80000))+"元";
            msgList.add(msg);
        }
        final Result<Object> result = new Result<Object>();
        result.setData(msgList);
        result.setSuccess(true);
        result.setMessage("获取最近借款成功的申请信息滚动展示成功");
        return result;
    }


}
