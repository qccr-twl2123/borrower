package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;
import java.util.List;

import com.trj.jk.web.domain.UserDriveLicence;

public class DriveLicence extends UserDriveLicence implements Serializable{
	
	private List<String> imgUrls;

	public List<String> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}

	@Override
	public String toString() {
		return "DriveLicence [imgUrls=" + imgUrls + "]";
	}

	
}
