package com.mj.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/{name}")
    public String getUserName(@PathVariable String name) {
        return "hello,"+ name;
    }
}