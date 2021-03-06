package com.trj.jk.web.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.product_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.goods_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.thumb
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String thumb;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.market_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private BigDecimal marketPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.sale_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private BigDecimal salePrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.avg_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private BigDecimal avgPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.advertise_title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String advertiseTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.periods
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte periods;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.periods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String periodsInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.goods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String goodsInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.sale_service
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String saleService;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.notice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String notice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String createPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String updatePerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_goods.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.id
     *
     * @return the value of jk_goods.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.id
     *
     * @param id the value for jk_goods.id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.product_id
     *
     * @return the value of jk_goods.product_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.product_id
     *
     * @param productId the value for jk_goods.product_id
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.title
     *
     * @return the value of jk_goods.title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.title
     *
     * @param title the value for jk_goods.title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.goods_name
     *
     * @return the value of jk_goods.goods_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.goods_name
     *
     * @param goodsName the value for jk_goods.goods_name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.thumb
     *
     * @return the value of jk_goods.thumb
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.thumb
     *
     * @param thumb the value for jk_goods.thumb
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.market_price
     *
     * @return the value of jk_goods.market_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.market_price
     *
     * @param marketPrice the value for jk_goods.market_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.sale_price
     *
     * @return the value of jk_goods.sale_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.sale_price
     *
     * @param salePrice the value for jk_goods.sale_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.avg_price
     *
     * @return the value of jk_goods.avg_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.avg_price
     *
     * @param avgPrice the value for jk_goods.avg_price
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.advertise_title
     *
     * @return the value of jk_goods.advertise_title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getAdvertiseTitle() {
        return advertiseTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.advertise_title
     *
     * @param advertiseTitle the value for jk_goods.advertise_title
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setAdvertiseTitle(String advertiseTitle) {
        this.advertiseTitle = advertiseTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.periods
     *
     * @return the value of jk_goods.periods
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getPeriods() {
        return periods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.periods
     *
     * @param periods the value for jk_goods.periods
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setPeriods(Byte periods) {
        this.periods = periods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.periods_info
     *
     * @return the value of jk_goods.periods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getPeriodsInfo() {
        return periodsInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.periods_info
     *
     * @param periodsInfo the value for jk_goods.periods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setPeriodsInfo(String periodsInfo) {
        this.periodsInfo = periodsInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.goods_info
     *
     * @return the value of jk_goods.goods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getGoodsInfo() {
        return goodsInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.goods_info
     *
     * @param goodsInfo the value for jk_goods.goods_info
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.sale_service
     *
     * @return the value of jk_goods.sale_service
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getSaleService() {
        return saleService;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.sale_service
     *
     * @param saleService the value for jk_goods.sale_service
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setSaleService(String saleService) {
        this.saleService = saleService;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.notice
     *
     * @return the value of jk_goods.notice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getNotice() {
        return notice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.notice
     *
     * @param notice the value for jk_goods.notice
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.status
     *
     * @return the value of jk_goods.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.status
     *
     * @param status the value for jk_goods.status
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.create_person
     *
     * @return the value of jk_goods.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.create_person
     *
     * @param createPerson the value for jk_goods.create_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.update_person
     *
     * @return the value of jk_goods.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.update_person
     *
     * @param updatePerson the value for jk_goods.update_person
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.modify_time
     *
     * @return the value of jk_goods.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.modify_time
     *
     * @param modifyTime the value for jk_goods.modify_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_goods.create_time
     *
     * @return the value of jk_goods.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_goods.create_time
     *
     * @param createTime the value for jk_goods.create_time
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods
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
        Goods other = (Goods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getThumb() == null ? other.getThumb() == null : this.getThumb().equals(other.getThumb()))
            && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
            && (this.getSalePrice() == null ? other.getSalePrice() == null : this.getSalePrice().equals(other.getSalePrice()))
            && (this.getAvgPrice() == null ? other.getAvgPrice() == null : this.getAvgPrice().equals(other.getAvgPrice()))
            && (this.getAdvertiseTitle() == null ? other.getAdvertiseTitle() == null : this.getAdvertiseTitle().equals(other.getAdvertiseTitle()))
            && (this.getPeriods() == null ? other.getPeriods() == null : this.getPeriods().equals(other.getPeriods()))
            && (this.getPeriodsInfo() == null ? other.getPeriodsInfo() == null : this.getPeriodsInfo().equals(other.getPeriodsInfo()))
            && (this.getGoodsInfo() == null ? other.getGoodsInfo() == null : this.getGoodsInfo().equals(other.getGoodsInfo()))
            && (this.getSaleService() == null ? other.getSaleService() == null : this.getSaleService().equals(other.getSaleService()))
            && (this.getNotice() == null ? other.getNotice() == null : this.getNotice().equals(other.getNotice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
            && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getThumb() == null) ? 0 : getThumb().hashCode());
        result = prime * result + ((getMarketPrice() == null) ? 0 : getMarketPrice().hashCode());
        result = prime * result + ((getSalePrice() == null) ? 0 : getSalePrice().hashCode());
        result = prime * result + ((getAvgPrice() == null) ? 0 : getAvgPrice().hashCode());
        result = prime * result + ((getAdvertiseTitle() == null) ? 0 : getAdvertiseTitle().hashCode());
        result = prime * result + ((getPeriods() == null) ? 0 : getPeriods().hashCode());
        result = prime * result + ((getPeriodsInfo() == null) ? 0 : getPeriodsInfo().hashCode());
        result = prime * result + ((getGoodsInfo() == null) ? 0 : getGoodsInfo().hashCode());
        result = prime * result + ((getSaleService() == null) ? 0 : getSaleService().hashCode());
        result = prime * result + ((getNotice() == null) ? 0 : getNotice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_goods
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
        sb.append(", productId=").append(productId);
        sb.append(", title=").append(title);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", thumb=").append(thumb);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", avgPrice=").append(avgPrice);
        sb.append(", advertiseTitle=").append(advertiseTitle);
        sb.append(", periods=").append(periods);
        sb.append(", periodsInfo=").append(periodsInfo);
        sb.append(", goodsInfo=").append(goodsInfo);
        sb.append(", saleService=").append(saleService);
        sb.append(", notice=").append(notice);
        sb.append(", status=").append(status);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}