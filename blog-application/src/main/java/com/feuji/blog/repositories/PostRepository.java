package com.feuji.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.blog.entity.Category;
import com.feuji.blog.entity.Post;
import com.feuji.blog.entity.User;

/**
 * @author Priya Patel
 * This interface is for database interaction of post resource
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> 
{
	/**
	 * This method get the all the post uploaded by the user, by user
	 * @param User
	 * @return List<Post>
	 */
	List<Post> findByUser(User user);
	
	/**
	 * This method get the all the post based on category., by category
	 * @param Category
	 * @return List<Post>
	 */
	List<Post> findByCategory(Category category);
	
	/**
	 * This method get the post list based on the which match which the postTitle
	 * @param postTitle
	 * @return List<Post>
	 */
	List<Post> findByPostTitleContaining(String postTitle);
	
}
