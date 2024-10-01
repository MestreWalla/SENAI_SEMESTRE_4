package com.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class LeituraAPI {
    public void exemplo() {
        try {
            URL url = new URL("https://api.github.com/users/MestreWalla");
            HttpURLConnection con = new HttpURLConnection(url);
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
