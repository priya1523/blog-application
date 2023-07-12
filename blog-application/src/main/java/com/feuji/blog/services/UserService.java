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
	 */
	UserDto createUser(UserDto userDto);
	
	/**
	 * Update the user by userId
	 * @param UserDto
	 * @param userId
	 * @return UserDto
	 */
	UserDto updateUser(UserDto userDto,int userId);
	
	/**
	 * get the single user by userId
	 * @param userId
	 * @return UserDto
	 */
	UserDto getUser(int userId);
	
	/**
	 * get the list of users
	 * @return List<UserDto>
	 */
	List<UserDto> getAllUsers();
	
	/**
	 * delete the user by id
	 * @param userId
	 */
	void  deleteUser(int userId);
}
