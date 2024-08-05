package org.example.domain.user;

import java.time.LocalDateTime;

public class User {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private LocalDateTime createdAt;

    public User(Long userId, String name, String email, String password, String phoneNumber, boolean isAdmin, LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
    }

    public User(String name, String email, String password, String phoneNumber, boolean isAdmin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
