package com.trj.jk.web.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.entity.LimitAuditBean;
import com.trj.jk.web.model.response.CashOutRes;

/**
 * Created by xierongli on 17/9/12.
 */
public interface LoanAuditService {
    PageList<LimitAuditBean> getLimitAuditList(Integer uid, PageBounds pageBounds);
    PageList<LimitAuditBean> getLimitAuditListByProduct(Integer uid, String productCode, PageBounds pageBounds);
    CashOutRes getCashOutModel(Integer userId,String orderNo, String productCode);
}
