package com.trj.jk.web.service.goodsorder.impl;

import com.trj.jk.web.domain.*;
import com.trj.jk.web.mapper.GoodsExtMapper;
import com.trj.jk.web.mapper.GoodsImgMapper;
import com.trj.jk.web.mapper.GoodsMapper;
import com.trj.jk.web.service.goodsorder.IGoodsService;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxin on 2017/5/31.
 */
@Service
@Transactional
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;


    /**
    * @description:根据id获取商品信息
    * @param:id
    * @return:Goods
    * @author:zhangxin
    * @Time: 16:34 2017/5/31
    *
    */
    @Override
    public Map<String,Object> selectByPrimaryKey(Integer id) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if(null!=id&&id>0){
            GoodsCriteria goodsCriteria=new GoodsCriteria();
            goodsCriteria.createCriteria().andIdIsNotNull();
            goodsCriteria.setOrderByClause("id");
            List<Goods> list=goodsMapper.selectByCriteria(goodsCriteria);
            Goods goods=null;
            if(null!=list&&!list.isEmpty()){
                goods=list.get(0);
                GoodsImgCriteria goodsImgCriteria=new GoodsImgCriteria();
                goodsImgCriteria.createCriteria().andGoodsIdEqualTo(goods.getId());
                List<GoodsImg> goodsImgList=goodsImgMapper.selectByCriteria(goodsImgCriteria);
                GoodsExtCriteria extCriteria=new GoodsExtCriteria();
                extCriteria.createCriteria().andGoodsIdEqualTo(goods.getId());
                List<GoodsExt> goodsExtList=goodsExtMapper.selectByCriteria(extCriteria);
                List<String> urlList=new ArrayList<String>();
                for(GoodsImg s:goodsImgList){
                    urlList.add(s.getImgUrl());
                }
                dataMap.put("goods",goods);
                dataMap.put("img",urlList);
                dataMap.put("ext",goodsExtList);
                return dataMap;
            }
        }
        return dataMap;
    }
}
