package com.doug.attus.controller;

import com.doug.attus.model.Parte;
import com.doug.attus.service.ParteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParteControllerTest {

    @InjectMocks
    private ParteController parteController;

    @Mock
    private ParteService parteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarParte() {
        Long processoId = 1L;
        Parte parte = new Parte();
        parte.setId(1L);
        parte.setNomeCompleto("João Silva");
        parte.setCpfCnpj("12345678900");

        when(parteService.adicionarParte(processoId, parte)).thenReturn(parte);

        ResponseEntity<Parte> response = parteController.adicionarParte(processoId, parte);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(parte, response.getBody());
        verify(parteService, times(1)).adicionarParte(processoId, parte);
    }

    @Test
    void listarPartesPorProcesso() {
        Long processoId = 1L;
        List<Parte> partes = new ArrayList<>();
        partes.add(new Parte());

        when(parteService.listarPartesPorProcesso(processoId)).thenReturn(partes);

        ResponseEntity<List<Parte>> response = parteController.listarPartesPorProcesso(processoId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(partes, response.getBody());
        verify(parteService, times(1)).listarPartesPorProcesso(processoId);
    }
}
