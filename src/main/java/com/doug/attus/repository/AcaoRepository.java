package com.doug.attus.repository;


import com.doug.attus.model.Acao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcaoRepository extends JpaRepository<Acao, Long> {
    List<Acao> findAllByProcessoId(Long processoId);
}