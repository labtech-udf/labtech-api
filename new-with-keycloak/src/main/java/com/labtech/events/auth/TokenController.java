package com.labtech.events.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
  @GetMapping("/api/token")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> hello() throws JsonProcessingException {
    String message = "ipdasjiodasio";
    // Create a JSON object with the desired key-value pairs
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(response));

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
