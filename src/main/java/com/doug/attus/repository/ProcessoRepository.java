package com.doug.attus.repository;

import com.doug.attus.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
    List<Processo> findByStatus(String status);
}