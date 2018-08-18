package com.trj.jk.web.domain;

import java.util.Date;

public class GoodsImg {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.goods_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.img_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String imgName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.img_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.redirct_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String redirctUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String createPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String updatePerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods_img.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.id
     *
     * @return the value of jk_goods_img.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.id
     *
     * @param id the value for jk_goods_img.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.goods_id
     *
     * @return the value of jk_goods_img.goods_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.goods_id
     *
     * @param goodsId the value for jk_goods_img.goods_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.img_name
     *
     * @return the value of jk_goods_img.img_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.img_name
     *
     * @param imgName the value for jk_goods_img.img_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.img_url
     *
     * @return the value of jk_goods_img.img_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.img_url
     *
     * @param imgUrl the value for jk_goods_img.img_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.redirct_url
     *
     * @return the value of jk_goods_img.redirct_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getRedirctUrl() {
        return redirctUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.redirct_url
     *
     * @param redirctUrl the value for jk_goods_img.redirct_url
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setRedirctUrl(String redirctUrl) {
        this.redirctUrl = redirctUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.status
     *
     * @return the value of jk_goods_img.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.status
     *
     * @param status the value for jk_goods_img.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.create_person
     *
     * @return the value of jk_goods_img.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.create_person
     *
     * @param createPerson the value for jk_goods_img.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.update_person
     *
     * @return the value of jk_goods_img.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.update_person
     *
     * @param updatePerson the value for jk_goods_img.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.modify_time
     *
     * @return the value of jk_goods_img.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.modify_time
     *
     * @param modifyTime the value for jk_goods_img.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods_img.create_time
     *
     * @return the value of jk_goods_img.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods_img.create_time
     *
     * @param createTime the value for jk_goods_img.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods_img
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
        GoodsImg other = (GoodsImg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getImgName() == null ? other.getImgName() == null : this.getImgName().equals(other.getImgName()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getRedirctUrl() == null ? other.getRedirctUrl() == null : this.getRedirctUrl().equals(other.getRedirctUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
            && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods_img
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getImgName() == null) ? 0 : getImgName().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getRedirctUrl() == null) ? 0 : getRedirctUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods_img
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
        sb.append(", goodsId=").append(goodsId);
        sb.append(", imgName=").append(imgName);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", redirctUrl=").append(redirctUrl);
        sb.append(", status=").append(status);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}