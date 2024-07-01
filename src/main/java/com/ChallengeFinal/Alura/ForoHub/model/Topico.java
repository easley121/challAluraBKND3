package com.ChallengeFinal.Alura.ForoHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @NotBlank
    private String titulo;

    @Setter
    @Getter
    @NotBlank
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Setter
    @Getter
    @NotBlank
    private String status;

    @Setter
    @Getter
    @ManyToOne
    @NotNull
    private Usuario autor;

    @Setter
    @Getter
    @ManyToOne
    @NotNull
    private Curso curso;

}
