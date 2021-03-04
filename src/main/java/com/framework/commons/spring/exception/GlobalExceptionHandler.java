package com.framework.commons.spring.exception;

import com.framework.commons.vo.response.Response;
import com.framework.commons.vo.response.ResponseStatus;
import com.framework.commons.exception.SysException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
/**
* @Description: TODO(/拦截异常并统一处理)
* @Param:
* @return:
* @Author: zcx
* @Date: 2020/8/11 22:19
*/
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(UnauthorizedException.class)
	public Response handleException(UnauthorizedException e) {
		logger.error("权限异常", e);
		return Response.failure(ResponseStatus.UNAUTHORIZED_FAILURE, "没有权限操作，请联系管理员");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response handleException(MethodArgumentNotValidException e) {
		logger.error("校验异常", e);
		return Response.failure(ResponseStatus.NOTVALID_FAILURE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(SysException.class)
	public Response handleException(SysException e) {
		logger.error("系统异常", e);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		e.printStackTrace(ps);
		return Response.failure(ResponseStatus.SYS_EXCEPTION_FAILURE, e.getMessage(), new String(out.toByteArray()));
	}

	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		logger.error("全局异常", e);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		e.printStackTrace(ps);
		return Response.failure(ResponseStatus.GLOBAL_EXCEPTION_FAILURE, e.getMessage(), new String(out.toByteArray()));
	}
}