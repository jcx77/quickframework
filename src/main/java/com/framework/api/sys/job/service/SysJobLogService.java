package com.framework.api.sys.job.service;

import com.framework.api.sys.job.mapper.SysJobLogMapper;
import com.framework.api.sys.job.model.SysJobLog;
import com.framework.api.sys.job.vo.SysJobLogParam;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

@Service
public class SysJobLogService {
	@Autowired
	private SysJobLogMapper sysJobLogMapper;

	@Transactional(readOnly = true)
	public List<SysJobLog> find(SysJobLogParam sysJobLogParam) {
		PageHelper.startPage(sysJobLogParam.getPage(), sysJobLogParam.getRows());
		Example example = new Example(SysJobLog.class);
		example.orderBy("createdTime").desc();
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("jobId", sysJobLogParam.getJobId());
		return sysJobLogMapper.selectByExample(example);
	}
	
	@Transactional
	public void deleteByLessCreatedTime(Date createdTime) {
		Example example = new Example(SysJobLog.class);
		Criteria criteria = example.createCriteria();
		criteria.andLessThan("createdTime", createdTime);
		sysJobLogMapper.deleteByExample(example);
	}
}