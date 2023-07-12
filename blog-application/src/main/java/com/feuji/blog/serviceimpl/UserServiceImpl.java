package com.feuji.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.payloads.UserDto;
import com.feuji.blog.repositories.UserRepository;
import com.feuji.blog.services.UserService;

/**
 * @author Priya Patel
 * This class is the implementation class for the UserService interface
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository; //inject the userrepository object
	
	//for create the user
	@Override
	public UserDto createUser(UserDto userDto)
	{
		User user=this.userDtoToUser(userDto);
		user=this.userRepository.save(user);
		return this.userToUserDto(user);
	}

	//for update the user by userId
	@Override
	public UserDto updateUser(UserDto userDto, int userId) 
	{
		User user=this.userRepository.findById(userId)
									.orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user=this.userRepository.save(user);
		return this.userToUserDto(user);
	}

	//get the single user by userId
	@Override
	public UserDto getUser(int userId) 
	{
		User user=this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		return this.userToUserDto(user);
	}

	//get the list of users
	@Override
	public List<UserDto> getAllUsers() 
	{
		List<User> users=this.userRepository.findAll();
		return users.stream()
					.map(u->this.userToUserDto(u))
					.collect(Collectors.toList());
	}

	//delete the user by userId
	@Override
	public void deleteUser(int userId) 
	{
		User user=this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		this.userRepository.delete(user);
	}
	
	/**
	 * take the User object and convert it into UserDto object
	 * @param User
	 * @return UserDto
	 */
	public UserDto userToUserDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
	/**
	 * take the UserDto object and convert it into User object
	 * @param UserDto
	 * @return User
	 */
	public User userDtoToUser(UserDto userDto)
	{
		User user=new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}
}
