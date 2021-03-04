package com.framework.commons.vo.response;

public interface ResponseStatus {
	int SUCCESS = 0;
	int AUTHENTICATION_FAILURE = 100;
	int UNAUTHORIZED_FAILURE = 200;
	int NOTVALID_FAILURE = 300;
	int SYS_EXCEPTION_FAILURE = 400;
	int GLOBAL_EXCEPTION_FAILURE = 500;
}