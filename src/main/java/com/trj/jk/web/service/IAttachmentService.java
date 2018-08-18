package com.trj.jk.web.service;

import java.io.File;

import com.trj.jk.web.domain.Attachment;

public interface IAttachmentService {
	Attachment saveAttachment(File file, String fileName);
	
	Integer saveAttachmentInfo(Attachment attachment);
}
