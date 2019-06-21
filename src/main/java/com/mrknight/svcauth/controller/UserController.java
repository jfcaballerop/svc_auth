package com.mrknight.svcauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {
    
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/userSecured")
    public String listUsersSecured() {

        return "listUsersSecured User or Admin";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userSecuredAdmin")
    public String listUsersSecuredAdmin() {

        return "listUsersSecured Admin";

    }

    @GetMapping("/users")
    public String listUsers() {

        return "listUsers";

    }

    @PreAuthorize("hasRole('ROLE_SVC')")
    @GetMapping("/userService")
    public String listUsersService() {

        return "listUsersService";

    }

}