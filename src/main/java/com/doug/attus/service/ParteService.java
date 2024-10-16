package com.doug.attus.service;
import com.doug.attus.model.Parte;

import java.util.List;

public interface ParteService {
    Parte adicionarParte(Long processoId, Parte parte);
    List<Parte> listarPartesPorProcesso(Long processoId);
}
