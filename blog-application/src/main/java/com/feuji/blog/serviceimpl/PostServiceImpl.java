package com.feuji.blog.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.feuji.blog.entity.Category;
import com.feuji.blog.entity.Post;
import com.feuji.blog.entity.User;
import com.feuji.blog.exceptions.ResourceNotFoundException;
import com.feuji.blog.payloads.PageResponse;
import com.feuji.blog.payloads.PostDto;
import com.feuji.blog.repositories.CategoryRepository;
import com.feuji.blog.repositories.PostRepository;
import com.feuji.blog.repositories.UserRepository;
import com.feuji.blog.services.PostService;
import com.feuji.blog.utils.AppUtils;

/**
 * @author Priya Patel
 * This class is an implementation class for the PostService interface
 */
@Service
public class PostServiceImpl implements PostService 
{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// for create the new post
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) throws Exception 
	{
		User user=this.userRepository.findById(userId)
									.orElseThrow(()->new ResourceNotFoundException(601, "User", "userId", userId));
		
		Category category=this.categoryRepository.findById(categoryId)
												.orElseThrow(()->new ResourceNotFoundException(601, "Category", "categoryId", categoryId));
		try {
			postDto.setImageName("default.png");
			postDto.setPostDate(new Date());
			Post post=this.modelMapper.map(postDto, Post.class);
			post.setUser(user);
			post.setCategory(category);
			post=this.postRepository.save(post);
			return this.modelMapper.map(post, PostDto.class);
		}
		catch(Exception e){
			throw new Exception("Something went wrong in creating post");
		}
	}

	//for update the post by postId
	@Override
	public PostDto updtaePost(PostDto postDto, int postId) throws Exception 
	{
		Post post=this.postRepository.findById(postId)
									.orElseThrow(()->new ResourceNotFoundException(601, "Post", "postId", postId));
		try {
			post.setPostTitle(postDto.getPostTitle());
			post.setPostContent(postDto.getPostContent());
			post.setPostDate(new Date());
			post.setImageName(postDto.getImageName());
			post=this.postRepository.save(post);
			return this.modelMapper.map(post, PostDto.class);
		}
		catch(Exception e) {
			throw new Exception("Somrthing went wrong in updating the post by posyId");
		}
	}

	//for get the single post by postId
	@Override
	public PostDto getPost(int postId) throws Exception 
	{
		Post post=this.postRepository.findById(postId)
									.orElseThrow(()->new ResourceNotFoundException(601, "Post", "postId", postId));
		try {
			return this.modelMapper.map(post, PostDto.class);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in get the post by postId");
		}
	}

	// for get the list of post with pagination and sorting
	@SuppressWarnings("static-access")
	@Override
	public PageResponse getAllPost(int pageNumber,int pageSize,String sortValue,String sortOrder) throws Exception
	{ 
		try {
			Sort sort=null;
			sort=(sortOrder.equalsIgnoreCase("asc"))?sort.by(sortValue).ascending():sort.by(sortValue).descending();
			Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
			Page<Post> pages=this.postRepository.findAll(pageable);
			
			List<Post> posts=pages.getContent();
			List<PostDto> postDtos=posts.stream()
										.map(post->this.modelMapper.map(post, PostDto.class))
										.collect(Collectors.toList());
			return AppUtils.setPageResonse(pages, postDtos);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in get list of post");
		}
	}

	//for get the list of post by categoryId
	@Override
	public List<PostDto> getAllPostByCategory(int categoryId) throws Exception 
	{
		Category category=this.categoryRepository.findById(categoryId)
												.orElseThrow(()->new ResourceNotFoundException(601, "Category", "categoryId", categoryId));
		try {
			List<Post> posts=this.postRepository.findByCategory(category);
			return posts.stream()
						.map(post->this.modelMapper.map(post,PostDto.class))
						.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in get list of post by category");
		}
	}

	//for get the list of post by userId
	@Override
	public List<PostDto> getAllPostByUser(int userId) throws Exception
	{
		User user=this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException(601, "User", "userId", userId));
		try {
			List<Post> posts=this.postRepository.findByUser(user);
			return posts.stream()
						.map(post->this.modelMapper.map(post,PostDto.class))
						.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in get list of post by user");
		}
	}

	//for delete the post by postId
	@Override
	public void deletePost(int postId) throws Exception 
	{
		Post post=this.postRepository.findById(postId)
									.orElseThrow(()->new ResourceNotFoundException(601, "Post", "postId", postId));
		try {
			this.postRepository.delete(post);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in delete the post by postId");
		}
	}

	@Override
	public List<PostDto> searchByTitle(String postTitle) throws Exception
	{
		try {
			List<Post> posts=this.postRepository.findByPostTitleContaining(postTitle);
			return posts.stream()
						.map(post->this.modelMapper.map(post,PostDto.class))
						.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while searching by postTitle");
		}
	}

}
