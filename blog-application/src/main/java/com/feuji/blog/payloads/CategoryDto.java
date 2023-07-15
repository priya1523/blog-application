package com.feuji.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is a dto class for the category resource
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto 
{
	
	private int categoryId;
	
	@NotEmpty(message = "Catogory title can not be empty !")
	@Size(min = 4,message = "Category should contain minimum 4 characters !")
	private String categoryTitle;
	
	@NotEmpty(message = "Category description can not be empty !")
	@Size(min = 10,message = "Category should contain minimum 10 characters !")
	private String categoryDescription;
	
}
