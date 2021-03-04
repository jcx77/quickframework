package com.framework.commons.report;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

public class CurrentBuildinDatasource implements BuildinDatasource {
	private DataSource dataSource;

	public CurrentBuildinDatasource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String name() {
		return "当前数据源";
	}

	@Override
	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
}
