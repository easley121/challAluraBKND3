package com.ChallengeFinal.Alura.ForoHub.controller;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import com.ChallengeFinal.Alura.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
