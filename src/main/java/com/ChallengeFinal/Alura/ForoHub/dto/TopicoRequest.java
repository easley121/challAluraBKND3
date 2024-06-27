package com.ChallengeFinal.Alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopicoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Long autorId;

    @NotNull
    private Long cursoId;

}
