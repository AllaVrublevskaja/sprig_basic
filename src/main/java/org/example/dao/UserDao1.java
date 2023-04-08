package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao1 {
    User findById(int id);
    User findByEmail(String email);
    List<User> findAll();
    void add(User user);
}
