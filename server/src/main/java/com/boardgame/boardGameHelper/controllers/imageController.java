package com.boardgame.boardGameHelper.controllers;

import com.boardgame.boardGameHelper.dao.impliment.imageDao;
import com.boardgame.boardGameHelper.dao.impliment.userDao;
import com.boardgame.boardGameHelper.models.imageRes;
import com.boardgame.boardGameHelper.models.images;
import com.boardgame.boardGameHelper.utils.auth.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;

@CrossOrigin(origins = "*")
@RestController
public class imageController {
    @Autowired
    private userDao userDB;
    @Autowired
    private imageDao imageDB;
    @Autowired
    private static jwtUtils jwt = new jwtUtils();

    @GetMapping("/api/image/")
    public static images getAllImages(String userToken) {
        return new images();
    }

    @GetMapping("/api/image/token")
    public static String getAllTokens(String userToken) {
        return "this will return an array of tokens";
    }

    @GetMapping("/api/image/token/{location}")
    public static String getOneToken(String userToken) {
        return "this will return one token";
    }

    @PostMapping("/api/images/token")
    public static String addNewToken(@RequestBody images requestBody) {

        return "this will add a new token";
    }

    @DeleteMapping("/api/image/token/remove")
    public static String removeOneToken(String userToken) {
        return "this will remove one desired token";
    }

    @GetMapping("/api/image/map")
    public static String getAllMaps(String userToken) {
        return "this will return an array of maps";
    }

    @GetMapping("/api/image/map/{location}")
    public static String getOneMap(String userToken) {
        return "this will return one map";
    }


    @PostMapping("/api/images/map")
    public ResponseEntity<Boolean> addNewMap(@RequestBody imageRes res) {
        String map = res.getImage();
        String user = res.getToken();

        if (map == null || user == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        try {
            boolean added = imageDB.addMap(user, map);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/image/map/remove")
    public static String removeOneMap(String userToken) {
        return "this will remove one desired map";
    }
}
