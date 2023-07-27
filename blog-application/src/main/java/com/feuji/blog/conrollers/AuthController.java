package com.feuji.blog.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.blog.payloads.JwtAuthRequest;
import com.feuji.blog.payloads.JwtAuthResponse;
import com.feuji.blog.payloads.UserDto;
import com.feuji.blog.services.AuthService;

/**
 * @author Priya Patel
 * This class is a controller for authentication
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	@Autowired
	private AuthService authService;
	
	/**
	 * This method is for login the user/admin and genrate the jwt token
	 * @param AuthRequest
	 * @return ResponseEntity<JwtAuthResponse>
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createJwtToken(@RequestBody JwtAuthRequest authRequest) throws Exception
	{
		JwtAuthResponse authResponse=this.authService.createJwtToken(authRequest);
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.ACCEPTED);
	}
	
	/**
	 *This method is for regiser the new user
	 * @param UserDto
	 * @return ResponseEntity<UserDto>
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) throws Exception
	{
		userDto=this.authService.registerUser(userDto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
}
