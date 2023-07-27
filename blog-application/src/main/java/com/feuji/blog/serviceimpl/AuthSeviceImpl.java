package com.feuji.blog.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feuji.blog.config.AppConstatnt;
import com.feuji.blog.entity.Role;
import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.InvalidTokenException;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.payloads.JwtAuthRequest;
import com.feuji.blog.payloads.JwtAuthResponse;
import com.feuji.blog.payloads.UserDto;
import com.feuji.blog.repositories.RoleRepository;
import com.feuji.blog.repositories.UserRepository;
import com.feuji.blog.security.CustomeUserDetailsService;
import com.feuji.blog.security.JwtTokenHelper;
import com.feuji.blog.services.AuthService;

/**
 * @author Priya Patel
 * This class is an implementation class for the AuthService interface
 */
@Service
public class AuthSeviceImpl implements AuthService
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	//for return the jwt token
	@Override
	public JwtAuthResponse createJwtToken(JwtAuthRequest authRequest) throws Exception 
	{
		try {
			this.authenticate(authRequest.getUsername(), authRequest.getPassword());
			UserDetails userDetails=this.customeUserDetailsService.loadUserByUsername(authRequest.getUsername());
			String token=this.jwtTokenHelper.generateToken(userDetails);
			JwtAuthResponse authResponse=new JwtAuthResponse();
			authResponse.setToken(token);
			return authResponse;
		}
		catch(DisabledException e)
		{
			throw new InvalidTokenException(604, "User not found with "+authRequest.getUsername());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	// for authentication match the username with password
	private void authenticate(String username,String password)
	{
		try 
		{
			UsernamePasswordAuthenticationToken authenticationToken=
					new UsernamePasswordAuthenticationToken(username, password);
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(DisabledException e)
		{
			throw new InvalidTokenException(604, "Worng credintials !");
		}
		
	}

	//for register the new user
	@Override
	public UserDto registerUser(UserDto userDto) throws Exception 
	{
		try {
			User user=this.modelMapper.map(userDto, User.class);
			String password=this.passwordEncoder.encode(userDto.getPassword());
			user.setPassword(password);
			
			Role role=this.roleRepository.findById(AppConstatnt.USER_ROLE_ID)
											.orElseThrow(()->new ResourceNotFoundException(601,"Role","roleId",AppConstatnt.USER_ROLE_ID));
			Set<Role> roles=new HashSet<>();
			roles.add(role);
			user.setRoles(roles);
			user=this.userRepository.save(user);
			return this.modelMapper.map(user, UserDto.class);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while regiter user !");
		}
	}
}
