package com.boardgame.boardGameHelper.controllers;

import com.boardgame.boardGameHelper.dao.impliment.userDao;
import com.boardgame.boardGameHelper.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class userController {

    @Autowired
    private userDao user;

    @PostMapping("api/create")
    public user hello(@RequestBody user newUser) throws SQLException {
        user.createUser(newUser);
        return newUser;
    }

}
