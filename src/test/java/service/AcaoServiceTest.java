package com.doug.attus.service;

import com.doug.attus.model.Acao;
import com.doug.attus.repository.AcaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcaoServiceTest {

    @InjectMocks
    private AcaoService acaoService;

    @Mock
    private AcaoRepository acaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarAcao() {
        Acao acao = new Acao();
        acao.setId(1L);

        when(acaoRepository.save(acao)).thenReturn(acao);

        Acao resultado = acaoService.adicionarAcao(1L, acao);

        assertNotNull(resultado);
        assertEquals(acao.getId(), resultado.getId());
        verify(acaoRepository, times(1)).save(acao);
    }

    @Test
    void listarAcoesPorProcesso() {
        List<Acao> acoes = new ArrayList<>();
        acoes.add(new Acao());

        when(acaoRepository.findAllByProcessoId(1L)).thenReturn(acoes);

        List<Acao> resultado = acaoService.listarAcoesPorProcesso(1L);

        assertNotNull(resultado);
        assertEquals(acoes.size(), resultado.size());
        verify(acaoRepository, times(1)).findAllByProcessoId(1L);
    }
}
