package com.framework.test;

import java.util.ResourceBundle;


import com.framework.commons.utils.JasyptUtils;
import com.framework.commons.utils.JdbcUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DatabaseRun {
	private String url;
	private String username;
	private String password;

	public DatabaseRun() {
		ResourceBundle bundle = ResourceBundle.getBundle("application-dev");
		url = bundle.getString("spring.datasource.url");
		username = bundle.getString("spring.datasource.username");
		password = bundle.getString("spring.datasource.password");
	}

	public void execute() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(JdbcUtils.getDriverClassName(url));
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(JasyptUtils.decrypt(password));
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.addScript(new ClassPathResource(String.format("sql/quartz/tables_%s.sql", JdbcUtils.getDbType(url))));
		populator.addScript(new ClassPathResource(String.format("sql/%s/sys_schema.sql", JdbcUtils.getDbType(url))));
		populator.addScript(new ClassPathResource(String.format("sql/%s/sys_data.sql", JdbcUtils.getDbType(url))));
		populator.addScript(new ClassPathResource(String.format("sql/%s/flow_schema.sql", JdbcUtils.getDbType(url))));
		populator.addScript(new ClassPathResource(String.format("sql/%s/flow_data.sql", JdbcUtils.getDbType(url))));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}

	public static void main(String[] args) {
		new DatabaseRun().execute();
	}
}