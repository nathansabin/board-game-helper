package com.boardgame.boardGameHelper.dao.impliment;

import com.boardgame.boardGameHelper.dao.config;
import com.boardgame.boardGameHelper.dao.userInterface;
import com.boardgame.boardGameHelper.models.user;
import com.boardgame.boardGameHelper.utils.auth.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class userDao implements userInterface {
    @Autowired
    private jwtUtils jwt = new jwtUtils();
    private final config connection = new config();
    private final Connection DB = this.connection.getConn();

    @Override
    public String createUser(user newUser) throws SQLException {
        Map<String, Object> claims = new HashMap<>();

        String query = "INSERT INTO user(username, password) VALUES(?, ?)";
        try (PreparedStatement stmt = this.DB.prepareStatement(query)) {
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String selectQuery = "SELECT * FROM user WHERE username=?";
        try (PreparedStatement stmt = this.DB.prepareStatement(selectQuery)){
            stmt.setString(1, newUser.getUsername());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("id");
                newUser.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        claims.put("username", newUser.getUsername());
        claims.put("password", newUser.getPassword());
        claims.put("id", newUser.getId());

        return jwt.generateToken(claims);
    }
    @Override
    public String login(user User) {

        Map<String, Object> claims = new HashMap<>();

        String query = "SELECT * FROM user WHERE username=? AND password=?";
        try (PreparedStatement stmt = this.DB.prepareStatement(query)){
            stmt.setString(1, User.getUsername());
            stmt.setString(2, User.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("username") == null){
                    throw new RuntimeException();
                }
                claims.put("username", rs.getString("username"));
                claims.put("password", rs.getString("password"));
                claims.put("id", rs.getInt("id"));

                return jwt.generateToken(claims);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
