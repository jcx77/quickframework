package com.framework.commons.cg.database.api;


import com.framework.commons.cg.database.dom.Table;
import com.framework.commons.cg.database.config.DatabaseConfig;
import com.framework.commons.cg.database.dom.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseGenerator {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private DatabaseConfig databaseConfig;

	public DatabaseGenerator(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	public List<Table> generate() {
		List<Table> tables = null;
		try {
			tables = new ArrayList<Table>();
			Class.forName(databaseConfig.getJdbcConfig().getDriverClassName());
			Properties props = new Properties();
			props.setProperty("user", databaseConfig.getJdbcConfig().getUsername());
			props.setProperty("password", databaseConfig.getJdbcConfig().getPassword());
			props.setProperty("remarks", "true");
			props.setProperty("useInformationSchema", "true");
			Connection con = DriverManager.getConnection(databaseConfig.getJdbcConfig().getUrl(), props);
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs1 = dbmd.getTables(databaseConfig.getTableConfig().getCatalog(), databaseConfig.getTableConfig().getSchema(), databaseConfig.getTableConfig().getTableName(), null);
			while (rs1.next()) {
				Table table = new Table();
				table.setName(rs1.getString("TABLE_NAME"));
				table.setComment(rs1.getString("REMARKS"));
				List<Column> columns = new ArrayList<Column>();
				ResultSet rs2 = dbmd.getColumns(databaseConfig.getTableConfig().getCatalog(), databaseConfig.getTableConfig().getSchema(), table.getName(), null);
				while (rs2.next()) {
					Column column = new Column();
					column.setName(rs2.getString("COLUMN_NAME"));
					column.setType(rs2.getString("TYPE_NAME"));
					column.setSize(rs2.getInt("COLUMN_SIZE"));
					column.setPrecision(rs2.getInt("DECIMAL_DIGITS"));
					if (rs2.getString("IS_NULLABLE").equals("NO")) {
						column.setRequired(true);
					} else {
						column.setRequired(false);
					}
					String comment = rs2.getString("REMARKS");
					if (null != comment && !"".equals(comment)) {
						column.setComment(comment);
					} else {
						column.setComment(column.getName());
					}
					column.setPrimaryKey(false);
					columns.add(column);
				}
				rs2.close();
				ResultSet rs3 = dbmd.getPrimaryKeys(databaseConfig.getTableConfig().getCatalog(), databaseConfig.getTableConfig().getSchema(), table.getName());
				while (rs3.next()) {
					for (Column column : columns) {
						if (column.getName().equals(rs3.getString("COLUMN_NAME"))) {
							column.setPrimaryKey(true);
						}
					}
				}
				rs3.close();
				table.setColumns(columns);
				tables.add(table);
			}
			rs1.close();
			con.close();
			if (tables.isEmpty()) {
				throw new RuntimeException("未找到" + databaseConfig.getTableConfig().getTableName());
			}
			logger.info("创建Tables成功");
		} catch (Exception e) {
			logger.error("创建Tables失败", e);
		}
		return tables;
	}
}