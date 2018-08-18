package com.trj.jk.web.service.zhima;

import java.util.Date;

public interface ZhimaCreditScoreService {

	/**
	 * * 查询芝麻信用准入判断结果
	 * <li>
	 * 	<ul>1:准入</ul>
	 * 	<ul>0:不准入</ul>
	 * 	<ul>-1:无法评估</ul>
	 * </li>
	 * 
	 * @param name 姓名
	 * @param certNo 身份证
	 * @param score 分数
	 * @return
	 */
	public int queryAdmittance(String name, String certNo, int score);
	
	/**
	 * 是否已经查询
	 * 
	 * @param certNo
	 * @param score
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean hasQuery(String certNo, int score, Date startDate, Date endDate);

}
