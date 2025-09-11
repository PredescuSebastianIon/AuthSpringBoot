package com.predescu.authSpringSecurity.services;

import com.predescu.authSpringSecurity.dto.RegisterRequest;
import com.predescu.authSpringSecurity.models.Users;
import com.predescu.authSpringSecurity.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
public class AuthService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;

    public AuthService(UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user " + username));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }

    public Users tryRegister(RegisterRequest request) {
        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (usersRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(encoder.encode(request.getPassword()));

        return usersRepository.save(newUser);
    }
}
