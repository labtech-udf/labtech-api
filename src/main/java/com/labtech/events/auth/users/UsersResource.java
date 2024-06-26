package com.labtech.events.auth.users;

import com.labtech.events.auth.TokenService;
import com.labtech.events.auth.users.records.LoginDTO;
import com.labtech.events.auth.users.records.RegisterDTO;
import com.labtech.events.auth.users.records.ResponseDTO;
import com.labtech.events.auth.users.records.RolesDTO;
import com.labtech.events.constants.Enums.Roles_user;
import com.labtech.events.utils.GenericResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api")
@Tag(name = "Users", description = "Gerenciamento de Usuários")
public class UsersResource extends GenericResource<UsersDTO, UsersResource> {

  private final UsersService service;
  private final PasswordEncoder passwordEncoder;
  private final UsersRepository repository;
  private final TokenService tokenService;
  private final UsersMapper mapper;

  public UsersResource(
    UsersService service,
    UsersRepository repository,
    PasswordEncoder passwordEncoder,
    TokenService tokenService, UsersMapper mapper) {
    super(service, "api/");
    this.service = service;
    this.repository = repository;
    this.tokenService = tokenService;
    this.passwordEncoder = passwordEncoder;
    this.mapper = mapper;
  }

  @GetMapping(value = "/private/auth/listUsers")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  @Operation(summary = "Listar usuarios", description = "Lista todos os usuarios para o administrador")
  public List<UsersDTO> listUsers() {
    return service.findAll();
  }

  @PostMapping("/private/auth/create")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(summary = "Criar usuarios adm", description = "Para o administrador cadastrar usuarios")
  public ResponseEntity<UsersDTO> create(@RequestBody UsersDTO user) throws Exception {
    super.createObject(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/private/auth/update")
  @PreAuthorize("hasRole('ROLE_USER')")
  @Operation(summary = "Atualizar perfil", description = "Para o usuario atualizar o dados do perfil")
  public ResponseEntity<UsersDTO> update(@RequestBody UsersDTO user) throws Exception {
    return ResponseEntity.ok(service.update(user));
  }

  @PostMapping("/public/auth/login")
  @Operation(summary = "Login", description = "Para o usuario efetuar o login, retornando o token necessario na busca dos dados em '/private/auth/getUser'")
  public ResponseEntity<?> login(@RequestBody LoginDTO body) {
    Optional<Users> optionalUser = this.repository.findByEmail(body.email());
    if (optionalUser.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Usuário não encontrado\"}");
    }

    Users user = optionalUser.get();
    if (!passwordEncoder.matches(body.password(), user.getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Senha incorreta\"}");

    }

    String token = this.tokenService.generateToken(user);
    return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
  }

  @GetMapping("/private/auth/getUser")
  @Operation(summary = "Buscar dados usuario", description = "Apos login, buscar dados do usuario com o token")
  public ResponseEntity<UsersDTO> getUser(@RequestParam String email) {
    Optional<Users> usr = this.repository.findByEmail(email);
    UsersDTO user = new UsersDTO();
    if (usr.isPresent()) {
      user = service.getUser(usr.get());
    }
    return ResponseEntity.ok(user);

  }

  @PostMapping(value = "/public/auth/register")
  @Operation(summary = "Cadastro", description = "Para o usuario realizar o registro")
  public ResponseEntity<?> register(@RequestBody RegisterDTO body) throws Exception {
    Optional<Users> usr = this.repository.findByEmail(body.getEmail());
    if (usr.isEmpty()) {
      Users user = this.service.register(body);
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
    }
    return ResponseEntity.badRequest().build();
  }

  @PutMapping("/public/auth/user_roler")
  @Operation(summary = "Edição de permissões do usuario", description = "Usuario administrador modifica as rolers do usuario")
  public ResponseEntity<?> roles(@RequestBody RolesDTO dto) throws Exception {
    Optional<Users> usr = this.repository.findByEmail(dto.email());
    if (usr.isPresent()) {
      Users user = usr.get();
      Set<Roles_user> usr_roles = user.getRoles();
      usr_roles.addAll(dto.roles());
      this.service.save(mapper.toDto(user));
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

}
