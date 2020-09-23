package com.implementation.redis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InternalException extends RuntimeException {
	public InternalException(String exception) {
		super(exception);
	}
}
