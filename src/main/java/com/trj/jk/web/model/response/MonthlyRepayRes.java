package com.trj.jk.web.model.response;


/**
 * Created by xierongli on 17/9/28.
 */
public class MonthlyRepayRes {

    private String monthlyRepayAmount;
    private String yearRate;


    public String getMonthlyRepayAmount() {
        return monthlyRepayAmount;
    }

    public void setMonthlyRepayAmount(String monthlyRepayAmount) {
        this.monthlyRepayAmount = monthlyRepayAmount;
    }

    public String getYearRate() {
        return yearRate;
    }

    public void setYearRate(String yearRate) {
        this.yearRate = yearRate;
    }
}
