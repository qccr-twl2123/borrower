package com.trj.jk.web.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.Banner;
import com.trj.jk.web.domain.LoanProduct;
import com.trj.jk.web.domain.entity.ProductSearchBean;
import com.trj.jk.web.domain.entity.loanProduct.ShowLoanProdcut;
import com.trj.jk.web.model.response.LoanProductDetailRes;

public interface ILoanProductService {

	public Map<String, Object> getSearchConditionInfo();
	
	public PageList<ShowLoanProdcut> SearchLoanProducts(ProductSearchBean searchBean,PageBounds pageBounds);
	
	public Map<String, Object> getLoanProductInfo(Integer productId);
	
	public Map<String, Object> getPropelAndHotProducts();
	
	public List<Banner> getBanner();

	LoanProductDetailRes queryLoanProductsV2(Integer productId);

	LoanProduct getByProductCode(String productCode);

	LoanProduct getProductById(Integer productId);


}
