package com.doug.attus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @Column(nullable = false)
    private String tipo; // Autor, Réu, Advogado

    @Column(name = "contato_email")
    private String contatoEmail;

    @Column(name = "contato_telefone")
    private String contatoTelefone;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    @JsonIgnore  // Evita referência circular
    private Processo processo;
}
