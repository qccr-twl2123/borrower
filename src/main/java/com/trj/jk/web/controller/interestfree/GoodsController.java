package com.trj.jk.web.controller.interestfree;

import com.trj.commons.result.Result;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.service.goodsorder.IGoodsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxin on 2017/5/31.
 */
@RestController
@RequestMapping(value = {"/goods"},method = RequestMethod.POST)
public class GoodsController {

    private static final Logger LOG = Logger.getLogger(GoodsController.class);

    @Autowired
    private IGoodsService goodsService;


    @RequestMapping(value = {"/getGoods"})
    @ResponseBody
    public Result<Object> selectByPrimaryKey(Integer id) {
        final Result<Object> result = new Result<Object>();
        try {
            if (null != id && id > 0) {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap = goodsService.selectByPrimaryKey(id);
                result.setData(dataMap);
                result.setSuccess(true);
                result.setMessage(ErrorMessageConstant.SUCCESS);
                return result;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            result.setSuccess(false);
            result.setMessage(ErrorMessageConstant.ERR_OCCURS);
        }

        return result;
    }


}
