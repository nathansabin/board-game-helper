package com.boardgame.boardGameHelper.dao.impliment;

import com.boardgame.boardGameHelper.dao.config;
import com.boardgame.boardGameHelper.dao.userInterface;
import com.boardgame.boardGameHelper.models.user;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;

@Service
public class userDao implements userInterface {
    private final config connection = new config();
    private final Connection DB = this.connection.getConn();

    @Override
    public user createUser(user newUser) throws SQLException {

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

        return newUser;
    }
    @Override
    public Boolean login(user User) {
        String query = "SELECT * FROM user WHERE username=? OR password=?";
        try (PreparedStatement stmt = this.DB.prepareStatement(query)){
            stmt.setString(1, User.getUsername());
            stmt.setString(2, User.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("username"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
