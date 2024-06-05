package com.boardgame.boardGameHelper.models;

import java.awt.*;
import java.util.Arrays;

public class images {
    private String username;
    private Image[] Maps;
    private Image[] Tokens;

    public images(String username) {
        this.username = username;
    }
    public images() {}

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Image[] getMaps() {
        return this.Maps;
    }
    public void setMaps(Image[] maps) {
        this.Maps = maps;
    }

    public Image[] getTokens() {
        return this.Tokens;
    }

    public void setTokens(Image[] tokens) {
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
