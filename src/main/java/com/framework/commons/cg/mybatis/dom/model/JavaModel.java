package com.framework.commons.cg.mybatis.dom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JavaModel {
	private String packageName;
	private Set<String> imports = new TreeSet<String>();
	private String tableName;
	private String className;
	private String lowerClassName;
	private List<Field> fields = new ArrayList<Field>();

	public String getLowerClassName() {
		return lowerClassName;
	}

	public void setLowerClassName(String lowerClassName) {
		this.lowerClassName = lowerClassName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void addImports(String s) {
		imports.add(s);
	}

	public Set<String> getImports() {
		return imports;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		this.lowerClassName = lowerFirst(className);
	}

	public void addFields(Field field) {
		fields.add(field);
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(packageName);
		sb.append(System.getProperties().getProperty("line.separator"));
		for (String s : imports) {
			sb.append(s);
			sb.append(System.getProperties().getProperty("line.separator"));
		}
		sb.append(String.format("%s [%s]", className, tableName));
		for (Field field : fields) {
			sb.append(System.getProperties().getProperty("line.separator"));
			sb.append(String.format("%-20s", field.getName()));
			sb.append(String.format("%-10s", field.getType().getSimpleName()));
			sb.append(String.format("%-20s", field.getColumn().getName()));
			sb.append(String.format("%-6s", field.getColumn().isPrimaryKey()));
		}
		return sb.toString();
	}
	/**
	 * @Description: TODO(将方法名首字母转换为小写)
	 * @Param: [className]
	 * @return: java.lang.String
	 * @Author: zcx
	 * @Date: 2020/7/6 8:42
	 */
	private String lowerFirst(String className) {
		char[] chars = className.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
}