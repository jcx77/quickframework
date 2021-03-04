package com.framework.commons.report;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DBReportProvider implements ReportProvider {
	@Autowired
	private ReportService reportService;


	private String prefix = "db:";

	@Override
	public InputStream loadReport(String file) {
		if (file.startsWith(prefix)) {
			file = file.substring(prefix.length(), file.length());
		}
		return reportService.loadReport(file);
	}

	@Override
	public void deleteReport(String file) {
		if (file.startsWith(prefix)) {
			file = file.substring(prefix.length(), file.length());
		}
		reportService.deleteReport(file);
	}

	@Override
	public List<ReportFile> getReportFiles() {
		return reportService.getReportFiles();
	}

	@Override
	public void saveReport(String file, String content) {
		if (file.startsWith(prefix)) {
			file = file.substring(prefix.length(), file.length());
		}
		reportService.saveReport(file, content);
	}

	@Override
	public String getName() {
		return "数据库";
	}

	@Override
	public boolean disabled() {
		return false;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}
}