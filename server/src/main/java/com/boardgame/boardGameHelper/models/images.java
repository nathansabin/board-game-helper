package com.boardgame.boardGameHelper.models;

import io.jsonwebtoken.lang.Maps;

import java.awt.*;
import java.util.Arrays;

public class images {
    private String username;
    private String[] Maps;
    private String[] Tokens;

    public images(String username, String[] maps, String[] tokens) {
        this.username = username;
        this.Maps = maps;
        this.Tokens = tokens;
    }
    public images() {}

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String[] getMaps() {
        return this.Maps;
    }
    public void setMaps(String[] maps) {
        this.Maps = maps;
    }

    public String[] getTokens() {
        return this.Tokens;
    }

    public void setTokens(String[] tokens) {
        this.Tokens = tokens;
    }

    @Override
    public String toString() {
        return "images{" +
                "username='" + username + '\'' +
                ", Maps=" + Arrays.toString(Maps) +
                ", Tokens=" + Arrays.toString(Tokens) +
                '}';
    }
}
