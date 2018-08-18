package com.trj.jk.web.service.goodsorder.impl;

import com.trj.jk.web.domain.GoodsImg;
import com.trj.jk.web.domain.GoodsImgCriteria;
import com.trj.jk.web.mapper.GoodsImgMapper;
import com.trj.jk.web.mapper.GoodsMapper;
import com.trj.jk.web.service.goodsorder.IGoodsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangxin on 2017/6/1.
 */
@Service
@Transactional
public class GoodsImgServiceImpl implements IGoodsImgService {

    @Autowired
    private GoodsImgMapper goodsImgMapper;


    @Override
    public List<GoodsImg> selectByCriteria(GoodsImgCriteria goodsImgCriteria) {
        return goodsImgMapper.selectByCriteria(goodsImgCriteria);
    }
}
