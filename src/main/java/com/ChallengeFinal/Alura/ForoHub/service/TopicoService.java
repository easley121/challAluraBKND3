package com.ChallengeFinal.Alura.ForoHub.service;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.model.Curso;
import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import com.ChallengeFinal.Alura.ForoHub.model.Usuario;
import com.ChallengeFinal.Alura.ForoHub.repository.CursoRepository;
import com.ChallengeFinal.Alura.ForoHub.repository.TopicoRepository;
import com.ChallengeFinal.Alura.ForoHub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Topico registrarTopico(TopicoRequest request) {
        if (topicoRepository.existsByTituloAndMensaje(request.getTitulo(), request.getMensaje())) {
            throw new IllegalArgumentException("El tópico ya existe");
        }

        Usuario autor = usuarioRepository.findById(request.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setStatus("Activo"); // o cualquier estado por defecto que desees
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }
}
