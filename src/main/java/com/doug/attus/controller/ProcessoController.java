package com.doug.attus.controller;

import com.doug.attus.dto.ProcessoDTO;
import com.doug.attus.exception.NumeroProcessoDuplicadoException;
import com.doug.attus.model.Processo;
import com.doug.attus.service.ProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    private static final Logger logger = LoggerFactory.getLogger(ProcessoController.class);


    @Operation(summary = "Cria um novo processo jurídico", description = "Cria um novo processo com as informações fornecidas. O ID é gerado automaticamente pelo banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProcessoDTO> criarProcesso(@RequestBody ProcessoDTO processoDTO) {
        Processo processo = processoDTO.toEntity();  // Convert DTO to entity
        Processo createdProcesso = processoService.criarProcesso(processo);  // Call service to save
        ProcessoDTO createdProcessoDTO = new ProcessoDTO(createdProcesso);  // Convert back to DTO
        return ResponseEntity.ok(createdProcessoDTO);  // Return the DTO in the response
    }

    @Operation(summary = "Busca processo por número", description = "Retorna o processo com base no número informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping("/{numeroProcesso}")
    public ResponseEntity<ProcessoDTO> buscarPorNumero(@PathVariable String numeroProcesso) {
        Processo processo = processoService.buscarPorNumero(numeroProcesso);
        return ResponseEntity.ok(new ProcessoDTO(processo));
    }

    @Operation(summary = "Lista todos os processos", description = "Retorna a lista de todos os processos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDTO.class)))
    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listarTodosProcessos() {
        List<ProcessoDTO> processos = processoService.listarTodosProcessos()
                .stream().map(ProcessoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(processos);
    }

    @Operation(summary = "Arquiva processo pelo id", description = "Realiza o arquivamento de um processos cadastrado")
    @ApiResponse(responseCode = "204", description = "Processo arquivo com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDTO.class)))
    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<Void> arquivarProcesso(@PathVariable Long id) {
        processoService.arquivarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NumeroProcessoDuplicadoException.class)
    public ResponseEntity<Object> handleNumeroProcessoDuplicado(NumeroProcessoDuplicadoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Número de processo duplicado");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
