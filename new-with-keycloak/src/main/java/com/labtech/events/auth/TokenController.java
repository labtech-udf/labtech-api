package com.labtech.events.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/token")
@RestController
public class TokenController {

  @PostMapping("/")
  public String token(@RequestBody User user) {
    HttpHeaders headers = new HttpHeaders();
    return "Sim";
  }

  public record User() {

  }
}
