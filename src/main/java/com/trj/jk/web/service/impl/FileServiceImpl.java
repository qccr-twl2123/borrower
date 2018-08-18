package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserExtCriteria;
import com.trj.jk.web.exception.RRException;
import com.trj.jk.web.mapper.UserExtMapper;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.service.FileService;
import com.trj.jk.web.service.IAttachmentService;
import com.trj.jk.web.service.UserExtService;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by xierongli on 17/8/18.
 */
@Service
public class FileServiceImpl implements FileService {
    public static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private IAttachmentService attachmentService;
    @Autowired
    private UserExtService userExtService;
    @Value("${app.upload.url}")
    private String uploadImgUrl;

    @Autowired
    private UserExtMapper userExtMapper;


    public Integer asyncUploadImage(Integer type, Integer uid, MultipartFile multipartFile, IdentityReq identityReq){
        logger.info("异步上传用户图像数据type[{}]",type);
        try{
            String data = HttpClientUtils.uploadFile(multipartFile.getInputStream(),multipartFile.getOriginalFilename(),uploadImgUrl);
            logger.info("身份验证图片上传结果data[{}]",data);
            if(StringUtils.isNotBlank(data)){
                Map map = (Map) JsonUtils.stringToObject(data, Map.class);
                if("true".equals(map.get("success").toString())){
                    String imgUrl = map.get("data").toString();
                    String imgName=imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
                    String imgPath=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
                    Attachment attach = new Attachment();
                    attach.setCreator(uid);
                    attach.setName(multipartFile.getOriginalFilename());
                    attach.setSaveName(imgName);
                    attach.setAttachPath(imgPath);
                    attach.setAttachSize(String.valueOf(multipartFile.getSize()));
                    attachmentService.saveAttachmentInfo(attach);

                    UserExt userExt = new UserExt();
                    userExt.setUid(uid);
                    userExt.setName(identityReq.getName());
                    userExt.setIdentityId(identityReq.getIdentityId());
                    userExt.setIdentityAddress(identityReq.getIdentityAddress());
                    userExt.setIdentityDate(identityReq.getEndDate());
                    //正->反->头像
                    if(type == 1){
                        userExt.setIdentityCardFrontImageId(attach.getId());
                    }else if(type ==2){
                        userExt.setIdentityCardOppositeImageId(attach.getId());
                    }else if(type ==3){
                        userExt.setHeadImageId(attach.getId());
                    }else if(type ==4){
                        userExt.setLivingBodyImageId(attach.getId());
                    }
                    UserExtCriteria userExtCriteria = new UserExtCriteria();
                    userExtCriteria.createCriteria().andUidEqualTo(uid);
                    int updateNum = userExtMapper.updateByCriteriaSelective(userExt,userExtCriteria);
                    logger.info("上传图片user_ext更新记录"+updateNum);
                    if(updateNum < 0){
                        logger.error("UserExt 更新失败");
                    }
                    return updateNum;
                }
            }
        } catch (IOException e) {
            logger.error("文件上传失败",e.getMessage());
            e.printStackTrace();
            throw  new RRException(multipartFile.getOriginalFilename()+"上传失败");
        }finally {
            return 0;
        }
    }

    public Integer asyncUploadImage(Integer type, Integer uid, File file, IdentityReq identityReq){
        logger.info("异步上传用户图像数据type[{}]",type);
        try{
            InputStream in = new FileInputStream(file);
            String data = HttpClientUtils.uploadFile(in,file.getName(),uploadImgUrl);
            logger.info("身份验证图片上传结果data[{}]",data);
            if(StringUtils.isNotBlank(data)){
                Map map = (Map) JsonUtils.stringToObject(data, Map.class);
                if("true".equals(map.get("success").toString())){
                    String imgUrl = map.get("data").toString();
                    String imgName=imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
                    String imgPath=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
                    Attachment attach = new Attachment();
                    attach.setCreator(uid);
                    attach.setName(file.getName());
                    attach.setSaveName(imgName);
                    attach.setAttachPath(imgPath);
                    attach.setAttachSize(String.valueOf(file.length()));
                    attachmentService.saveAttachmentInfo(attach);

                    UserExt userExt = new UserExt();
                    userExt.setUid(uid);
                    userExt.setName(identityReq.getName());
                    userExt.setIdentityId(identityReq.getIdentityId());
                    userExt.setIdentityAddress(identityReq.getIdentityAddress());
                    //正->反->头像
                    if(type == 1){
                        userExt.setIdentityCardFrontImageId(attach.getId());
                    }else if(type ==2){
                        userExt.setIdentityCardOppositeImageId(attach.getId());
                    }else if(type ==3){
                        userExt.setHeadImageId(attach.getId());
                    }else if(type ==4){
                        userExt.setLivingBodyImageId(attach.getId());
                    }
                    UserExtCriteria userExtCriteria = new UserExtCriteria();
                    userExtCriteria.createCriteria().andUidEqualTo(uid);
                    int updateNum = userExtMapper.updateByCriteriaSelective(userExt,userExtCriteria);
                    logger.info("上传图片user_ext更新记录"+updateNum);
                    if(updateNum < 0){
                        logger.error("UserExt 更新失败");
                    }
                    return updateNum;
                }
            }
        } catch (IOException e) {
            logger.error("文件上传失败",e.getMessage());
            e.printStackTrace();
            throw  new RRException(file.getName()+"上传失败");
        }finally {
            return 0;
        }
    }
}
