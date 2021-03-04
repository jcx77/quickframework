package com.framework.boot.listener;


import com.framework.commons.spring.tools.SpringTools;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {
	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		SpringTools.setApplicationContext(event.getApplicationContext());
	}
}