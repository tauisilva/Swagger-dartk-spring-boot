package com.swagger_dark.swagger_dark.exemple;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/examples")
@Tag(name = "Example", description = "Operações de CRUD para exemplos")
public class ExampleController {

  private final List<ExampleDTO> exampleList = new ArrayList<>();

  @Operation(summary = "Listar todos os exemplos", description = "Retorna uma lista de todos os exemplos disponíveis.")
  @ApiResponse(responseCode = "200", description = "Lista de exemplos retornada com sucesso",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExampleDTO.class))))
  @GetMapping
  public ResponseEntity<List<ExampleDTO>> getAll() {
    return ResponseEntity.ok(exampleList);
  }

  @Operation(summary = "Buscar exemplo por ID", description = "Obtém um exemplo específico pelo ID fornecido.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exemplo encontrado",
      content = @Content(schema = @Schema(implementation = ExampleDTO.class))),
    @ApiResponse(responseCode = "404", description = "Exemplo não encontrado")
  })
  @GetMapping("/{id}")
  public ResponseEntity<ExampleDTO> getById(@PathVariable int id) {
    return exampleList.stream()
      .filter(ex -> ex.getId() == id)
      .findFirst()
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @Operation(summary = "Criar um novo exemplo", description = "Adiciona um novo exemplo na lista.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Exemplo criado com sucesso",
      content = @Content(schema = @Schema(implementation = ExampleDTO.class))),
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
  })
  @PostMapping
  public ResponseEntity<ExampleDTO> create(@Valid @RequestBody ExampleDTO example) {
    exampleList.add(example);
    return ResponseEntity.status(HttpStatus.CREATED).body(example);
  }

  @Operation(summary = "Atualizar um exemplo", description = "Modifica os dados de um exemplo existente pelo ID.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exemplo atualizado com sucesso",
      content = @Content(schema = @Schema(implementation = ExampleDTO.class))),
    @ApiResponse(responseCode = "404", description = "Exemplo não encontrado")
  })
  @PutMapping("/{id}")
  public ResponseEntity<ExampleDTO> update(@PathVariable int id, @Valid @RequestBody ExampleDTO updatedExample) {
    for (int i = 0; i < exampleList.size(); i++) {
      if (exampleList.get(i).getId() == id) {
        exampleList.set(i, updatedExample);
        return ResponseEntity.ok(updatedExample);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @Operation(summary = "Deletar um exemplo", description = "Remove um exemplo pelo ID fornecido.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Exemplo deletado com sucesso"),
    @ApiResponse(responseCode = "404", description = "Exemplo não encontrado")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    boolean removed = exampleList.removeIf(ex -> ex.getId() == id);
    return removed ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @Operation(summary = "Obter cabeçalhos dos exemplos", description = "Retorna apenas os cabeçalhos sem o corpo.")
  @ApiResponse(responseCode = "200", description = "Cabeçalhos retornados com sucesso")
  @RequestMapping(method = RequestMethod.HEAD)
  public ResponseEntity<Void> head() {
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Obter métodos suportados", description = "Retorna os métodos HTTP suportados pelo recurso.")
  @ApiResponse(responseCode = "200", description = "Métodos retornados com sucesso")
  @RequestMapping(method = RequestMethod.OPTIONS)
  public ResponseEntity<Void> options() {
    return ResponseEntity.ok()
      .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.HEAD)
      .build();
  }

  @Operation(summary = "Atualizar parcialmente um exemplo", description = "Modifica parcialmente os dados de um exemplo existente pelo ID.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Exemplo atualizado com sucesso",
      content = @Content(schema = @Schema(implementation = ExampleDTO.class))),
    @ApiResponse(responseCode = "404", description = "Exemplo não encontrado"),
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
  })
  @PatchMapping("/{id}")
  public ResponseEntity<ExampleDTO> patch(@PathVariable int id, @RequestBody Map<String, Object> updates) {
    for (ExampleDTO example : exampleList) {
      if (example.getId() == id) {
        updates.forEach((key, value) -> {
          Field field;
          try {
            field = ExampleDTO.class.getDeclaredField(key);
            field.setAccessible(true);
            field.set(example, value);
          } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Erro ao atualizar o campo: " + key);
          }
        });
        return ResponseEntity.ok(example);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}