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
                        jsonFalha.getString("data"),
                        jsonFalha.getString("problema"),
                        jsonFalha.getString("prioridade"),
                        jsonFalha.getString("operador")
                );
                falhas.add(falha);
            }
        }
        return falhas;
    }

    public static int getMaxId() {
        String json = ApiConnection.getData("falhas");
        int maxId = 0;

        if (json != null && !json.trim().isEmpty()) {
            JSONArray falhaArray = new JSONArray(json);
            for (int i = 0; i < falhaArray.length(); i++) {
                JSONObject jsonFalha = falhaArray.getJSONObject(i);
                int id = jsonFalha.getInt("id");
                if (id > maxId) {
                    maxId = id;
                }
                System.out.println("Maior ID: " + maxId);
            }
        }
        return maxId;
    }

    public static void postFalha(Falha falha) {
        JSONObject falhaObject = new JSONObject();
        int novoId = getMaxId() + 1;

        falhaObject.put("id", String.valueOf(novoId)); // Converte o novo ID para String antes de enviar
        falhaObject.put("maquinaId", falha.getMaquinaId());
        falhaObject.put("data", falha.getData());
        falhaObject.put("problema", falha.getProblema());
        falhaObject.put("prioridade", falha.getPrioridade());
        falhaObject.put("operador", falha.getOperador());

        if (!falhaObject.isEmpty()) {
            ApiConnection.postData("falhas", falhaObject.toString());
        }
    }

    public static void putFalha(Falha falha) {
        JSONObject falhaObject = new JSONObject();
        falhaObject.put("id", falha.getId());
        falhaObject.put("maquinaId", falha.getMaquinaId());
        falhaObject.put("data", falha.getData());
        falhaObject.put("problema", falha.getProblema());
        falhaObject.put("prioridade", falha.getPrioridade());
        falhaObject.put("operador", falha.getOperador());

        ApiConnection.putData("falhas", falhaObject.toString(), String.valueOf(falha.getId()));
    }

    public static void deleteFalha(int id) {
        ApiConnection.deleteData("falhas", id);
    }
}
