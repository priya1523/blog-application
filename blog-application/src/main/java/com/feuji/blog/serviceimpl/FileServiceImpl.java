package com.feuji.blog.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.feuji.blog.services.FileService;

/**
 * @author Priya Patel
 * This class is an implementation class for the FileService interface
 */
@Service
public class FileServiceImpl implements FileService 
{

	//for image upload
	@Override
	public String uploadImage(String path, MultipartFile file) throws Exception
	{
		try {
			//get the name of the image
			String imageName=file.getOriginalFilename();
			
			//genrate the randome name for image
			String randomId=UUID.randomUUID().toString();
			String name=randomId.concat(imageName.substring(imageName.lastIndexOf(".")));
			
			//set full path
			String filePath=path+File.separator+name;
			
			//cretae folder if not exist
			File f=new File(path);
			if(! f.exists())
			{
				f.mkdir();
			}
			
			//copy the files
			Files.copy(file.getInputStream(), Paths.get(filePath));
			
			return name;
		}
		catch(IOException io) {
			throw new IOException("Image is not saved !");
		}
		catch(Exception e) {
			throw new Exception("Something went wrong while uploading image");
		}
	}

	// for get the image bu imageName
	@Override
	public InputStream getImage(String path, String fileName) throws Exception 
	{
		try {
			String filePath=path+File.separator+fileName;
			InputStream inputStream=new FileInputStream(filePath);
			return inputStream;
		}
		catch(Exception e) {
			throw new Exception("Something went wrong in getting image by imageName");
		}
	}

}
