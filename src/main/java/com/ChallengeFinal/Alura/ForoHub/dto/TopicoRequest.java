package com.ChallengeFinal.Alura.ForoHub.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TopicoRequest {
    @Setter
    @Getter
    @NotBlank
    private String titulo;

    @Setter
    @Getter
    @NotBlank
    private String mensaje;

    @Setter
    @Getter
    @NotNull
    private int usuarioId;

    @Setter
    @Getter
    @NotNull
    private int cursoId;

}
