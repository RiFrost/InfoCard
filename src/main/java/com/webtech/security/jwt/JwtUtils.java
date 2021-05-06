package com.webtech.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.webtech.security.service.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
  
  @Value("${infocard.app.jwtSecret}")
  private String jwtSecret;

  @Value("${infocard.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder().setSubject((userPrincipal.getEmail())).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getEmailFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      System.out.printf("Invalid JWT signature: %s", e.getMessage());
    } catch (MalformedJwtException e) {
      System.out.printf("Invalid JWT token: %s", e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.out.printf("JWT token is unsupported: %s", e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.printf("JWT claims string is empty: %s", e.getMessage());
    } catch (Exception e) {
      System.out.printf("Exception: %s", e.getMessage());
    }

    return false;
  }

}
