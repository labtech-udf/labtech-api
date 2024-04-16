package com.labtech.events.auth.users;

import com.labtech.events.auth.TokenService;
import com.labtech.events.auth.users.records.LoginDTO;
import com.labtech.events.auth.users.records.RegisterDTO;
import com.labtech.events.auth.users.records.ResponseDTO;
import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@Tag(name = "Users", description = "Gerenciamento de Usuários")
public class UsersResource extends GenericResource<UsersDTO, UsersResource> {

  private final UsersService service;
  private final PasswordEncoder passwordEncoder;
  private final UsersRepository repository;
  private final TokenService tokenService;

  public UsersResource(
    UsersService service,
    UsersRepository repository,
    PasswordEncoder passwordEncoder,
    TokenService tokenService) {
    super(service, "api/");
    this.service = service;
    this.repository = repository;
    this.tokenService = tokenService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/public/listUsers")
  public List<UsersDTO> listUsers() {
    return service.findAll();
  }

  @PostMapping("/private/create")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO user) throws Exception {
    super.createObject(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/public/update")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<UsersDTO> update(@RequestBody UsersDTO user) throws Exception {
    super.updateObject(user);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/public/login")
  public ResponseEntity login(@RequestBody LoginDTO body) {
    Optional<Users> optionalUser = this.repository.findByEmail(body.email());
    if (optionalUser.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    Users user = optionalUser.get();
    if (!passwordEncoder.matches(body.password(), user.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
    }

    String token = this.tokenService.generateToken(user);
    return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
  }

  @PostMapping("/public/register")
  public ResponseEntity register(@RequestBody RegisterDTO body) throws Exception {
    Optional<Users> usr = this.repository.findByEmail(body.email());
    if (usr.isEmpty()) {
      Users user = this.service.register(body);
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
    }
    return ResponseEntity.badRequest().build();
  }
}
