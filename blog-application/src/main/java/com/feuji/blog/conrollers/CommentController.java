package com.feuji.blog.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.blog.payloads.ApiResponse;
import com.feuji.blog.payloads.CommentDto;
import com.feuji.blog.services.CommentService;

/**
 * @author Priya Patel
 * This class is a controller for the comment resource
 */

@RestController
@RequestMapping("/api")
public class CommentController
{
	@Autowired
	private CommentService commentService;
	
	/**
	 * This method is an api call for the create the comment by userId & postId
	 * @param CommentDto
	 * @param userId
	 * @param postId
	 * @return ResponseEntity<CommentDto>
	 * @throws Exception
	 */
	@PostMapping("/user/{userId}/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(
					@RequestBody CommentDto commentDto,
					@PathVariable(name = "userId") int userId,
					@PathVariable(name = "postId") int postId
					) throws Exception
	{
		commentDto=this.commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.ACCEPTED);
	}
	
	/**
	 * This method is an api call for the delete the comment by commentId
	 * @param commentId
	 * @return ResponseEntity<ApiResponse>
	 * @throws Exception
	 */
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(
			@PathVariable(name = "commentId") int commentId
			) throws Exception
	{
		this.commentService.deleteComment(commentId);
		ApiResponse apiResponse=new ApiResponse("Comment deleted successfully !",true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
}
