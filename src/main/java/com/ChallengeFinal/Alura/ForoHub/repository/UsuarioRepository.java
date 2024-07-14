package com.ChallengeFinal.Alura.ForoHub.repository;


import com.ChallengeFinal.Alura.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);

    static Optional<Usuario> findByCorreo(String correoElectronico) {
        return Optional.empty();
    }

}