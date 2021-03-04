package com.framework.commons.cg.mybatis.converter;



import com.framework.commons.utils.JdbcUtils;

import java.util.HashMap;
import java.util.Map;

public class DBConverterFactory {
	private static Map<String, DBConverter> map = new HashMap<String, DBConverter>();
	static {
		map.put(JdbcUtils.H2_TYPE, new H2Converter());
		map.put(JdbcUtils.MYSQL_TYPE, new MysqlConverter());
		map.put(JdbcUtils.ORACLE_TYPE, new OracleConverter());
		map.put(JdbcUtils.SQL_SERVER_TYPE, new SqlServerConverter());
	}

	public static DBConverter create(String db) {
		DBConverter dbConverter = map.get(db);
		if (dbConverter == null) {
			throw new RuntimeException("无法创建[" + db + "]数据库的转换器");
		}
		return dbConverter;
	}
}