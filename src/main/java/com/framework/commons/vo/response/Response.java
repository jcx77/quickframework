package com.framework.commons.vo.response;

import java.io.Serializable;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	private int status;
	private String msg;
	private String stackTrace;

	public Response() {
		this(ResponseStatus.SUCCESS, "操作成功");
	}

	public Response(String msg) {
		this(ResponseStatus.SUCCESS, msg);
	}

	public Response(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public Response(int status, String msg, String stackTrace) {
		this.status = status;
		this.msg = msg;
		this.stackTrace = stackTrace;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public static Response success() {
		return new Response();
	}

	public static Response success(String msg) {
		return new Response(msg);
	}

	public static Response failure(int status, String msg) {
		return new Response(status, msg);
	}

	public static Response failure(int status, String msg, String stackTrace) {
		return new Response(status, msg, stackTrace);
	}
}