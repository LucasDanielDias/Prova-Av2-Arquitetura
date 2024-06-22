package com.example.ProvaAv2.controller;

import com.example.ProvaAv2.model.UsuarioEntity;
import com.example.ProvaAv2.service.UsuarioService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioEntity usuario) {
        String token = usuarioService.generateToken(usuario.getNome());
        return "Token: " + token;
    }


    @GetMapping("/nomeusuario/{token}")
    public String extractUsername(@PathVariable String token) {
        String username = usuarioService.extractUsername(token);
        return username;
    }

    @Secured("GERENTE")
    @GetMapping(value = "/gerente/{token}")
    public String buscaGerente(@PathVariable String token) {
        System.out.println("Chegou aqui controller");
        String nomeUsuario = usuarioService.extractUsername(token);
        return "Gerente: " + nomeUsuario;
    }

    @Secured("ADMIN")
    @GetMapping(value = "/admin/{token}")
    public String buscaAdmin(@PathVariable String token) {
        System.out.println("Chegou aqui controller");
        String nomeUsuario = usuarioService.extractUsername(token);
        return "Admin: " + nomeUsuario;
    }
}
