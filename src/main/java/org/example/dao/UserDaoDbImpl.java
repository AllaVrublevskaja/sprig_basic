package org.example.dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoDbImpl implements UserDao1 {
    DataSource src;
    private final Connection cnn;
    private final Statement st;
    @Autowired
    public UserDaoDbImpl(DataSource src) throws SQLException {
        this.src = src;
        cnn = src.getConnection();
        st = cnn.createStatement();
    }

    @Override
    public User findById(int id)  {
        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = cnn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

            ResultSet set = statement.getResultSet();
            set.next();
            if (set.getRow()>0)
                return User.builder()
                        .id(set.getInt("id"))
                        .email(set.getString("email"))
                        .password(set.getString("password"))
                        .registrationDate(set.getDate("registrationDate"))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement;
        try {
            statement = cnn.prepareStatement(query);
            statement.setString(1, email);
            statement.execute();

            ResultSet set = statement.getResultSet();
            set.next();
            if (set.getRow()>0)
                return User.builder()
                        .id(set.getInt("id"))
                        .email(set.getString("email"))
                        .password(set.getString("password"))
                        .registrationDate(set.getDate("registrationDate"))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList();
        PreparedStatement statement;
        try {
            statement = cnn.prepareStatement(query);
            statement.execute();
            ResultSet set = statement.getResultSet();

            while(set.next()) {
                User user = User.builder()
                        .id(set.getInt("id"))
                        .email(set.getString("email"))
                        .password(set.getString("password"))
                        .registrationDate(set.getDate("registrationDate"))
                        .build();
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user) {
        String query = "INSERT INTO users(email, password, registrationDate) VALUES(?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = cnn.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
