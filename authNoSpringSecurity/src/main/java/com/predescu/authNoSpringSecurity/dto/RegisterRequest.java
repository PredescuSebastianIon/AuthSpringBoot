package com.predescu.authNoSpringSecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    @NotBlank @Size(min = 3, max = 50)
    private String username;
    @NotBlank @Email(message = "Please type a valid email") @Size(max=100)
    private String email;
    @NotBlank @Size(min=6, max=100)
    private String password;
}
