package com.doug.attus.controller;

import com.doug.attus.model.Parte;
import com.doug.attus.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partes")
public class ParteController {

    @Autowired
    private ParteService parteService;

    @PostMapping("/processos/{processoId}")
    public ResponseEntity<Parte> adicionarParte(@PathVariable Long processoId, @RequestBody Parte parte) {
        Parte novaParte = parteService.adicionarParte(processoId, parte);
        return ResponseEntity.ok(novaParte);
    }

    @GetMapping("/processos/{processoId}")
    public ResponseEntity<List<Parte>> listarPartesPorProcesso(@PathVariable Long processoId) {
        List<Parte> partes = parteService.listarPartesPorProcesso(processoId);
        return ResponseEntity.ok(partes);
    }
}
