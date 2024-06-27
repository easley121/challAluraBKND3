package com.ChallengeFinal.Alura.ForoHub.repository;


import com.ChallengeFinal.Alura.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}