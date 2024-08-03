package org.boardgame.boardgamehelper.utils;

import org.boardgame.boardgamehelper.models.metaData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsonHandler {
    public static Boolean writeJSON(String saveName, String description, List<String> tokens, List<String> maps) {
        JSONObject obj = new JSONObject();
        boolean online = false;
        if (metaData.getInstance().getToken() != null) {
            online = true;
        }

        obj.put("name", saveName);
        obj.put("description", description);
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

    public static List<JSONObject> getAll() {
        List<JSONObject> allSaveData = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        File dir = new File("src/main/resources/saves/");
        File[] allImages = dir.listFiles();

        for (int i=0; i< allImages.length; i++) {
            try (FileReader reader = new FileReader("src/main/resources/saves/" + allImages[i].getName())){
                JSONObject obj = (JSONObject) jsonParser.parse(reader);
                allSaveData.add(obj);
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return allSaveData;
    }
}
