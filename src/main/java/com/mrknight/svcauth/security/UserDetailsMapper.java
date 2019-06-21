package com.mrknight.svcauth.security;

import java.util.HashSet;
import java.util.Set;

import com.mrknight.svcauth.model.Role;
import com.mrknight.svcauth.model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * UserDetailsMapper
 */
public class UserDetailsMapper {
    public static UserDetails build(Usuario user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(Usuario retrievedUser) {
        Set<Role> roles = retrievedUser.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority( role.getName())));
        return authorities;
    }
}