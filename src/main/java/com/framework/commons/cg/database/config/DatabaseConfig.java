package com.framework.commons.cg.database.config;

public class DatabaseConfig {
	private JdbcConfig jdbcConfig;
	private TableConfig tableConfig;

	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}

	public void setJdbcConfig(JdbcConfig jdbcConfig) {
		this.jdbcConfig = jdbcConfig;
	}

	public TableConfig getTableConfig() {
		return tableConfig;
	}

	public void setTableConfig(TableConfig tableConfig) {
		this.tableConfig = tableConfig;
	}
}