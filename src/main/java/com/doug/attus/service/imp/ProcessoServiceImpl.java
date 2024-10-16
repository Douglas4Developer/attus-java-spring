package com.doug.attus.service.imp;

import com.doug.attus.exception.ResourceNotFoundException;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public Processo criarProcesso(Processo processo) {
        return processoRepository.save(processo);
    }

    @Override
    public Processo buscarPorNumero(String numeroProcesso) {
        return processoRepository.findByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new ResourceNotFoundException("Processo com número " + numeroProcesso + " não encontrado"));
    }

    @Override
    public void arquivarProcesso(Long id) {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Processo com ID " + id + " não encontrado"));
        processo.setStatus("Arquivado");
        processoRepository.save(processo);
    }

    @Override
    public List<Processo> listarTodosProcessos() {
        return processoRepository.findAll();
    }

    @Override
    public List<Processo> listarProcessosPorStatus(String status) {
        return processoRepository.findByStatus(status);
    }
}
