package com.labtech.events.auth;

import lombok.NonNull;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TokenService {

  @Value("${keycloak.realm}")
  private String realm;

  @Autowired
  private Keycloak keycloak;

  public List<RoleRepresentation> getRoles() {
    return keycloak.realm(realm).roles().list();
  }

  public List<UserRepresentation> getUsers() {
    return keycloak.realm(realm).users().list();
  }

  public Response createUser(pessoa p) {
    UserRepresentation user = new UserRepresentation();
    user.setFirstName(p.firstName());
    user.setLastName(p.lastName());
    user.setEmail(p.email());
    user.setEmailVerified(true);
    List<RoleRepresentation> roles = getRoles();
    if (!roles.isEmpty()) {
      user.setRealmRoles(List.of(roles.getFirst().getName()));
    }
    try {
      return keycloak.realm(realm).users().create(user);
    } catch (Exception e) {
      // Implemente o tratamento de erros aqui
      throw new RuntimeException("Falha ao criar usuário: " + e.getMessage());
    }
  }

}
