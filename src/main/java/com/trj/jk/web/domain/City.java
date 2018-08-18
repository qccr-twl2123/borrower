package com.trj.jk.web.domain;

public class City {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_city.code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_city.province_code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String provinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_city.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_city.is_hot
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private Byte isHot;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_city.search_key
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    private String searchKey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_city.code
     *
     * @return the value of jk_city.code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_city.code
     *
     * @param code the value for jk_city.code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_city.province_code
     *
     * @return the value of jk_city.province_code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_city.province_code
     *
     * @param provinceCode the value for jk_city.province_code
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_city.name
     *
     * @return the value of jk_city.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_city.name
     *
     * @param name the value for jk_city.name
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_city.is_hot
     *
     * @return the value of jk_city.is_hot
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Byte getIsHot() {
        return isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_city.is_hot
     *
     * @param isHot the value for jk_city.is_hot
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_city.search_key
     *
     * @return the value of jk_city.search_key
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getSearchKey() {
        return searchKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_city.search_key
     *
     * @param searchKey the value for jk_city.search_key
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_city
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
        City other = (City) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getProvinceCode() == null ? other.getProvinceCode() == null : this.getProvinceCode().equals(other.getProvinceCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIsHot() == null ? other.getIsHot() == null : this.getIsHot().equals(other.getIsHot()))
            && (this.getSearchKey() == null ? other.getSearchKey() == null : this.getSearchKey().equals(other.getSearchKey()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_city
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getProvinceCode() == null) ? 0 : getProvinceCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIsHot() == null) ? 0 : getIsHot().hashCode());
        result = prime * result + ((getSearchKey() == null) ? 0 : getSearchKey().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_city
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", name=").append(name);
        sb.append(", isHot=").append(isHot);
        sb.append(", searchKey=").append(searchKey);
        sb.append("]");
        return sb.toString();
    }
}