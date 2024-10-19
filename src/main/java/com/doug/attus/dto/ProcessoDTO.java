package com.doug.attus.dto;

import com.doug.attus.model.Processo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessoDTO {

    private Long id;

    @Schema(description = "Número do Processo", example = "12345-67.2024.8.09.0011")
    private String numeroProcesso;

    @Schema(description = "Data de Abertura do Processo", example = "2024-10-19")
    private LocalDate dataAbertura;

    @Schema(description = "Descrição do Processo", example = "Processo sobre litígio contratual entre as partes.")
    private String descricao;

    @Schema(description = "Status do Processo", example = "Ativo")
    private String status;

    private List<ParteDTO> partes;
    private List<AcaoDTO> acoes;

    // Construtor que converte de entidade para DTO
    public ProcessoDTO(Processo processo) {
        this.id = processo.getId(); // Mapeando o ID
        this.numeroProcesso = processo.getNumeroProcesso();
        this.dataAbertura = processo.getDataAbertura();
        this.descricao = processo.getDescricao();
        this.status = processo.getStatus();
        this.partes = processo.getPartes() != null ? processo.getPartes().stream().map(ParteDTO::new).collect(Collectors.toList()) : new ArrayList<>();
        this.acoes = processo.getAcoes() != null ? processo.getAcoes().stream().map(AcaoDTO::new).collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessoDTO that = (ProcessoDTO) o;
        return Objects.equals(numeroProcesso, that.numeroProcesso) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dataAbertura, that.dataAbertura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroProcesso, descricao, status, dataAbertura);
    }

    // Método que converte de DTO para a entidade Processo
    public Processo toEntity() {
        Processo processo = new Processo();
        processo.setId(this.id);
        processo.setNumeroProcesso(this.numeroProcesso);
        processo.setDescricao(this.descricao);
        processo.setStatus(this.status);
        processo.setDataAbertura(this.dataAbertura);
        return processo;
    }
}
