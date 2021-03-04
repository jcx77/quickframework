package com.framework.commons.log;

import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseStatus;
import com.framework.commons.spring.tools.ServletTools;
import com.framework.commons.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AutoLogAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LogService logService;

	@Around("@annotation(com.framework.commons.log.AutoLog)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = null;
		try {
			result = point.proceed();
		} catch (Exception e) {
			result = Response.failure(ResponseStatus.SYS_EXCEPTION_FAILURE, e.getMessage());
			throw e;
		} finally {
			try {
				MethodSignature signature = (MethodSignature) point.getSignature();
				Method method = signature.getMethod();
				String type = method.getAnnotation(AutoLog.class).type().getValue();
				String content = method.getAnnotation(AutoLog.class).value();
				String clientIp = ServletTools.getClientIp();
				String requestUrl = ServletTools.getRequest().getRequestURI();
				String requestParam = JsonUtils.getJson(point.getArgs());
				String responseResult = JsonUtils.getJson(result);
				String className = point.getTarget().getClass().getName();
				String methodName = method.getName();
				String executeMethod = className + "." + methodName + "()";
				Long executeTime = System.currentTimeMillis() - beginTime;
				String id = logService.save(type, content, clientIp, requestUrl, requestParam, responseResult, executeMethod, executeTime);
				logger.info("日志写入成功 {}", id);
			} catch (Exception e) {
				logger.error("日志写入失败", e);
			}
		}
		return result;
	}
}