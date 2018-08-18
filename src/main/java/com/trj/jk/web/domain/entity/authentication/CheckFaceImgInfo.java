package com.trj.jk.web.domain.entity.authentication;

import java.io.File;
import java.io.Serializable;

public class CheckFaceImgInfo implements Serializable{

	private String name;
	
	private String IdentityId;
	
	private File livingBodyImage;
	
	private File headImage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityId() {
		return IdentityId;
	}

	public void setIdentityId(String identityId) {
		IdentityId = identityId;
	}

	public File getLivingBodyImage() {
		return livingBodyImage;
	}

	public void setLivingBodyImage(File livingBodyImage) {
		this.livingBodyImage = livingBodyImage;
	}

	public File getHeadImage() {
		return headImage;
	}

	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "CheckFaceImgInfo [name=" + name + ", IdentityId=" + IdentityId + ", livingBodyImage=" + livingBodyImage
				+ ", headImage=" + headImage + "]";
	}
	
	
}
