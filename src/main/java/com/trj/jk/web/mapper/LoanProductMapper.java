package com.trj.jk.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.LoanProduct;
import com.trj.jk.web.domain.LoanProductCriteria;
import com.trj.jk.web.domain.entity.ProductSearchBean;
import com.trj.mybatis.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanProductMapper extends BaseMapper<LoanProduct, LoanProductCriteria, Integer> {
	
	public List<LoanProduct> SearchLoanProducts(ProductSearchBean searchBean, PageBounds pageBounds);

	public LoanProduct searchProductByOrderId(Integer orderId);
}