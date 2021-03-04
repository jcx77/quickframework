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
import java.sql.Timestamp;

public class TimestampModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	public TimestampModule() {
		addSerializer(Timestamp.class, new JsonSerializer<Timestamp>() {
			@Override
			public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString(DateTimeUtils.format(value));
			}
		});
		addDeserializer(Timestamp.class, new JsonDeserializer<Timestamp>() {
			@Override
			public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				if (StrUtil.isEmpty(p.getText())) {
					return null;
				}
				return DateTimeUtils.parseTimestamp(p.getText());
			}
		});
	}
}