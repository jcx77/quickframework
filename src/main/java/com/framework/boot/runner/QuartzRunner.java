package com.framework.boot.runner;


import com.framework.api.sys.job.service.SysJobService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class QuartzRunner implements CommandLineRunner {
	@Autowired
	private SysJobService sysJobService;
	@Autowired
	private Scheduler scheduler;

	@Override
	public void run(String... args) throws Exception {
		sysJobService.init();
		scheduler.start();
	}
}