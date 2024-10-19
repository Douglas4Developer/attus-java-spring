package com.doug.attus.controller;

import com.doug.attus.dto.ParteDTO;
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
import java.util.stream.Collectors;

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

        // Inicializando corretamente a Parte
        Parte parte = new Parte();
        parte.setNomeCompleto("João Silva");
        parte.setCpfCnpj("12345678900");
        parte.setTipo("Autor");
        parte.setContatoEmail("joao.silva@example.com");
        parte.setContatoTelefone("+55 62 91234-5678");

        // Criando um ParteDTO com os mesmos dados
        ParteDTO parteInputDTO = new ParteDTO(parte);

        // Configura o mock para retornar a entidade Parte quando o serviço for chamado
        when(parteService.adicionarParte(eq(processoId), any(Parte.class))).thenReturn(parte);

        // Chama o método do controller
        ResponseEntity<ParteDTO> response = parteController.adicionarParte(processoId, parteInputDTO);

        // Verifica se a resposta não é nula
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Verifica se os campos retornados estão corretos
        ParteDTO responseBody = response.getBody();
        assertNotNull(responseBody);  // O corpo da resposta não deve ser null
        assertEquals("João Silva", responseBody.getNomeCompleto());  // Comparar nomeCompleto corretamente
        assertEquals("12345678900", responseBody.getCpfCnpj());
        assertEquals("Autor", responseBody.getTipo());
        assertEquals("joao.silva@example.com", responseBody.getContatoEmail());
        assertEquals("+55 62 91234-5678", responseBody.getContatoTelefone());

        // Verifica se o método adicionarParte foi chamado no mock
        verify(parteService, times(1)).adicionarParte(eq(processoId), any(Parte.class));
    }


    @Test
    void listarPartesPorProcesso() {
        Long processoId = 1L;

        List<Parte> partes = new ArrayList<>();
        Parte parte = new Parte();
        parte.setNomeCompleto("João Silva");
        parte.setCpfCnpj("12345678900");
        parte.setTipo("Autor");
        parte.setContatoEmail("joao.silva@example.com");
        parte.setContatoTelefone("+55 62 91234-5678");
        partes.add(parte);

        // Criar DTO com base na lista de partes
        List<ParteDTO> parteDTOs = partes.stream().map(ParteDTO::new).collect(Collectors.toList());

        // Mocking the service
        when(parteService.listarPartesPorProcesso(processoId)).thenReturn(partes);

        // Executa o método do controller
        ResponseEntity<List<ParteDTO>> response = parteController.listarPartesPorProcesso(processoId);

        // Validações
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Verificar os valores de cada campo no DTO
        List<ParteDTO> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());

        ParteDTO expectedParteDTO = parteDTOs.get(0);
        ParteDTO actualParteDTO = responseBody.get(0);

        // Comparar todos os campos
        assertEquals(expectedParteDTO.getNomeCompleto(), actualParteDTO.getNomeCompleto());
        assertEquals(expectedParteDTO.getCpfCnpj(), actualParteDTO.getCpfCnpj());
        assertEquals(expectedParteDTO.getTipo(), actualParteDTO.getTipo());
        assertEquals(expectedParteDTO.getContatoEmail(), actualParteDTO.getContatoEmail());
        assertEquals(expectedParteDTO.getContatoTelefone(), actualParteDTO.getContatoTelefone());
    }


}
