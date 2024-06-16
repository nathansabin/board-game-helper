package org.boardgame.boardgamehelper.models;

public class metaData {
    private static metaData instance;
    private String token;

    private metaData(){}

    public static metaData getInstance(){
        if (instance == null){
            instance = new metaData();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "metaData{" +
                "token='" + token + '\'' +
                '}';
    }
}
