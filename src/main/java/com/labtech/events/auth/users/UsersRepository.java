package com.labtech.events.auth.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

  List<Users> findAllByExcluded(Boolean excluded);

  Optional<Users> findByEmail(String email);

}
