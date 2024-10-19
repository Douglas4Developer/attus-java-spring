package com.doug.attus.controller;

import com.doug.attus.dto.AcaoDTO;
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
import java.util.stream.Collectors;

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

        // Inicializando corretamente a Acao
        Acao acao = new Acao();
        acao.setTipo("Petição");
        acao.setDataRegistro(LocalDate.now());
        acao.setDescricao("Descrição da Ação");

        // Criando um AcaoDTO com os mesmos dados
        AcaoDTO acaoInputDTO = new AcaoDTO(acao);

        // Configura o mock para retornar a entidade Acao quando o serviço for chamado
        when(acaoService.adicionarAcao(eq(processoId), any(Acao.class))).thenReturn(acao);

        // Chama o método do controller
        ResponseEntity<AcaoDTO> response = acaoController.adicionarAcao(processoId, acaoInputDTO);

        // Verifica se a resposta não é nula
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Verifica se os campos retornados estão corretos
        AcaoDTO responseBody = response.getBody();
        assertNotNull(responseBody);  // O corpo da resposta não deve ser null
        assertEquals("Petição", responseBody.getTipo());  // Comparar tipo corretamente
        assertEquals(LocalDate.now(), responseBody.getDataRegistro());  // Verificar a data corretamente
        assertEquals("Descrição da Ação", responseBody.getDescricao());

        // Verifica se o método adicionarAcao foi chamado no mock
        verify(acaoService, times(1)).adicionarAcao(eq(processoId), any(Acao.class));
    }


    @Test
    void listarAcoesPorProcesso() {
        Long processoId = 1L;
        List<Acao> acoes = new ArrayList<>();
        Acao acao = new Acao();
        acao.setTipo("Audiência");
        acao.setDataRegistro(LocalDate.now());
        acao.setDescricao("Descrição da Ação");
        acoes.add(acao);

        List<AcaoDTO> acaoDTOs = acoes.stream().map(AcaoDTO::new).collect(Collectors.toList());

        when(acaoService.listarAcoesPorProcesso(processoId)).thenReturn(acoes);

        ResponseEntity<List<AcaoDTO>> response = acaoController.listarAcoesPorProcesso(processoId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(acaoDTOs, response.getBody());  // Comparar listas de DTOs
    }


}
