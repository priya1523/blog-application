package com.feuji.blog.services;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
/**
 * @author Priya Patel
 * This class is for file/image upload and get
 */
public interface FileService 
{
	/**
	 * This method is for upload the image and store in the images folder
	 * @param String path
	 * @param MultipartFile file
	 * @return String
	 * @throws Exception
	 */
	String uploadImage(String path,MultipartFile file) throws Exception;
	
	/**
	 * This method is forget the image by imageName fom the folder
	 * @param String path
	 * @param String fileName
	 * @return InputStream
	 * @throws Exception
	 */
	InputStream getImage(String path,String fileName) throws Exception;
}
