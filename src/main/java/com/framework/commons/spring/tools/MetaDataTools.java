package com.framework.commons.spring.tools;

import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public abstract class MetaDataTools {
	private static String url;

	static {
		try {
			url = (String) JdbcUtils.extractDatabaseMetaData(SpringTools.getBean(DataSource.class), new DatabaseMetaDataCallback() {
				@Override
				public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
					return dbmd.getURL();
				}
			});
		} catch (MetaDataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getURL() {
		return url;
	}
}