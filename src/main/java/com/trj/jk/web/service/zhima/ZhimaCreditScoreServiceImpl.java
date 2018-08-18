package com.trj.jk.web.service.zhima;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZhimaCreditScoreBriefGetRequest;
import com.alipay.api.response.ZhimaCreditScoreBriefGetResponse;
import com.google.gson.Gson;
import com.mysql.jdbc.Statement;
import com.trj.jk.web.config.AlipayConfig;

@Service("zhimaCreditScoreService")
public class ZhimaCreditScoreServiceImpl implements ZhimaCreditScoreService {

	private static final Logger	LOG	= LoggerFactory.getLogger(ZhimaCreditScoreServiceImpl.class);

	@Autowired
	private AlipayConfig		alipayConfig;
	@Autowired
	private Gson				gson;
	@Resource
	private JdbcTemplate		cfdJdbcTemplate;

	@Override
	public int queryAdmittance(String name, String certNo, int score) {
		Assert.hasText(name, "姓名不能为空");
		Assert.hasText(certNo, "证件号码不能为空");
		
		String transactionId = "" + System.currentTimeMillis() + certNo;
		
		BizContent bizContent = new BizContent(transactionId, alipayConfig.getProductCode(), certNo, name, score);
		long id = insert(bizContent);
		ZhimaCreditScoreBriefGetResponse response = invokeZhimaCreditScore(bizContent);
		int result = 0;
		String bizNo = null;
		if (response.isSuccess()) {
			String isAdmittance = response.getIsAdmittance();
			bizNo = response.getBizNo();
			if ("Y".equals(isAdmittance)) {
				result = 2;
			} else if ("N".equals(isAdmittance)) {
				result = 1;
			} else {
				result = -1;
			}
		} else {
//			LOG.error();
		}

		update(id, result, bizNo, gson.toJson(response));
		return result;
	}

	private void update(final long id, final int admittance, final String bizNo, final String resData) {
		final String sql = "update cfd_hd_zhima_credit_score set admittance=?,biz_no=?,res_data=? where id=?";

		cfdJdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub

				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, admittance);
				ps.setString(2, bizNo);
				ps.setString(3, resData);
				ps.setLong(4, id);

				return ps;
			}
		});

	}

	private long insert(final BizContent bizContent) {

		final String sql = "insert into cfd_hd_zhima_credit_score (ctime,mtime,transaction_id,cert_no,name,score) values (?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		cfdJdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				Date date = new Date();
				long dateLong = date.getTime() / 1000;

				ps.setLong(1, dateLong);
				ps.setLong(2, dateLong);
				ps.setString(3, bizContent.getTransaction_id());
				ps.setString(4, bizContent.getCert_no());
				ps.setString(5, bizContent.getName());
				ps.setInt(6, bizContent.getAdmittance_score());

				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	private ZhimaCreditScoreBriefGetResponse invokeZhimaCreditScore(BizContent bizContent) {

		Assert.notNull(bizContent, "bizContent 不允许为空");

		ZhimaCreditScoreBriefGetResponse result;

		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey(), "json", "UTF-8",
				alipayConfig.getPublicKey(), "RSA2");

		ZhimaCreditScoreBriefGetRequest request = new ZhimaCreditScoreBriefGetRequest();
		String bizContentJson = gson.toJson(bizContent);
		LOG.info("bizContentJson=" + bizContentJson);
		request.setBizContent(bizContentJson);

		try {
			result = alipayClient.execute(request);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

		LOG.info("ZhimaCreditScoreBriefGetResponse=" + gson.toJson(result));
		return result;

	}

	public static class BizContent {
		/** 商户请求的唯一标志 */
		private String	transaction_id;
		/** 产品码 */
		private String	product_code;
		/** 证件类型 */
		private String	cert_type			= "IDENTITY_CARD";
		/** 证件号码 */
		private String	cert_no;
		/** 用户姓名 */
		private String	name;
		/** 业务判断的准入标准 */
		private int		admittance_score	= 650;

		public BizContent(String transaction_id, String product_code, String cert_no, String name, int admittance_score) {
			super();
			this.transaction_id = transaction_id;
			this.product_code = product_code;
			this.cert_no = cert_no;
			this.name = name;
			this.admittance_score = admittance_score;
		}

		public String getTransaction_id() {
			return transaction_id;
		}

		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}

		public String getProduct_code() {
			return product_code;
		}

		public void setProduct_code(String product_code) {
			this.product_code = product_code;
		}

		public String getCert_type() {
			return cert_type;
		}

		public void setCert_type(String cert_type) {
			this.cert_type = cert_type;
		}

		public String getCert_no() {
			return cert_no;
		}

		public void setCert_no(String cert_no) {
			this.cert_no = cert_no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAdmittance_score() {
			return admittance_score;
		}

		public void setAdmittance_score(int admittance_score) {
			this.admittance_score = admittance_score;
		}

	}

	@Override
	public boolean hasQuery(String certNo, int score, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		Assert.hasText(certNo, "证件号码不能为空");
		Assert.notNull(startDate, "开始日期不能为空");
		Assert.notNull(endDate, "截止日期不能为空");

		String sql = "select id from cfd_hd_zhima_credit_score where cert_no=? and score=? and admittance <> 0 and ctime >= ? and ctime < ? ";

		List<Long> list = cfdJdbcTemplate.queryForList(sql, new Object[] { certNo, score, startDate.getTime() / 1000, endDate.getTime() / 1000 }, Long.class);

		boolean result = list != null && list.size() > 0;

		return result;
	}

}
