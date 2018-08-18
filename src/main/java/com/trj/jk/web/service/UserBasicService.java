package com.trj.jk.web.service;

import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.model.request.SignatureReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xierongli on 17/8/3.
 */
public interface UserBasicService {

    void insert(UserBasic userBasic);

    UserBasic queryByMobile(String mobile);

    void saveUserIdentity(UserExt userExt);

    UserBasic getByIdentityId(String identityId);

    UserBasic getById(Integer id);

    int updateByPrimaryKey(UserBasic userBasic);

    void doIdentityAuth(Integer uid, List<MultipartFile> files,IdentityReq identityReq) throws ExecutionException, InterruptedException;

    void doIdentityAuth(Integer uid, File file1, File file2, IdentityReq identityReq,String orderNo) throws ExecutionException, InterruptedException;

    void liveBodyAuthAndSave(Integer uid,Integer loanApplyId);

    Boolean compareTrueFaceAndHeadImage(Integer uid,MultipartFile file);

    Integer  doElectronSignature(SignatureReq signatureReq,Integer uid,LoanApply loanApply) ;


}
