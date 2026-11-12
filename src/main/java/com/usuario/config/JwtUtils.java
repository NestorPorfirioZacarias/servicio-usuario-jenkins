package com.usuario.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/*
 * que es un token:
 * 
 * 
 * ciclo de token:
 * 
 * donde gusrda los token
 * 
 * puedes gusrda en ememori
 * 
 * que es una  key en java;
 */
@Component
public class JwtUtils {
	//
	
	private static final String SECRET_KEY = "2a$10$ebwAYfWpN4QrjSXg1s52ieeF4qQzJy7UEWvquIvuiuXsDVu8/0Pq";
	
	private static final long EXPIRATION_TIME =3600000;
	
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	public String generarToken(String username, String role) {
		return Jwts
				.builder()
				.setSubject(username)
				.claim("role", role)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
				
	}
	
}
