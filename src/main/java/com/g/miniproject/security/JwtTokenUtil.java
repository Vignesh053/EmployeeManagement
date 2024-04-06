package com.g.miniproject.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenUtil {

    //private secret key for jwt token creation
    private final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // the token will be authenticated for 18 minutes
    private final static int expirationTimeInMs = 300 * 60 * 60;


    //generate jwt token using username
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("EmployeeManagement")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(key)
                .compact();
    }

    //validate the token by checking its expiry time and if token contains username
    public boolean validate(String token){
        if (getUsername(token) != null && isExpired(token) ){
            return true;
        }else {
            return false;
        }
    }
    public String getUsername (String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token){
        Claims claims = getClaims(token);

        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    // extracting claims from jwt token will provide details of token such as username, expiry time etc.,
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
