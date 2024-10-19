package service;

import com.doug.attus.model.Acao;
import com.doug.attus.model.Processo;
import com.doug.attus.repository.AcaoRepository;
import com.doug.attus.repository.ProcessoRepository;
import com.doug.attus.service.imp.AcaoServiceImpl;
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

class AcaoServiceTest {

    @InjectMocks
    private AcaoServiceImpl acaoService;

    @Mock
    private AcaoRepository acaoRepository;

    @Mock
    private ProcessoRepository processoRepository; // Mock do ProcessoRepository adicionado

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarAcao() {
        Long processoId = 1L;
        Acao acao = new Acao();
        acao.setId(1L);

        Processo processo = new Processo();
        processo.setId(processoId);

        // Mock do ProcessoRepository para retornar um processo válido
        when(processoRepository.findById(processoId)).thenReturn(Optional.of(processo));
        when(acaoRepository.save(acao)).thenReturn(acao);

        Acao resultado = acaoService.adicionarAcao(processoId, acao);

        assertNotNull(resultado);
        assertEquals(acao.getId(), resultado.getId());
        verify(processoRepository, times(1)).findById(processoId); // Verifica se o processo foi buscado
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
