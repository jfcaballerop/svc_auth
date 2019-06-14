package com.logesta.svcauth.controller;

import java.util.List;

import com.logesta.svcauth.model.Usuario;
import com.logesta.svcauth.repository.UsuarioRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRespository repo;

    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // public UsuarioController(UsuarioRepository usuarioRepository,
    // BCryptPasswordEncoder bCryptPasswordEncoder) {
    // this.usuarioRepository = usuarioRepository;
    // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    // }

    // @PostMapping("/users/")
    // public void saveUsuario(@RequestBody Usuario user) {
    // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    // usuarioRepository.save(user);
    // }

    @GetMapping("/users")
    public List<Usuario> getAllUsuarios() {
        return repo.findAll();
    }

    // @GetMapping("/users/{username}")
    // public Usuario getUsuario(@PathVariable String username) {
    // return usuarioRepository.findByUsername(username);
    // }
}