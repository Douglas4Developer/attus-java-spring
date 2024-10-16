package com.doug.attus.controller;

import com.doug.attus.model.Acao;
import com.doug.attus.service.AcaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcaoControllerTest {

    @InjectMocks
    private AcaoController acaoController;

    @Mock
    private AcaoService acaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarAcao() {
        Long processoId = 1L;
        Acao acao = new Acao();
        acao.setId(1L);
        acao.setTipo("Petição");
        acao.setDataRegistro(LocalDate.now());
        acao.setDescricao("Descrição da Ação");

        when(acaoService.adicionarAcao(processoId, acao)).thenReturn(acao);

        ResponseEntity<Acao> response = acaoController.adicionarAcao(processoId, acao);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(acao, response.getBody());
        verify(acaoService, times(1)).adicionarAcao(processoId, acao);
    }

    @Test
    void listarAcoesPorProcesso() {
        Long processoId = 1L;
        List<Acao> acoes = new ArrayList<>();
        acoes.add(new Acao());

        when(acaoService.listarAcoesPorProcesso(processoId)).thenReturn(acoes);

        ResponseEntity<List<Acao>> response = acaoController.listarAcoesPorProcesso(processoId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(acoes, response.getBody());
        verify(acaoService, times(1)).listarAcoesPorProcesso(processoId);
    }
}
