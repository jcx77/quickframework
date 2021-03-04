package com.framework.api.sys.log.service;

import cn.hutool.core.util.StrUtil;
import com.framework.api.sys.log.mapper.SysLogMapper;
import com.framework.api.sys.log.model.SysLog;
import com.github.pagehelper.PageHelper;
import com.framework.api.sys.vo.param.SysLogParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

@Service
public class SysLogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@Transactional(readOnly = true)
	public List<SysLog> find(SysLogParam sysLogParam) {
		PageHelper.startPage(sysLogParam.getPage(), sysLogParam.getRows());
		Example example = new Example(SysLog.class);
		example.orderBy("createdTime");
		Criteria criteria = example.createCriteria();
		if (StrUtil.isNotEmpty(sysLogParam.getType())) {
			criteria.andEqualTo("type", sysLogParam.getType());
		}
		if (StrUtil.isNotEmpty(sysLogParam.getContent())) {
			criteria.andLike("content", "%" + sysLogParam.getContent() + "%");
		}
		if (sysLogParam.getCreatedTimeBegin() != null) {
			criteria.andGreaterThanOrEqualTo("createdTime", sysLogParam.getCreatedTimeBegin());
		}
		if (sysLogParam.getCreatedTimeEnd() != null) {
			criteria.andLessThanOrEqualTo("createdTime", sysLogParam.getCreatedTimeEnd());
		}
		return sysLogMapper.selectByExample(example);
	}

	@Transactional
	public void deleteByLessCreatedTime(Date createdTime) {
		Example example = new Example(SysLog.class);
		Criteria criteria = example.createCriteria();
		criteria.andLessThan("createdTime", createdTime);
		sysLogMapper.deleteByExample(example);
	}
}