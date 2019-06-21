package com.mrknight.svcauth.repository;

import com.mrknight.svcauth.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRespository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String rolename);

}