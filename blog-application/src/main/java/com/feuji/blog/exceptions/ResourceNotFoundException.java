package com.feuji.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Priya Patel
 * This is an Exception class which is indicating the resouces are not found for the entity
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
	
	public ResourceNotFoundException(String resourceName, String fieldName, long value)
	{
		super(String.format("%s not found with %s : %l", resourceName,fieldName,value));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.value = value;
	}
	
	
}
