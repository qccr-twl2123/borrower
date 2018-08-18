package com.trj.jk.web.domain.entity.regex;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexContext implements Serializable{

	//全中文验证正则
	public static final String CHINESE_REGEX = "^[\u4e00-\u9fa5]+$";
	
	//邮箱地址正则
	public static final String EMAIL_REGEX = "^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	
	//座机电话正则
	public static final String TEL_REGEX = "^0\\d{2,3}-[1-9]\\d{6,7}$";
	
	//验证身份证号（15位或18位数字）
	public static final String IDENTITY_REGEX = "^(\\d{14}|\\d{17})(\\d|[xX])$";
	
	//手机号码正则
	public static final String MOBILE_REGEX="^1[3|4|5|7|8][0-9]{9}$";
	
	//车牌号正则
	public static final String CARNO_REGEX="^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
	
	//车架号正则
	public static final String CARVIN_REGEX="^[a-zA-Z0-9]{17}$";
	
	//详细地址信息正则
	public static final String ADRESS_REGEX="^.*[\u4e00-\u9fa5].*$";//"^(?=.*?[\u4E00-\u9FA5])[\\dA-Za-z\u4E00-\u9FA5]+";
	
	public static final String SIX_NUMBER="^\\d{6}$";
	
	//密码校验:要求同时有数字和字母，长度最小6，最大16
	public static  final String PASSWORD_REGEX ="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";


	/**
	 * @param regex
	 * 正则表达式字符串
	 * @param str
	 * 要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	
}
