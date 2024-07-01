package com.ChallengeFinal.Alura.ForoHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Curso {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Getter
    @Setter
    private String nombre;

    @NotBlank
    @Getter
    @Setter
    private String categoria;

    @OneToMany(mappedBy = "curso")
    @Getter
    @Setter
    private List<Topico> topicos;


}