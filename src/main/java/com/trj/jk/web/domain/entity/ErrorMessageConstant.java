package com.trj.jk.web.domain.entity;

public class ErrorMessageConstant {

	public static final String SUCCESS="成功";
	public static final String MODIFY_SUCCESS="修改成功";
	public static final String SAVE_SUCCESS="保存成功";
	public static final String ERR_OCCURS="系统异常";

	public static final String ERR_PARAM_ERROR = "参数错误";
	public static final String ERR_DATA_ERROR = "请不要重复选择同一联系人！";
	public static final String ERR_DATA_CAR_ERROR = "车辆信息已存在！";
	public static final String ERR_DATA_EMPTY = "数据信息不存在！";

	public static final String OK_LOGIN="登录成功";
	public static final String UN_LOGIN="用户未登录";	
	public static final String OK_SIGNON = "注册成功";
	public static final String MOBILE_REGISTERED = "用户已注册";
	public static final String MOBIEL_UNREGISTERED = "用户未注册";
	public static final String RESET_SUCCESS = "修改密码成功";
	public static final String OK_LOGOUT="退出成功";
	public static final String UN_LOGOUT="退出失败";
	

	public static final String ERR_LOGIN_NULL="手机号或密码不能为空";
	public static final String ERR_MOBILE_NULL="手机号不能为空";
	public static final String ERR_PASSWORD_NULL="密码不能为空";
	
	
	public static final String ERR_OLD_PASSWORD_NULL="当前密码不能为空";
	public static final String ERR_PASSWORD_VERIFY="当前密码输入有误";
	public static final String ERR_REAL_NAME_VERIFY="实名信息不匹配";
	
	
	public static final String ERR_VERIFY_NULL= "动态码不能为空";
	public static final String ERR_VERIFY_INCORRECT ="动态码不正确";
	public static final String ERR_VERIFY_INVALID = "动态码已失效";
	public static final String ERR_STATUS_INVALID = "用户状态异常";
	public static final String ERR_AUTH_STATUS_NULL="认证结果状态不可以为空！";
	public static final String ERR_LOGIN_INCORRECT = "账号或密码错误";
	
	public static final String ERR_PERIODS_NULL = "还款期数不能为空";
	

	public static final String ERR_ES_ADD_ACCOUNT= "电子签章开户发生错误";
	public static final String ERR_ES_TEMPLATE_CREATE="模板签章创建发生错误";
	public static final String ERR_ES_EXIST_ACCOUNT= "电子签章已开过户";
	public static final String ERR_NO_ES_EXIST_ACCOUNT= "电子签章未开户";
	public static final String ERR_NULL_CONTRACT = "无法找到合同";
	public static final String ERR_NULL_CONTRACT_ATTACH ="无法找到合同附件";
	public static final String ERR_NULL_ES_ACCOUNT = "无法找到e签宝账户";
	public static final String ERR_ES_SIGN = "电子签章发生错误"; 
	public static final String ERR_ES_CONTRACT_NUMBER = "合同数量错误";
	
	public static final String ERR_SENDE_VERIFY_CODE="获取验证码失败！";
	
	public static final String OK_BIND_CARD_APPLY = "绑卡签约成功"; 
	public static final String FAIL_BIND_CARD_APPLY = "绑卡签约失败"; 	
	public static final String OK_BIND_CARD_CONFIRM = "绑卡确认成功"; 
	public static final String FAIL_BIND_CARD_CONFIRM = "绑卡确认失败"; 
	public static final String NO_CARD = "未绑卡";	
	
	public static final String ERR_SOOPAY_SIGN="联动平台请求字段规则或者数据签名发生异常";
	public static final String ERR_SOOPAY_VERIFY="联动平台返回数据校验失败";
	public static final String ERR_AMOUNT_OVERFLOW = "扣款数量超出未还金额";	
	
	
	public static final String ERR_NOT_EXIST = "验证信息不存在！！！";
	public static final String ERR_NULL_ID="ID不能为空！";
	public static final String ERR_NULL_UID="UID不能为空！";
	public static final String ERR_NULL_CERTFICATIONID="certficationId不能为空！";
	public static final String ERR_NULL_NAME="名字不能为空";
	public static final String ERR_NULL_IDENTITY_ID="身份信息不能为空";
	
	public static final String ERR_NULL_PRODUCT = "借款申请产品不能为空！";
	public static final String ERR_NULL_AMOUNT="借款申请金额不能为空！";
	
	public static final String ERR_NULL_CITY="城市不能为空！";
	
	public static final String ERR_DRIVELICENCENO_NULL_ERROR="驾驶证号不能为空！";
	public static final String ERR_DRIVELICENCENO_ERROR="驾驶证号格式错误！";
	public static final String ERR_NAME_ERROR="姓名应为中文！";
	public static final String ERR_NAME_NULL_ERROR="姓名不能为空！";
	public static final String ERR_ADRESS_ERROR="详细地址信息不可以全英文或全数字！";
	public static final String ERR_EMAIL_ERROR="邮箱地址格式错误！";
	
	public static final String ERR_TEL_ERROR="座机号码格式错误！";
	
	public static final String ERR_MOBILE_ERROR="手机号码格式错误！";
	
	public static final String ERR_CARNO_ERROR="车牌号格式错误！";
	
	public static final String ERR_CARVIN_ERROR="车架号格式错误！";

	public static final String ERR_NO_USEREXT_ERROR="用户信息未找到！";
	
	public static final String ERR_NO_IDENTITY_AUTH_ERROR="身份信息未认证！";
	
	public static final String ERR_LOAN_APPLY_ID_ERROR="借款申请Id不能为空！";

	public static final String ERR_LOAN_APPLY_DUPLICATE="当前已有一笔借款待发放！";
	
	public static final String ERR_PRODUCT_ID_ERROR="产品Id不能为空！";
	
	public static final String ERR_AMOUNT_ERROR="金额不能大于可用额度！";
	
	public static final String ERR_REPAYTYPE_ERROR="还款方式不能为空！";
	
	public static final String ERR_TERM_ERROR="期限不能为空！";
	
	public static final String ERR_TERM_UNIT_ERROR="期限单位不能为空！";
	
	public static final String ERR_AMOUNT_NULL_ERROR="金额不能为空！";
	
	public static final String ERR_NULL_FACE_INMAGE="人脸识别对比图片信息不可以为空！";
	
	public static final String  ERR_FACE_INFO_REPEAT="身份信息已被实名认证！";
	
	public static final String  AMOUNT_IS_INVALID="申请金额大于最大可借额度！";
	
	public static final String AMOUNT_IS_MINUS_INVALID="申请金额小于最小可借额度！";
	
	public static final String IS_HAVE_VALID_APPLY_ERROR="已经存在有效的该产品借款申请！";
	
	public static final String ERR_BANKCARDID_ERROR="放款银行卡不可以为空！";
	
	public static final String ERR_INTEREST_ERROR="预算总费率不能为空！";
	
	public static final String ERR_EXPECTREPAYAMOUNT_ERROR="预计还款不可以为空！";
	
	public static final String ERR_USERBASIC_ERROR="用户未找到！";
	
	public static final String ERR_NULL_PROVINCE="省份信息不可以为空！";
	
	public static final String ERR_NULL_DISTRICT="区域信息不可以为空！";
	
	public static final String ERR_NO_OPEN="区域未开放！";
	
	public static final String ERR_GET_VERIFY_CODE_ERROR="获取验证码失败！";
	
	public static final String ERR_SMSTPL_ERROR="短息模板未找到！";
	
	public static final String ERR_MOBILE_EXIST_ERROR="手机号码已被注册！";

	public static final String USER_CREDIT_VALUE_NOT_EXIST="暂无长富分";

	public static final String ERR_USERAMOUNT_INVALID_ERROR="使用金额需大于1000元！";
	
	public static final String ERR_IDENTITYID_EXIST="该身份证已通过实名验证！";
	public static final String ERR_IDENTITYID_USE_OTHER="一个账号只能验证一个身份证";

	public static final String ERR_NULL_CONTRACT_IMG_ATTACH="无法找到合同图片附件！";
	
	public static final String ERR_ATTACHID_NULL_ERROR="附件资料不可以为空！";
	
	public static final String ERR_AUTH_TYPE="认证类型不可以为空！";
	
	public static final String ERR_NULL_USER_CERTFICATION="认证记录不存在！";
}
