package com.feuji.blog.payloads;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is a dto class for user resource
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDto
{
	private int userId;

	@NotEmpty(message = "Name can not be empty !")
	@Size(min = 4 , message = "Name should contain minimum 4 characters !")
	private String name;
	
	@NotEmpty(message = "Email can not be empty !")
	@Email(message = "Enter the valid email !")
	private String email;
	
	@NotEmpty(message = "Password can not be empty !")
	@Size(min = 4,message = "Passowrd should contains minimum 4 characters !")
	private String password;
	
	@NotEmpty(message = "About can not be empty !")
	private String about;
	
	private Set<RoleDto> roles;
}
