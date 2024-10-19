package com.doug.attus.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "acao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo; // Petição, Audiência, Sentença

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    @JsonIgnore  // Evita referência circular
    private Processo processo;
}
