package com.trj.jk.web.mapper.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.trj.jk.web.domain.entity.MobileValidateCode;

@Repository
public class JdbcDaoImpl implements JdbcDao{
	@Autowired
	private  JdbcTemplate trjJdbcTemplate;
	
	@Autowired
	private  JdbcTemplate trbsJdbcTemplate;

	@Override
	public String getTrbsVerifyCodeStatus(String mobile, String verifyCode) {
		List<String> result =  trbsJdbcTemplate.queryForList("select status from cfd_mobile_validate_code where mobile=? and code=? and valid_time>now() order by id desc limit 1", new Object[]{mobile,verifyCode}, String.class);
		if(result !=null && !result.isEmpty()){
			return result.get(0);
		}else{
			return null;
		}		
	}
	
	@Override
	public String getTrjVerifyCodeStatus(String mobile, String verifyCode) {
		List<String> result =  trjJdbcTemplate.queryForList("select status from fi_mobile_validate_code where mobile=? and code=? order by id desc limit 1", new Object[]{mobile,verifyCode}, String.class);
		if(result !=null && !result.isEmpty()){
			return result.get(0);
		}else{
			return null;
		}		
	}
	
	@Override
	public long saveVerifyCode(final MobileValidateCode mobileValidateCode){
		final String sql = "insert into cfd_mobile_validate_code (uid,sms_id,code,mobile,valid_time,status,mi_no) values (?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		trbsJdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				Date date = new Date();
				ps.setLong(1, mobileValidateCode.getUid());
				ps.setLong(2, mobileValidateCode.getSmsId());
				ps.setString(3,  mobileValidateCode.getCode());
				ps.setString(4,  mobileValidateCode.getMobile());
				ps.setInt(6, mobileValidateCode.getStatus());
				ps.setString(7, mobileValidateCode.getMiNo());
				ps.setTimestamp(5, new Timestamp(new Date().getTime()+200000));
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}
	
	@Override
	public  Map getSmstplByMtype(final Byte mtype) {
		final String sql = "select id,content from cfd_mobile_message_tpl where mtype=? and pass_type=1";       
		List dataList = new ArrayList();
		Map map=null;
		dataList=(List)trbsJdbcTemplate.execute(sql, new PreparedStatementCallback() {
			@Override
			public Object doInPreparedStatement(java.sql.PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setByte(1, mtype.byteValue());
				ResultSet rs=ps.executeQuery();
			    List list=new ArrayList();  
                while (rs.next()) {  
                	Map map=new HashMap();
                	map.put("content", rs.getString("content"));
                	map.put("id", rs.getString("id"));
                	list.add(map);
                }  
				return list;
			}
		});
		if(dataList!=null&&!dataList.isEmpty()){
			map=(Map)dataList.get(0);
		}
		 
		return map;
	}

	@Override
	public List<Map<String, Object>> getContractByLoanLimitId(final Integer loanLimitId) {
		final String sql = "select contract.id,contract.sign_attach_id,contract.sign_img_attach_id,contract.contract_name,contract.tpl_id,tpl.tpl_no from uwc_applyloan_contract contract,es_file_tpl tpl where contract.loan_limit_id=? and contract.tpl_id=tpl.id";
		List dataList = new ArrayList();
		Map map=null;
		dataList=(List)trbsJdbcTemplate.execute(sql, new PreparedStatementCallback() {
			@Override
			public Object doInPreparedStatement(java.sql.PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, loanLimitId);
				ResultSet rs=ps.executeQuery();
			    List list=new ArrayList();  
                while (rs.next()) {  
                	Map map=new HashMap();
                	map.put("id", rs.getString("id"));
                	map.put("signAttachId", rs.getString("sign_attach_id"));
                	map.put("signImgAttachId", rs.getString("sign_img_attach_id"));
                	map.put("contractName", rs.getString("contract_name"));
                	map.put("tplId", rs.getString("tpl_id"));
                	map.put("tplNo", rs.getString("tpl_no"));
                	list.add(map);
                }  
				return list;
			}
		});
		 
		return dataList;
	}
}
