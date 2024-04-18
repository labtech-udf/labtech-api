package com.labtech.events.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.labtech.events.auth.users.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  @Value("${api.security.issuer}")
  private String issuer;

  public String generateToken(Users user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.create()
        .withIssuer(issuer)
        .withSubject(user.getEmail())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);

    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro durante a geração de token");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
        .withIssuer(issuer)
        .build().verify(token)
        .getSubject();

    } catch (JWTVerificationException e) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
