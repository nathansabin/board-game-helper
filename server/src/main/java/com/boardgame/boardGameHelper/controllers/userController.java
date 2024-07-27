package com.boardgame.boardGameHelper.controllers;

import com.boardgame.boardGameHelper.dao.impliment.userDao;
import com.boardgame.boardGameHelper.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class userController {

    @Autowired
    private userDao userDB;

    @PostMapping("api/create")
    public ResponseEntity<String> createNewUser(@RequestBody user newUser) throws SQLException {
        try {
            String token= userDB.createUser(newUser);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody user User) throws SQLException {
        try {
            String token = userDB.login(User);
            if (token == null) {
                throw new RuntimeException();
            }
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
