package com.predescu.authNoSpringSecurity.controllers;

import com.predescu.authNoSpringSecurity.dto.LoginRequest;
import com.predescu.authNoSpringSecurity.dto.RegisterRequest;
import com.predescu.authNoSpringSecurity.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError error:result.getFieldErrors()) {
                errors.append(String.format("%s: %s\n", error.getField(), error.getDefaultMessage()));
            }
            model.addAttribute("registerError", errors.toString());
            return "register";
        }

        try {
            authService.register(request);
            return "redirect:/login";
        } catch (IllegalArgumentException err) {
            model.addAttribute("registerError", err.getMessage());
            return "register";
        }
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequest request,BindingResult result, Model model) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (var error:result.getFieldErrors()) {
                errors.append(String.format("%s: %s\n", error.getField(), error.getDefaultMessage()));
            }
            model.addAttribute("loginError", errors.toString());
            return "login";
        }

        try {
            authService.login(request);
            return "redirect:/";
        } catch (IllegalArgumentException err) {
            model.addAttribute("loginError", err.getMessage());
            return "login";
        }
    }

}
