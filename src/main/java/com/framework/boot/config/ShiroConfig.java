package com.framework.boot.config;

import com.framework.commons.shiro.authc.JwtRealm;
import com.framework.commons.shiro.filter.JwtFilter;
import com.framework.commons.shiro.mgt.JwtWebSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public SubjectFactory subjectFactory() {
		return new JwtWebSubjectFactory();
	}

	@Bean
	public SessionStorageEvaluator sessionStorageEvaluator() {
		DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false);
		return sessionStorageEvaluator;
	}

	@Bean
	public SubjectDAO subjectDAO(SessionStorageEvaluator sessionStorageEvaluator) {
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
		return subjectDAO;
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(false);
		return sessionManager;
	}

	@Bean
	public Realm realm() {
		return new JwtRealm();
	}

	@Bean
	public SecurityManager securityManager(SubjectFactory subjectFactory, SubjectDAO subjectDAO, SessionManager sessionManager, Realm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSubjectFactory(subjectFactory);
		securityManager.setSubjectDAO(subjectDAO);
		securityManager.setSessionManager(sessionManager);
		securityManager.setRealm(realm);
		return securityManager;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		filters.put("jwt", new JwtFilter());
		shiroFilterFactoryBean.setFilters(filters);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/api/core/login", "anon");
		filterChainDefinitionMap.put("/api/**", "jwt");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
}