package com.feuji.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is a dto class for the role resource
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto 
{
	private int roleId;
	
	private String roleName;
}
