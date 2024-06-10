package com.labtech.events;

import com.labtech.events.files.UrlConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "LabTech",
    version = "1.0",
    description = "Api Labtech"
  )
)

@SecurityScheme(
  name = "bearerAuth",
  description = "JWT auth",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)

public class LabtechEventosApiApplication {

  @Value("${api.url}")
  private String absolutePath;

  public static void main(String[] args) {
    SpringApplication.run(LabtechEventosApiApplication.class, args);
  }

  @PostConstruct
  public void init() {
    UrlConstant.setAbsoluteUrl(this.absolutePath);
  }

}
