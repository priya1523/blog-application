package com.feuji.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.repositories.UserRepository;

/**
 * @author Priya Patel
 * This class is for authentication
 */
@Component
public class CustomeUserDetailsService implements UserDetailsService
{
	@Autowired 
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user=this.userRepository.findByEmail(username)
									.orElseThrow(()->new ResourceNotFoundException(601,"User","Email: "+username,0));
		return user;
	}

}
