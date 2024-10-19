package com.doug.attus.dto;

import com.doug.attus.model.Parte;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor // Construtor sem argumentos necessário para o Jackson
@AllArgsConstructor // Construtor com todos os argumentos (opcional, pode ajudar a criar o DTO manualmente)
@Builder // Facilita a criação de objetos usando o padrão builder
public class ParteDTO {


    @Schema(description = "Nome Completo da Parte", example = "João da Silva")
    private String nomeCompleto;

    @Schema(description = "CPF ou CNPJ da Parte", example = "123.456.789-09")
    private String cpfCnpj;

    @Schema(description = "Tipo da Parte (Autor, Réu, Advogado)", example = "Autor")
    private String tipo;

    @Schema(description = "Email de Contato da Parte", example = "joao.silva@example.com")
    private String contatoEmail;

    @Schema(description = "Telefone de Contato da Parte", example = "+55 62 91234-5678")
    private String contatoTelefone;


    public ParteDTO(Parte parte) {
        if (parte != null) {
            this.nomeCompleto = parte.getNomeCompleto();
            this.cpfCnpj = parte.getCpfCnpj();
            this.tipo = parte.getTipo();
            this.contatoEmail = parte.getContatoEmail();
            this.contatoTelefone = parte.getContatoTelefone();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParteDTO parteDTO = (ParteDTO) o;
        return Objects.equals(nomeCompleto, parteDTO.nomeCompleto) &&
                Objects.equals(cpfCnpj, parteDTO.cpfCnpj) &&
                Objects.equals(tipo, parteDTO.tipo) &&
                Objects.equals(contatoEmail, parteDTO.contatoEmail) &&
                Objects.equals(contatoTelefone, parteDTO.contatoTelefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, cpfCnpj, tipo, contatoEmail, contatoTelefone);
    }

    public Parte toEntity() {
        Parte parte = new Parte();
        parte.setNomeCompleto(this.nomeCompleto);
        parte.setCpfCnpj(this.cpfCnpj);
        parte.setTipo(this.tipo);
        parte.setContatoEmail(this.contatoEmail);
        parte.setContatoTelefone(this.contatoTelefone);
        return parte;
    }

}
