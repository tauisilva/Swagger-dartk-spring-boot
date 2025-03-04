package com.swagger_dark.swagger_dark;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Titulo da aplicação", version = "0.1 - Alpha", description = "Descrição da aplicação"))
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT auth",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
public class SwaggerDarkApplication {

  public static void main(String[] args) {
    SpringApplication.run(SwaggerDarkApplication.class, args);
  }

}
