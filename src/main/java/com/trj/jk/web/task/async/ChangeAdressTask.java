package com.trj.jk.web.task.async;


import org.apache.log4j.Logger;
import com.trj.jk.web.domain.MobileLocationInterval;
import com.trj.jk.web.mapper.MobileLocationIntervalMapper;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.util.PositionUtil;
import com.trj.jk.web.util.SpringUtil;
import com.fasterxml.jackson.databind.JsonNode;

public class ChangeAdressTask implements IThreadTaskService.Task{
	
	private MobileLocationInterval mobileLocationInterval;
	
	private static final Logger LOG = Logger.getLogger(ChangeAdressTask.class);

	public ChangeAdressTask(MobileLocationInterval mobileLocationInterval) {
		super();
		this.mobileLocationInterval = mobileLocationInterval;
	}

	@Override
	public String getName() {
		return getClass().getName();
	}

	@Override
	public void doExecute() {
		try {
			JsonNode result = PositionUtil.getposition(String.valueOf(mobileLocationInterval.getLatitude()), String.valueOf(mobileLocationInterval.getLongitude()));
			String status = result.findValue("status").toString();
			System.out.println("\"OK\"".equals(status));
			if(status!=null && "\"OK\"".equals(status)){
				JsonNode resultNode = result.findValue("result");
				JsonNode addressComponent = resultNode.findValue("addressComponent");
				String province = addressComponent.findValue("province").toString().replace("\"","");
				String city = addressComponent.findValue("city").toString().replace("\"","");
				String district = addressComponent.findValue("district").toString().replace("\"","");
				mobileLocationInterval.setProvince(province);
				mobileLocationInterval.setCity(city);
				mobileLocationInterval.setDistrict(district);
				MobileLocationIntervalMapper mobileLocationIntervalMapper = (MobileLocationIntervalMapper)SpringUtil.getBean("mobileLocationIntervalMapper");
				mobileLocationIntervalMapper.updateByPrimaryKey(mobileLocationInterval);
			}
	         
		} catch (Exception e) {
			LOG.error("经纬度转换地址信息异常！"+e.getMessage());
		}
	}

}
