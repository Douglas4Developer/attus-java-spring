package com.doug.attus.controller;

import com.doug.attus.model.Processo;
import com.doug.attus.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @PostMapping
    public ResponseEntity<Processo> criarProcesso(@RequestBody Processo processo) {
        return ResponseEntity.ok(processoService.criarProcesso(processo));
    }

    @GetMapping("/{numeroProcesso}")
    public ResponseEntity<Processo> buscarPorNumero(@PathVariable String numeroProcesso) {
        return ResponseEntity.ok(processoService.buscarPorNumero(numeroProcesso));
    }

    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<Void> arquivarProcesso(@PathVariable Long id) {
        processoService.arquivarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Processo>> listarTodosProcessos() {
        return ResponseEntity.ok(processoService.listarTodosProcessos());
    }
}
