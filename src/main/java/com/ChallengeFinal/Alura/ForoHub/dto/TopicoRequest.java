package com.ChallengeFinal.Alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long autorId;

    @Setter
    @Getter
    @NotNull
    private Long cursoId;

}
