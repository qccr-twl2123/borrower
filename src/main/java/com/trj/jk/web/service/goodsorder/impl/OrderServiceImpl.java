package com.trj.jk.web.service.goodsorder.impl;

import com.trj.jk.web.domain.*;
import com.trj.jk.web.dto.OrderItemDTO;
import com.trj.jk.web.mapper.OrderAuditMapper;
import com.trj.jk.web.mapper.OrderItemMapper;
import com.trj.jk.web.mapper.OrderMapper;
import com.trj.jk.web.mapper.RefGeocodeMapper;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.service.IDeliverAddressService;
import com.trj.jk.web.service.ILoginService;
import com.trj.jk.web.service.RedisNumberGenerator;
import com.trj.jk.web.service.goodsorder.IOrderService;
import com.trj.jk.web.util.HttpClientUtils;
import com.trj.jk.web.util.JsonUtils;

import com.trj.jk.web.util.UtilConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mybatis.repository.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxin on 2017/5/31.
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserBasicMapper userBasicMapper;

    @Autowired
    private RefGeocodeMapper refGeocodeMapper;

    @Autowired
    private IDeliverAddressService deliverAddressService;

    @Autowired
    private OrderItemMapper orderItemMapper;
    
    @Autowired 
    private OrderAuditMapper orderAuditMapper;

    @Autowired
    private ILoginService loginService;


    @Autowired
    private RedisNumberGenerator redisNumberGenerator;

    /*//验证推荐码接口地址
    @Value("${app.remote.domain.referralCode}")
    private String referralCode;

    //crm系统域名
    @Value("${app.remote.domain.crm}")
    private String	remoteDomainCrm;*/

    @Override
    public Integer saveOrder(Order order,OrderItem orderItem) {
        String orderNo=redisNumberGenerator.generateNumber("", 5);
        order.setOrderNo(orderNo);
        orderItem.setOrderNo(orderNo);
        orderMapper.insertSelective(order);
        orderItem.setOrderId(order.getId());
        orderItemMapper.insertSelective(orderItem);

        return order.getId();
    }

    @Override
    public Map<String, Object> getOrderAddressByTrjUid(String mobile,Integer trjUid) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        UserBasicCriteria userBasicCriteria=new UserBasicCriteria();
        userBasicCriteria.createCriteria().andMobileEqualTo(mobile).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
        UserBasic userBasic=null;
        List<UserBasic> userBasicList=userBasicMapper.selectByCriteria(userBasicCriteria);
        Integer uid=0;
        if(null!=userBasicList&&userBasicList.size()>0){
            userBasic=userBasicList.get(0);
            DeliverAddress deliverAddress=deliverAddressService.queryForDeliverAddress(userBasic.getId());
            if(null!=deliverAddress){
                RefGeocode refGeocode=refGeocodeMapper.selectByPrimaryKey(deliverAddress.getGeocodeId());
                if(null!=refGeocode){
                    dataMap.put("mobile",deliverAddress.getMobile());
                    dataMap.put("address",deliverAddress.getAddress());
                    Map provinceMap=new HashMap();
                    provinceMap.put("code",refGeocode.getProvinceCode());
                    provinceMap.put("name",refGeocode.getProvinceName());
                    dataMap.put("province",provinceMap);
                    Map cityMap=new HashMap();
                    cityMap.put("code",refGeocode.getCityFullCode());
                    cityMap.put("name",refGeocode.getCityName());
                    dataMap.put("city",cityMap);
                    Map countyMap=new HashMap();
                    countyMap.put("code",refGeocode.getDistrictFullCode());
                    countyMap.put("name",refGeocode.getDistrictName());
                    dataMap.put("county",countyMap);
                }
            }else{
                dataMap.put("province",null);
                dataMap.put("city",null);
                dataMap.put("county",null);
            }
            if(userBasic!=null && userBasic.getTrjUid()==null){
            	userBasic.setTrjUid(trjUid);
            	userBasicMapper.updateByPrimaryKeySelective(userBasic);
            }
            dataMap.put("uid",userBasic.getId());
        }else{
            userBasic=new UserBasic();
            userBasic.setMobile(mobile);
            userBasic.setPassword("123456");
            userBasic.setTrjUid(trjUid);
            uid=loginService.signOnAuto(userBasic);
            dataMap.put("uid",uid);
        }
        return dataMap;
    }

    /*@Override
    public boolean checkReferralCode(String referralCodeStr) {
        boolean flag=false;
        if(StringUtils.isEmpty(referralCodeStr)){
            return true;
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("referralCode",referralCodeStr);
        LOG.info("crm验证推荐码是否正确："+(remoteDomainCrm+referralCode));
        LOG.info("crm验证推荐码是否正确参数："+param.toString());
        String body = HttpClientUtils.httpPost((remoteDomainCrm+referralCode), null, param, null, null);
        Map<String, Object> result = (Map<String, Object>) JsonUtils.stringToObject(body, Map.class);
        if("true".equals(result.get("data").toString())){
            flag=true;
        }
        return flag;
    }*/

    @Override
    public Order findOrderById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public List<OrderItemDTO> getOrderItemByUidOrMobile(Integer uid, String mobile) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("mobile",mobile);
        List<OrderItemDTO> orderItemDTOList=orderMapper.getOrderItemByUidOrMobile(map);
        return orderItemDTOList;
    }

	@Override
	public void createOrderAudit(Order order) {
        OrderAudit orderAudit = new OrderAudit();
        orderAudit.setOrderNo(order.getOrderNo());
        orderAudit.setUid(order.getUid());
        orderAuditMapper.insertSelective(orderAudit);		
	}


    /*public Order getOrderByUid(Integer uid){
        Order order=null;
        OrderCriteria orderCriteria=new OrderCriteria();
        orderCriteria.createCriteria().andUidEqualTo(uid);
        orderCriteria.setOrderByClause("id");
        List<Order> orderList=orderMapper.selectByCriteria(orderCriteria);
        if(null!=orderList&&orderList.size()>0){
            order=orderList.get(0);
        }
        return order;
    }*/



}
