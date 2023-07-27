package com.feuji.blog.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.blog.entity.Comment;
import com.feuji.blog.entity.Post;
import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.payloads.CommentDto;
import com.feuji.blog.repositories.CommentRepository;
import com.feuji.blog.repositories.PostRepository;
import com.feuji.blog.repositories.UserRepository;
import com.feuji.blog.services.CommentService;

/**
 * @author Priya Patel
 * This class is an implementation class for the COmmentService interface
 */
@Service
public class CommentServiceImpl implements CommentService 
{
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	// for create the new comment by postId & userId
	@Override
	public CommentDto createComment(CommentDto commentDto,int postId, int userId) throws Exception
	{
		User user=this.userRepository.findById(userId)
									.orElseThrow(()->new ResourceNotFoundException(601, "User", "userId", userId));
		Post post=this.postRepository.findById(postId)
									.orElseThrow(()->new ResourceNotFoundException(601, "Post", "postId", postId));
		try {
			Comment comment=this.modelMapper.map(commentDto, Comment.class);
			comment.setPost(post);
			comment.setUser(user);
			comment=this.commentRepository.save(comment);
			return this.modelMapper.map(comment,CommentDto.class);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in crateting comment");
		}
	}

	//for delete the comment by commentId
	@Override
	public void deleteComment(int commentId) throws Exception
	{
		Comment comment=this.commentRepository.findById(commentId)
											.orElseThrow(()->new ResourceNotFoundException(601, "Comment", "commentId", commentId));
		try {
			this.commentRepository.delete(comment);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in deleteing comment by commentId");
		}
	}

}
