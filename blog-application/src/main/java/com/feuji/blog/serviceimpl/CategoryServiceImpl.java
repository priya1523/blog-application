package com.feuji.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.blog.entity.Category;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.payloads.CategoryDto;
import com.feuji.blog.repositories.CategoryRepository;
import com.feuji.blog.services.CategoryService;
/**
 * @author Priya Patel
 * This class is an implementation for the CategoryService interface
 */
@Service
public class CategoryServiceImpl implements CategoryService 
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//for create the new category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) throws Exception 
	{
		try {
			Category category=this.modelMapper.map(categoryDto, Category.class);
			category=this.categoryRepository.save(category);
			return this.modelMapper.map(category, CategoryDto.class);
		}
		catch(Exception e) {
			 throw new Exception("Something went worng in creating category");
		}
	}

	//for update the category by categoryId
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) throws Exception
	{
		Category category=this.categoryRepository.findById(categoryId)
												.orElseThrow(()->new ResourceNotFoundException(601, "Category", "categoryId", categoryId));
		try {
			category.setCategoryTitle(categoryDto.getCategoryTitle());
			category.setCategoryDescription(categoryDto.getCategoryDescription());
			category=this.categoryRepository.save(category);
			return this.modelMapper.map(category, CategoryDto.class);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in updating category");
		}
	}

	//for get the single category by categoryId
	@Override
	public CategoryDto getCategory(int categoryId) throws Exception
	{
		Category category=this.categoryRepository.findById(categoryId)
												.orElseThrow(()->new ResourceNotFoundException(601, "Category", "categoryId", categoryId));
		try {
			return this.modelMapper.map(category, CategoryDto.class);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in getting single category by categoryId");
		}
	}

	//for get the list of all the category
	@Override
	public List<CategoryDto> getAllCategory() throws Exception 
	{
		try {
			List<Category> categories=this.categoryRepository.findAll();
			return categories.stream()
							.map(category->this.modelMapper.map(category, CategoryDto.class))
							.collect(Collectors.toList());			
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in getting list of all category");
		}
	}

	//for delete the category by categoryId
	@Override
	public void deleteCategory(int categoryId) throws Exception 
	{
		Category category=this.categoryRepository.findById(categoryId)
												.orElseThrow(()->new ResourceNotFoundException(601, "Category", "categoryId", categoryId));
		try {
			this.categoryRepository.delete(category);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in getting single category by categoryId");
		}
	}

}
