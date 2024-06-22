package com.example.ProvaAv2.service;

import com.example.ProvaAv2.model.UsuarioEntity;
import com.example.ProvaAv2.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public String generateToken(String nomeUsuario) {
        String token = JwtUtil.generateToken(nomeUsuario);
        return token;
    }

    public String extractUsername(String token) {
        String username = JwtUtil.extractUsername(token);
        return username;
    }
}
