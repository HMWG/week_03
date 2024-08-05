package org.example.domain.user.service;

import org.example.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    void update(User user);
    void deleteById(Long id);
}
