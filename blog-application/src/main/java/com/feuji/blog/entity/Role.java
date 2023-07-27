package com.feuji.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is an entity class for role resouce
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role 
{
	@Id
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "rol_name")
	private String roleName;
}
