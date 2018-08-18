package com.trj.jk.web.domain;

public class BankBranch {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_bank_branch.code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_bank_branch.bank_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String bankCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_bank_branch.city_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String cityCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_bank_branch.pbc_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String pbcNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jk_bank_branch.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_bank_branch.code
     *
     * @return the value of jk_bank_branch.code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_bank_branch.code
     *
     * @param code the value for jk_bank_branch.code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_bank_branch.bank_code
     *
     * @return the value of jk_bank_branch.bank_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_bank_branch.bank_code
     *
     * @param bankCode the value for jk_bank_branch.bank_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_bank_branch.city_code
     *
     * @return the value of jk_bank_branch.city_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_bank_branch.city_code
     *
     * @param cityCode the value for jk_bank_branch.city_code
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_bank_branch.pbc_no
     *
     * @return the value of jk_bank_branch.pbc_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getPbcNo() {
        return pbcNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_bank_branch.pbc_no
     *
     * @param pbcNo the value for jk_bank_branch.pbc_no
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setPbcNo(String pbcNo) {
        this.pbcNo = pbcNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jk_bank_branch.name
     *
     * @return the value of jk_bank_branch.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jk_bank_branch.name
     *
     * @param name the value for jk_bank_branch.name
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_bank_branch
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
        BankBranch other = (BankBranch) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getBankCode() == null ? other.getBankCode() == null : this.getBankCode().equals(other.getBankCode()))
            && (this.getCityCode() == null ? other.getCityCode() == null : this.getCityCode().equals(other.getCityCode()))
            && (this.getPbcNo() == null ? other.getPbcNo() == null : this.getPbcNo().equals(other.getPbcNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_bank_branch
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getBankCode() == null) ? 0 : getBankCode().hashCode());
        result = prime * result + ((getCityCode() == null) ? 0 : getCityCode().hashCode());
        result = prime * result + ((getPbcNo() == null) ? 0 : getPbcNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_bank_branch
     *
     * @mbg.generated Tue Jun 13 16:01:18 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", bankCode=").append(bankCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", pbcNo=").append(pbcNo);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}