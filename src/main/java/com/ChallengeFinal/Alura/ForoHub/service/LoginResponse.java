package com.ChallengeFinal.Alura.ForoHub.service;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponse {
    private String token;

    private long expiresIn;

    public LoginResponse(String token) {
        this.token = token;
    }

}
