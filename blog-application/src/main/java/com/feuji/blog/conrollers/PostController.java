package com.feuji.blog.conrollers;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.feuji.blog.config.AppConstatnt;
import com.feuji.blog.payloads.ApiResponse;
import com.feuji.blog.payloads.PageResponse;
import com.feuji.blog.payloads.PostDto;
import com.feuji.blog.services.FileService;
import com.feuji.blog.services.PostService;

/**
 * @author Priya Patel
 * This class is controller for the post resource
 */
@RestController
@RequestMapping("/api")
public class PostController
{
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String imagePath;
	
	/**
	 * This method is an api call for the create new post, accept the POST http request
	 * @param PostDto
	 * @param userId
	 * @param categoryId
	 * @return ResponseEntity<PostDto>
	 * @throws Exception
	 */
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
								@PathVariable(name = "userId") int userId,
								@PathVariable(name = "categoryId") int categoryId
								) throws Exception
	{
		postDto=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.ACCEPTED);
	}
	
	/**
	 * This method is an api call for the update post by postId, accept PUT http request
	 * @param PostDto
	 * @param postId
	 * @return ResponseEntity<PostDto>
	 * @throws Exception
	 */
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
								@PathVariable(name = "postId") int postId
								)throws Exception
	{
		postDto=this.postService.updtaePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for the get the single post by postId, accept GET http request
	 * @param postId
	 * @return ResponseEntity<PostDto>
	 * @throws Exception
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable(name = "postId") int postId)
								throws Exception
	{
		PostDto postDto=this.postService.getPost(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for get the list of post, accept GET http request
	 * @param pageNumber
	 * @param pageSize
	 * @param sortValue
	 * @param sortOrder 
	 * @return ResponseEntity<List<PostDto>>
	 * @throws Exception
	 */
	@GetMapping("/posts")
	public ResponseEntity<PageResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstatnt.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstatnt.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortValue",defaultValue = "postId",required = false) String sortValue,
			@RequestParam(value = "sortOrder",defaultValue = AppConstatnt.SORT_ORDER,required = false) String sortOrder
		) throws Exception
	{
		PageResponse postDtos =this.postService.getAllPost(pageNumber,pageSize,sortValue,sortOrder);
		return new ResponseEntity<PageResponse>(postDtos,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for the get the list of post by user, accept GET http request
	 * @param userId
	 * @return ResponseEntity<List<PostDto>>
	 * @throws Exception
	 */
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable(name = "userId") int userId)
										throws Exception
	{
		List<PostDto> postDtos=this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for get the list of post by category, accept GET http request
	 * @param categoryId
	 * @return ResponseEntity<List<PostDto>>
	 * @throws Exception
	 */
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(name = "categoryId") int categoryId)
										throws Exception
	{
		List<PostDto> postDtos=this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for delete the post by postId, accept DELETE http request
	 * @param postId
	 * @return ResponseEntity<ApiResponse>
	 * @throws Exception
	 */
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") int postId)
										throws Exception
	{
		this.postService.deletePost(postId);
		ApiResponse apiResponse=new ApiResponse("Post deleted successfully !",true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for the search the post besed on the postTitle, accept the GET http request
	 * @param postTitle
	 * @return ResponseEntity<List<PostDto>>
	 * @throws Exception
	 */
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> searchByPostTitle(@RequestParam(value = "postTitle") String postTitle) throws Exception
	{
		List<PostDto> postDtos=this.postService.searchByTitle(postTitle);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	/**
	 * This method is an api call for upload the image for the post, accept the POST http request
	 * @param postId
	 * @param MultipartFile image
	 * @return ResponseEntity<PostDto>
	 * @throws Exception
	 */
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@PathVariable("postId") int postId,
			@RequestParam(value = "image") MultipartFile image
			) throws Exception
	{
		PostDto postDto=this.postService.getPost(postId);
		String imageName=this.fileService.uploadImage(this.imagePath, image);
		postDto.setImageName(imageName);
		postDto=this.postService.updtaePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
				
	/**
	 * This method is an api call for the get image for post by imageName, accept the GET http request
	 * @param String imageName
	 * @param HttpServletResponse httpServletResponse
	 * @throws Exception
	 */
	@GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getPostImage(
			@PathVariable(value = "imageName") String imageName,
			HttpServletResponse httpServletResponse
			) throws Exception
	{
		InputStream inputStream=this.fileService.getImage(this.imagePath, imageName);
		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
	}
}
