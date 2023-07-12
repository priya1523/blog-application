package com.feuji.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto
{
	private int userId;

	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
}