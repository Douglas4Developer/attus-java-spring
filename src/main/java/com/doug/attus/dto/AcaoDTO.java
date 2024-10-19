package com.doug.attus.dto;

import com.doug.attus.model.Acao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor // Construtor sem argumentos necessário para o Jackson
@AllArgsConstructor // Construtor com todos os argumentos (opcional, pode ajudar a criar o DTO manualmente)
@Builder // Facilita a criação de objetos usando o padrão builder
public class AcaoDTO {


    @Schema(description = "Tipo da Ação, ex: Petição, Audiência, Sentença", example = "Audiência")
    private String tipo;

    @Schema(description = "Data de Registro da Ação", example = "2024-10-20")
    private LocalDate dataRegistro;

    @Schema(description = "Descrição da Ação", example = "Audiência marcada para julgamento.")
    private String descricao;

    public AcaoDTO(Acao acao) {
        if (acao != null) {
            this.tipo = acao.getTipo();
            this.dataRegistro = acao.getDataRegistro();
            this.descricao = acao.getDescricao();
        } else {
            throw new IllegalArgumentException("Ação não pode ser nula");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcaoDTO acaoDTO = (AcaoDTO) o;
        return Objects.equals(tipo, acaoDTO.tipo) &&
                Objects.equals(dataRegistro, acaoDTO.dataRegistro) &&
                Objects.equals(descricao, acaoDTO.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, dataRegistro, descricao);
    }


    public Acao toEntity() {
        Acao acao = new Acao();
        acao.setTipo(this.tipo);
        acao.setDataRegistro(this.dataRegistro);
        acao.setDescricao(this.descricao);
        return acao;
    }
}
