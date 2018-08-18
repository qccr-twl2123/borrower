package com.trj.jk.web.config;

import com.trj.jk.web.config.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.trj.jk.web.config.interceptor.SecurityInterceptor;
import com.trj.jk.web.domain.entity.thirdparty.soopay.NotifySignableAgreementRequestParam;
import com.trj.jk.web.domain.entity.thirdparty.soopay.SignableAgreementRequesParam;
import com.trj.jk.web.service.thirdparty.soopay.CardSoopayServiceImpl;
import com.trj.jk.web.service.thirdparty.soopay.TradeSoopayServiceImpl;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger	LOG							= LoggerFactory.getLogger(WebMvcConfig.class);

	@Value("${app.config.security.exclude}")
	private String				securityExcludePathPatterns	= null;
	
	@Autowired 
	@Qualifier(value="agreementParamWithoutNotify")
	SignableAgreementRequesParam agreementParamWithoutNotify;
	
	@Autowired
	@Qualifier(value="agreementParamWithNotify")
	NotifySignableAgreementRequestParam agreementParamWithNotify;

	@Autowired
	private TokenInterceptor tokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);

		LOG.info("securityExcludePathPatterns={}", securityExcludePathPatterns);
		registry.addInterceptor(tokenInterceptor).addPathPatterns("/outRegister/**");

		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
		//API must be exclude
		interceptorRegistration.excludePathPatterns("/v3/*/**");
		if (StringUtils.hasText(securityExcludePathPatterns)) {
			String[] excludes = securityExcludePathPatterns.split(",");
			for (String ex : excludes) {
				if (StringUtils.hasText(ex)) {
					interceptorRegistration.excludePathPatterns(ex.trim());
				}
			}
		}

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/doc/**").addResourceLocations("/api/doc/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

		Jackson2ObjectMapperFactoryBean factory = new Jackson2ObjectMapperFactoryBean();
		factory.setSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		factory.afterPropertiesSet();
		MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
		jackson2Converter.setObjectMapper(factory.getObject());
		converters.add(jackson2Converter);
	}
	
	@Bean
	public CardSoopayServiceImpl soopayCardService(){
		CardSoopayServiceImpl bean = new CardSoopayServiceImpl();
		bean.setAgreementParam(agreementParamWithoutNotify);
		return bean;
	}
	
	@Bean
	public TradeSoopayServiceImpl tradeSoopayService(){
		TradeSoopayServiceImpl bean= new TradeSoopayServiceImpl();
		bean.setAgreementParam(agreementParamWithNotify);
		bean.setPayAgreementParam(agreementParamWithoutNotify);
		return bean;
	} 
}
