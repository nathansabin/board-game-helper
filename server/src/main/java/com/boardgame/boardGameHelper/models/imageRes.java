package com.boardgame.boardGameHelper.models;

public class imageRes {
    private String token;
    private String image;

    imageRes (String token, String image) {
        this.token = token;
        this.image = image;
    }

    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "imageRes{" +
                "token='" + token + '\'' +
                ",image='" + image + '\'' +
                '}';
    }
}
