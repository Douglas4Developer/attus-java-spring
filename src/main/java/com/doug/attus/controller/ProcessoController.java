package com.doug.attus.controller;

import com.doug.attus.model.Processo;
import com.doug.attus.service.ProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Operation(summary = "Cria um novo processo jurídico", description = "Cria um novo processo com as informações fornecidas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Processo> criarProcesso(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do novo processo")
            @RequestBody Processo processo) {
        return ResponseEntity.ok(processoService.criarProcesso(processo));
    }

    @Operation(summary = "Busca processo por número", description = "Retorna o processo com base no número informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping("/{numeroProcesso}")
    public ResponseEntity<Processo> buscarPorNumero(
            @Parameter(description = "Número do processo a ser buscado")
            @PathVariable String numeroProcesso) {
        return ResponseEntity.ok(processoService.buscarPorNumero(numeroProcesso));
    }

    @Operation(summary = "Arquiva um processo", description = "Altera o status do processo para 'Arquivado'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Processo arquivado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content)
    })
    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<Void> arquivarProcesso(
            @Parameter(description = "ID do processo a ser arquivado")
            @PathVariable Long id) {
        processoService.arquivarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista todos os processos", description = "Retorna a lista de todos os processos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class)))
    @GetMapping
    public ResponseEntity<List<Processo>> listarTodosProcessos() {
        return ResponseEntity.ok(processoService.listarTodosProcessos());
    }
}
