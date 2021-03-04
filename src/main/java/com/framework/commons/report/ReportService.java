package com.framework.commons.report;

import com.bstek.ureport.provider.report.ReportFile;

import java.io.InputStream;
import java.util.List;

public interface ReportService {
	InputStream loadReport(String file);

	List<ReportFile> getReportFiles();

	void deleteReport(String file);

	void saveReport(String file, String content);
}