package com.trj.jk.web.service.goodsorder;

import com.trj.jk.web.domain.Goods;

import java.util.Map;

/**
 * Created by zhangxin on 2017/5/31.
 */
public interface IGoodsService {

    public Map<String,Object> selectByPrimaryKey(Integer id);

}
