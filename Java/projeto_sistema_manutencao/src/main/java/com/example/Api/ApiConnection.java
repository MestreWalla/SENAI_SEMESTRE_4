package com.example.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {

    private static final String API_URL = "http://localhost:3000/";

    public static String getData(String endpoint) {
        return makeRequest(endpoint, "GET", null);
    }

    public static String postData(String endpoint, String jsonInputString) {
        return makeRequest(endpoint, "POST", jsonInputString);
    }

    public static String putData(String endpoint, String jsonInputString) {
        return makeRequest(endpoint, "PUT", jsonInputString);
    }

    public static String deleteData(String endpoint) {
        return makeRequest(endpoint, "DELETE", null);
    }

    private static String makeRequest(String endpoint, String method, String jsonInputString) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");

            if (jsonInputString != null) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            return content.toString();

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
