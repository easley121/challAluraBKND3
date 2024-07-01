package com.ChallengeFinal.Alura.ForoHub.model;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @NotBlank
    @Getter
    @Setter
    private String nombre;

    @NotBlank
    @Getter
    @Setter
    private String correoElectronico;

    @NotBlank
    @Getter
    @Setter
    private String contrasena;

    @OneToMany(mappedBy = "autor")
    @Getter
    @Setter
    private List<Topico> topicos;

}