package com.trj.jk.web.enums;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 砍头息利率
 * Created by xierongli on 17/9/13.
 */
public enum CutRateEnum {
    RATE_6(6,3),
    RATE_12(12,5),
    RATE_18(18,7),
    RATE_24(24,8);

    private Integer term;
    private Integer rate;

    CutRateEnum(Integer term, Integer rate) {
        this.term = term;
        this.rate = rate;
    }

    public static BigDecimal getRateByTerm(Integer term){
        for(CutRateEnum cutRateEnum : CutRateEnum.values()){
            if(term != null && term == cutRateEnum.getTerm()){
                return new BigDecimal(cutRateEnum.getRate()).divide(new BigDecimal(100),2, RoundingMode.HALF_UP);
            }
        }
        return null;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
