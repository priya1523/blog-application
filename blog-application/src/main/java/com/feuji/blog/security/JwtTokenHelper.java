package com.feuji.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.feuji.blog.config.AppConstatnt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Priya Patel
 *
 */
@Component
public class JwtTokenHelper 
{	
	//for get the username form jwt token
	public String getUserNameFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//for get the expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	//for get Claims from jwt token
	public <T> T getClaimFromToken(String token,Function<Claims, T> function)
	{
		final Claims claims=getAllClaimsFromToken(token);
		return function.apply(claims);
	}
	
	//for get all claims from jwt token
	public Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser()
					.setSigningKey(AppConstatnt.SECRET)
					.parseClaimsJws(token)
					.getBody();
	}
	
	//for check token is expire or not
	public Boolean isTokenExpired(String token)
	{
		final Date expiraation=getExpirationDateFromToken(token);
		return expiraation.before(new Date());
	}
	
	//for get the generated the token
	public String generateToken(UserDetails userDetails) throws Exception
	{
		Map<String, Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	// for generate the token
	private String doGenerateToken(Map<String, Object> claims,String username) throws Exception
	{
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(username)					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+AppConstatnt.JWT_TOKEN_VALIDITY*1000))
					.signWith(SignatureAlgorithm.HS256,AppConstatnt.SECRET)
					.compact();
	}
	
	//for validating the token
	public Boolean validateToken(String token,UserDetails userDetails)
	{
		final String username=getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
}
