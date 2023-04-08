package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    Object findById(int id);
    Object findByEmail(String email);
    List<User> findAll();
    void add(User user);
}
