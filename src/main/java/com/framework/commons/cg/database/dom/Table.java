package com.framework.commons.cg.database.dom;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private String name;

	private String comment;

	private List<Column> columns = new ArrayList<Column>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s [%s]", name, comment));
		for (Column column : columns) {
			sb.append(System.getProperties().getProperty("line.separator"));
			sb.append(String.format("%-20s", column.getName()));
			sb.append(String.format("%-10s", column.getType()));
			sb.append(String.format("%-12s", column.getSize()));
			sb.append(String.format("%-2s", column.getPrecision()));
			sb.append(String.format("%-6s", column.isPrimaryKey()));
			sb.append(String.format("%-6s", column.isRequired()));
			sb.append(String.format("%-20s", column.getComment()));
		}
		return sb.toString();
	}
}