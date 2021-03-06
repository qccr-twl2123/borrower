package com.trj.jk.web.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.user_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer loanLimitId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer applyLoanId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.order_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.amount
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.user_bank_card_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer userBankCardId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.deliver_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer deliverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.is_invoice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte isInvoice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.saler_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String salerCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String createPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String updatePerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_order.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.id
     *
     * @return the value of jk_order.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.id
     *
     * @param id the value for jk_order.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.uid
     *
     * @return the value of jk_order.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.uid
     *
     * @param uid the value for jk_order.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.user_name
     *
     * @return the value of jk_order.user_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.user_name
     *
     * @param userName the value for jk_order.user_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.loan_limit_id
     *
     * @return the value of jk_order.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getLoanLimitId() {
        return loanLimitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.loan_limit_id
     *
     * @param loanLimitId the value for jk_order.loan_limit_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setLoanLimitId(Integer loanLimitId) {
        this.loanLimitId = loanLimitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.apply_loan_id
     *
     * @return the value of jk_order.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getApplyLoanId() {
        return applyLoanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.apply_loan_id
     *
     * @param applyLoanId the value for jk_order.apply_loan_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setApplyLoanId(Integer applyLoanId) {
        this.applyLoanId = applyLoanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.order_no
     *
     * @return the value of jk_order.order_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.order_no
     *
     * @param orderNo the value for jk_order.order_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.amount
     *
     * @return the value of jk_order.amount
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.amount
     *
     * @param amount the value for jk_order.amount
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.status
     *
     * @return the value of jk_order.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.status
     *
     * @param status the value for jk_order.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.user_bank_card_id
     *
     * @return the value of jk_order.user_bank_card_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getUserBankCardId() {
        return userBankCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.user_bank_card_id
     *
     * @param userBankCardId the value for jk_order.user_bank_card_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUserBankCardId(Integer userBankCardId) {
        this.userBankCardId = userBankCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.deliver_id
     *
     * @return the value of jk_order.deliver_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getDeliverId() {
        return deliverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.deliver_id
     *
     * @param deliverId the value for jk_order.deliver_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.is_invoice
     *
     * @return the value of jk_order.is_invoice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getIsInvoice() {
        return isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.is_invoice
     *
     * @param isInvoice the value for jk_order.is_invoice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setIsInvoice(Byte isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.saler_code
     *
     * @return the value of jk_order.saler_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getSalerCode() {
        return salerCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.saler_code
     *
     * @param salerCode the value for jk_order.saler_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setSalerCode(String salerCode) {
        this.salerCode = salerCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.create_person
     *
     * @return the value of jk_order.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.create_person
     *
     * @param createPerson the value for jk_order.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.update_person
     *
     * @return the value of jk_order.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.update_person
     *
     * @param updatePerson the value for jk_order.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.modify_time
     *
     * @return the value of jk_order.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.modify_time
     *
     * @param modifyTime the value for jk_order.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_order.create_time
     *
     * @return the value of jk_order.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_order.create_time
     *
     * @param createTime the value for jk_order.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_order
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getLoanLimitId() == null ? other.getLoanLimitId() == null : this.getLoanLimitId().equals(other.getLoanLimitId()))
            && (this.getApplyLoanId() == null ? other.getApplyLoanId() == null : this.getApplyLoanId().equals(other.getApplyLoanId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserBankCardId() == null ? other.getUserBankCardId() == null : this.getUserBankCardId().equals(other.getUserBankCardId()))
            && (this.getDeliverId() == null ? other.getDeliverId() == null : this.getDeliverId().equals(other.getDeliverId()))
            && (this.getIsInvoice() == null ? other.getIsInvoice() == null : this.getIsInvoice().equals(other.getIsInvoice()))
            && (this.getSalerCode() == null ? other.getSalerCode() == null : this.getSalerCode().equals(other.getSalerCode()))
            && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
            && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_order
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getLoanLimitId() == null) ? 0 : getLoanLimitId().hashCode());
        result = prime * result + ((getApplyLoanId() == null) ? 0 : getApplyLoanId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserBankCardId() == null) ? 0 : getUserBankCardId().hashCode());
        result = prime * result + ((getDeliverId() == null) ? 0 : getDeliverId().hashCode());
        result = prime * result + ((getIsInvoice() == null) ? 0 : getIsInvoice().hashCode());
        result = prime * result + ((getSalerCode() == null) ? 0 : getSalerCode().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_order
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", userName=").append(userName);
        sb.append(", loanLimitId=").append(loanLimitId);
        sb.append(", applyLoanId=").append(applyLoanId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", userBankCardId=").append(userBankCardId);
        sb.append(", deliverId=").append(deliverId);
        sb.append(", isInvoice=").append(isInvoice);
        sb.append(", salerCode=").append(salerCode);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}