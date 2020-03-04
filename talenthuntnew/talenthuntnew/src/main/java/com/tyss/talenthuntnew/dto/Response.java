package com.tyss.talenthuntnew.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private boolean error;
	private String message;
	private Object data;
	
}
