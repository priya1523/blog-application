package com.feuji.blog.payloads;

import lombok.Data;

/**
 * @author Priya Patel
 * For the reponse of the jwt auth
 */
@Data
public class JwtAuthResponse 
{
	private String token;

}
