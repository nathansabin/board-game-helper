package com.boardgame.boardGameHelper.controllers;

import com.boardgame.boardGameHelper.models.images;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;

@CrossOrigin(origins = "*")
@RestController
public class imageController {

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
    public static String addNewMap(String userToken) {
        return "this will add a new map";
    }

    @DeleteMapping("/api/image/map/remove")
    public static String removeOneMap(String userToken) {
        return "this will remove one desired map";
    }
}
