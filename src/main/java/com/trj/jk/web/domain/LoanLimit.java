package com.trj.jk.web.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LoanLimit {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.uid
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.loan_apply_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Integer loanApplyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.loan_receipt_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String loanReceiptId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.product_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.name
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.mobile
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.bank_card_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Integer bankCardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.use_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private BigDecimal useAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.cut_charge
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private BigDecimal cutCharge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.repay_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String repayType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.term
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String term;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.term_unit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String termUnit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.expect_repay_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private BigDecimal expectRepayAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.repay_periods
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Byte repayPeriods;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.interest
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private BigDecimal interest;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.status
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.tenant
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String tenant;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.publish_contract_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String publishContractId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.is_contract_publish
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Byte isContractPublish;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.modify_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.create_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_loan_limit.loan_use_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    private String loanUseType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.id
     *
     * @return the value of jk_loan_limit.id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.id
     *
     * @param id the value for jk_loan_limit.id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.uid
     *
     * @return the value of jk_loan_limit.uid
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.uid
     *
     * @param uid the value for jk_loan_limit.uid
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.loan_apply_id
     *
     * @return the value of jk_loan_limit.loan_apply_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.loan_apply_id
     *
     * @param loanApplyId the value for jk_loan_limit.loan_apply_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.loan_receipt_id
     *
     * @return the value of jk_loan_limit.loan_receipt_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getLoanReceiptId() {
        return loanReceiptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.loan_receipt_id
     *
     * @param loanReceiptId the value for jk_loan_limit.loan_receipt_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setLoanReceiptId(String loanReceiptId) {
        this.loanReceiptId = loanReceiptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.product_id
     *
     * @return the value of jk_loan_limit.product_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.product_id
     *
     * @param productId the value for jk_loan_limit.product_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.name
     *
     * @return the value of jk_loan_limit.name
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.name
     *
     * @param name the value for jk_loan_limit.name
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.mobile
     *
     * @return the value of jk_loan_limit.mobile
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.mobile
     *
     * @param mobile the value for jk_loan_limit.mobile
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.bank_card_id
     *
     * @return the value of jk_loan_limit.bank_card_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Integer getBankCardId() {
        return bankCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.bank_card_id
     *
     * @param bankCardId the value for jk_loan_limit.bank_card_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.use_amount
     *
     * @return the value of jk_loan_limit.use_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public BigDecimal getUseAmount() {
        return useAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.use_amount
     *
     * @param useAmount the value for jk_loan_limit.use_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.cut_charge
     *
     * @return the value of jk_loan_limit.cut_charge
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public BigDecimal getCutCharge() {
        return cutCharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.cut_charge
     *
     * @param cutCharge the value for jk_loan_limit.cut_charge
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setCutCharge(BigDecimal cutCharge) {
        this.cutCharge = cutCharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.repay_type
     *
     * @return the value of jk_loan_limit.repay_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getRepayType() {
        return repayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.repay_type
     *
     * @param repayType the value for jk_loan_limit.repay_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.term
     *
     * @return the value of jk_loan_limit.term
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getTerm() {
        return term;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.term
     *
     * @param term the value for jk_loan_limit.term
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.term_unit
     *
     * @return the value of jk_loan_limit.term_unit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.term_unit
     *
     * @param termUnit the value for jk_loan_limit.term_unit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.expect_repay_amount
     *
     * @return the value of jk_loan_limit.expect_repay_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public BigDecimal getExpectRepayAmount() {
        return expectRepayAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.expect_repay_amount
     *
     * @param expectRepayAmount the value for jk_loan_limit.expect_repay_amount
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setExpectRepayAmount(BigDecimal expectRepayAmount) {
        this.expectRepayAmount = expectRepayAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.repay_periods
     *
     * @return the value of jk_loan_limit.repay_periods
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Byte getRepayPeriods() {
        return repayPeriods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.repay_periods
     *
     * @param repayPeriods the value for jk_loan_limit.repay_periods
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setRepayPeriods(Byte repayPeriods) {
        this.repayPeriods = repayPeriods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.interest
     *
     * @return the value of jk_loan_limit.interest
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.interest
     *
     * @param interest the value for jk_loan_limit.interest
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.status
     *
     * @return the value of jk_loan_limit.status
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.status
     *
     * @param status the value for jk_loan_limit.status
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.tenant
     *
     * @return the value of jk_loan_limit.tenant
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getTenant() {
        return tenant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.tenant
     *
     * @param tenant the value for jk_loan_limit.tenant
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.publish_contract_id
     *
     * @return the value of jk_loan_limit.publish_contract_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getPublishContractId() {
        return publishContractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.publish_contract_id
     *
     * @param publishContractId the value for jk_loan_limit.publish_contract_id
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setPublishContractId(String publishContractId) {
        this.publishContractId = publishContractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.is_contract_publish
     *
     * @return the value of jk_loan_limit.is_contract_publish
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Byte getIsContractPublish() {
        return isContractPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.is_contract_publish
     *
     * @param isContractPublish the value for jk_loan_limit.is_contract_publish
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setIsContractPublish(Byte isContractPublish) {
        this.isContractPublish = isContractPublish;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.modify_time
     *
     * @return the value of jk_loan_limit.modify_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.modify_time
     *
     * @param modifyTime the value for jk_loan_limit.modify_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.create_time
     *
     * @return the value of jk_loan_limit.create_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.create_time
     *
     * @param createTime the value for jk_loan_limit.create_time
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.type
     *
     * @return the value of jk_loan_limit.type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.type
     *
     * @param type the value for jk_loan_limit.type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_loan_limit.loan_use_type
     *
     * @return the value of jk_loan_limit.loan_use_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public String getLoanUseType() {
        return loanUseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_loan_limit.loan_use_type
     *
     * @param loanUseType the value for jk_loan_limit.loan_use_type
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    public void setLoanUseType(String loanUseType) {
        this.loanUseType = loanUseType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_limit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
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
        LoanLimit other = (LoanLimit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getLoanApplyId() == null ? other.getLoanApplyId() == null : this.getLoanApplyId().equals(other.getLoanApplyId()))
            && (this.getLoanReceiptId() == null ? other.getLoanReceiptId() == null : this.getLoanReceiptId().equals(other.getLoanReceiptId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getBankCardId() == null ? other.getBankCardId() == null : this.getBankCardId().equals(other.getBankCardId()))
            && (this.getUseAmount() == null ? other.getUseAmount() == null : this.getUseAmount().equals(other.getUseAmount()))
            && (this.getCutCharge() == null ? other.getCutCharge() == null : this.getCutCharge().equals(other.getCutCharge()))
            && (this.getRepayType() == null ? other.getRepayType() == null : this.getRepayType().equals(other.getRepayType()))
            && (this.getTerm() == null ? other.getTerm() == null : this.getTerm().equals(other.getTerm()))
            && (this.getTermUnit() == null ? other.getTermUnit() == null : this.getTermUnit().equals(other.getTermUnit()))
            && (this.getExpectRepayAmount() == null ? other.getExpectRepayAmount() == null : this.getExpectRepayAmount().equals(other.getExpectRepayAmount()))
            && (this.getRepayPeriods() == null ? other.getRepayPeriods() == null : this.getRepayPeriods().equals(other.getRepayPeriods()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTenant() == null ? other.getTenant() == null : this.getTenant().equals(other.getTenant()))
            && (this.getPublishContractId() == null ? other.getPublishContractId() == null : this.getPublishContractId().equals(other.getPublishContractId()))
            && (this.getIsContractPublish() == null ? other.getIsContractPublish() == null : this.getIsContractPublish().equals(other.getIsContractPublish()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getLoanUseType() == null ? other.getLoanUseType() == null : this.getLoanUseType().equals(other.getLoanUseType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_limit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getLoanApplyId() == null) ? 0 : getLoanApplyId().hashCode());
        result = prime * result + ((getLoanReceiptId() == null) ? 0 : getLoanReceiptId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getBankCardId() == null) ? 0 : getBankCardId().hashCode());
        result = prime * result + ((getUseAmount() == null) ? 0 : getUseAmount().hashCode());
        result = prime * result + ((getCutCharge() == null) ? 0 : getCutCharge().hashCode());
        result = prime * result + ((getRepayType() == null) ? 0 : getRepayType().hashCode());
        result = prime * result + ((getTerm() == null) ? 0 : getTerm().hashCode());
        result = prime * result + ((getTermUnit() == null) ? 0 : getTermUnit().hashCode());
        result = prime * result + ((getExpectRepayAmount() == null) ? 0 : getExpectRepayAmount().hashCode());
        result = prime * result + ((getRepayPeriods() == null) ? 0 : getRepayPeriods().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTenant() == null) ? 0 : getTenant().hashCode());
        result = prime * result + ((getPublishContractId() == null) ? 0 : getPublishContractId().hashCode());
        result = prime * result + ((getIsContractPublish() == null) ? 0 : getIsContractPublish().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLoanUseType() == null) ? 0 : getLoanUseType().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_loan_limit
     *
     * @mbg.generated Thu May 17 13:39:33 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", loanApplyId=").append(loanApplyId);
        sb.append(", loanReceiptId=").append(loanReceiptId);
        sb.append(", productId=").append(productId);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", bankCardId=").append(bankCardId);
        sb.append(", useAmount=").append(useAmount);
        sb.append(", cutCharge=").append(cutCharge);
        sb.append(", repayType=").append(repayType);
        sb.append(", term=").append(term);
        sb.append(", termUnit=").append(termUnit);
        sb.append(", expectRepayAmount=").append(expectRepayAmount);
        sb.append(", repayPeriods=").append(repayPeriods);
        sb.append(", interest=").append(interest);
        sb.append(", status=").append(status);
        sb.append(", tenant=").append(tenant);
        sb.append(", publishContractId=").append(publishContractId);
        sb.append(", isContractPublish=").append(isContractPublish);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", type=").append(type);
        sb.append(", loanUseType=").append(loanUseType);
        sb.append("]");
        return sb.toString();
    }
}