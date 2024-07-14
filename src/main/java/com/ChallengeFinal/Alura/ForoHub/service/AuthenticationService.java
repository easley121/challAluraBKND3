package com.ChallengeFinal.Alura.ForoHub.service;

import com.ChallengeFinal.Alura.ForoHub.dto.LoginUserDto;
import com.ChallengeFinal.Alura.ForoHub.dto.RegisterUserDto;
import com.ChallengeFinal.Alura.ForoHub.model.Usuario;
import com.ChallengeFinal.Alura.ForoHub.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsuarioRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario signup(RegisterUserDto input) {
        Usuario user = new Usuario();
        user.setNombre(input.getFullName());
        user.setCorreoElectronico(input.getEmail());
        user.setContrasena(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }


    public Usuario authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCorreoElectronico(),
                        input.getPassword()
                )
        );

        return UsuarioRepository.findByCorreo(input.getCorreoElectronico())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

}
