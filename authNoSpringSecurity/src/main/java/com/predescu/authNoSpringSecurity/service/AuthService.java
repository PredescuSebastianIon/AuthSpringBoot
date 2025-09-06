package com.predescu.authNoSpringSecurity.service;

import com.predescu.authNoSpringSecurity.dto.LoginRequest;
import com.predescu.authNoSpringSecurity.dto.RegisterRequest;
import com.predescu.authNoSpringSecurity.models.User;
import com.predescu.authNoSpringSecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository repository;
    public AuthService(UserRepository repository) {
        this.repository = repository;
    }
    public User register(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("username already in use");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return repository.save(user);
    }
    public Boolean login(LoginRequest request) {
        Optional<User> user = repository.findByUsername(request.getUsername());
        if (!user.isPresent()) {
            throw new IllegalArgumentException("This username does not exist");
        }
        if (!request.getPassword().equals(user.get().getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        return true;
    }
}
