package com.trj.jk.web.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.mapper.AttachmentMapper;
import com.trj.jk.web.service.IAttachmentService;
import com.trj.jk.web.util.SessionUtil;


@Service
@Transactional (propagation=Propagation.REQUIRES_NEW)
public class AttachmentServiceImpl implements IAttachmentService{

	private  Logger LOG = Logger.getLogger(AttachmentServiceImpl.class);
	private  DateFormat	DATE_YEAR_FORMAT		= new SimpleDateFormat("yyyy");
	private  DateFormat	DATE_MONTH_DAY_FORMAT	= new SimpleDateFormat("MMdd");	
	
	@Value("${app.attach.path}")
	private   String			attachmentFilePath;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Override
	public Attachment saveAttachment(File file, String fileName) {
		
		Attachment ret = null;
		if (file != null) {
			try {
				String filePath = generatorFilePath();
				createFilePath(filePath);
				String newFileName = generatorFileName(fileName);
				String fullFileName = getFullFilePath(filePath, newFileName);
				while (isFileExist(fullFileName)) {
					newFileName = generatorFileName(fileName);
					fullFileName = getFullFilePath(filePath, newFileName);
				}
				FileUtils.copyFile(file, new File(fullFileName));
				ret = new Attachment();
				ret.setName(fileName);
				ret.setSaveName(newFileName);
				ret.setAttachPath(filePath);
				ret.setAttachSize(String.valueOf(file.length()));
				if (SessionUtil.getUserLogonInfo() != null) {
					ret.setCreator((Integer)SessionUtil.getUserLogonInfo());
				}
				
				attachmentMapper.insert(ret);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return ret;
	}
	
	private  String generatorFileName(String fileName) {
		return "" + System.currentTimeMillis() + getFileSuffix(fileName);
	}

	private  String generatorFilePath() {
		Date now = new Date();
		return DATE_YEAR_FORMAT.format(now) + File.separator + DATE_MONTH_DAY_FORMAT.format(now)+File.separator;
	}	
	
	private   void createFilePath(String filePath) {
		if (StringUtils.isNotBlank(filePath)) {
			File file = new File(attachmentFilePath + File.separator + filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	private  boolean isFileExist(String filePath) {
		boolean ret = false;
		if (StringUtils.isNotBlank(filePath)) {
			File file = new File(filePath);
			ret = file.exists();
		}
		return ret;
	}

	public String getFullFilePath(String filePath, String fileName) {
		String ret = attachmentFilePath;
		if (StringUtils.isNotBlank(filePath)) {
			ret += File.separator + filePath;
		}
		if (StringUtils.isNotBlank(fileName)) {
			ret += File.separator + fileName;
		}
		return ret;
	}
	
	private  String getFileSuffix(String fileName) {
		String suffix = "";
		if (fileName.contains(".")) {
			suffix = fileName.substring(fileName.lastIndexOf("."));
		}
		return suffix;
	}

	@Override
	public Integer saveAttachmentInfo(Attachment attachment) {
		return attachmentMapper.insert(attachment);
	}	
	

}
