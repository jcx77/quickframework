package com.framework.boot.config;

import javax.sql.DataSource;


import com.framework.commons.report.CurrentBuildinDatasource;
import com.framework.commons.report.ReportFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

import com.bstek.ureport.UReportPropertyPlaceholderConfigurer;
import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
/**
* @Description: TODO(@ImportResource 导入Spring的配置文件，让配置文件里面的内容生效；装载ureport)
* @Param: 
* @return: 
* @Author: zcx
* @Date: 2020/9/15 14:47
*/
@Configuration
@ImportResource("classpath:ureport-console-context.xml")
public class ReportConfig {
	@Bean
	public UReportPropertyPlaceholderConfigurer uReportPropertyPlaceholderConfigurer() {
		UReportPropertyPlaceholderConfigurer uReportPropertyPlaceholderConfigurer = new UReportPropertyPlaceholderConfigurer();
		uReportPropertyPlaceholderConfigurer.setLocation(new ClassPathResource("report.properties"));
		return uReportPropertyPlaceholderConfigurer;
	}

	@Bean
	public FilterRegistrationBean<ReportFilter> reportFilter() {
		FilterRegistrationBean<ReportFilter> registration = new FilterRegistrationBean<ReportFilter>();
		registration.setFilter(new ReportFilter());
		registration.setName("reportFilter");
		registration.addUrlPatterns("/ureport/*");
		return registration;
	}

	@Bean
	public ServletRegistrationBean<UReportServlet> UReportServlet() {
		return new ServletRegistrationBean<UReportServlet>(new UReportServlet(), "/ureport/*");
	}

	@Bean
	public BuildinDatasource buildinDatasource(DataSource dataSource) {
		return new CurrentBuildinDatasource(dataSource);
	}
}