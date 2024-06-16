package org.boardgame.boardgamehelper.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiHandler {

    public static String login(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080" + "/api/login");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{" +
                    "\"username\":\"" + username + "\"," +
                    "\"password\":\"" + password + "\"" +
                    "}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();

            InputStream inputStream;
            if (responseCode == 200) {
                inputStream = con.getInputStream();
            } else {
                inputStream = con.getErrorStream();
                if (inputStream == null) {
                    throw new Exception("Failed to get error stream");
                }
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String register(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080" + "/api/create");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{" +
                    "\"username\":\"" + username + "\"," +
                    "\"password\":\"" + password + "\"" +
                    "}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();

            InputStream inputStream;
            if (responseCode == 200) {
                inputStream = con.getInputStream();
            } else {
                inputStream = con.getErrorStream();
                if (inputStream == null) {
                    throw new Exception("Failed to get error stream");
                }
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }
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
