package com.labtech.events.auth.users;

import com.labtech.events.auth.users.records.RegisterDTO;
import com.labtech.events.utils.GenericService;

import java.util.List;
import java.util.Optional;

public interface UsersService extends GenericService<UsersDTO> {

  List<UsersDTO> findAll();

  Users register(RegisterDTO body) throws Exception;

  Optional<Users> findByEmail(String login);

  UsersDTO getUser(Users users);
}
