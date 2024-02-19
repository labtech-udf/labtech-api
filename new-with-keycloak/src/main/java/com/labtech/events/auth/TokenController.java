package com.labtech.events.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TokenController {

  @Autowired
  private TokenService service;

  @GetMapping("/api/token")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> hello() throws JsonProcessingException {
    String message = "ipdasjiodasio";
    // Create a JSON object with the desired key-value pairs
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(response));

  }

  @GetMapping("/api/public/roles")
  public List<RoleRepresentation> getRoles() {
    return this.service.getRoles();
  }

  @GetMapping("/api/public/user")
  public List<UserRepresentation> getusers() {
    return this.service.getUsers();
  }

  @PostMapping("/api/public/create")
  public ResponseEntity<Response> createUser(
    @RequestBody pessoa request) {
    Response user = service.createUser(request);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/api/public/get")
  public ResponseEntity<String> getText() throws JsonProcessingException {
    String message = "Working no user login";
    // Create a JSON object with the desired key-value pairs
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(response));
  }
}
