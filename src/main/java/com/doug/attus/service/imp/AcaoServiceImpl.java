package com.doug.attus.service.imp;

import com.doug.attus.exception.ResourceNotFoundException;
import com.doug.attus.model.Acao;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.AcaoRepository;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public Acao adicionarAcao(Long processoId, Acao acao) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new ResourceNotFoundException("Processo com ID " + processoId + " não encontrado"));
        acao.setProcesso(processo);
        return acaoRepository.save(acao);
    }

    @Override
    public List<Acao> listarAcoesPorProcesso(Long processoId) {
        return acaoRepository.findAllByProcessoId(processoId);
    }
}
