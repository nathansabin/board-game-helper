package com.boardgame.boardGameHelper.dao;

import com.boardgame.boardGameHelper.models.user;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface userInterface {
    public user createUser(user newUser) throws SQLException;
    public String login(user User);
}
