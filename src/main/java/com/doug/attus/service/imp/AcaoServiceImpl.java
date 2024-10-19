package com.doug.attus.service.imp;


import com.doug.attus.exception.ProcessoNaoEncontradoException;
import com.doug.attus.model.Acao;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.AcaoRepository;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.AcaoService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private ProcessoRepository processoRepository;


    @Override
    public Acao adicionarAcao(Long processoId, Acao acao) {
        // Buscar o processo no banco de dados
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo com ID " + processoId + " não encontrado"));

        // Associar a ação ao processo
        acao.setProcesso(processo);

        // Salvar a ação no banco de dados
        Acao acaoSalva = acaoRepository.save(acao);

        return acaoSalva;
    }
    @Override
    public List<Acao> listarAcoesPorProcesso(Long processoId) {
        return acaoRepository.findAllByProcessoId(processoId);
    }
}
