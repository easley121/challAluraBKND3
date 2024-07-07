package com.ChallengeFinal.Alura.ForoHub.service;

import com.ChallengeFinal.Alura.ForoHub.dto.TopicoRequest;
import com.ChallengeFinal.Alura.ForoHub.exception.ResourceNotFoundException;
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

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(TopicoRequest request) {
        if (topicoRepository.existsByTituloAndMensaje(request.getTitulo(), request.getMensaje())) {
            throw new IllegalArgumentException("El tópico ya existe");
        }

        Usuario autor = usuarioRepository.findById(Long.valueOf(request.getUsuarioId()))
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(Long.valueOf(request.getCursoId()))
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setStatus("Activo");
        topico.setUsuario(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }
    // Devuelve la lista mediante un metodo GET de todos los topicos registrados

    public Page<Topico> listarTodosLosTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Page<Topico> listarTopicos(String cursoNombre, int year, int page, int size) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("fechaCreacion").ascending());
        return topicoRepository.findByCursoNombreAndFechaCreacionBetween(cursoNombre, startDate, endDate, pageable);
    }

    public Optional<Topico> obtenerDetalleTopico(int id) {
        return topicoRepository.findById(id);
    }

    public Topico actualizarTopico(int id, TopicoRequest request) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.setTitulo(request.getTitulo());
            topico.setMensaje(request.getMensaje());

            Usuario usuario = usuarioRepository.findById(Long.valueOf(request.getUsuarioId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id " + request.getUsuarioId()));
            topico.setUsuario(usuario);

            Curso curso = cursoRepository.findById(Long.valueOf(request.getCursoId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con el id " + request.getCursoId()));
            topico.setCurso(curso);

            return topicoRepository.save(topico);
        } else {
            throw new ResourceNotFoundException("Tópico no encontrado con el id " + id);
        }
    }

    @Transactional
    public void eliminarTopico(int id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Tópico no encontrado con el id " + id);
        }
    }
}

