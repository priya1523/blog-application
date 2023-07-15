package com.feuji.blog.conrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.blog.payloads.ApiResponse;
import com.feuji.blog.payloads.CategoryDto;
import com.feuji.blog.services.CategoryService;

/**
 * @author Priya Patel
 * This is a controller for the Category Resource
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * This method is an api call for the create the new category, accept the POST http request
	 * @param CategoryDto
	 * @return ResponseEntity<CategoryDto>
	 * @throws Exception
	 */
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto) throws Exception
	{
		categoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.ACCEPTED);
	}
	
	/**
	 * This method is an api call for update the category by categoryId, accept the PUT http request
	 * @param CategoryDto
	 * @param categoryId
	 * @return ResponseEntity<CategoryDto>
	 * @throws Exception
	 */
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable(name = "categoryId") int categoryId) throws Exception
	{
		categoryDto=this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for get single category by categoryId, accept the GET http request 
	 * @param categoryId
	 * @return ResponseEntity<CategoryDto> 
	 * @throws Exception
	 */
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "categoryId") int categoryId) throws Exception
	{
		CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for get the list of all the category, accept the GET http request
	 * @return ResponseEntity<List<CategoryDto>>
	 * @throws Exception
	 */
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() throws Exception
	{
		List<CategoryDto> categoryDtos=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param categoryId
	 * @return ResponseEntity<ApiResponse>
	 * @throws Exception
	 */
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "categoryId") int categoryId) throws Exception
	{
		this.categoryService.deleteCategory(categoryId);
		ApiResponse apiResponse=new ApiResponse("Category Deleted Successfully !",true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
}
