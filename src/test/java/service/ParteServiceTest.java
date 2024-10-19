package service;

import com.doug.attus.model.Parte;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.ParteRepository;
import com.doug.attus.repository.ProcessoRepository;

import com.doug.attus.service.imp.ParteServiceImpl;
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

class ParteServiceTest {

    @InjectMocks
    private ParteServiceImpl parteService;  // Usando a implementação

    @Mock
    private ParteRepository parteRepository;

    @Mock
    private ProcessoRepository processoRepository;

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

        Processo processo = new Processo();
        processo.setId(processoId);

        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(parteRepository.save(parte)).thenReturn(parte);

        Parte resultado = parteService.adicionarParte(processoId, parte);

        assertNotNull(resultado);
        assertEquals(parte.getId(), resultado.getId());
        assertEquals(processo, resultado.getProcesso());
        verify(processoRepository, times(1)).findById(processoId);
        verify(parteRepository, times(1)).save(parte);
    }

    @Test
    void listarPartesPorProcesso() {
        Long processoId = 1L;
        List<Parte> partes = new ArrayList<>();
        Parte parte = new Parte();
        partes.add(parte);

        when(parteRepository.findAllByProcessoId(processoId)).thenReturn(partes);

        List<Parte> resultado = parteService.listarPartesPorProcesso(processoId);

        assertNotNull(resultado);
        assertEquals(partes.size(), resultado.size());
        verify(parteRepository, times(1)).findAllByProcessoId(processoId);
    }
}
