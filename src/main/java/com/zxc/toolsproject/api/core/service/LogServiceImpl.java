package com.zxc.toolsproject.api.core.service;


import com.zxc.toolsproject.api.sys.mapper.SysLogMapper;
import com.zxc.toolsproject.api.core.model.SysLog;
import com.zxc.toolsproject.commons.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@Transactional
	@Override
	public String save(String type, String content, String clientIp, String requestUrl, String requestParam, String responseResult, String executeMethod, Long executeTime) {
		SysLog sysLog = new SysLog();
		sysLog.setType(type);
		sysLog.setContent(content);
		sysLog.setClientIp(clientIp);
		sysLog.setRequestUrl(requestUrl);
		sysLog.setRequestParam(requestParam);
		sysLog.setResponseResult(responseResult);
		sysLog.setExecuteMethod(executeMethod);
		sysLog.setExecuteTime(executeTime);
		sysLogMapper.insertSelective(sysLog);
		return sysLog.getId();
	}
}