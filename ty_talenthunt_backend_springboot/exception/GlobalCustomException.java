package com.tyss.ty_talenthunt_backend_springboot.exception;

public class GlobalCustomException extends RuntimeException{
	
	private String message;
	
	public GlobalCustomException(String message){
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
