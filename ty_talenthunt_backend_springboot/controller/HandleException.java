package com.tyss.ty_talenthunt_backend_springboot.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tyss.ty_talenthunt_backend_springboot.dto.Response;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> getException(Exception ex) {
//		Response resp = new Response();
//		resp.setError(true);
//		resp.setMessage(ex.getMessage());
//		return new ResponseEntity<Object>(resp , HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error" , "true");

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("message(s)", errors);

        return new ResponseEntity<>(body,status);

    }
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		Map<String, Object> body = new LinkedHashMap<>();
		if (mostSpecificCause != null) {
        body.put("error" , "true");
        body.put("message" , mostSpecificCause.getMessage());
		}

		return new ResponseEntity<Object>(body , status);
		
		
    }
	}



