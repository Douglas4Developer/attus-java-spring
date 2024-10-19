package com.doug.attus.service.imp;
import com.doug.attus.controller.ProcessoController;
import com.doug.attus.exception.NumeroProcessoDuplicadoException;
import com.doug.attus.exception.ProcessoNaoEncontradoException;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.ProcessoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessoController.class);


    @Autowired
    private ProcessoRepository processoRepository;


    @Override
    public Processo criarProcesso(Processo processo) {
        try {
            return processoRepository.save(processo);
        } catch (DataIntegrityViolationException ex) {
            // Aqui estamos capturando a exceção de violação de integridade
            throw new NumeroProcessoDuplicadoException("O número do processo já existe: " + processo.getNumeroProcesso());
        }
    }

    @Override
    public Processo buscarPorNumero(String numeroProcesso) {
        return processoRepository.findByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo com número " + numeroProcesso + " não encontrado"));
    }

    @Override
    public void arquivarProcesso(Long id) {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo com ID " + id + " não encontrado"));
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
