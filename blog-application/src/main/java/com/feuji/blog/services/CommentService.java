package com.feuji.blog.services;

import com.feuji.blog.payloads.CommentDto;

/**
 * @author Priya Patel
 * This interface contain the curd method for comment resource
 */
public interface CommentService 
{
	/**
	 * This method is for create the new comment by postId and userID
	 * @param postId
	 * @param userId
	 * @return CommentDto
	 * @throws Exception
	 */
	CommentDto createComment(CommentDto commentDto,int postId,int userId) throws Exception;
	
	/**
	 * This method is for delete the comment by commentId
	 * @param commentId
	 * @throws Exception
	 */
	void deleteComment(int commentId) throws Exception;
}
