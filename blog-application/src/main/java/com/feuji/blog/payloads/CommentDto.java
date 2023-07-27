package com.feuji.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is a dto class for comment resource
 */

@Getter
@Setter
@NoArgsConstructor
public class CommentDto 
{
		private int commentId;
		
		private String commentContent;
		
		private UserDto user;
}

