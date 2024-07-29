package org.boardgame.boardgamehelper.utils;

import org.boardgame.boardgamehelper.models.metaData;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.List;

public class jsonHandler {
    public static Boolean writeJSON(String saveName, List<String> tokens, List<String> maps) {
        JSONObject obj = new JSONObject();
        boolean online = false;
        if (metaData.getInstance().getToken() != null) {
            online = true;
        }

        obj.put("name", saveName);
        obj.put("online", online);
        obj.put("tokens", tokens);
        obj.put("maps", maps);

        try {
            FileWriter file = new FileWriter("src/main/resources/saves/" + saveName + ".json");
            metaData.getInstance().setSessionSettings(obj);
            file.write(obj.toJSONString());

            file.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
