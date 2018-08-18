package com.trj.jk.web.controller.interestfree;

import com.trj.commons.result.Result;
import com.trj.crm.service.api.IJbpmService;
import com.trj.jk.web.domain.Order;
import com.trj.jk.web.domain.OrderItem;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.dto.OrderItemDTO;
import com.trj.jk.web.service.IDeliverAddressService;
import com.trj.jk.web.service.ILoanInfoService;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.goodsorder.IOrderService;
import com.trj.jk.web.util.OrderUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */
@RestController
@RequestMapping(value = {"/order"},method = RequestMethod.POST)
public class OrderController {

    private static final Logger LOG = Logger.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDeliverAddressService deliverAddressService;

    @Autowired
    private ILoanInfoService loanInfoService;
    
	

    /**
    * @description:新增订单并查询该用户在长富贷中是否注册过,如果注册过则返回对应的用户信息
    * @param:
    * @return:
    * @author:zhangxin
    * @Time: 14:20 2017/6/6
    *
    */
    @RequestMapping(value={"/addOrder"})
    @ResponseBody
    public Result<Object> saveOrder(Order order, OrderItem orderItem, Integer uid,String county,String address,String mobile){
        final Result<Object> result = new Result<Object>();
        //1、验证推荐码  2、返回用户信息(银行卡 身份证图片url) 3、返回订单id
        LOG.info("****************uid_:"+uid);
        try {
            if(null!=uid&&uid>0){
                Integer addRessId=deliverAddressService.findAddressIdByDistrictFullCode(county,address,mobile,uid);
                //String orderNo=OrderUtil.getOrderNo("TRJ",orderItem.getGoodsId(),uid);
                //String orderNo=OrderUtil.getOrderNo("TRJ",orderItem.getGoodsId(),uid);
                order.setStatus((byte) 0);
                //order.setOrderNo(orderNo);
                order.setDeliverId(addRessId);
                order.setUserName(mobile);
                //orderItem.setOrderNo(orderNo);
                Integer orderId=orderService.saveOrder(order,orderItem);
                if(null!=orderId&&orderId>0){
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap=userService.getUserInfoByTrjUid(uid);
                    dataMap.put("orderId",orderId);
                    result.setSuccess(true);
                    result.setMessage(ErrorMessageConstant.SAVE_SUCCESS);
                    result.setData(dataMap);

                }else{
                    result.setSuccess(false);
                    result.setMessage("订单插入失败！");
                }
            }else{
                result.setSuccess(false);
                result.setMessage("用户id不允许为空");
            }
        }  catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

    /**
     * @description:进入订单页面查询该用户在长富贷中是否注册过,如果注册过则返回对应的用户信息
     * @param:
     * @return:
     * @author:zhangxin
     * @Time: 14:20 2017/6/6
     *
     */
    @RequestMapping(value={"/findUser"})
    @ResponseBody
    public Result<Object> saveGoodsOrder(String mobile, Integer trjUid){
        LOG.info("*****************mobile:"+mobile);
        final Result<Object> result = new Result<Object>();
        try {
            if(!StringUtils.isEmpty(mobile)&&mobile.matches("^1[3|4|5|7|8][0-9]{9}$")){
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap=orderService.getOrderAddressByTrjUid(mobile, trjUid);
                result.setData(dataMap);
                result.setSuccess(true);
                result.setMessage(ErrorMessageConstant.SUCCESS);
            }else{
                result.setSuccess(false);
                result.setMessage("手机号码不允许为空");
            }
        }  catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

    /**
    * @description:根据uid获取用户订单详情列表
    * @param:
    * @return:
    * @author:
    * @Time: 9:39 2017/6/10
    *
    */
    @RequestMapping(value={"/getOrderItem"})
    @ResponseBody
    public Result<Object> getOrderItemByUid(Integer uid,String mobile){
        final Result<Object> result = new Result<Object>();
        try {
            if(null==uid&&StringUtils.isEmpty(mobile)){
                result.setSuccess(false);
                result.setMessage("用户id和电话号码不能同事为空！");
                return result;
            }
            List<OrderItemDTO> orderItemDTOList=orderService.getOrderItemByUidOrMobile(uid,mobile);
            result.setSuccess(true);
            result.setMessage(ErrorMessageConstant.SUCCESS);
            result.setData(orderItemDTOList);
        }  catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

    @RequestMapping(value={"/getOrderBill"})
    @ResponseBody
    public Result<Object> getOrderBill(OrderItemDTO orderItemDTO){
        final Result<Object> result = new Result<Object>();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap=loanInfoService.getLoanRepayPlanByLimitId(orderItemDTO.getLimitId());
            if(dataMap.size()>0){
                dataMap.put("goodsName",orderItemDTO.getGoodsName());
                dataMap.put("orderNo",orderItemDTO.getOrderNo());
                dataMap.put("limitid",orderItemDTO.getLimitId());
                result.setSuccess(true);
                result.setMessage(ErrorMessageConstant.SUCCESS);
                result.setData(dataMap);
            }else{
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_OCCURS);
            }
        }  catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }

}
