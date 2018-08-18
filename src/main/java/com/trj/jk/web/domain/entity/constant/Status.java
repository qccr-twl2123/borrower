package com.trj.jk.web.domain.entity.constant;

public class Status {
	public static final Byte USER_INVALID=0;
	
	//绑卡状态
	public static final byte STATUS_BIND_VERIFY = 1;	
	public static final byte STATUS_BIND_CONFIRM = 2;
	public static final byte STATUS_BIND_DONE = 3;	
	
	//支付状态
	public static final byte ORDER_SUCCESS=1;
	public static final byte ORDER_FAIL=2;
	public static final byte REPAY_SUCCESS=3;
	public static final byte REPAY_FAIL=4;	
	public static final byte REPAY_NOTIFY_RECEIVED=5;
	
	public static final String VERIFY_EXPIRE="0";
	
	//申请借款阶段
	public static final Byte STEP_CONTACT=1;
	public static final Byte STEP_PROFESSIOIN=2;
	public static final Byte STEP_CERTIFICATION=3;
	public static final Byte STEP_DONE=4;
	
	//申请借款审批
	public static final Byte STEP_WAIT_SCORE=3;

	
	//申请借款状态
	public static final Byte STATUS_APPLY_SUBMIT=1;
	public static final Byte STATUS_APPLY_WAIT=0;

	
	public static final Byte STATUS_APPLY_INVALID=0;
	public static final Byte STATUS_APPLY_VALID=1;
	
	//认证状态
	public static final Byte STATUS_CERT_UNDO=0;
	public static final Byte STATUS_CERT_ING=1;
	public static final Byte STATUS_CERT_SUCCESS=2;
	public static final Byte STATUS_CERT_FAIL=3;
	
	//认证结果
	public static final Byte STATUS_CERT_TRUE=1;
	public static final Byte STATUS_CERT_FALSE=0;
	
	//产品
	public static final Byte NEED_AUDIT=1;
	

	
	
	//认证返回ｃｏｄｅ
	public static final int STATUS_RETURN_SUCCESS=0;
	public static final int STATUS_PROCESS_10001=10001;//再次输入短信验证码
	public static final int STATUS_PROCESS_10002=10002; //输入短信验证码
	public static final int STATUS_PROCESS_10003=10003;//密码错误
	public static final int STATUS_PROCESS_10004=10004;//短信验证码错误
	public static final int STATUS_PROCESS_10006=10006;//短信验证码失效系统已自动重新下发
	public static final int STATUS_PROCESS_10007=10007;//简单密码或初始密码无法登录
	public static final int STATUS_PROCESS_10008=10008;//开始采集行为数据
	public static final int STATUS_PROCESS_10010=10010;//新密码格式错误
	public static final int STATUS_PROCESS_10017=10017;//请用本机发送CXXD至10001获取查询详单的验证码
	public static final int STATUS_PROCESS_10018=10018;//短信码失效请用本机发送CXXD至10001获取查询详单的验证码
	public static final int STATUS_PROCESS_10022=10022;//输入查询密码
	public static final int STATUS_PROCESS_10023=10023;//查询密码错误
	public static final int STATUS_PROCESS_11000=11000;//重置密码成功
	public static final int STATUS_PROCESS_30000=30000;//错误信息
	public static final int STATUS_PROCESS_31000=31000;//错误信息

	

}
