package com.trj.jk.web.domain.entity.thirdparty.soopay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.trj.jk.web.domain.LoanRepayRecord;

public class TradeBusRequestParam {
	private String goods_id;
	private String goods_inf;
	private String order_id;
	private String mer_date;
	private BigDecimal amount;
	private String amt_type;
	private String mer_priv;
	private String expand;
	private String user_ip;
	private String expire_time;
	private String risk_expand;
	
	public TradeBusRequestParam(){
		
	}
	
	public TradeBusRequestParam(Object obj) {
		LoanRepayRecord record=(LoanRepayRecord)obj;
		this.goods_id=record.getGoodsId()==null?"":record.getGoodsId();
		this.goods_inf=record.getGoodsInf()==null?"":record.getGoodsInf();
		this.order_id = record.getOrderId();
		this.mer_date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		this.amount = record.getAmount().multiply(BigDecimal.valueOf(100)).setScale(0);
		this.amt_type="RMB";
		this.mer_priv = record.getMerPriv()==null?"":record.getMerPriv();
		this.expand = record.getExpand()==null?"":record.getExpand();
		this.user_ip = record.getUserIp()==null?"":record.getUserIp();
//		this.expire_time=AbstractSoopayService.p.getProperty("soopay.expire.time");
		this.risk_expand=record.getRiskExpand()==null?"":record.getRiskExpand();
	}
	
	
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_inf() {
		return goods_inf;
	}
	public void setGoods_inf(String goods_inf) {
		this.goods_inf = goods_inf;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getMer_date() {
		return mer_date;
	}
	public void setMer_date(String mer_date) {
		this.mer_date = mer_date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmt_type() {
		return amt_type;
	}
	public void setAmt_type(String amt_type) {
		this.amt_type = amt_type;
	}
	public String getMer_priv() {
		return mer_priv;
	}
	public void setMer_priv(String mer_priv) {
		this.mer_priv = mer_priv;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	public String getRisk_expand() {
		return risk_expand;
	}
	public void setRisk_expand(String risk_expand) {
		this.risk_expand = risk_expand;
	}
	
	
}
