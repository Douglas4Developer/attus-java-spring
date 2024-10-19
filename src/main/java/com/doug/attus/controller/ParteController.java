package com.doug.attus.controller;

import com.doug.attus.dto.ParteDTO;
import com.doug.attus.model.Parte;
import com.doug.attus.service.ParteService;
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
@RequestMapping("/api/partes")
public class ParteController {

    @Autowired
    private ParteService parteService;

    @Operation(summary = "Adiciona uma parte a um processo", description = "Adiciona uma parte ao processo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parte adicionada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content)
    })
    @PostMapping("/processos/{processoId}")
    public ResponseEntity<ParteDTO> adicionarParte(
            @Parameter(description = "ID do processo onde a parte será adicionada") @PathVariable Long processoId,
            @RequestBody ParteDTO parteInputDTO) {
        Parte novaParte = parteService.adicionarParte(processoId, parteInputDTO.toEntity());
        return ResponseEntity.ok(new ParteDTO(novaParte));
    }

    @Operation(summary = "Lista as partes de um processo", description = "Retorna todas as partes de um processo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de partes retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado", content = @Content)
    })
    @GetMapping("/processos/{processoId}")
    public ResponseEntity<List<ParteDTO>> listarPartesPorProcesso(@PathVariable Long processoId) {
        List<ParteDTO> partes = parteService.listarPartesPorProcesso(processoId)
                .stream().map(ParteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(partes);
    }
}
