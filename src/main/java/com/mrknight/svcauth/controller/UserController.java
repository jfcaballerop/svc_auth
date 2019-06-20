package com.mrknight.svcauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {

    @GetMapping("/users")
    public String listUsers() {

        return "listUsers";

    }

}