package com.ChallengeFinal.Alura.ForoHub.controller;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import com.ChallengeFinal.Alura.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@RequestBody @Valid TopicoRequest request) {
        Topico topico = topicoService.registrarTopico(request);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public Page<Topico> listarTopicos(
            @RequestParam String cursoNombre,
            @RequestParam int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return (Page<Topico>) topicoService.listarTopicos(cursoNombre, year, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerDetalleTopico(@PathVariable int id) {
        Optional<Topico> topico = topicoService.obtenerDetalleTopico(id);
        return topico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}