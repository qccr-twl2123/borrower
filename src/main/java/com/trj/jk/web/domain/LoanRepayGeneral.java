package com.trj.jk.web.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LoanRepayGeneral {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.prj_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer prjId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer applyLoanId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer loanLimitId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.current_period
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Byte currentPeriod;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.repay_error_status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Byte repayErrorStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.repay_day
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Byte repayDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.rest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal restAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.repayed_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal repayedAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal principal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.interest
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal interest;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.commission_charge
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal commissionCharge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal penaltyInterestAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.this_period_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal thisPeriodAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.overdue_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal overdueAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.overdue_periods
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String overduePeriods;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.repayed_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal repayedPrincipal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.rest_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal restPrincipal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.repayed_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal repayedPenaltyInterestAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.rest_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal restPenaltyInterestAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.year_rate
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private BigDecimal yearRate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_repay_general.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.id
     *
     * @return the value of jk_loan_repay_general.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.id
     *
     * @param id the value for jk_loan_repay_general.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.uid
     *
     * @return the value of jk_loan_repay_general.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.uid
     *
     * @param uid the value for jk_loan_repay_general.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.prj_id
     *
     * @return the value of jk_loan_repay_general.prj_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getPrjId() {
        return prjId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.prj_id
     *
     * @param prjId the value for jk_loan_repay_general.prj_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setPrjId(Integer prjId) {
        this.prjId = prjId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.apply_loan_id
     *
     * @return the value of jk_loan_repay_general.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getApplyLoanId() {
        return applyLoanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.apply_loan_id
     *
     * @param applyLoanId the value for jk_loan_repay_general.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setApplyLoanId(Integer applyLoanId) {
        this.applyLoanId = applyLoanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.loan_limit_id
     *
     * @return the value of jk_loan_repay_general.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getLoanLimitId() {
        return loanLimitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.loan_limit_id
     *
     * @param loanLimitId the value for jk_loan_repay_general.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setLoanLimitId(Integer loanLimitId) {
        this.loanLimitId = loanLimitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.current_period
     *
     * @return the value of jk_loan_repay_general.current_period
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Byte getCurrentPeriod() {
        return currentPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.current_period
     *
     * @param currentPeriod the value for jk_loan_repay_general.current_period
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setCurrentPeriod(Byte currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.status
     *
     * @return the value of jk_loan_repay_general.status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.status
     *
     * @param status the value for jk_loan_repay_general.status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.repay_error_status
     *
     * @return the value of jk_loan_repay_general.repay_error_status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Byte getRepayErrorStatus() {
        return repayErrorStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.repay_error_status
     *
     * @param repayErrorStatus the value for jk_loan_repay_general.repay_error_status
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRepayErrorStatus(Byte repayErrorStatus) {
        this.repayErrorStatus = repayErrorStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.repay_day
     *
     * @return the value of jk_loan_repay_general.repay_day
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Byte getRepayDay() {
        return repayDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.repay_day
     *
     * @param repayDay the value for jk_loan_repay_general.repay_day
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRepayDay(Byte repayDay) {
        this.repayDay = repayDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.amount
     *
     * @return the value of jk_loan_repay_general.amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.amount
     *
     * @param amount the value for jk_loan_repay_general.amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.rest_amount
     *
     * @return the value of jk_loan_repay_general.rest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRestAmount() {
        return restAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.rest_amount
     *
     * @param restAmount the value for jk_loan_repay_general.rest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRestAmount(BigDecimal restAmount) {
        this.restAmount = restAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.repayed_amount
     *
     * @return the value of jk_loan_repay_general.repayed_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRepayedAmount() {
        return repayedAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.repayed_amount
     *
     * @param repayedAmount the value for jk_loan_repay_general.repayed_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRepayedAmount(BigDecimal repayedAmount) {
        this.repayedAmount = repayedAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.principal
     *
     * @return the value of jk_loan_repay_general.principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getPrincipal() {
        return principal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.principal
     *
     * @param principal the value for jk_loan_repay_general.principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.interest
     *
     * @return the value of jk_loan_repay_general.interest
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.interest
     *
     * @param interest the value for jk_loan_repay_general.interest
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.commission_charge
     *
     * @return the value of jk_loan_repay_general.commission_charge
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getCommissionCharge() {
        return commissionCharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.commission_charge
     *
     * @param commissionCharge the value for jk_loan_repay_general.commission_charge
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setCommissionCharge(BigDecimal commissionCharge) {
        this.commissionCharge = commissionCharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.penalty_interest_amount
     *
     * @return the value of jk_loan_repay_general.penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getPenaltyInterestAmount() {
        return penaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.penalty_interest_amount
     *
     * @param penaltyInterestAmount the value for jk_loan_repay_general.penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setPenaltyInterestAmount(BigDecimal penaltyInterestAmount) {
        this.penaltyInterestAmount = penaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.this_period_amount
     *
     * @return the value of jk_loan_repay_general.this_period_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getThisPeriodAmount() {
        return thisPeriodAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.this_period_amount
     *
     * @param thisPeriodAmount the value for jk_loan_repay_general.this_period_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setThisPeriodAmount(BigDecimal thisPeriodAmount) {
        this.thisPeriodAmount = thisPeriodAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.overdue_amount
     *
     * @return the value of jk_loan_repay_general.overdue_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.overdue_amount
     *
     * @param overdueAmount the value for jk_loan_repay_general.overdue_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.overdue_periods
     *
     * @return the value of jk_loan_repay_general.overdue_periods
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getOverduePeriods() {
        return overduePeriods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.overdue_periods
     *
     * @param overduePeriods the value for jk_loan_repay_general.overdue_periods
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setOverduePeriods(String overduePeriods) {
        this.overduePeriods = overduePeriods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.repayed_principal
     *
     * @return the value of jk_loan_repay_general.repayed_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRepayedPrincipal() {
        return repayedPrincipal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.repayed_principal
     *
     * @param repayedPrincipal the value for jk_loan_repay_general.repayed_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRepayedPrincipal(BigDecimal repayedPrincipal) {
        this.repayedPrincipal = repayedPrincipal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.rest_principal
     *
     * @return the value of jk_loan_repay_general.rest_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRestPrincipal() {
        return restPrincipal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.rest_principal
     *
     * @param restPrincipal the value for jk_loan_repay_general.rest_principal
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRestPrincipal(BigDecimal restPrincipal) {
        this.restPrincipal = restPrincipal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.repayed_penalty_interest_amount
     *
     * @return the value of jk_loan_repay_general.repayed_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRepayedPenaltyInterestAmount() {
        return repayedPenaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.repayed_penalty_interest_amount
     *
     * @param repayedPenaltyInterestAmount the value for jk_loan_repay_general.repayed_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRepayedPenaltyInterestAmount(BigDecimal repayedPenaltyInterestAmount) {
        this.repayedPenaltyInterestAmount = repayedPenaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.rest_penalty_interest_amount
     *
     * @return the value of jk_loan_repay_general.rest_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getRestPenaltyInterestAmount() {
        return restPenaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.rest_penalty_interest_amount
     *
     * @param restPenaltyInterestAmount the value for jk_loan_repay_general.rest_penalty_interest_amount
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setRestPenaltyInterestAmount(BigDecimal restPenaltyInterestAmount) {
        this.restPenaltyInterestAmount = restPenaltyInterestAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.year_rate
     *
     * @return the value of jk_loan_repay_general.year_rate
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public BigDecimal getYearRate() {
        return yearRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.year_rate
     *
     * @param yearRate the value for jk_loan_repay_general.year_rate
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.modify_time
     *
     * @return the value of jk_loan_repay_general.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.modify_time
     *
     * @param modifyTime the value for jk_loan_repay_general.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_repay_general.create_time
     *
     * @return the value of jk_loan_repay_general.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_repay_general.create_time
     *
     * @param createTime the value for jk_loan_repay_general.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_repay_general
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LoanRepayGeneral other = (LoanRepayGeneral) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getPrjId() == null ? other.getPrjId() == null : this.getPrjId().equals(other.getPrjId()))
            && (this.getApplyLoanId() == null ? other.getApplyLoanId() == null : this.getApplyLoanId().equals(other.getApplyLoanId()))
            && (this.getLoanLimitId() == null ? other.getLoanLimitId() == null : this.getLoanLimitId().equals(other.getLoanLimitId()))
            && (this.getCurrentPeriod() == null ? other.getCurrentPeriod() == null : this.getCurrentPeriod().equals(other.getCurrentPeriod()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRepayErrorStatus() == null ? other.getRepayErrorStatus() == null : this.getRepayErrorStatus().equals(other.getRepayErrorStatus()))
            && (this.getRepayDay() == null ? other.getRepayDay() == null : this.getRepayDay().equals(other.getRepayDay()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRestAmount() == null ? other.getRestAmount() == null : this.getRestAmount().equals(other.getRestAmount()))
            && (this.getRepayedAmount() == null ? other.getRepayedAmount() == null : this.getRepayedAmount().equals(other.getRepayedAmount()))
            && (this.getPrincipal() == null ? other.getPrincipal() == null : this.getPrincipal().equals(other.getPrincipal()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getCommissionCharge() == null ? other.getCommissionCharge() == null : this.getCommissionCharge().equals(other.getCommissionCharge()))
            && (this.getPenaltyInterestAmount() == null ? other.getPenaltyInterestAmount() == null : this.getPenaltyInterestAmount().equals(other.getPenaltyInterestAmount()))
            && (this.getThisPeriodAmount() == null ? other.getThisPeriodAmount() == null : this.getThisPeriodAmount().equals(other.getThisPeriodAmount()))
            && (this.getOverdueAmount() == null ? other.getOverdueAmount() == null : this.getOverdueAmount().equals(other.getOverdueAmount()))
            && (this.getOverduePeriods() == null ? other.getOverduePeriods() == null : this.getOverduePeriods().equals(other.getOverduePeriods()))
            && (this.getRepayedPrincipal() == null ? other.getRepayedPrincipal() == null : this.getRepayedPrincipal().equals(other.getRepayedPrincipal()))
            && (this.getRestPrincipal() == null ? other.getRestPrincipal() == null : this.getRestPrincipal().equals(other.getRestPrincipal()))
            && (this.getRepayedPenaltyInterestAmount() == null ? other.getRepayedPenaltyInterestAmount() == null : this.getRepayedPenaltyInterestAmount().equals(other.getRepayedPenaltyInterestAmount()))
            && (this.getRestPenaltyInterestAmount() == null ? other.getRestPenaltyInterestAmount() == null : this.getRestPenaltyInterestAmount().equals(other.getRestPenaltyInterestAmount()))
            && (this.getYearRate() == null ? other.getYearRate() == null : this.getYearRate().equals(other.getYearRate()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_repay_general
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getPrjId() == null) ? 0 : getPrjId().hashCode());
        result = prime * result + ((getApplyLoanId() == null) ? 0 : getApplyLoanId().hashCode());
        result = prime * result + ((getLoanLimitId() == null) ? 0 : getLoanLimitId().hashCode());
        result = prime * result + ((getCurrentPeriod() == null) ? 0 : getCurrentPeriod().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRepayErrorStatus() == null) ? 0 : getRepayErrorStatus().hashCode());
        result = prime * result + ((getRepayDay() == null) ? 0 : getRepayDay().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRestAmount() == null) ? 0 : getRestAmount().hashCode());
        result = prime * result + ((getRepayedAmount() == null) ? 0 : getRepayedAmount().hashCode());
        result = prime * result + ((getPrincipal() == null) ? 0 : getPrincipal().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getCommissionCharge() == null) ? 0 : getCommissionCharge().hashCode());
        result = prime * result + ((getPenaltyInterestAmount() == null) ? 0 : getPenaltyInterestAmount().hashCode());
        result = prime * result + ((getThisPeriodAmount() == null) ? 0 : getThisPeriodAmount().hashCode());
        result = prime * result + ((getOverdueAmount() == null) ? 0 : getOverdueAmount().hashCode());
        result = prime * result + ((getOverduePeriods() == null) ? 0 : getOverduePeriods().hashCode());
        result = prime * result + ((getRepayedPrincipal() == null) ? 0 : getRepayedPrincipal().hashCode());
        result = prime * result + ((getRestPrincipal() == null) ? 0 : getRestPrincipal().hashCode());
        result = prime * result + ((getRepayedPenaltyInterestAmount() == null) ? 0 : getRepayedPenaltyInterestAmount().hashCode());
        result = prime * result + ((getRestPenaltyInterestAmount() == null) ? 0 : getRestPenaltyInterestAmount().hashCode());
        result = prime * result + ((getYearRate() == null) ? 0 : getYearRate().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_repay_general
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", prjId=").append(prjId);
        sb.append(", applyLoanId=").append(applyLoanId);
        sb.append(", loanLimitId=").append(loanLimitId);
        sb.append(", currentPeriod=").append(currentPeriod);
        sb.append(", status=").append(status);
        sb.append(", repayErrorStatus=").append(repayErrorStatus);
        sb.append(", repayDay=").append(repayDay);
        sb.append(", amount=").append(amount);
        sb.append(", restAmount=").append(restAmount);
        sb.append(", repayedAmount=").append(repayedAmount);
        sb.append(", principal=").append(principal);
        sb.append(", interest=").append(interest);
        sb.append(", commissionCharge=").append(commissionCharge);
        sb.append(", penaltyInterestAmount=").append(penaltyInterestAmount);
        sb.append(", thisPeriodAmount=").append(thisPeriodAmount);
        sb.append(", overdueAmount=").append(overdueAmount);
        sb.append(", overduePeriods=").append(overduePeriods);
        sb.append(", repayedPrincipal=").append(repayedPrincipal);
        sb.append(", restPrincipal=").append(restPrincipal);
        sb.append(", repayedPenaltyInterestAmount=").append(repayedPenaltyInterestAmount);
        sb.append(", restPenaltyInterestAmount=").append(restPenaltyInterestAmount);
        sb.append(", yearRate=").append(yearRate);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}