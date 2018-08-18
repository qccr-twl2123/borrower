package com.trj.jk.web.service;

import com.trj.jk.web.model.request.IdentityReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by xierongli on 17/8/18.
 */
public interface FileService {

     Integer asyncUploadImage(Integer type, Integer uid, MultipartFile multipartFile, IdentityReq identityReq);
     Integer asyncUploadImage(Integer type, Integer uid, File file, IdentityReq identityReq);
}
