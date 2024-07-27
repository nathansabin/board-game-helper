package org.boardgame.boardgamehelper.models;

import org.json.simple.JSONObject;

public class metaData {
    private static metaData instance;
    private String token;
    private JSONObject sessionSettings;

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

    public JSONObject getSessionSettings() {
        return this.sessionSettings;
    }
    public void setSessionSettings(JSONObject sessionSettings) {
        this.sessionSettings = sessionSettings;
    }

    @Override
    public String toString() {
        return "metaData{" +
                "token='" + token + '\'' +
                '}';
    }
}
