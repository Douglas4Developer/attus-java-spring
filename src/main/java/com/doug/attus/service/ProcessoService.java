package com.doug.attus.service;
import com.doug.attus.model.Processo;

import java.util.List;

public interface ProcessoService {
    Processo criarProcesso(Processo processo);
    Processo buscarPorNumero(String numeroProcesso);
    void arquivarProcesso(Long id);
    List<Processo> listarTodosProcessos();
    List<Processo> listarProcessosPorStatus(String status);
}
