package com.trj.jk.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.trj.jk.web.framework.ds.DBContextHolder;
import com.trj.jk.web.framework.ds.DynamicDataSource;
import com.trj.jk.web.util.SpringUtil;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableRedisHttpSession
public class JkWebApplication extends SpringBootServletInitializer {

	private static final Logger				LOG					= LoggerFactory.getLogger(JkWebApplication.class);

	private static Class<JkWebApplication>	applicationClass	= JkWebApplication.class;

	public static void main(String[] args) {
		final ApplicationContext  applicationContext=SpringApplication.run(JkWebApplication.class, args);
		SpringUtil.setApplicationContext(applicationContext);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

				String[] beanNames = ctx.getBeanDefinitionNames();
				Arrays.sort(beanNames);
				for (String bn : beanNames) {
					LOG.info(bn);
				}
			}
		};
	}
	
	 @Bean
	 public EmbeddedServletContainerCustomizer containerCustomizer(){
	        return new EmbeddedServletContainerCustomizer() {
	            @Override
	            public void customize(ConfigurableEmbeddedServletContainer container) {
	                 container.setSessionTimeout(600);//单位为S
	           }
	     };
	 }

	@Bean
	@ConfigurationProperties("app.datasource.jkweb")
	public PoolConfiguration jkwebPoolConfiguration() {
		return new PoolProperties();
	}

//	@Bean(initMethod = "createPool")
//	public DataSource jkwebDataSource() {
//		return new DataSource(jkwebPoolConfiguration());
//	}

	@Bean
	@Primary
	public javax.sql.DataSource jkwebDynamicDataSource() throws Exception {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		DataSource dataSourceMaster = new DataSource(jkwebPoolConfiguration());
		dataSourceMaster.createPool();
		targetDataSources.put(DBContextHolder.DATA_SOURCE_JKWEB_MASTER, dataSourceMaster);
//		targetDataSources.put(DBContextHolder.DATA_SOURCE_JKWEB_SLAVE1, dataSourceMaster);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
		return dynamicDataSource;
	}
	

	@Bean
	@ConfigurationProperties("app.datasource.tourongjia")
	public PoolConfiguration tourongjiaPoolConfiguration() {
		return new PoolProperties();
	}

	@Bean(initMethod = "createPool")
	public DataSource tourongjiaDataSource() {
		return new DataSource(tourongjiaPoolConfiguration());
	}

	@Bean
	public JdbcTemplate trjJdbcTemplate() {
		return new JdbcTemplate(tourongjiaDataSource());
	}

	@Bean
	@ConfigurationProperties(prefix = "app.datasource.changfudai")
	public PoolConfiguration changfudaiPoolConfiguration() {
		return new PoolProperties();
	}

	@Bean(initMethod = "createPool")
	public DataSource changfudaiDataSource() {
		return new DataSource(changfudaiPoolConfiguration());
	}

	@Bean
	public JdbcTemplate cfdJdbcTemplate() {
		return new JdbcTemplate(changfudaiDataSource());
	}

	@Bean
	@ConfigurationProperties(prefix="app.thread.pool")
	public ThreadPoolTaskExecutor taskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
	
	@Bean
	@ConfigurationProperties("app.datasource.trbs")
	public PoolConfiguration trbsPoolConfiguration() {
		return new PoolProperties();
	}
	
	@Bean(initMethod = "createPool")
	public DataSource trbsDataSource() {
		return new DataSource(trbsPoolConfiguration());
	}
	
	@Bean
	public JdbcTemplate trbsJdbcTemplate() {
		return new JdbcTemplate(trbsDataSource());
	}
}
