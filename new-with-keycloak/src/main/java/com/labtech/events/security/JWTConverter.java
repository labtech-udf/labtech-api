package com.labtech.events.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

//public class JWTConverter implements Converter<OAuth2ResourceServerProperties.Jwt, AbstractAuthenticationToken> {
//
//  @Override
//  public AbstractAuthenticationToken convert(final Jwt jwt) {
//    return null;
//  }
//
//  @Override
//  public <U> Converter<OAuth2ResourceServerProperties.Jwt, U> andThen(final Converter<? super AbstractAuthenticationToken, ? extends U> after) {
//    return Converter.super.andThen(after);
//  }
//}
