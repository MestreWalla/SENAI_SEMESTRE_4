package com.example.Api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Falha;

public class FalhaAPI {

    // Define o formato da data
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // Get all fails records
    public static List<Falha> getFalha() {
        String json = ApiConnection.getData("manutencao");
        List<Falha> manutencao = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Falha falha;
                falha = new Falha(
                        jsonObject.getInt("id"),
                        jsonObject.getInt("falhaId"),
                        parseDate(jsonObject.getString("data")),
                        jsonObject.getString("problemas"),
                        jsonObject.getString("prioridade"),
                        jsonObject.getString("operador")
                );
                manutencao.add(falha);
            }
        }
        return manutencao;
    }

    // Método para converter strings de data
    private static Date parseDate(String dateString) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, FORMATTER);
            return java.sql.Timestamp.valueOf(localDateTime); // Converte para java.util.Date, se necessário
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
