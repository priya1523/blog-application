package com.feuji.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.blog.entity.User;

/**
 * @author Priya Patel
 * This interface is for User resource database connection
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

	/**
	 * This method is check the email is already present or not
	 * @param email
	 * @return boolean
	 */
	boolean existsByEmail(String email);

	/**
	 * This method get the user by email
	 * @param email
	 * @return Optional<User>
	 */
	Optional<User> findByEmail(String email);
}
