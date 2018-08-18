package com.trj.jk.web.domain;

import java.util.Date;

public class DeliverAddress {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.geocode_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer geocodeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.type
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.address
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.mobile
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String createPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String updatePerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_deliver_address.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.id
     *
     * @return the value of jk_deliver_address.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.id
     *
     * @param id the value for jk_deliver_address.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.geocode_id
     *
     * @return the value of jk_deliver_address.geocode_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getGeocodeId() {
        return geocodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.geocode_id
     *
     * @param geocodeId the value for jk_deliver_address.geocode_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setGeocodeId(Integer geocodeId) {
        this.geocodeId = geocodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.uid
     *
     * @return the value of jk_deliver_address.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.uid
     *
     * @param uid the value for jk_deliver_address.uid
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.type
     *
     * @return the value of jk_deliver_address.type
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.type
     *
     * @param type the value for jk_deliver_address.type
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.address
     *
     * @return the value of jk_deliver_address.address
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.address
     *
     * @param address the value for jk_deliver_address.address
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.name
     *
     * @return the value of jk_deliver_address.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.name
     *
     * @param name the value for jk_deliver_address.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.mobile
     *
     * @return the value of jk_deliver_address.mobile
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.mobile
     *
     * @param mobile the value for jk_deliver_address.mobile
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.create_person
     *
     * @return the value of jk_deliver_address.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.create_person
     *
     * @param createPerson the value for jk_deliver_address.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.create_time
     *
     * @return the value of jk_deliver_address.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.create_time
     *
     * @param createTime the value for jk_deliver_address.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.update_person
     *
     * @return the value of jk_deliver_address.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.update_person
     *
     * @param updatePerson the value for jk_deliver_address.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_deliver_address.modify_time
     *
     * @return the value of jk_deliver_address.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_deliver_address.modify_time
     *
     * @param modifyTime the value for jk_deliver_address.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_deliver_address
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
        DeliverAddress other = (DeliverAddress) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGeocodeId() == null ? other.getGeocodeId() == null : this.getGeocodeId().equals(other.getGeocodeId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_deliver_address
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGeocodeId() == null) ? 0 : getGeocodeId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_deliver_address
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
        sb.append(", geocodeId=").append(geocodeId);
        sb.append(", uid=").append(uid);
        sb.append(", type=").append(type);
        sb.append(", address=").append(address);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createTime=").append(createTime);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append("]");
        return sb.toString();
    }
}