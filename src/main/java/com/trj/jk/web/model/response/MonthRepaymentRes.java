package com.trj.jk.web.model.response;


/**
 * Created by xierongli on 17/9/13.
 */
public class MonthRepaymentRes {

   /**索引*/
   private Integer term;

   /**还款金额*/
   private String termAmount;

   public Integer getTerm() {
      return term;
   }

   public void setTerm(Integer term) {
      this.term = term;
   }

   public String getTermAmount() {
      return termAmount;
   }

   public void setTermAmount(String termAmount) {
      this.termAmount = termAmount;
   }
}
