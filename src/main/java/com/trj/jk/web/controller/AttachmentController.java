package com.trj.jk.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.UploadResult;
import com.trj.jk.web.service.IAttachmentService;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;
import com.trj.jk.web.util.SessionUtil;


@RestController
@RequestMapping(value={"/attachment"})
public class AttachmentController {
	
	@Resource
	private IAttachmentService attachmentService;
	
	@Value("${app.upload.url}")
	private String uploadImgUrl;
	
	private static final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);
	

	/**
	 * 文件上传接口
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public Result<Object> upload(@RequestParam("file") List<MultipartFile> files,ServletRequest request) {
		Result<Object> result = new Result<Object>();
		List<UploadResult> uploadResultList = new ArrayList<UploadResult>();
		LOG.info("文件上传接口开始执行...");
		try{
			if(!files.isEmpty()){
				for(int i =0; i< files.size(); ++i){
					MultipartFile file=files.get(i);
					LOG.info("fileName："+file.getOriginalFilename());
					String data = HttpClientUtils.uploadFile(file.getInputStream(),file.getOriginalFilename(),uploadImgUrl);
					Map map = (Map) JsonUtils.stringToObject(data, Map.class);
					if("true".equals(map.get("success").toString())){
						String imgUrl = map.get("data").toString();
						String imgName=imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
						String imgPath=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
						Attachment attach = new Attachment();
						if (SessionUtil.getUserLogonInfo() != null) {
							attach.setCreator((Integer)SessionUtil.getUserLogonInfo());
						}
						attach.setName(file.getOriginalFilename());
						attach.setSaveName(imgName);
						attach.setAttachPath(imgPath);
						attach.setAttachSize(String.valueOf(file.getSize()));
						attachmentService.saveAttachmentInfo(attach);
						UploadResult uploadResult = new UploadResult();
						uploadResult.setAttachId(attach.getId());
						uploadResult.setName(imgName);
						uploadResult.setPath(imgPath);
						uploadResultList.add(uploadResult);
					}
				}

			}
			result.setData(uploadResultList);
		
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}
		LOG.info(String.format("文件上传接口返回：%s",result.toString()));
		return result;
	}



	@RequestMapping(value="/uploadFromTrj",method=RequestMethod.POST)
	public Result<Object> uploadFromTrj(@RequestParam("file") List<MultipartFile> files,ServletRequest request) {
		Result<Object> result = new Result<Object>();
		List<UploadResult> uploadResultList = new ArrayList<UploadResult>();
		try{
			if(!files.isEmpty()){
				for(int i =0; i< files.size(); ++i){
					MultipartFile file=files.get(i);
					LOG.info("fileName："+file.getOriginalFilename());
					String data = HttpClientUtils.uploadFile(file.getInputStream(),file.getOriginalFilename(),uploadImgUrl);
					Map map = (Map) JsonUtils.stringToObject(data, Map.class);
					if("true".equals(map.get("success").toString())){
						String imgUrl = map.get("data").toString();
						String imgName=imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
						String imgPath=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
						Attachment attach = new Attachment();
						if (SessionUtil.getUserLogonInfo() != null) {
							attach.setCreator((Integer)SessionUtil.getUserLogonInfo());
						}
						attach.setName(file.getOriginalFilename());
						attach.setSaveName(imgName);
						attach.setAttachPath(imgPath);
						attach.setAttachSize(String.valueOf(file.getSize()));
						attachmentService.saveAttachmentInfo(attach);
						UploadResult uploadResult = new UploadResult();
						uploadResult.setAttachId(attach.getId());
						uploadResult.setName(imgName);
						uploadResult.setPath(imgPath);
						uploadResultList.add(uploadResult);
					}
				}

			}
			result.setData(uploadResultList);

		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);
		}

		return result;
	}



}
