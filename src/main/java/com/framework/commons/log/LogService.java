package com.framework.commons.log;

public interface LogService {
	String save(String type, String content, String clientIp, String requestUrl, String requestParam, String responseResult, String executeMethod, Long executeTime);
}