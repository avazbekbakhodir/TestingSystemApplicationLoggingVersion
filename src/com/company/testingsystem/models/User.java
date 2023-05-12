package com.company.testingsystem.models;

import com.company.testingsystem.enums.Role;

import java.util.UUID;

public class User implements Comparable<User> {
    private final UUID id;
    private final String fullName;
    private final String username;
    private String password;
    private final Role role;

    public User(UUID id, String fullName, String username, String password, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(UUID id, String fullName, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = Role.STUDENT;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return id.compareTo(user.id);
    }
}
