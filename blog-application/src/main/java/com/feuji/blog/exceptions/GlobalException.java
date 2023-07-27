package com.feuji.blog.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.feuji.blog.payloads.ApiResponse;


/**
 * @author Priya Patel
 * This class is a global exception handler, which handle all the controllers exception as per requiremnets
 */

@RestControllerAdvice
public class GlobalException
{
	/**
	 * This method is for handle the resource not found exception globally
	 * @param ResourceNotFoundException
	 * @return ResponseEntity<ApiResponse>
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception)
	{
		String message=exception.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return ResponseEntity.status(exception.getErrorCode()).body(apiResponse);
	}
	
	/**
	 * This method is for handle the resource already exist exception globally
	 * @param ResourceAlreadyExistException
	 * @return ResponseEntity<ApiResponse>
	 */
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ApiResponse> resourceAlreadyExistException(ResourceAlreadyExistException exception)
	{
		String message=exception.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return ResponseEntity.status(exception.getErrorCode()).body(apiResponse);
	}
	
	/**
	 * This method is for handle the validation exception
	 * @param MethodArgumentNotValidException
	 * @return ResponseEntity<Map<String, String>>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgsNotValidException(MethodArgumentNotValidException exception)
	{
		Map<String, String> responseMap=new HashMap<>();
		exception.getBindingResult()
				.getAllErrors()
				.forEach((error)->{
					String fieldName=((FieldError)error).getField();
					String errorMessage=error.getDefaultMessage();
					responseMap.put(fieldName, errorMessage);
				});
		return new ResponseEntity<Map<String,String>>(responseMap,HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * This method handle the IOException globally
	 * @param IOException
	 * @return ResponseEntity<ApiResponse>
	 */
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> ioExcetion(IOException ioException)
	{
		String message=ioException.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}
	
	/**
	 * This method is for handle the parent exception globally
	 * @param Exception
	 * @return ResponseEntity<ApiResponse>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> exception(Exception exception)
	{
		String message=exception.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}	
	
	/**
	 * This method is for handle the InvalidTokenException globally
	 * @param invalidToken
	 * @return
	 */
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ApiResponse> invalidTokenException(InvalidTokenException invalidToken)
	{
		String message=invalidToken.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return ResponseEntity.status(invalidToken.getErrorCode()).body(apiResponse);
	}
	 
}
