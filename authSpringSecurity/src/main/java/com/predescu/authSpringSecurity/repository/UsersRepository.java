package com.predescu.authSpringSecurity.repository;

import com.predescu.authSpringSecurity.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long> {
    Optional <Users> findByUsername(String username);
    Optional <Users> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
