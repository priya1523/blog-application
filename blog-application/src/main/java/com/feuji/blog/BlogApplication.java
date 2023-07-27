package com.feuji.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.feuji.blog.config.AppConstatnt;
import com.feuji.blog.entity.Role;
import com.feuji.blog.repositories.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMaper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception 
	{
		System.out.println(this.passwordEncoder.encode("Priya@123"));
		try {
			Role role1=new Role();
			role1.setRoleId(AppConstatnt.USER_ROLE_ID);
			role1.setRoleName("ROLE_USER");
			
			Role role2=new Role();
			role2.setRoleId(AppConstatnt.ADMIN_ROLE_ID);
			role2.setRoleName("ROLE_ADMIN");
			
			List<Role> roles=List.of(role1,role2);
			this.roleRepository.saveAll(roles);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in creating roles");
		}
	}

}
