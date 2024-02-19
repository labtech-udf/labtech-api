package com.labtech.events.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  //
  @Value("${keycloak.username}")
  private String user;

  @Value("${keycloak.password}")
  private String pass;
//
  @Value("${keycloak.auth-server-url}")
  private String url;
//
//  @Value("${keycloak.clientId}")
//  private String clId;
//
//  @Value("${keycloak.realm}")
//  private String realm;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(
          "/api/public/**",
          "/swagger-ui/**",
          "/swagger-resources/*",
          "/v3/api-docs/**",
          "/api-docs/**",
          "http://localhost:8090/**"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())));

    return http.build();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull final CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
      .serverUrl(url)
      .realm("master")
      .clientId("admin-cli")
      .grantType(OAuth2Constants.PASSWORD)
      .clientSecret("5GoyYjv4l1emoUw0yDNxgiGvSAH3tVM4")
      .username(user)
      .password(pass)
      .build();
  }

}
