package com.feuji.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTokenException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	private String message;
	
	private int errorCode;
	
	public InvalidTokenException(int errorCode,String message)
	{
		super(String.format("%s",message));
		this.message=message;
		this.errorCode=errorCode;
	}
}
