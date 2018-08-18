package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.DeliverAddress;
import com.trj.jk.web.domain.DeliverAddressCriteria;
import com.trj.jk.web.domain.RefGeocode;
import com.trj.jk.web.domain.RefGeocodeCriteria;
import com.trj.jk.web.mapper.DeliverAddressMapper;
import com.trj.jk.web.mapper.RefGeocodeMapper;
import com.trj.jk.web.service.IDeliverAddressService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by xierongli on 17/6/7.
 */
@Service
public class DeliverAddressServiceImpl implements IDeliverAddressService {

    @Autowired
    private DeliverAddressMapper deliverAddressMapper;

    @Autowired
    private RefGeocodeMapper refGeocodeMapper;

    @Override
    public int insert(DeliverAddress deliverAddress) {
        if(deliverAddress == null){return 0;}
        //1.查询地址是否存在
        if(StringUtils.isNotBlank(deliverAddress.getAddress())){
            DeliverAddressCriteria deliverAddressCriteria  = new DeliverAddressCriteria();
            deliverAddressCriteria.createCriteria().andAddressEqualTo(deliverAddress.getAddress());
            List<DeliverAddress> deliverAddressList = deliverAddressMapper.selectByCriteria(deliverAddressCriteria);
            if(CollectionUtils.isEmpty(deliverAddressList)){
                //2.不存在新增
                return deliverAddressMapper.insert(deliverAddress);

            }
        }
        return 0;
    }

    @Override
    public List<DeliverAddress> querForList(Integer uid) {
        if(uid != null){
            DeliverAddressCriteria deliverAddressCriteria  = new DeliverAddressCriteria();
            deliverAddressCriteria.createCriteria().andUidEqualTo(uid);
            return deliverAddressMapper.selectByCriteria(deliverAddressCriteria);

        }
        return null;
    }

    @Override
    public DeliverAddress queryForDeliverAddress(Integer uid) {
        List<DeliverAddress> list=null;
        DeliverAddress deliverAddress=null;
        if(uid != null){
            DeliverAddressCriteria deliverAddressCriteria  = new DeliverAddressCriteria();
            deliverAddressCriteria.createCriteria().andUidEqualTo(uid);
            deliverAddressCriteria.setOrderByClause("id desc");
            list=deliverAddressMapper.selectByCriteria(deliverAddressCriteria);
            if(null!=list&&list.size()>0){
                deliverAddress=list.get(0);
            }
        }
        return deliverAddress;
    }

    @Override
    public Integer findAddressIdByDistrictFullCode(String districtFullCode,String address,String mobile,Integer uid) {
        List<RefGeocode> list=null;
        Integer geoCodeId=0;
        Integer addressId=0;
        if(!StringUtils.isEmpty(districtFullCode)&&!StringUtils.isEmpty(address)&&!StringUtils.isEmpty(mobile)){
            RefGeocodeCriteria refGeocodeCriteria  = new RefGeocodeCriteria();
            refGeocodeCriteria.createCriteria().andDistrictFullCodeEqualTo(districtFullCode);
            list=refGeocodeMapper.selectByCriteria(refGeocodeCriteria);
            if(null!=list&&list.size()>0){
                RefGeocode refGeocode=list.get(0);
                geoCodeId=refGeocode.getId();
            }
            DeliverAddressCriteria deliverAddressCriteria=new DeliverAddressCriteria();
            deliverAddressCriteria.createCriteria().andGeocodeIdEqualTo(geoCodeId).andAddressEqualTo(address).andMobileEqualTo(mobile).andUidEqualTo(uid);
            DeliverAddress deliverAddress=null;
            List<DeliverAddress> deliverAddressList=deliverAddressMapper.selectByCriteria(deliverAddressCriteria);
            if(null!=deliverAddressList&&deliverAddressList.size()>0){
                deliverAddress=deliverAddressList.get(0);
                addressId=deliverAddress.getId();
            }else{
                deliverAddress=new DeliverAddress();
                deliverAddress.setGeocodeId(geoCodeId);
                deliverAddress.setAddress(address);
                deliverAddress.setMobile(mobile);
                deliverAddress.setUid(uid);
                deliverAddressMapper.insertSelective(deliverAddress);
                addressId=deliverAddress.getId();
            }

        }
        return addressId;
    }
}
