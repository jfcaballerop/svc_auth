package com.mrknight.svcauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {

    @GetMapping("/userSecured")
    public String listUsersSecured() {

        return "listUsersSecured";

    }
    @GetMapping("/users")
    public String listUsers() {

        return "listUsers";

    }
    @GetMapping("/userService")
    public String listUsersService() {

        return "listUsersService";

    }

}