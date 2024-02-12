package com.labtech.events.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwt extends JwtAuthenticationToken {
  private String name;
  private String lastname;

  public CustomJwt(final Jwt jwt, final Collection<? extends GrantedAuthority> authorities) {
    super(jwt, authorities);
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(final String lastname) {
    this.lastname = lastname;
  }
}
