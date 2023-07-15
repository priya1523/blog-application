package com.feuji.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Priya Patel
 * This is an Exception class which is indicating the resources are not found for the entity
 */

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException 
{
	/**
	 * default serialVersionUid
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private long value;
	private int errorCode;
	
	public ResourceNotFoundException(int errorCode,String resourceName, String fieldName, long value)
	{
		super(String.format("%s not found with %s : %d", resourceName,fieldName,value));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.value = value;
		this.errorCode=errorCode;
	}
	
	
}
