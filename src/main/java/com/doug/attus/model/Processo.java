package com.doug.attus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "processo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O número do processo é obrigatório e não pode ser vazio.")
    @Column(name = "numero_processo", unique = true, nullable = false)
    private String numeroProcesso;

    @NotNull(message = "A data de abertura é obrigatória.")
    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @NotBlank(message = "A descrição do processo é obrigatória.")
    @Column(nullable = false)
    private String descricao;

    @NotBlank(message = "O status do processo é obrigatório.")
    @Column(nullable = false)
    private String status; // Ativo, Suspenso, Arquivado

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private List<Parte> partes = new ArrayList<>();

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private List<Acao> acoes = new ArrayList<>();
}
