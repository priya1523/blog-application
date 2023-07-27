package com.feuji.blog.config;

/**
 * @author Priya Patel
 * This class contains all the constant variable
 */
public class AppConstatnt 
{
	/**
	 * This is a constant for page number
	 */
	public static final String PAGE_NUMBER="0";
	
	/**
	 * This is a constant for page element size
	 */
	public static final String PAGE_SIZE="5";
	

	/**
	 * This is a constant for sorting order,asc or desc
	 */	
	public static final String SORT_ORDER="asc";
	
	/**
	 * This ia a constant for jwt token validity
	 */
	public static final int JWT_TOKEN_VALIDITY=5*60*60;
	
	/**
	 * This is a secret key for blog application
	 */
	public static final String SECRET="jwttokenkeyforblogapplication";
	
	/**
	 * This is for user role id
	 */
	public static final int USER_ROLE_ID=501;
	
	/**
	 * This is for the admin role id
	 */
	public static final int ADMIN_ROLE_ID=502;


}
