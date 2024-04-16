package com.labtech.events.auth.users;

import com.labtech.events.auth.users.records.RegisterDTO;
import com.labtech.events.constants.Enums.Permission;
import com.labtech.events.utils.GenericServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl extends GenericServiceImpl<Users, UsersDTO> implements UsersService {

  private final UsersRepository repository;
  private final UsersMapper mapper;
  private final PasswordEncoder passwordEncoder;

  public UsersServiceImpl(
    UsersRepository repository,
    UsersMapper mapper,
    PasswordEncoder passwordEncoder) {
    super(repository, mapper);
    this.repository = repository;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<Users> findByEmail(String login) {
    return this.repository.findByEmail(login);
  }

  @Override
  public List<UsersDTO> findAll() {
    List<Users> listUsers = this.repository.findAllByExcluded(Boolean.FALSE);
    return this.mapper.toDto(listUsers);
  }

  @Override
  public Users register(RegisterDTO obj) throws Exception {
    Users user = new Users();
    user.setEmail(obj.email());
    user.setName(obj.name());
    user.setUid(UUID.randomUUID());
    user.setRoles(Collections.singleton(Permission.USER));
    user.setPassword(passwordEncoder.encode(obj.password()));
    save(mapper.toDto(user));
    return user;
  }
}
