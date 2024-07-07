package com.ChallengeFinal.Alura.ForoHub.controller;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import com.ChallengeFinal.Alura.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/filter")
    public Page<Topico> listarTopicos(
            @RequestParam String cursoNombre,
            @RequestParam int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return topicoService.listarTopicos(cursoNombre, year, page, size);
    }

    @GetMapping
    public Page<Topico> listarTodosLosTopicos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").ascending());
        return topicoService.listarTodosLosTopicos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerDetalleTopico(@PathVariable int id) {
        Optional<Topico> topico = topicoService.obtenerDetalleTopico(id);
        return topico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(
            @PathVariable int id,
            @RequestBody @Valid TopicoRequest request) {
        Topico topicoActualizado = topicoService.actualizarTopico(id, request);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTopico(@PathVariable int id) {
        topicoService.eliminarTopico(id);
    }

}