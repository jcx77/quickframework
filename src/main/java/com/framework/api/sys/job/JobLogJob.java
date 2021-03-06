package com.framework.api.sys.job;


import com.framework.api.sys.job.service.SysJobLogService;
import com.framework.commons.quartz.QuartzJob;
import com.framework.commons.spring.tools.JdbcTools;
import com.framework.commons.spring.tools.SpringTools;
import com.framework.commons.utils.DateTimeUtils;
import com.framework.commons.vo.response.Response;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class JobLogJob extends QuartzJob {
	@Override
	public Response execute() {
		int day = Integer.parseInt(getParam());
		Calendar calendar = DateTimeUtils.toCalendar(JdbcTools.getDate());
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		SysJobLogService sysJobLogService = SpringTools.getBean(SysJobLogService.class);
		sysJobLogService.deleteByLessCreatedTime(calendar.getTime());
		return Response.success();
	}
}