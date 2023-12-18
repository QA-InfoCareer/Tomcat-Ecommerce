package com.myshopping.myshopping.utility;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.myshopping.myshopping.dto.UserDto;
import com.myshopping.myshopping.modal.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class Utility {
	Date date=new Date();
	private static String secret="this_is_secret";
	private static long expiration=600000;
	Date expire=new Date(date.getTime()+expiration);
	public String generateJwtToken(User user) {
		UserDto userDto=new UserDto();
		Claims claims=Jwts.claims()
				.setIssuer(user.getId())
				.setExpiration(expire)
				.setIssuedAt(date);
		claims.put("name",user.getName());
		claims.put("password",user.getPassword());
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512,secret)
				.compact();		
	}
}
