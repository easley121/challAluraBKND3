package com.ChallengeFinal.Alura.ForoHub.repository;

import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoNombreAndFechaCreacionBetween
            (String cursoNombre, LocalDate startDate, LocalDate endDate, Pageable pageable);

}

