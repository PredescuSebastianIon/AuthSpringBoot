package com.predescu.authSpringSecurity.controllers;

import com.predescu.authSpringSecurity.dto.RegisterRequest;
import com.predescu.authSpringSecurity.models.Users;
import com.predescu.authSpringSecurity.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public String registerUser(@ModelAttribute @Valid RegisterRequest request,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
//            for (var err:result.getFieldErrors()) {
//                model.addAttribute(err.getField(), err.getDefaultMessage());
//            }
            StringBuilder errors = new StringBuilder();
            for (FieldError error:result.getFieldErrors()) {
                errors.append(String.format("%s: %s\n", error.getField(), error.getDefaultMessage()));
            }
            model.addAttribute("regError", errors.toString());
            return "register";
        }

        try {
            authService.tryRegister(request);
            return "redirect:/login";
        } catch (IllegalArgumentException err) {
            model.addAttribute("regError", err.getMessage());
            return "register";
        }
    }
}
