package com.labtech.events.auth;

import com.labtech.events.auth.users.Users;
import com.labtech.events.auth.users.UsersRepository;
import com.labtech.events.constants.Enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UsersRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPassword(),
      getAuthorities(user.getRoles())
    );
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Set<Permission> permissions) {
    return permissions.stream()
      .map(permission -> new SimpleGrantedAuthority("ROLE_" + permission.name()))
      .collect(Collectors.toList());
  }

}