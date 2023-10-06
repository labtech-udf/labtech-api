package br.com.labtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="LabTech", version = "1", description = "Api Labtech"))
public class LabtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabtechApplication.class, args);
	}

}
