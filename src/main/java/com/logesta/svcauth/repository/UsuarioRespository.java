package com.logesta.svcauth.repository;

import com.logesta.svcauth.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UsuarioRespository
 */
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

}