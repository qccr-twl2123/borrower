package com.trj.jk.web.domain.entity.personInfo.certfication;

import com.google.common.collect.Lists;
import java.util.List;

public class CertificationPage {
	private List<CertficationInfo> mandatorys;
	private List<CertficationInfo> optionals;
	private List<CertficationInfo> selectOne;
	private String remark;
	private Boolean isQualify=true;


	public static CertificationPage transform(CertificationPage certificationPage){
		List<CertficationInfo> certficationInfoList= Lists.newArrayList();
		for(CertficationInfo certficationInfo : certificationPage.getMandatorys()){
			if(certficationInfo.getField().equals("isAddressFill")){
				continue;
			}
			certficationInfoList.add(certficationInfo);
		}
		certificationPage.setMandatorys(certficationInfoList);
		return certificationPage;
	}
	
	public Boolean getIsQualify() {
		return isQualify;
	}
	public void setIsQualify(Boolean isQualify) {
		this.isQualify = isQualify;
	}
	public List<CertficationInfo> getSelectOne() {
		return selectOne;
	}
	public void setSelectOne(List<CertficationInfo> selectOne) {
		this.selectOne = selectOne;
	}
	public List<CertficationInfo> getMandatorys() {
		return mandatorys;
	}
	public void setMandatorys(List<CertficationInfo> mandatorys) {
		this.mandatorys = mandatorys;
	}
	public List<CertficationInfo> getOptionals() {
		return optionals;
	}
	public void setOptionals(List<CertficationInfo> optionals) {
		this.optionals = optionals;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
