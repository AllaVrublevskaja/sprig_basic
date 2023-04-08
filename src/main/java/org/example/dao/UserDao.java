package org.example.dao;

import org.example.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void add(User user);
}
