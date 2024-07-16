package com.ChallengeFinal.Alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {

    @NotBlank
    private String correoElectronico;

    @NotBlank
    private String contrasena;

    public @NotBlank CharSequence getPassword() {

        return contrasena;
    }

    public @NotBlank String getCorreoElectronico() {
        return correoElectronico;
    }
}
