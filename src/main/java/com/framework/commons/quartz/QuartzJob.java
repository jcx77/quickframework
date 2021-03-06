package com.framework.commons.quartz;

import com.framework.commons.spring.tools.ServletTools;
import com.framework.commons.spring.tools.SpringTools;
import com.framework.commons.utils.DateTimeUtils;
import com.framework.commons.utils.JsonUtils;
import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseStatus;
import org.quartz.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class QuartzJob implements Job {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private String param;

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date beginTime = DateTimeUtils.getDate();
		Response response = null;
		try {
			response = execute();
		} catch (Exception e) {
			response = Response.failure(ResponseStatus.GLOBAL_EXCEPTION_FAILURE, e.getMessage());
			throw e;
		} finally {
			try {
				JobLogService jobLogService = SpringTools.getBean(JobLogService.class);
				String jobId = context.getJobDetail().getKey().getName();
				String param = getParam();
				String result = JsonUtils.getJson(response);
				String server = ServletTools.getServerName();
				Date endTime = DateTimeUtils.getDate();
				String id = jobLogService.save(jobId, param, result, server, beginTime, endTime);
				logger.info("任务日志写入成功 {}", id);
			} catch (Exception e) {
				logger.error("任务日志写入失败", e);
			}
		}
	}

	public abstract Response execute();
}
