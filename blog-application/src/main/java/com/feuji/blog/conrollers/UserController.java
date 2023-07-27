package com.feuji.blog.conrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	 * @return ResponseEntity<UserDto>
	 * @throws Exception 
	 */
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws Exception
	{
		userDto=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
	
	/**
	 * This is a api for update user by userId, accept the PUT http request
	 * @param UserDto
	 * @param userId
	 * @return ResponseEntity<UserDto>
	 * @throws Exception 
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(name = "userId") int userId) throws Exception
	{
		userDto=this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
	}
	
	/**
	 * This is a api for the get the single user by userId, accept the GET http request
	 * @param userId
	 * @return ResponseEntity<UserDto>
	 * @throws Exception 
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable(name = "userId") int userId) throws Exception
	{
		UserDto userDto=this.userService.getUser(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	/**
	 * This is a api call for the get all the users, accept the GET http request
	 * @return ResponseEntity<List<UserDto>>
	 * @throws Exception 
	 */
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() throws Exception
	{
		List<UserDto> userDtos=this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
	}
	
	/**
	 * This is a api call for the delete the user by userId, accept the DELETE http request
	 * @param userId
	 * @return ResponseEntity<ApiResponse>
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "userId") int userId) throws Exception
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted SuccessFully !",true),HttpStatus.OK);
	}
	
	
}
