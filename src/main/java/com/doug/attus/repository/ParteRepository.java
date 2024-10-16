package com.doug.attus.repository;


import com.doug.attus.model.Parte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParteRepository extends JpaRepository<Parte, Long> {
    List<Parte> findAllByProcessoId(Long processoId);
}