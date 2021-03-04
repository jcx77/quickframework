package com.framework.commons.spring.mvc.module;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.framework.commons.utils.DateTimeUtils;


import java.io.IOException;
import java.util.Date;

public class DateModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	public DateModule() {
		addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString(DateTimeUtils.format(value));
			}
		});
		addDeserializer(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				if (StrUtil.isEmpty(p.getText())) {
					return null;
				}
				return DateTimeUtils.parseDate(p.getText());
			}
		});
	}
}