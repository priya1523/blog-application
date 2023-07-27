package com.feuji.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.blog.entity.Role;

/**
 * @author Priya Patel
 * This interface is a repository for role resource,database connection of role resource
 */
public interface RoleRepository extends JpaRepository<Role, Integer>
{

}
