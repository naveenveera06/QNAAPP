package com.stackroute.exception;

import javax.persistence.NoResultException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class QnAExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { NoResultException.class })
	protected ResponseEntity<Object> handleNoContent(RuntimeException ex, WebRequest request) {

		String bodyOfResponse = "No Content found exception";

		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
	}

	@ExceptionHandler(value = { UnknownError.class })
	protected ResponseEntity<Object> handleUnknown(WebRequest request) {

		String bodyOfResponse = "UnExpected Error!";

		return handleExceptionInternal(null, bodyOfResponse, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, request);
	}


}