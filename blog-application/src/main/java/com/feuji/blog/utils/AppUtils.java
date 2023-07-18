package com.feuji.blog.utils;

import java.util.List;

import org.springframework.data.domain.Page;

import com.feuji.blog.payloads.PageResponse;

/**
 * @author Priya Patel
 * This class contains all the util methods
 */
public class AppUtils
{
	/**
	 * This method set the PageResponse attribute
	 * @param Page<?>
	 * @param List<?>
	 * @return PageResponse
	 */
	public static PageResponse setPageResonse(Page<?> page,List<?> list)
	{
		PageResponse pageResponse=new PageResponse();
		pageResponse.setContent(list);
		pageResponse.setFirstPage(page.isFirst());
		pageResponse.setLastPage(page.isLast());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalElements(page.getTotalElements());
		pageResponse.setPageNumber(page.getNumber());
		pageResponse.setPageElements(page.getNumberOfElements());
		return pageResponse;
	}
}
