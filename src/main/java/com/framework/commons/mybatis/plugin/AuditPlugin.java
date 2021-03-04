package com.framework.commons.mybatis.plugin;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Properties;

import javax.persistence.Column;

import com.framework.commons.constant.DataConstant;
import com.framework.commons.mybatis.tools.AuditTools;
import com.framework.commons.spring.tools.JdbcTools;
import com.framework.commons.utils.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;


@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class AuditPlugin implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		Object parameter = null;
		for (int i = 1; i <= 1; i++) {
			parameter = invocation.getArgs()[i];
		}
		if (parameter == null) {
			return invocation.proceed();
		}
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Field[] fields = parameter.getClass().getDeclaredFields();
		if (SqlCommandType.INSERT == sqlCommandType) {
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), parameter.getClass());
					String name = column.name().toUpperCase();
					switch (name) {
					case "ID_":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, StringUtils.uuid());
						}
						break;
					case "CREATED_BY":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, AuditTools.getUserId());
						}
						break;
					case "CREATED_TIME":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, JdbcTools.getDate());
						}
						break;
					case "DELETE_FLAG":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, DataConstant.DELETE_FLAG_0);
						}
						break;
					}
				}
			}
		} else if (SqlCommandType.UPDATE == sqlCommandType) {
			for (Field field : fields) {
				Column column = field.getAnnotation(Column.class);
				if (column != null) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), parameter.getClass());
					String name = column.name().toUpperCase();
					switch (name) {
					case "UPDATED_BY":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, AuditTools.getUserId());
						}
						break;
					case "UPDATED_TIME":
						if (propertyDescriptor.getReadMethod().invoke(parameter) == null) {
							propertyDescriptor.getWriteMethod().invoke(parameter, JdbcTools.getDate());
						}
						break;
					}
				}
			}
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties properties) {
	}
}