package br.com.labtech;

import br.com.labtech.utils.UrlConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "LabTech", version = "1", description = "Api Labtech"))
public class LabtechApplication {
  @Value("${api.url.photo}")
  private String absolutePath;

  public static void main(String[] args) {
    SpringApplication.run(LabtechApplication.class, args);
  }

  @PostConstruct
  public void init() {
    UrlConstant.setAbsoluteUrl(this.absolutePath);
  }

}
