package com.labtech.events.auth.users;

import com.labtech.events.auth.users.records.RegisterDTO;
import com.labtech.events.constants.Enums.Roles_user;
import com.labtech.events.files.ArquivoDTO;
import com.labtech.events.files.ArquivoMapper;
import com.labtech.events.files.ArquivoService;
import com.labtech.events.utils.GenericServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl extends GenericServiceImpl<Users, UsersDTO> implements UsersService {

  private final UsersRepository repository;
  private final UsersMapper mapper;
  private final PasswordEncoder passwordEncoder;
  private final ArquivoService arquivoService;
  private final ArquivoMapper arquivoMapper;

  public UsersServiceImpl(
    UsersRepository repository,
    UsersMapper mapper, PasswordEncoder passwordEncoder,
    ArquivoService arquivoService, ArquivoMapper arquivoMapper) {
    super(repository, mapper);
    this.repository = repository;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
    this.arquivoService = arquivoService;
    this.arquivoMapper = arquivoMapper;
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
    user.setEmail(obj.getEmail());
    user.setName(obj.getName());
    user.setUid(UUID.randomUUID());
    user.setRoles(Collections.singleton(Roles_user.USER));
    user.setPassword(passwordEncoder.encode(obj.getPassword()));
    user.setFoto_capa(
      this.arquivoMapper.toEntity(
        arquivoService.save(new ArquivoDTO())
      )
    );
    user.setFoto_perfil(
      this.arquivoMapper.toEntity(
        arquivoService.save(new ArquivoDTO())
      )
    );
    this.save(mapper.toDto(user));
    return user;
  }

  @Override
  public UsersDTO getUser(Users user) {
    UsersDTO usr = new UsersDTO();
    usr.setEmail(user.getEmail());
    usr.setId(user.getId());
    usr.setName(user.getName());
    usr.setRoles(new ArrayList<>(user.getRoles()));
    usr.setUid(user.getUid());
    usr.setFoto_capa(arquivoMapper.toDto(user.getFoto_capa()));
    usr.setFoto_perfil(arquivoMapper.toDto(user.getFoto_perfil()));
    return usr;
  }

  @Override
  public UsersDTO update(final UsersDTO obj) throws Exception {
    Optional<Users> usr = findByEmail(obj.getEmail());
    if (usr.isPresent()) {
      Users user = usr.get();
      obj.setPassword(user.getPassword());
      obj.setUpdatedBy(String.valueOf(user.getUid()));
    }
    return this.save(obj);
  }

}
