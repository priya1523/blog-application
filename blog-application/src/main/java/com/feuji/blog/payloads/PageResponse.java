package com.feuji.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Priya Patel
 * This class is act as a response in pagination format
 */
@Getter
@Setter
@NoArgsConstructor
public class PageResponse 
{
	private int pageNumber;
	
	private int pageElements;
	
	private int totalPages;
	
	private long totalElements;
	
	private boolean isLastPage;
	
	private boolean isFirstPage;
	
	private List<?> content;
}
