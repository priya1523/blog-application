package com.feuji.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is for better response to client
 */
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse 
{
	private String message;
	private boolean success;
	
	public ApiResponse(String message, boolean success)
	
	{
		super();
		this.message = message;
		this.success = success;
	}
	
	
}
