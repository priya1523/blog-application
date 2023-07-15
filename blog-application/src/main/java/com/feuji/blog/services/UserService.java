package com.feuji.blog.services;

import java.util.List;

import com.feuji.blog.payloads.UserDto;


/**
 * @author Priya Patel
 * This interface contains the User Crud methods
 */
public interface UserService 
{
	/**
	 * Create a new User
	 * @param UserDto
	 * @return UserDto
	 * @throws Exception 
	 */
	UserDto createUser(UserDto userDto) throws Exception;
	
	/**
	 * Update the user by userId
	 * @param UserDto
	 * @param userId
	 * @return UserDto
	 * @throws Exception 
	 */
	UserDto updateUser(UserDto userDto,int userId) throws Exception;
	
	/**
	 * get the single user by userId
	 * @param userId
	 * @return UserDto
	 * @throws Exception 
	 */
	UserDto getUser(int userId) throws Exception;
	
	/**
	 * get the list of users
	 * @return List<UserDto>
	 * @throws Exception 
	 */
	List<UserDto> getAllUsers() throws Exception;
	
	/**
	 * delete the user by id
	 * @param userId
	 * @throws Exception 
	 */
	void  deleteUser(int userId) throws Exception;
}
