package org.example.domain.user.service;

import org.example.domain.user.User;

import java.util.List;
import java.util.Optional;

public class LoginService {

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String email, String password) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        if(byEmail.isPresent()) {
            User user = byEmail.get();
            if(user.getPassword().equals(password))
                return Optional.of(user);
            else
                return Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
