package com.example.Api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnection {

    private static final String API_URL = "http://localhost:3000/";

    // Métodos GET
    public static String getData(String endpoint) {
        try {
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // POST
    public static void postData(String endPoint, String inputData) {
        try {
            URL url = new URL(API_URL + endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); // enviar os dados para a API

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
                bw.write(inputData);
                bw.flush();
            }
            // Verificar o status da resposta
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_CREATED) { // HTTP 201 Created
                throw new Exception("Erro ao criar maquina: " + status);
            }

            System.out.println("Cadastro Realizado com Sucesso");
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void putData(String endPoint, String inputData, String id) {
    HttpURLConnection connection = null;
    try {
        URL url = new URL(API_URL + endPoint + "/" + id);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true); // Enviar os dados para a API
        
        // Definir timeout
        connection.setConnectTimeout(5000); // 5 segundos
        connection.setReadTimeout(5000); // 5 segundos

        // Enviar dados
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"))) {
            bw.write(inputData);
            bw.flush();
        }

        // Verificar o status da resposta
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            // Ler a resposta do erro
            String errorResponse = readResponse(connection);
            throw new Exception("Erro ao atualizar usuário: " + status + " - " + errorResponse);
        }

        System.out.println("Atualização Realizada com Sucesso");

    } catch (Exception e) {
        e.printStackTrace(); // Considere usar um logger
    } finally {
        if (connection != null) {
            connection.disconnect();
        }
    }
}

// Método auxiliar para ler a resposta do servidor
private static String readResponse(HttpURLConnection connection) throws IOException {
    StringBuilder response = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
    }
    return response.toString();
}


    // DELETE
    public static void deleteData(String endPoint, int id) {
        try {
            URL url = new URL(API_URL + endPoint + "/" + id);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_NO_CONTENT) { // HTTP 204 No Content
                throw new Exception("Erro ao deletar usuário: " + status);
            }

            System.out.println("Máquina excluída com sucesso!");
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
