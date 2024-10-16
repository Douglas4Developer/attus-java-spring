package com.doug.attus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "numero_processo", unique = true, nullable = false)
    private String numeroProcesso;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String status; // Ativo, Suspenso, Arquivado

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private List<Parte> partes;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
    private List<Acao> acoes;
}
