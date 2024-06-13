package org.boardgame.boardgamehelper.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiHandler {

    public static boolean sendUserImage(String image, String token) throws IOException {
        try {
            URL url = new URL("http://localhost:8080" + "/api/images/map");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{" +
                    "\"token\":\"" + token + "\"," +
                    "\"image\":\"" + image + "\"" +
                    "}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            var response = con.getResponseMessage();
            int code = con.getResponseCode();
            System.out.println("Response Code: " + code);

            return true;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
