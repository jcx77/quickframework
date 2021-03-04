package com.framework.commons.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.commons.spring.mvc.module.DateModule;
import com.framework.commons.spring.mvc.module.TimestampModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
		objectMapper.registerModule(new DateModule());
		objectMapper.registerModule(new TimestampModule());
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static String getJson(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static <T> T getValue(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static <T> List<T> getList(String json, Class<T> clazz) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static Map<String, Object> getMap(String json) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}