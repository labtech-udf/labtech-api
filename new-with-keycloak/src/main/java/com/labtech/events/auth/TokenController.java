package com.labtech.events.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labtech.events.security.CustomJwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {

  @GetMapping("/token")
  @PreAuthorize("hasAuthority('ROLE_PROFESSOR')")
  public Message hello() {
    var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
    var message = MessageFormat.format(
      "Hello {0} {1}, Bem vindo!",
      jwt.getName(), jwt.getLastname()
    );
    return new Message(message);

  }

  public record Message(String message) {

  }

  @GetMapping("/public/get")
  public ResponseEntity<String> getText() throws JsonProcessingException {
    String message = "Working no user login";
    // Create a JSON object with the desired key-value pairs
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(response));
  }

}
