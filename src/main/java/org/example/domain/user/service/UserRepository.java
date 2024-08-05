package org.example.domain.user.service;

import org.example.domain.user.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    List<User> findByEmail(String email);
    List<User> findByName(String name);
    void update(User user);
    void deleteById(Long id);
}
