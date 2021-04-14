package com.implementation.redis.exception;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice()
@Generated
@Slf4j
public class ErrorAdvice {
	
	private static final String INPUT_ERROR = "Input data error ";

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> notFoundException(NotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Resource not found", details);
		ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		log.error("Resource not found: " + ex.getLocalizedMessage());
		return errorResponse;
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> badRequestException(BadRequestException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		log.error(INPUT_ERROR + ex.getLocalizedMessage());
		return errorResponse;
	}

	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<Object> conflictException(ConflictException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Conflict", details);
		ResponseEntity<Object> errorResponse = new ResponseEntity<>(error, HttpStatus.CONFLICT);
		log.error("Conflict: " + ex.getLocalizedMessage());
		return errorResponse;
	}

	@ExceptionHandler(InternalException.class)
	public final ResponseEntity<Object> internalException(InternalException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Internal error", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public final ResponseEntity<Object> methodConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
			details.add(error.getMessage());
		}
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BindException.class)
	public final ResponseEntity<Object> bindExceptionException(BindException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public final ResponseEntity<Object> handleException(HttpMessageNotReadableException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add("The method you are querying requires a valid JSON in the body of the Http request");
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public final ResponseEntity<Object> handleException(NumberFormatException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add("The method you are querying requires a number or a list of numbers as a parameter");
		ErrorResponse error = new ErrorResponse(INPUT_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
