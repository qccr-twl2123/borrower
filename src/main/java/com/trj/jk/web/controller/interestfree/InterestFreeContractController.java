package com.trj.jk.web.controller.interestfree;

import com.trj.commons.result.InvokerResult;
import com.trj.commons.result.Result;
import com.trj.crm.service.api.IJbpmService;
import com.trj.jk.web.domain.Order;
import com.trj.jk.web.domain.OrderAudit;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.constant.Constant;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.goodsorder.IOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxin on 2017/6/22.
 */
@RestController
@RequestMapping(value = {"/freeContract"},method = RequestMethod.POST)
public class InterestFreeContractController {

    private static final Logger LOG = LoggerFactory.getLogger(InterestFreeContractController.class);

    @Autowired
    private IUserService userService;
    
    @Autowired
	private IJbpmService jbpmService;    

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value={"/doInterestFreeSignature"},method=RequestMethod.POST)//loanApplyId=680,productId=1,useAmount=1000,repayType=permonth,term=6
    @ResponseBody
    public Result<Object> doInterestFreeSignature(Integer uid,Integer orderId, String verifyCode, Integer bankCardId){
        final Result<Object> result = new Result<Object>();
        try {
            if(uid!=null&&orderId!=null&&verifyCode!=null && bankCardId!=null){
                userService.doInterestFreeSignature(uid,orderId,verifyCode);
                Order order=orderService.findOrderById(orderId);
              
                //开启审核流程
                InvokerResult invokeResult = jbpmService.startProcess(orderId, Constant.JBPM_JSQ);
                if(invokeResult.isSuccess()){
                    System.out.println("开启流程成功，orderNo=" + order.getOrderNo());     
                    orderService.createOrderAudit(order);
                }else{
                	LOG.warn("开启流程失败，orderNo=" + order.getOrderNo() + "message = " + invokeResult.getMessage());
                }
                
                order.setStatus((byte) 2);
                order.setUserBankCardId(bankCardId);
                orderService.updateOrder(order);
                result.setSuccess(true);
                result.setMessage("电子签章调用成功！");
            }else{
                result.setSuccess(false);
                result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
            }
        }catch(RuntimeException e){
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }
        return result;
    }


}
