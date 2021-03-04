package com.framework.commons.spring.mvc.converter;

import java.util.Date;

import com.framework.commons.utils.DateTimeUtils;
import org.springframework.core.convert.converter.Converter;

import cn.hutool.core.util.StrUtil;

public class DateConverter implements Converter<String, Date> {
	public Date convert(String source) {
		if (StrUtil.isEmpty(source)) {
			return null;
		}
		return DateTimeUtils.parseDate(source);
	}
}