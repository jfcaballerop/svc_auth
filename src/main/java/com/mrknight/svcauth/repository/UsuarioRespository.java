package com.mrknight.svcauth.repository;

import com.mrknight.svcauth.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UsuarioRespository
 */
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

}