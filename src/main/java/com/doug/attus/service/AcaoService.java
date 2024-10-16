package com.doug.attus.service;

import  com.doug.attus.model.Acao;

import java.util.List;

public interface AcaoService {
    Acao adicionarAcao(Long processoId, Acao acao);
    List<Acao> listarAcoesPorProcesso(Long processoId);
}
