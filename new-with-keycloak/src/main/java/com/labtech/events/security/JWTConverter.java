package com.labtech.events.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class JWTConverter implements Converter<Jwt, CustomJwt> {


  @Override
  public CustomJwt convert(final Jwt source) {
    List<GrantedAuthority> authorityList = extractAuthorities(source);
    var customJwt = new CustomJwt(source, authorityList);
    customJwt.setName(source.getClaimAsString("given_name"));
    customJwt.setLastname(source.getClaimAsString("family_name"));
    return customJwt;
  }

  private List<GrantedAuthority> extractAuthorities(final Jwt source) {
    var result = new ArrayList<GrantedAuthority>();
    var realm_acs = source.getClaimAsMap("realm_access");
    if (realm_acs != null && realm_acs.get("roles") != null) {
      var roles = realm_acs.get("roles");
      if (roles instanceof List l) {
        l.forEach(role -> {
          result.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
      }
    }
    return result;
  }
}
