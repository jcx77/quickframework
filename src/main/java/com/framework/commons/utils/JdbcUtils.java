package com.framework.commons.utils;

public abstract class JdbcUtils {
	public static final String H2_TYPE = "h2";
	private static final String H2_URL = "jdbc:h2:";
	private static final String H2_DRIVER_CLASS_NAME = "org.h2.Driver";
	private static final String H2_QUERY_DATE = "select current_timestamp";

	public static final String MYSQL_TYPE = "mysql";
	private static final String MYSQL_URL = "jdbc:mysql:";
	private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String MYSQL_QUERY_DATE = "select current_timestamp";

	public static final String SQL_SERVER_TYPE = "sqlserver";
	private static final String SQL_SERVER_URL = "jdbc:sqlserver:";
	private static final String SQL_SERVER_DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String SQL_SERVER_QUERY_DATE = "select getdate()";

	public static final String ORACLE_TYPE = "oracle";
	private static final String ORACLE_URL = "jdbc:oracle:";
	private static final String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static final String ORACLE_QUERY_DATE = "select current_timestamp from dual";

	public static String getDriverClassName(String url) {
		if (url == null) {
			return null;
		}
		if (url.startsWith(H2_URL)) {
			return H2_DRIVER_CLASS_NAME;
		} else if (url.startsWith(MYSQL_URL)) {
			return MYSQL_DRIVER_CLASS_NAME;
		} else if (url.startsWith(SQL_SERVER_URL)) {
			return SQL_SERVER_DRIVER_CLASS_NAME;
		} else if (url.startsWith(ORACLE_URL)) {
			return ORACLE_DRIVER_CLASS_NAME;
		} else {
			return null;
		}
	}

	public static String getDbType(String url) {
		if (url == null) {
			return null;
		}
		if (url.startsWith(H2_URL)) {
			return H2_TYPE;
		} else if (url.startsWith(MYSQL_URL)) {
			return MYSQL_TYPE;
		} else if (url.startsWith(SQL_SERVER_URL)) {
			return SQL_SERVER_TYPE;
		} else if (url.startsWith(ORACLE_URL)) {
			return ORACLE_TYPE;
		} else {
			return null;
		}
	}

	public static String getQueryDate(String url) {
		if (url == null) {
			return null;
		}
		if (url.startsWith(H2_URL)) {
			return H2_QUERY_DATE;
		} else if (url.startsWith(MYSQL_URL)) {
			return MYSQL_QUERY_DATE;
		} else if (url.startsWith(SQL_SERVER_URL)) {
			return SQL_SERVER_QUERY_DATE;
		} else if (url.startsWith(ORACLE_URL)) {
			return ORACLE_QUERY_DATE;
		} else {
			return null;
		}
	}
}