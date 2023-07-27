package com.feuji.blog.services;

import java.util.List;

import com.feuji.blog.payloads.PageResponse;
import com.feuji.blog.payloads.PostDto;

/**
 * @author Priya Patel
 * This interface contains the curd methods for the post resource
 */
public interface PostService
{
	/**
	 * This method id for create the new post with category and user
	 * @param PostDto
	 * @param userId
	 * @param categoryId
	 * @return PostDto
	 * @throws Exception
	 */
	PostDto createPost(PostDto postDto,int userId,int categoryId) throws Exception;
	
	/**
	 * This method is for update the post by postId
	 * @param PostDto
	 * @param postId
	 * @return PostDto
	 * @throws Exception
	 */
	PostDto updtaePost(PostDto postDto,int postId) throws Exception;
	
	/**
	 * This method is for the get the single post by postId
	 * @param postId
	 * @return PostDto
	 * @throws Exception
	 */
	PostDto getPost(int postId) throws Exception;
	
	/**
	 * This method is for get the list of post
	 * @param sortOrder 
	 * @param sortValue 
	 * @param pageSize 
	 * @param pageNumber 
	 * @return PageResponse
	 * @throws Exception
	 */
	PageResponse getAllPost(int pageNumber, int pageSize, String sortValue, String sortOrder) throws Exception;
	
	/**
	 * This method is for the get the list of post by category
	 * @param categoryId
	 * @return List<PostDto>
	 * @throws Exception
	 */
	List<PostDto> getAllPostByCategory(int categoryId) throws Exception;
	
	/**
	 * This method is for get the list of post by user
	 * @param userId
	 * @return List<PostDto>
	 * @throws Exception
	 */
	List<PostDto> getAllPostByUser(int userId) throws Exception;
	
	/**
	 * This method is for delete the post by postId
	 * @param postId
	 * @throws Exception
	 */
	void deletePost(int postId) throws Exception;
	
	/**
	 * This method is for search the post by the postTitle 
	 * @param postTitle
	 * @return List<PostDto>
	 * @throws Exception 
	 */
	List<PostDto> searchByTitle(String postTitle) throws Exception;

}
