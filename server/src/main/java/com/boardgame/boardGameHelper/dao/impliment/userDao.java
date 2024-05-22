package com.boardgame.boardGameHelper.dao.impliment;

import com.boardgame.boardGameHelper.dao.config;
import com.boardgame.boardGameHelper.dao.userInterface;
import com.boardgame.boardGameHelper.models.user;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;

@Service
public class userDao implements userInterface {
    @Override
    public user createUser(user newUser) throws SQLException {
        config connection = new config();
        Connection DB = connection.getConn();

        String query = "INSERT INTO user(username, password) VALUES(?, ?)";
        try (PreparedStatement stmt = DB.prepareStatement(query)) {
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String selectQuery = "SELECT * FROM user WHERE username=?";
        try (PreparedStatement stmt = DB.prepareStatement(selectQuery)){
            stmt.setString(1, newUser.getUsername());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("id");
                newUser.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newUser;
    }
    @Override
    public user login(user User) {
        return new user("kevin", "1234abcd");
    }
}
