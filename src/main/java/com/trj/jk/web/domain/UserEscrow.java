package com.trj.jk.web.domain;

import java.util.Date;

public class UserEscrow {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.uid
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.name
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.mobile
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.identity_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String identityType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.identity_id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String identityId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.status
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.bank_card_no
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String bankCardNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.remark
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.client_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Byte clientType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.create_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String createPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.update_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private String updatePerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.modify_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_escrow.create_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.id
     *
     * @return the value of jk_user_escrow.id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.id
     *
     * @param id the value for jk_user_escrow.id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.uid
     *
     * @return the value of jk_user_escrow.uid
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.uid
     *
     * @param uid the value for jk_user_escrow.uid
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.name
     *
     * @return the value of jk_user_escrow.name
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.name
     *
     * @param name the value for jk_user_escrow.name
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.mobile
     *
     * @return the value of jk_user_escrow.mobile
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.mobile
     *
     * @param mobile the value for jk_user_escrow.mobile
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.identity_type
     *
     * @return the value of jk_user_escrow.identity_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.identity_type
     *
     * @param identityType the value for jk_user_escrow.identity_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.identity_id
     *
     * @return the value of jk_user_escrow.identity_id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getIdentityId() {
        return identityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.identity_id
     *
     * @param identityId the value for jk_user_escrow.identity_id
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.type
     *
     * @return the value of jk_user_escrow.type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.type
     *
     * @param type the value for jk_user_escrow.type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.status
     *
     * @return the value of jk_user_escrow.status
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.status
     *
     * @param status the value for jk_user_escrow.status
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.bank_card_no
     *
     * @return the value of jk_user_escrow.bank_card_no
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.bank_card_no
     *
     * @param bankCardNo the value for jk_user_escrow.bank_card_no
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.remark
     *
     * @return the value of jk_user_escrow.remark
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.remark
     *
     * @param remark the value for jk_user_escrow.remark
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.client_type
     *
     * @return the value of jk_user_escrow.client_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Byte getClientType() {
        return clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.client_type
     *
     * @param clientType the value for jk_user_escrow.client_type
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.create_person
     *
     * @return the value of jk_user_escrow.create_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.create_person
     *
     * @param createPerson the value for jk_user_escrow.create_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.update_person
     *
     * @return the value of jk_user_escrow.update_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.update_person
     *
     * @param updatePerson the value for jk_user_escrow.update_person
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.modify_time
     *
     * @return the value of jk_user_escrow.modify_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.modify_time
     *
     * @param modifyTime the value for jk_user_escrow.modify_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_escrow.create_time
     *
     * @return the value of jk_user_escrow.create_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_escrow.create_time
     *
     * @param createTime the value for jk_user_escrow.create_time
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_escrow
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
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
        UserEscrow other = (UserEscrow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getIdentityType() == null ? other.getIdentityType() == null : this.getIdentityType().equals(other.getIdentityType()))
            && (this.getIdentityId() == null ? other.getIdentityId() == null : this.getIdentityId().equals(other.getIdentityId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBankCardNo() == null ? other.getBankCardNo() == null : this.getBankCardNo().equals(other.getBankCardNo()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getClientType() == null ? other.getClientType() == null : this.getClientType().equals(other.getClientType()))
            && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
            && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_escrow
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getIdentityType() == null) ? 0 : getIdentityType().hashCode());
        result = prime * result + ((getIdentityId() == null) ? 0 : getIdentityId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBankCardNo() == null) ? 0 : getBankCardNo().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getClientType() == null) ? 0 : getClientType().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_escrow
     *
     * @mbg.generated Tue Nov 28 15:51:55 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", identityType=").append(identityType);
        sb.append(", identityId=").append(identityId);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", bankCardNo=").append(bankCardNo);
        sb.append(", remark=").append(remark);
        sb.append(", clientType=").append(clientType);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}