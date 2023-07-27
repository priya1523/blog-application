package com.feuji.blog.services;

import com.feuji.blog.payloads.JwtAuthRequest;
import com.feuji.blog.payloads.JwtAuthResponse;
import com.feuji.blog.payloads.UserDto;

/**
 * @author Priya Patel
 * This is an interface for the authentication logics
 */
public interface AuthService 
{
	/**
	 * This method takes the username and password and return the jwt token
	 * @param JwtAuthRequest
	 * @return JwtAuthResponse
	 * @throws Exception
	 */
	public JwtAuthResponse createJwtToken(JwtAuthRequest authRequest) throws Exception;
	
	/**
	 * This method is for register the new user
	 * @param UserDto
	 * @return UserDto
	 * @throws Exception
	 */
	public UserDto registerUser(UserDto userDto) throws Exception;
}
