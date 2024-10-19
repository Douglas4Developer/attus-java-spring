package com.doug.attus.service.imp;


import com.doug.attus.exception.ProcessoNaoEncontradoException;
import com.doug.attus.model.Parte;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.ParteRepository;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteServiceImpl implements ParteService {

    @Autowired
    private ParteRepository parteRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public Parte adicionarParte(Long processoId, Parte parte) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo com ID " + processoId + " não encontrado"));
        parte.setProcesso(processo);
        return parteRepository.save(parte);
    }

    @Override
    public List<Parte> listarPartesPorProcesso(Long processoId) {
        return parteRepository.findAllByProcessoId(processoId);
    }
}
