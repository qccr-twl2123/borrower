package com.trj.jk.web.domain;

import java.util.Date;

public class UserEcommence {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.account
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_user_ecommence.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.id
     *
     * @return the value of jk_user_ecommence.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.id
     *
     * @param id the value for jk_user_ecommence.id
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.uid
     *
     * @return the value of jk_user_ecommence.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.uid
     *
     * @param uid the value for jk_user_ecommence.uid
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.name
     *
     * @return the value of jk_user_ecommence.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.name
     *
     * @param name the value for jk_user_ecommence.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.account
     *
     * @return the value of jk_user_ecommence.account
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.account
     *
     * @param account the value for jk_user_ecommence.account
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.modify_time
     *
     * @return the value of jk_user_ecommence.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.modify_time
     *
     * @param modifyTime the value for jk_user_ecommence.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_user_ecommence.create_time
     *
     * @return the value of jk_user_ecommence.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_user_ecommence.create_time
     *
     * @param createTime the value for jk_user_ecommence.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_ecommence
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
        UserEcommence other = (UserEcommence) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_ecommence
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_ecommence
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
        sb.append(", name=").append(name);
        sb.append(", account=").append(account);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}