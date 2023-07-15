package com.feuji.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Priya Patel
 * This is an exception class indicate that the resource are already exist
 */

@Getter
@Setter
public class ResourceAlreadyExistException extends RuntimeException
{
	/**
	 * default serialVersionUid
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private int errorCode;
	
	public ResourceAlreadyExistException( int errorCode,String resourceName, String fieldName)
	{
		super(String.format("%s with the %s already exist",resourceName,fieldName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.errorCode = errorCode;
	}
	
}
