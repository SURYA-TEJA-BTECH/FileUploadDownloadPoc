package com.surya.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.surya.FileNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleUnhandledException(Exception exception) {

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
				exception.getMessage());

		problemDetail.setTitle("Internal server error");
		problemDetail.setProperty("error category", "Generic");
		problemDetail.setProperty("timestamp", Instant.now());

		return problemDetail;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request data");

		List<String> errors = new ArrayList<>();

		List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			errors.add(fieldError.getDefaultMessage());
		}

		problemDetail.setTitle("Bad Request");
		problemDetail.setProperty("error category", "Generic");
		problemDetail.setProperty("timestamp", Instant.now());
		problemDetail.setProperty("errors", errors);

		return problemDetail;
	}

	

	@ExceptionHandler(FileNotFoundException.class)

	public ProblemDetail handleFileNotFoundExceptionn(FileNotFoundException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

		problemDetail.setTitle("Not found ");
		problemDetail.setProperty("error category", "Generic");
		problemDetail.setProperty("timestamp", Instant.now());

		return problemDetail;
	}

}
