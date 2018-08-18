package com.trj.jk.web.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.enums.DocumentItemEnum;
import com.trj.jk.web.enums.LoanProductEnum;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.trj.jk.web.domain.Banner;
import com.trj.jk.web.domain.BannerCriteria;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.CityCriteria;
import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.CodeCriteria;
import com.trj.jk.web.domain.LoanProduct;
import com.trj.jk.web.domain.LoanProductCriteria;
import com.trj.jk.web.domain.entity.ProductSearchBean;
import com.trj.jk.web.domain.entity.loanProduct.ShowLoanProdcut;
import com.trj.jk.web.domain.entity.loanProduct.ShowProductDetails;
import com.trj.jk.web.mapper.BannerMapper;
import com.trj.jk.web.mapper.CityMapper;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.mapper.LoanProductMapper;
import com.trj.jk.web.mapper.RefGeocodeMapper;
import com.trj.jk.web.service.ILoanProductService;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.model.response.LoanProductDetailRes;

@Service
@Transactional
public class LoanProductServiceImpl implements ILoanProductService{

	private final static String LABEL_ICO = "2017/08/25/1503627448164.png";

	@Value("${app.upload.download}")
	private String downloadImgUrl;
	@Value("${server.url}")
	private String serverUrl;

	@Resource
	private RefGeocodeMapper refGeocodeMapper;
	
	@Resource
	private CodeMapper codeMapper;
	
	@Resource
	private LoanProductMapper loanProductMapper;
	
	@Resource
	private CityMapper cityMapper;
	
	@Resource
	private BannerMapper bannerMapper;

	@Override
	public Map<String, Object> getSearchConditionInfo() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//获取城市
		List<City> allHotCities = new ArrayList<City>();
		City defaultCity = new City();
		defaultCity.setName("全部");
		allHotCities.add(defaultCity);
		CityCriteria cityCriteria = new CityCriteria();
		cityCriteria.createCriteria().andIsHotEqualTo(Byte.parseByte("1"));
		List<City> hotCities = cityMapper.selectByCriteria(cityCriteria);
		for(City city:hotCities){
			allHotCities.add(city);
		}
		dataMap.put("hotCity",allHotCities);
		
		CodeCriteria codeCriteria1 = new CodeCriteria();
		codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.LOAN_LIMIT);
		codeCriteria1.setOrderByClause("CODE_NO asc");
		List<Code>  limitCodeList = codeMapper.selectByCriteria(codeCriteria1);
		dataMap.put("LimitCodeList", limitCodeList);
		
		CodeCriteria codeCriteria2 = new CodeCriteria();
		codeCriteria2.createCriteria().andCodeKeyEqualTo(UtilConstant.LOAN_TERM_);
		codeCriteria2.setOrderByClause("CODE_NO asc");
		List<Code>  interestCodeList = codeMapper.selectByCriteria(codeCriteria2);
		dataMap.put("interestCodeList", interestCodeList);
		return dataMap;
	}

	@Override
	public PageList<ShowLoanProdcut> SearchLoanProducts(ProductSearchBean searchBean, PageBounds pageBounds) {
		searchBean.setSort(Byte.parseByte("0"));
		if("0".equals(searchBean.getCity()) || searchBean.getCity()==null){
			searchBean.setCity("");
		}
		switch (searchBean.getLimitCode()) {
		case 1:
			searchBean.setLimitScopeFrom(0);
			searchBean.setLimitScopeTo(10000);
			break;
		case 2:
			searchBean.setLimitScopeFrom(10000);
			searchBean.setLimitScopeTo(50000);
			break;
		case 3:
			searchBean.setLimitScopeFrom(50000);
			searchBean.setLimitScopeTo(100000);
			break;
		case 4:
			searchBean.setLimitScopeFrom(100000);
			searchBean.setLimitScopeTo(200000);
			break;
		case 5:
			searchBean.setLimitScopeFrom(200000);
			searchBean.setLimitScopeTo(200000);
			break;
		default:
			searchBean.setLimitScopeFrom(0);
			searchBean.setLimitScopeTo(0);
			break;
		}
		
		switch (searchBean.getTermCode()) {
		case 1:
			searchBean.setTermFromDay(0);
			searchBean.setTermToDay(30);
			break;
		case 2:
			searchBean.setTermFromDay(30);
			searchBean.setTermToDay(90);
			break;
		case 3:
			searchBean.setTermFromDay(90);
			searchBean.setTermToDay(360);
			break;
		case 4:
			searchBean.setTermFromDay(365);
			searchBean.setTermToDay(720);
			break;
		case 5:
			searchBean.setTermFromDay(720);
			searchBean.setTermToDay(720);
			break;
		default:
			searchBean.setTermFromDay(0);
			searchBean.setTermToDay(0);
			break;
		}
		PageList<LoanProduct> dataList = (PageList<LoanProduct>)loanProductMapper.SearchLoanProducts(searchBean,pageBounds);
		PageList<ShowLoanProdcut> resultList = new PageList<ShowLoanProdcut>(dataList.getPaginator());
		if(dataList!=null&&!dataList.isEmpty()){
			for(LoanProduct loanProduct:dataList){
				if(loanProduct.getProductCode() != null && UtilConstant.LOAN_PRODUCTCODE_GJJ.equals(loanProduct.getProductCode())){
					continue;
				}
				resultList.add(loanProductChangeShowLoanProduct(loanProduct,Byte.parseByte("1")));
			}
		}
		return resultList;
	}

	@Override
	public Map<String, Object> getLoanProductInfo(Integer productId) {
		LoanProduct loanProduct = loanProductMapper.selectByPrimaryKey(productId);
		if(loanProduct!=null){
			Map<String, Object> result = LoanProductChangeShowProductDetails(loanProduct);
			return result;
		}else{
			
			return null;
		}
	}

	@Override
	public LoanProductDetailRes queryLoanProductsV2(Integer productId){
		LoanProduct loanProduct = loanProductMapper.selectByPrimaryKey(productId);
		Assert.isNull(loanProduct, ErrorMessageConstant.ERR_NULL_PRODUCT);
		LoanProductDetailRes loanProductDetailRes = new LoanProductDetailRes();
		loanProductDetailRes.setProductUrl(loanProduct.getProductUrl());
		Integer amount = loanProduct.getLimitScopeTo();
		if(amount>10000){
			loanProductDetailRes.setAmount((amount/10000)+".0万");
		}else{
			loanProductDetailRes.setAmount(amount+"元");
		}

		CodeCriteria codeCriteria = new CodeCriteria();
		codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanProduct.getInterestUnit());
		if("MD".equals(loanProduct.getProductCode())){
			loanProductDetailRes.setInterest(loanProduct.getInterestScopeTo()+"%");
		}else{
			loanProductDetailRes.setInterest(loanProduct.getInterestScopeFrom()+"-"+loanProduct.getInterestScopeTo()+"%");
		}
		if(loanProduct.getIsPropel()==1 && loanProduct.getType()==0){
			loanProductDetailRes.setTermDescribe("期限范围");
			loanProductDetailRes.setLendingTimeDescribe("放款周期");
			loanProductDetailRes.setInterestDescribe("综合利率");
		}else{
			loanProductDetailRes.setTermDescribe("期限范围");
			loanProductDetailRes.setLendingTimeDescribe("放款周期");
			loanProductDetailRes.setInterestDescribe("综合利率");
		}
		if(loanProduct.getProductCode().equals(UtilConstant.LOAN_PRODUCTCODE_YND)){
			loanProductDetailRes.setInterestDescribe("月利率");
		}
		CodeCriteria codeCriteria1 = new CodeCriteria();
		codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanProduct.getTermUnit());
		loanProductDetailRes.setTerm(loanProduct.getTermScopeFrom()+"-"+loanProduct.getTermScopeTo()+codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName());
		loanProductDetailRes.setLendingTime(loanProduct.getLendingTime()+"个工作日");
		loanProductDetailRes.setProductId(loanProduct.getId());
		loanProductDetailRes.setProductCode(loanProduct.getProductCode());
		loanProductDetailRes.setProductType(loanProduct.getProductCode());
		loanProductDetailRes.setDescription(loanProduct.getIntroduce());
		loanProductDetailRes.setProductName(loanProduct.getName());
		loanProductDetailRes.setAmountDescribe("最高额度");
		loanProductDetailRes.setGuideUrl(getGuideUrl(loanProduct.getName()));
		loanProductDetailRes.setLabelUrl(serverUrl+"/images/icon/group-s.png");
		if(loanProduct.getProductCode().equals(LoanProductEnum.GOU_CHE_BAO.getProductCode())){
			loanProductDetailRes.setShortDescription("限合作经销商处购车用户");
		}
		loanProductDetailRes.setLoanProductItemResList(DocumentItemEnum.getByProductCode(loanProduct.getProductCode(),serverUrl));
        return loanProductDetailRes;
	}

	@Override
	public LoanProduct getByProductCode(String productCode) {
		LoanProductCriteria loanProductCriteria = new LoanProductCriteria();
		loanProductCriteria.createCriteria().andProductCodeEqualTo(productCode);
		List<LoanProduct> loanProductList = loanProductMapper.selectByCriteria(loanProductCriteria);
		if(CollectionUtils.isNotEmpty(loanProductList)){return  loanProductList.get(0);}
		return null;
	}

	@Override
	public LoanProduct getProductById(Integer productId) {
		return loanProductMapper.selectByPrimaryKey(productId);
	}

	public String getGuideUrl(String loanName){
		BannerCriteria bannerCriteria = new BannerCriteria();
		bannerCriteria.createCriteria().andNameLike("%"+loanName+"%");
		List<Banner> banners= bannerMapper.selectByCriteria(bannerCriteria);
		if(CollectionUtils.isEmpty(banners)){return "";}
		return banners.get(0).getLinkUrl();
	}


	public Map<String, Object> LoanProductChangeShowProductDetails(LoanProduct loanProduct){
		ShowProductDetails showProductDetails = new ShowProductDetails();
		ShowLoanProdcut showLoanProdcut = loanProductChangeShowLoanProduct(loanProduct,Byte.parseByte("1"));
		showProductDetails.setProductId(loanProduct.getId());
		String limit = "";
		if(loanProduct.getLimitScopeFrom()>=10000){
			limit+=loanProduct.getLimitScopeFrom()/10000;
		}else{
			limit+=loanProduct.getLimitScopeFrom();
		}
		limit+="-";
		if(loanProduct.getLimitScopeTo()>=10000){
			limit+=loanProduct.getLimitScopeTo()/10000;
		}else{
			limit+=loanProduct.getLimitScopeTo();
		}
		if(loanProduct.getLimitScopeTo()>10000){
			limit+="万";
		}else{
			limit+="元";
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("left","借款额度：");
		map1.put("right", limit);
		map1.put("type", 0);
		dataList.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("left","借款期限：");
		map2.put("right", showLoanProdcut.getTerm());
		map2.put("type", 0);
		dataList.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("left","还款方式：");
		if(loanProduct.getRepayType()!=null){
			CodeCriteria codeCriteria = new CodeCriteria();
			codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.REPAY_TYPE).andCodeNoEqualTo(loanProduct.getRepayType());
			String repayType = codeMapper.selectByCriteria(codeCriteria).get(0).getCodeName();
			map3.put("right", repayType);
		}else{
			map3.put("right", "");
		}
		map3.put("type", 0);
		dataList.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("left",showLoanProdcut.getInterestDescribe());
		map4.put("right", showLoanProdcut.getInterest());
		map4.put("type", 0);
		dataList.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("left","放款时间：");
		map5.put("right", showLoanProdcut.getLendingTime());
		map5.put("type", 0);
		dataList.add(map5);
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("left","申请条件");
		map6.put("right", loanProduct.getApplyCondition());
		map6.put("type", 1);
		dataList.add(map6);
		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("left","所需资料");
		map7.put("right", loanProduct.getRequiredInformation());
		map7.put("type", 1);
		dataList.add(map7);
		Map<String, Object> map8 = new HashMap<String, Object>();
		map8.put("left","特别说明");
		map8.put("right", loanProduct.getSpecialIllustrate());
		map8.put("type", 1);
		dataList.add(map8);
		dataMap.put("productInfo", dataList);
		dataMap.put("productUrl", loanProduct.getProductUrl());
		dataMap.put("productId", loanProduct.getId());
		dataMap.put("productName", loanProduct.getName());
		dataMap.put("productType", loanProduct.getProductCode());
		return dataMap;
	}
	
	@Override
	public Map<String, Object> getPropelAndHotProducts() {
		Map<String, Object> dateMap = new HashMap<String, Object>();
		//推荐产品
		LoanProductCriteria loanProductCriteria1 = new LoanProductCriteria();
		loanProductCriteria1.createCriteria().andIsPropelEqualTo(Byte.parseByte(UtilConstant.VALIDATE)).andTypeEqualTo(Byte.parseByte("0"));
		List<LoanProduct> propelProdcut = loanProductMapper.selectByCriteria(loanProductCriteria1);
		List<ShowLoanProdcut> showLoanProdcuts = new ArrayList<ShowLoanProdcut>();
		for(LoanProduct loanProduct:propelProdcut){
			ShowLoanProdcut showLoanProdcut = loanProductChangeShowLoanProduct(loanProduct,Byte.parseByte("0"));
			showLoanProdcuts.add(showLoanProdcut);
		}
		dateMap.put("propelProdcut", showLoanProdcuts);
		//热门产品
		LoanProductCriteria loanProductCriteria2 = new LoanProductCriteria();
		loanProductCriteria2.createCriteria().andIsHotEqualTo(Byte.parseByte(UtilConstant.VALIDATE)).andTypeEqualTo(Byte.parseByte("0"));
		List<LoanProduct> hotProduct = loanProductMapper.selectByCriteria(loanProductCriteria2);
		List<ShowLoanProdcut> showLoanProdcuts2 = new ArrayList<ShowLoanProdcut>();
		for(LoanProduct loanProduct:hotProduct){
			ShowLoanProdcut showLoanProdcut = loanProductChangeShowLoanProduct(loanProduct,Byte.parseByte("0"));
			showLoanProdcuts2.add(showLoanProdcut);
		}
		dateMap.put("hotProduct", showLoanProdcuts2);
		return dateMap;
	}
	
	public ShowLoanProdcut loanProductChangeShowLoanProduct(LoanProduct loanProduct,Byte type){
		ShowLoanProdcut showLoanProdcut = new ShowLoanProdcut();
	    showLoanProdcut.setProductId(loanProduct.getId());
		showLoanProdcut.setProductName(loanProduct.getName());
		showLoanProdcut.setProductIconUrl(loanProduct.getProductIconUrl());
		showLoanProdcut.setFirstIllustrationUrl(loanProduct.getFirstIllustrationUrl());
		showLoanProdcut.setSecondIllustrationUrl(loanProduct.getSecondIllustrationUrl());
		showLoanProdcut.setAmountDescribe("最高额度");
		Integer amount = loanProduct.getLimitScopeTo();
		if(amount != null && amount>10000){
			showLoanProdcut.setAmount((amount/10000)+".0万");
		}else{
			showLoanProdcut.setAmount(amount+"元");
		}
		CodeCriteria codeCriteria = new CodeCriteria();
		codeCriteria.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanProduct.getInterestUnit());
		if("MD".equals(loanProduct.getProductCode())){
			showLoanProdcut.setInterest(loanProduct.getInterestScopeTo()+"%");
		}else{	
			showLoanProdcut.setInterest(loanProduct.getInterestScopeFrom()+"-"+loanProduct.getInterestScopeTo()+"%");
		}
		if(loanProduct.getIsPropel()==1 && type==0){
			showLoanProdcut.setTermDescribe("期限范围");
			showLoanProdcut.setLendingTimeDescribe("放款周期");
			showLoanProdcut.setInterestDescribe("综合利率");
		}else{			
			showLoanProdcut.setTermDescribe("期限范围：");
			showLoanProdcut.setLendingTimeDescribe("放款周期：");
			showLoanProdcut.setInterestDescribe("综合利率：");
		}
		if(loanProduct.getProductCode().equals(UtilConstant.LOAN_PRODUCTCODE_YND)){
			showLoanProdcut.setInterestDescribe("月利率");
		}
		CodeCriteria codeCriteria1 = new CodeCriteria();
		codeCriteria1.createCriteria().andCodeKeyEqualTo(UtilConstant.DATE_UNIT_KEY).andCodeNoEqualTo(loanProduct.getTermUnit());
		showLoanProdcut.setTerm(loanProduct.getTermScopeFrom()+"-"+loanProduct.getTermScopeTo()+codeMapper.selectByCriteria(codeCriteria1).get(0).getCodeName());
		showLoanProdcut.setLendingTime(loanProduct.getLendingTime()+"个工作日");
		showLoanProdcut.setProductType(loanProduct.getProductCode());
		showLoanProdcut.setIsOpen(loanProduct.getIsOpen());
		return showLoanProdcut;
	}

	@Override
	public List<Banner> getBanner() {
		BannerCriteria bannerCriteria = new BannerCriteria();
		bannerCriteria.setOrderByClause("sort asc");
		return bannerMapper.selectByCriteria(bannerCriteria);
	}
}
