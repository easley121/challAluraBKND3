package com.ChallengeFinal.Alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDto {
    @Setter
    @Getter
    @NotBlank
    private String correoElectronico;

    @Setter
    @Getter
    @NotBlank
    private String contrasena;

    @Setter
    @Getter
    @NotBlank
    private String nombre;

    public @NotBlank CharSequence getPassword() {
        return contrasena;
    }

    public @NotBlank String getEmail() {
        return correoElectronico;
    }

    public @NotBlank String getFullName() {
        return nombre;
    }
}