package com.example.demo.user.jwt;

import com.example.demo.user.entity.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;  //24h

    @Value("${jwtSecret}")
    private String secretKey;

    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getEmail())
                .setIssuer("vijay")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Boolean validateAccessToken(String token){
        try{
            Jws<Claims>  jwClains =   Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            System.out.println("------------------doFilterInternal------------jwClains----------------------------"+jwClains);

          //  return true;

        }catch (ExpiredJwtException ex){
            System.err.println("Jwt Expired : "+ex);
        }catch (IllegalArgumentException ex) {
            System.err.println("Token is null : " + ex);
        }catch (MalformedJwtException ex){
            System.err.println("Jwt is invalid : "+ex);
        }catch (UnsupportedJwtException ex){
            System.err.println("Jwt is not supported : " + ex);
        }catch (SignatureException ex){
            System.err.println("Signature validation failed : " + ex);
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
