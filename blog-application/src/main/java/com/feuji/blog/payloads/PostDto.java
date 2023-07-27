package com.feuji.blog.payloads;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This is a dto class for the Post resource
 */

@Getter
@Setter
@NoArgsConstructor
public class PostDto 
{
	private int postId;
	
	@NotEmpty(message = "Post title can not be empty !")
	@Size(min = 4,message = "Post title should contain minimum 4 characters !")
	private String postTitle;
	
	@NotEmpty(message = "Post content can not be empty !")
	private String postContent;
	
	private String imageName;
	
	private Date postDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comments;

}
