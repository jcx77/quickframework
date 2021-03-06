package com.framework.commons.quartz;

import java.util.Date;

public interface JobLogService {
	String save(String jobId, String param, String result, String server, Date beginTime, Date endTime);
}