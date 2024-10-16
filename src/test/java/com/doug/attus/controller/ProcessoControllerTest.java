package com.doug.attus.controller;

import com.doug.attus.model.Processo;
import com.doug.attus.service.ProcessoService;
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

class ProcessoControllerTest {

    @InjectMocks
    private ProcessoController processoController;

    @Mock
    private ProcessoService processoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProcesso() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setNumeroProcesso("12345");
        processo.setDataAbertura(LocalDate.now());
        processo.setDescricao("Descrição do Processo");
        processo.setStatus("Ativo");

        when(processoService.criarProcesso(processo)).thenReturn(processo);

        ResponseEntity<Processo> response = processoController.criarProcesso(processo);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(processo, response.getBody());
        verify(processoService, times(1)).criarProcesso(processo);
    }

    @Test
    void buscarPorNumero() {
        String numeroProcesso = "12345";
        Processo processo = new Processo();
        processo.setNumeroProcesso(numeroProcesso);

        when(processoService.buscarPorNumero(numeroProcesso)).thenReturn(processo);

        ResponseEntity<Processo> response = processoController.buscarPorNumero(numeroProcesso);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(processo, response.getBody());
        verify(processoService, times(1)).buscarPorNumero(numeroProcesso);
    }

    @Test
    void arquivarProcesso() {
        Long processoId = 1L;

        doNothing().when(processoService).arquivarProcesso(processoId);

        ResponseEntity<Void> response = processoController.arquivarProcesso(processoId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(processoService, times(1)).arquivarProcesso(processoId);
    }

    @Test
    void listarTodosProcessos() {
        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo());

        when(processoService.listarTodosProcessos()).thenReturn(processos);

        ResponseEntity<List<Processo>> response = processoController.listarTodosProcessos();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(processos, response.getBody());
        verify(processoService, times(1)).listarTodosProcessos();
    }
}
