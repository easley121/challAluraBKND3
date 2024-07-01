package com.ChallengeFinal.Alura.ForoHub.service;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.model.Curso;
import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import com.ChallengeFinal.Alura.ForoHub.model.Usuario;
import com.ChallengeFinal.Alura.ForoHub.repository.CursoRepository;
import com.ChallengeFinal.Alura.ForoHub.repository.TopicoRepository;
import com.ChallengeFinal.Alura.ForoHub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TopicoService {



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

    @Autowired
    private TopicoRepository topicoRepository;

    public Page<Topico> listarTopicos(String cursoNombre, int year, int page, int size) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("fechaCreacion").ascending());
        return topicoRepository.findByCursoNombreAndFechaCreacionBetween(cursoNombre, startDate, endDate, pageable);
    }

    public Optional<Topico> obtenerDetalleTopico(int id) {
        return topicoRepository.findById(id);
    }

}
