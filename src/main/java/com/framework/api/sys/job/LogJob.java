package com.framework.api.sys.job;


import com.framework.api.sys.log.service.SysLogService;
import com.framework.commons.quartz.QuartzJob;
import com.framework.commons.spring.tools.JdbcTools;
import com.framework.commons.spring.tools.SpringTools;
import com.framework.commons.utils.DateTimeUtils;
import com.framework.commons.vo.response.Response;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class LogJob extends QuartzJob {
	@Override
	public Response execute() {
		int day = Integer.parseInt(getParam());
		Calendar calendar = DateTimeUtils.toCalendar(JdbcTools.getDate());
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		SysLogService sysLogService = SpringTools.getBean(SysLogService.class);
		sysLogService.deleteByLessCreatedTime(calendar.getTime());
		return Response.success();
	}
}