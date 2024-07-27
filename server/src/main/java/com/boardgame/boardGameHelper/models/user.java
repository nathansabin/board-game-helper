package com.boardgame.boardGameHelper.models;

public class user {

    private Integer id;
    private String username;
    private String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public user(){}

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
