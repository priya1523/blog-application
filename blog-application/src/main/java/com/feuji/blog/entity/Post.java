package com.feuji.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is the entity class for the post resource
 */

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post 
{
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int postId;
	
	@Column(name =  "post_title")
	private String postTitle;
	
	@Column(name = "post_content")
	private String postContent;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "post_date")
	private Date postDate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
