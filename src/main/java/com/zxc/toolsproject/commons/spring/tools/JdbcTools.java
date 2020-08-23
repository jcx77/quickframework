package com.zxc.toolsproject.commons.spring.tools;


import com.zxc.toolsproject.commons.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

public abstract class JdbcTools {
	private static final JdbcTemplate jdbcTemplate;
	private static final String QUERY_DATE;

	static {
		jdbcTemplate = new JdbcTemplate(SpringTools.getBean(DataSource.class));
		QUERY_DATE = JdbcUtils.getQueryDate(MetaDataTools.getURL());
	}

	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public static Date getDate() {
		return jdbcTemplate.queryForObject(QUERY_DATE, Date.class);
	}
}