package com.boardgame.boardGameHelper.controllers;

import com.boardgame.boardGameHelper.dao.impliment.userDao;
import com.boardgame.boardGameHelper.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class userController {

    @Autowired
    private userDao userDB;

    @PostMapping("api/create")
    public user createNewUser(@RequestBody user newUser) throws SQLException {
        userDB.createUser(newUser);
        return newUser;
    }

    @PostMapping("api/login")
    public String login(@RequestBody user User) throws SQLException {
        return userDB.login(User);
    }
    
}
