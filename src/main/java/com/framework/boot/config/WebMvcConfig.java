package com.framework.boot.config;

import com.framework.commons.spring.mvc.converter.DateConverter;
import com.framework.commons.utils.JsonUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>();
//		registration.setFilter(new CorsFilter());
//		registration.setName("corsFilter");
//		registration.addUrlPatterns("/api/*");
//		return registration;
//	}

//	@Bean
//	public FilterRegistrationBean<XssFilter> xssFilter() {
//		FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<XssFilter>();
//		registration.setFilter(new XssFilter());
//		registration.setName("xssFilter");
//		registration.addUrlPatterns("/api/*");
//		return registration;
//	}

	//shiro Filter--拦截器
	@Bean
	public FilterRegistrationBean<AbstractShiroFilter> shiroFilter(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception {
		FilterRegistrationBean<AbstractShiroFilter> registration = new FilterRegistrationBean<>();
		registration.setName("shiroFilter");
		registration.setFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
		registration.addUrlPatterns("/api/*");
		return registration;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return JsonUtils.getObjectMapper();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/view/html/login.html");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}
}