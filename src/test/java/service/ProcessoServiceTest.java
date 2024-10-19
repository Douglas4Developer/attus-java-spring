package service;

import com.doug.attus.model.Processo;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.imp.ProcessoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessoServiceTest {

    @InjectMocks
    private ProcessoServiceImpl processoService;  // Usando a implementação

    @Mock
    private ProcessoRepository processoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProcesso() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setNumeroProcesso("12345");

        when(processoRepository.save(processo)).thenReturn(processo);

        Processo resultado = processoService.criarProcesso(processo);

        assertNotNull(resultado);
        assertEquals(processo.getId(), resultado.getId());
        verify(processoRepository, times(1)).save(processo);
    }

    @Test
    void buscarPorNumero() {
        String numeroProcesso = "12345";
        Processo processo = new Processo();
        processo.setNumeroProcesso(numeroProcesso);

        when(processoRepository.findByNumeroProcesso(numeroProcesso)).thenReturn(Optional.of(processo));

        Processo resultado = processoService.buscarPorNumero(numeroProcesso);

        assertNotNull(resultado);
        assertEquals(numeroProcesso, resultado.getNumeroProcesso());
        verify(processoRepository, times(1)).findByNumeroProcesso(numeroProcesso);
    }

    @Test
    void arquivarProcesso() {
        Long processoId = 1L;
        Processo processo = new Processo();
        processo.setId(processoId);
        processo.setStatus("Ativo");

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));

        processoService.arquivarProcesso(processoId);

        assertEquals("Arquivado", processo.getStatus());
        verify(processoRepository, times(1)).findById(processoId);
        verify(processoRepository, times(1)).save(processo);
    }

    @Test
    void listarTodosProcessos() {
        List<Processo> processos = new ArrayList<>();
        Processo processo = new Processo();
        processos.add(processo);

        when(processoRepository.findAll()).thenReturn(processos);

        List<Processo> resultado = processoService.listarTodosProcessos();

        assertNotNull(resultado);
        assertEquals(processos.size(), resultado.size());
        verify(processoRepository, times(1)).findAll();
    }
}
