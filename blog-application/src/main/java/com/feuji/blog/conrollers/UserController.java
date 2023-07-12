package com.feuji.blog.conrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.blog.payloads.ApiResponse;
import com.feuji.blog.payloads.UserDto;
import com.feuji.blog.services.UserService;


/**
 * @author Priya Patel
 * This class is a controller class which contains the all he user related api
 */
@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	/**
	 * This is a api for create new user, accept the POST http request
	 * @param UserDto
	 * @return UserDto
	 */
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		userDto=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
	
	/**
	 * This is a api for update user by userId, accept the PUT http request
	 * @param UserDto
	 * @param userId
	 * @return UserDto
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable(name = "userId") int userId)
	{
		userDto=this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
	}
	
	/**
	 * This is a api for the get the single user by userId, accept the GET http request
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable(name = "userId") int userId)
	{
		UserDto userDto=this.userService.getUser(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	/**
	 * This is a api call for the get all the users, accept the GET http request
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> userDtos=this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "userId") int userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted SuccessFully !",true),HttpStatus.OK);
	}
	
	
}
