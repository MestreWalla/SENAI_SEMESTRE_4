package com.example.Api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Manutencao;

public class ManutencaoAPI {

    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("manutencoes");
        List<Manutencao> manutencoes = new ArrayList<>();
    
        if (json != null && !json.trim().isEmpty()) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
    
                String maquinaId;
                if (jsonObject.has("maquinaId")) {
                    maquinaId = String.valueOf(jsonObject.getInt("maquinaId"));
                } else {
                    maquinaId = "";
                }
    
                Manutencao manutencao = new Manutencao(
                        jsonObject.getInt("id"),
                        maquinaId,
                        jsonObject.getString("data"),
                        jsonObject.getString("tipo"),
                        jsonObject.getString("pecasTrocadas"),
                        jsonObject.getInt("tempoDeParada"),
                        jsonObject.getString("tecnico"),
                        jsonObject.getString("observacoes")
                );
                manutencoes.add(manutencao);
            }
        }
        return manutencoes;
    }
    
    public static int getMaxId() {
        String json = ApiConnection.getData("manutencoes");
        int maxId = 0;

        if (json != null && !json.trim().isEmpty()) {
            JSONArray manutencaoArray = new JSONArray(json);
            for (int i = 0; i < manutencaoArray.length(); i++) {
                JSONObject jsonManutencao = manutencaoArray.getJSONObject(i);
                int id = jsonManutencao.getInt("id");
                if (id > maxId) {
                    maxId = id;
                }
                System.out.println("Maior ID: " + maxId);
            }
        }
        return maxId;
    }

    public static void postManutencoes(Manutencao manutencao) {
        JSONObject manutencaoObject = new JSONObject();
        int novoId = getMaxId() + 1;

        manutencaoObject.put("id", String.valueOf(novoId));
        manutencaoObject.put("maquinaId", manutencao.getMaquinaId());
        manutencaoObject.put("data", manutencao.getData());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnico", manutencao.getTecnico());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        ApiConnection.postData("manutencoes", manutencaoObject.toString());
    }

    public static void putManutencao(Manutencao manutencao) {
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("id", String.valueOf(manutencao.getId()));
        manutencaoObject.put("maquinaId", manutencao.getMaquinaId());
        manutencaoObject.put("data", manutencao.getData());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnico", manutencao.getTecnico());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        ApiConnection.putData("manutencoes", manutencaoObject.toString(), String.valueOf(manutencao.getId()));
    }

    public static void deleteManutencao(int id) {
        ApiConnection.deleteData("manutencoes", id);
    }
}
