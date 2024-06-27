package com.ChallengeFinal.Alura.ForoHub.repository;

import com.ChallengeFinal.Alura.ForoHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
