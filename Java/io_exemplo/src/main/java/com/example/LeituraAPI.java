package com.example;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeituraAPI {
    public void exemplo() {
        try {
            URL url = new URL("https://api.github.com/users/MestreWalla");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status == 200) {
                throw new EOFException("Erro de conexao");
            }
            BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String linha;
            StringBuffer conteudo = new StringBuffer();
            while ((linha = br.readLine()) != null) {
                // System.out.println(linha);
                conteudo.append(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
