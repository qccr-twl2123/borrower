package com.trj.jk.web.util;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 通用计算工具类
 * @author l46001
 *
 */
public class ComputeUtil implements Serializable{
	/**
	 * * 每月等额还款
	 * 计算每月应还金额
	 * @param amount：借款总额
	 * @param term：借款期限
	 * monthRate:月利率
	 */
	public static BigDecimal getMonthlyRepayAmount(BigDecimal amount,BigDecimal monthRate,int term){
		BigDecimal a = amount.multiply(monthRate).multiply(new BigDecimal(Math.pow(monthRate.add(new BigDecimal("1.00")).doubleValue(),(double)term)));
		
		BigDecimal b=new BigDecimal(Math.pow(monthRate.add(new BigDecimal("1.00")).doubleValue(),(double)term)).subtract(new BigDecimal("1.00"));
		return a.divide(b,2,BigDecimal.ROUND_HALF_UP);
	}
	

}

