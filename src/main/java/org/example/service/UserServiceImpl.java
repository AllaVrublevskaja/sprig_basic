package org.example.service;

import org.example.dao.UserDao1;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserServiceImpl implements UserService {

    private final UserDao1 dao;

    @Autowired
    public UserServiceImpl(UserDao1 dao) {
        this.dao = dao;
    }

    @Override
    public Object findById(int id){
        if(dao.findById(id) == null) {
            return String.format("User with id = %d doesn't exist", id);
        }
       return dao.findById(id);
    }

    @Override
    public Object findByEmail(String email) {
        if(dao.findByEmail(email) == null) {
            return String.format("User with email = %s doesn't exist", email);
        }
        return dao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(User user) {
        List <User> users = dao.findAll();
        boolean stop = false;
        for ( User u : users)
            if (u.getEmail().equals(user.getEmail())) {
                stop = true;
                break;
            }
        if (stop)
            System.out.printf("User with email %s already exists \n", user.getEmail());
        else
            dao.add(user);
    }
}
