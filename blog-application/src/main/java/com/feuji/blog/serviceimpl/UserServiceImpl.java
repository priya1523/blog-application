package com.feuji.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.ResourceAlreadyExistException;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	//for create the user
	@Override
	public UserDto createUser(UserDto userDto) throws Exception
	{
		User user=this.userDtoToUser(userDto);
		if (this.userRepository.existsByEmail(user.getEmail())) //check email is already exist or not
		{
			throw new ResourceAlreadyExistException(602, "User", "Email");
		}
		else
		{
			try {
				user=this.userRepository.save(user);
				return this.userToUserDto(user); 
			}
			catch (Exception e) {
				throw new Exception("Something went wrong while creating user");
			}
		}
		
	}

	//for update the user by userId
	@Override
	public UserDto updateUser(UserDto userDto, int userId) throws Exception 
	{
		User user=this.userRepository.findById(userId)
									.orElseThrow(()->new ResourceNotFoundException(601,"User", "userId", userId));
		if (this.userRepository.existsByEmail(userDto.getEmail())) //check email is already exist or not
		{
			throw new ResourceAlreadyExistException(602, "User", "Email");
		}
		try {
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			user=this.userRepository.save(user);
			return this.userToUserDto(user);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while updating user");
		}
	}

	//get the single user by userId
	@Override
	public UserDto getUser(int userId) throws Exception 
	{
		User user=this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException(601,"User", "userId", userId));
		try {
			return this.userToUserDto(user);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while getting user by userId");
		}
	}

	//get the list of users
	@Override
	public List<UserDto> getAllUsers() throws Exception 
	{
		try {
			List<User> users=this.userRepository.findAll();
		return users.stream()
					.map(u->this.userToUserDto(u))
					.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while getting list of users");
		}
		
	}

	//delete the user by userId
	@Override
	public void deleteUser(int userId) throws Exception 
	{
		User user=this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException(601,"User", "userId", userId));
		try {
			this.userRepository.delete(user);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while deleting user");
		}
	}
	
	/**
	 * take the User object and convert it into UserDto object
	 * @param User
	 * @return UserDto
	 */
	public UserDto userToUserDto(User user)
	{
		return this.modelMapper.map(user, UserDto.class);
	}
	
	/**
	 * take the UserDto object and convert it into User object
	 * @param UserDto
	 * @return User
	 */
	public User userDtoToUser(UserDto userDto)
	{
		return this.modelMapper.map(userDto, User.class);
	}
}
