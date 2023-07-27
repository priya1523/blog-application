package com.feuji.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.feuji.blog.exceptions.InvalidTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

/**
 * @author Priya Patel
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String url=request.getRequestURI();
		
		if (!(url.equals("/api/auth/login")) 
				&& !(url.equals("/api/auth/register")) 
				&& !(url.equals("/v2/api-docs"))
				&& !(url.equals("/webjars"))
				&& !(url.contains("swagger-resources"))
				&& !(url.contains("swagger-ui")))
		{
			String requestToken=request.getHeader("Authorization");
			String username=null;
			String token=null;
			
			if(requestToken !=null && requestToken.startsWith("Bearer"))
			{
				try {
					token=requestToken.substring(7);
					username=this.jwtTokenHelper.getUserNameFromToken(token);
				}
				catch(IllegalArgumentException e)
				{
					throw new InvalidTokenException(604, "Token not found !");
				}
				catch (ExpiredJwtException e) 
				{
					throw new InvalidTokenException(604, "Token is expired !");
				}
				catch(MalformedJwtException e)
				{
					throw new InvalidTokenException(604, "Token is invalid !");
				}
			}
			else
			{
				throw new InvalidTokenException(604,"Token not starts with Bearer !");
			}
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails=this.customeUserDetailsService.loadUserByUsername(username);
				if(this.jwtTokenHelper.validateToken(token,userDetails))
				{
					UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource()
														.buildDetails(request));
					SecurityContextHolder.getContext()
										.setAuthentication(authenticationToken);
				}
				else
				{
					throw new InvalidTokenException(604, "Invalid token !");
				}
			}
			else
			{
				throw new InvalidTokenException(604, "Username or Context is null in token !");
			}
		}
		filterChain.doFilter(request, response);
	}

}
