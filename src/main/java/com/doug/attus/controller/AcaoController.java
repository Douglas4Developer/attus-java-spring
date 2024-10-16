package com.doug.attus.controller;

import com.doug.attus.model.Acao;
import com.doug.attus.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acoes")
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @PostMapping("/processos/{processoId}")
    public ResponseEntity<Acao> adicionarAcao(@PathVariable Long processoId, @RequestBody Acao acao) {
        Acao novaAcao = acaoService.adicionarAcao(processoId, acao);
        return ResponseEntity.ok(novaAcao);
    }

    @GetMapping("/processos/{processoId}")
    public ResponseEntity<List<Acao>> listarAcoesPorProcesso(@PathVariable Long processoId) {
        List<Acao> acoes = acaoService.listarAcoesPorProcesso(processoId);
        return ResponseEntity.ok(acoes);
    }
}
