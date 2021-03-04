package com.framework.commons.vo.response;

public class ResponseResult<T> extends Response {
	private static final long serialVersionUID = 1L;

	private T result;

	public ResponseResult(T result) {
		super();
		this.result = result;
	}

	public ResponseResult(String msg, T result) {
		super(msg);
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}