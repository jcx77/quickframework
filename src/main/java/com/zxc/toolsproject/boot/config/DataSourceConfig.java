package com.zxc.toolsproject.boot.config;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import com.zxc.toolsproject.commons.mybatis.plugin.AuditPlugin;
import com.zxc.toolsproject.commons.utils.JdbcUtils;

import java.io.IOException;
import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.zxc.toolsproject.**.mapper.**")
public class DataSourceConfig {
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(JdbcUtils.getDriverClassName(url));
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMinimumIdle(5);
		dataSource.setMaximumPoolSize(20);
		dataSource.setAutoCommit(false);
		dataSource.setIdleTimeout(5 * 60 * 1000);
		dataSource.setMaxLifetime(20 * 60 * 1000);
		dataSource.setConnectionTimeout(20 * 1000);
		return dataSource;
	}

	@Bean
	public ResourcePatternResolver resourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ResourcePatternResolver resourcePatternResolver) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(resourcePatternResolver.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:com/zxc/toolsproject/**/mapper/**/" + JdbcUtils.getDbType(url) + "/*.xml"));
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { new AuditPlugin() });
		return sqlSessionFactoryBean;
	}
}