package com.doug.attus.controller;

import com.doug.attus.dto.AcaoDTO;
import com.doug.attus.model.Acao;
import com.doug.attus.service.AcaoService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acoes")
public class AcaoController {

    @Autowired
    private AcaoService acaoService;
    @Operation(summary = "Adiciona uma ação a um processo", description = "Adiciona uma nova ação ao processo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ação adicionada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AcaoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content)
    })
    @PostMapping("/processos/{processoId}")
    public ResponseEntity<AcaoDTO> adicionarAcao(
            @Parameter(description = "ID do processo onde a ação será adicionada") @PathVariable Long processoId,
            @RequestBody AcaoDTO acaoInputDTO) {
        if (acaoInputDTO == null) {
            throw new IllegalArgumentException("Ação não pode ser nula");
        }
        Acao novaAcao = acaoService.adicionarAcao(processoId, acaoInputDTO.toEntity());
        return ResponseEntity.ok(new AcaoDTO(novaAcao));
    }


    @Operation(summary = "Lista as ações de um processo", description = "Retorna todas as ações de um processo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ações retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AcaoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content)
    })
    @GetMapping("/processos/{processoId}")
    public ResponseEntity<List<AcaoDTO>> listarAcoesPorProcesso(@PathVariable Long processoId) {
        List<AcaoDTO> acoes = acaoService.listarAcoesPorProcesso(processoId)
                .stream().map(AcaoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(acoes);
    }
}
