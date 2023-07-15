package com.feuji.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.feuji.blog.payloads.CategoryDto;

/**
 * @author Priya Patel
 * This is an interface which contains the curd method for category resource
 */
@Service
public interface CategoryService 
{
	/**
	 * This method is for create the new category
	 * @param CategoryDto
	 * @return CategoryDto
	 * @throws Exception
	 */
	CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
	
	/**
	 * This method is for update the category bt categoryId
	 * @param CategoryDto
	 * @param categoryId
	 * @return CategoryDto
	 * @throws Exception
	 */
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId) throws Exception;
	
	/**
	 * This method is for get the single category by categoryId
	 * @param categoryId
	 * @return CategoryDto
	 * @throws Exception
	 */
	CategoryDto  getCategory(int categoryId) throws Exception;
	
	/**
	 * This method is for get the list of all the category
	 * @return List<CategoryDto>
	 * @throws Exception
	 */
	List<CategoryDto> getAllCategory() throws Exception;
	
	/**
	 * This method is for delete the category by categoryId
	 * @param categoryId
	 * @throws Exception
	 */
	void deleteCategory(int categoryId) throws Exception;
}
