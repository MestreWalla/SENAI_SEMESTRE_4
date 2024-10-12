package com.example.Api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Falha;

public class FalhaAPI {

    public static List<Falha> getFalhas() {
        String json = ApiConnection.getData("falhas");
        List<Falha> falhas = new ArrayList<>();

        if (json != null && !json.trim().isEmpty()) {
            JSONArray falhaArray = new JSONArray(json);
            for (int i = 0; i < falhaArray.length(); i++) {
                JSONObject jsonFalha = falhaArray.getJSONObject(i);
                Falha falha = new Falha(
                    jsonFalha.getInt("id"),
                    jsonFalha.getInt("maquinaId"),
                    java.sql.Date.valueOf(jsonFalha.getString("data")),
                    jsonFalha.getString("problema"),
                    jsonFalha.getString("prioridade"),
                    jsonFalha.getString("operador")
                );
                falhas.add(falha);
            }
        }
        return falhas;
    }

    public static void postFalha(Falha falha) {
        JSONObject falhaObject = new JSONObject();
        falhaObject.put("id", falha.getId());
        falhaObject.put("maquinaId", falha.getMaquinaId());
        falhaObject.put("data", falha.getData().toString());
        falhaObject.put("problema", falha.getProblema());
        falhaObject.put("prioridade", falha.getPrioridade());
        falhaObject.put("operador", falha.getOperador());

        if (!falhaObject.isEmpty()) {
            ApiConnection.postData("falhas", falhaObject.toString());
        }
    }
}
