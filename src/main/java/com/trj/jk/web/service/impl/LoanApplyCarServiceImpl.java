package com.trj.jk.web.service.impl;

import com.google.common.collect.Lists;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.*;
import com.trj.jk.web.mapper.*;
import com.trj.jk.web.model.request.LoanApplyCarReq;
import com.trj.jk.web.model.response.LoanApplyCarRes;
import com.trj.jk.web.service.LoanApplyCarService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xierongli on 17/8/21.
 */
@Service
public class LoanApplyCarServiceImpl implements LoanApplyCarService {

    @Autowired
    private LoanApplyCarMapper loanApplyCarMapper;

    @Autowired
    private UserExtMapper userExtMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Value("${app.upload.download}")
    private String downloadImgUrl;
    @Autowired
    private CarSalesOrganizationMapper carSalesOrganizationMapper;
    @Autowired
    private LoanApplyAddressMapper loanApplyAddressMapper;

    @Override
    public int saveOrUpdate(LoanApplyCarReq loanApplyCarReq) {
        LoanApplyCar loanApplyCar = ObjectConvert.convertObject(loanApplyCarReq,LoanApplyCar.class);

        //判断是否新增过地址信息
        LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
        loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyCarReq.getLoanApplyId());
        List<LoanApplyAddress> loanApplyAddressList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);

        LoanApplyAddress loanApplyAddress = ObjectConvert.convertObject(loanApplyCarReq,LoanApplyAddress.class);
        if(CollectionUtils.isEmpty(loanApplyAddressList)){
            //执行新增操作
            loanApplyCarMapper.insertSelective(loanApplyCar);
            loanApplyAddressMapper.insertSelective(loanApplyAddress);
            return 1;
        }else{
            //执行更新操作
            LoanApplyCarCriteria loanApplyCarCriteria = new LoanApplyCarCriteria();
            loanApplyCarCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyCarReq.getLoanApplyId());
            loanApplyCarMapper.updateByCriteriaSelective(loanApplyCar,loanApplyCarCriteria);

            loanApplyAddressMapper.updateByCriteriaSelective(loanApplyAddress,loanApplyAddressCriteria);

        }
        return 0;
    }

    @Override
    public LoanApplyCarRes getLoanApplyInfo(Integer uid, Integer loanApplyId) {
       LoanApplyAddressCriteria loanApplyAddressCriteria = new LoanApplyAddressCriteria();
       loanApplyAddressCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
       List<LoanApplyAddress> loanApplyAddressList = loanApplyAddressMapper.selectByCriteria(loanApplyAddressCriteria);
        //从UserExt获取居住地址信息
        if(CollectionUtils.isEmpty(loanApplyAddressList)){
            UserExtCriteria userExtCriteria = new UserExtCriteria();
            userExtCriteria.createCriteria().andUidEqualTo(uid);
            List<UserExt> userExtList = userExtMapper.selectByCriteria(userExtCriteria);
            if(CollectionUtils.isNotEmpty(userExtList)){
                if(StringUtils.isNotBlank(userExtList.get(0).getResidentialProvince())){
                    return ObjectConvert.convertObject(userExtList.get(0),LoanApplyCarRes.class);
                }
                return null;
            }
        }
        LoanApplyAddress loanApplyAddress = loanApplyAddressList.get(0);
        LoanApplyCarRes loanApplyCarRes = ObjectConvert.convertObject(loanApplyAddress,LoanApplyCarRes.class);

        LoanApplyCarCriteria loanApplyCarCriteria = new LoanApplyCarCriteria();
        loanApplyCarCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
        List<LoanApplyCar> loanApplyCarList = loanApplyCarMapper.selectByCriteria(loanApplyCarCriteria);

        loanApplyCarRes.setSalesOrganization(loanApplyCarList.get(0).getSalesOrganization());
        loanApplyCarRes.setAttachId(loanApplyCarList.get(0).getAttachId());
        loanApplyCarRes.setBrand(loanApplyCarList.get(0).getBrand());
        loanApplyCarRes.setModel(loanApplyCarList.get(0).getModel());
        loanApplyCarRes.setNakedBikeAmount(loanApplyCarList.get(0).getNakedBikeAmount());
        //设置附件图片链接
        List<String> imgUrls = Lists.newArrayList();
        List<String> attachIds = Arrays.asList(loanApplyCarList.get(0).getAttachId().split(","));
        for(String attachId:attachIds){
            Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(attachId.trim()));
            if(attachment!=null && attachment.getAttachPath()!=null && attachment.getSaveName()!=null){
                imgUrls.add(downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
            }
        }
        loanApplyCarRes.setImgUrls(imgUrls);
        //设置销售机构信息
        if(loanApplyCarList.get(0).getSalesOrganization()!=null){
            CarSalesOrganization carSalesOrganization = carSalesOrganizationMapper.selectByPrimaryKey(loanApplyCarList.get(0).getSalesOrganization());
            if(carSalesOrganization!=null){
                loanApplyCarRes.setCityName(carSalesOrganization.getCityName());
                loanApplyCarRes.setCityCode(carSalesOrganization.getCityCode());
                loanApplyCarRes.setSoName(carSalesOrganization.getName());
            }
        }
        return loanApplyCarRes;
    }
}
