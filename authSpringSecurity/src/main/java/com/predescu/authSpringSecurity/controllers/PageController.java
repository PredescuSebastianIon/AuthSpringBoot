package com.predescu.authSpringSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(path = "/login")
    public String getAuth() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String getRegister() {
        return "register";
    }

    @GetMapping(path = "/")
    public String getIndex() {
        return "index";
    }
}
