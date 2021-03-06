package com.framework.commons.report;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ReportBean {

	public List<Map<String, Object>> loadReportData(String dsName, String datasetName, Map<String, Object> parameters) {
		//根据报表名称获取 数据项
		List<Map<String, Object>> list =new ArrayList();//tUserDataSetService.findDataItem(datasetName);
		return list;
	}
}