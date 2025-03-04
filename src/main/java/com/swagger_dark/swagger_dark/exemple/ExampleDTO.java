package com.swagger_dark.swagger_dark.exemple;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO representando um exemplo")
public class ExampleDTO {

  @Schema(description = "Identificador único do exemplo", example = "1")
  private int id;

  @Schema(description = "Nome do exemplo", example = "Exemplo Teste")
  @NotBlank(message = "O nome não pode estar vazio")
  private String name;

  @Schema(description = "Descrição do exemplo", example = "Descrição detalhada do exemplo")
  private String description;
}
