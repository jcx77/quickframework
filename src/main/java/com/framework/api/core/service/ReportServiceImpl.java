package com.framework.api.core.service;

import com.bstek.ureport.provider.report.ReportFile;
import com.framework.api.sys.report.mapper.SysReportMapper;
import com.framework.api.sys.report.model.SysReport;
import com.framework.commons.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private SysReportMapper sysReportMapper;

	@Transactional(readOnly = true)
	@Override
	public InputStream loadReport(String file) {
		SysReport sysReport = sysReportMapper.selectByPrimaryKey(file);
		if (sysReport != null) {
			return new ByteArrayInputStream(sysReport.getContent().getBytes(StandardCharsets.UTF_8));
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ReportFile> getReportFiles() {
		List<ReportFile> reportFiles = new ArrayList<ReportFile>();
		Example example = new Example(SysReport.class);
		example.orderBy("file");
		List<SysReport> list = sysReportMapper.selectByExample(example);
		for (SysReport sysReport : list) {
			Date updateDate = sysReport.getUpdatedTime() != null ? sysReport.getUpdatedTime() : sysReport.getCreatedTime();
			reportFiles.add(new ReportFile(sysReport.getFile(), updateDate));
		}
		return reportFiles;
	}

	@Transactional
	@Override
	public void deleteReport(String file) {
		sysReportMapper.deleteByPrimaryKey(file);
	}

	@Transactional
	@Override
	public void saveReport(String file, String content) {
		SysReport sysReport = new SysReport();
		sysReport.setFile(file);
		sysReport.setContent(content);
		if (sysReportMapper.existsWithPrimaryKey(file)) {
			sysReportMapper.updateByPrimaryKeySelective(sysReport);
		} else {
			sysReportMapper.insertSelective(sysReport);
		}
	}
}