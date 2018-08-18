package com.trj.jk.web.service.goodsorder;

import com.trj.jk.web.domain.GoodsImg;
import com.trj.jk.web.domain.GoodsImgCriteria;

import java.util.List;

/**
 * Created by zhangxin on 2017/6/1.
 */
public interface IGoodsImgService {

    public List<GoodsImg> selectByCriteria(GoodsImgCriteria goodsImgCriteria);

}
