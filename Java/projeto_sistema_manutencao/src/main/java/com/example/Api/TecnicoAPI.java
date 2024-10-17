package com.example.Api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Tecnico;

public class TecnicoAPI {

    public static List<Tecnico> getTecnicos() {
        String json = ApiConnection.getData("tecnicos");
        List<Tecnico> tecnicos = new ArrayList<>();

        if (json != null && !json.trim().isEmpty()) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Tecnico tecnico = new Tecnico(
                        jsonObject.getInt("id"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("especialidade"),
                        jsonObject.getString("disponibilidade")
                );
                tecnicos.add(tecnico);
            }
        }
        return tecnicos;
    }

    public static int getMaxId() {
        String json = ApiConnection.getData("tecnicos");
        int maxId = 0;

        if (json != null && !json.trim().isEmpty()) {
            JSONArray tecnicoArray = new JSONArray(json);
            for (int i = 0; i < tecnicoArray.length(); i++) {
                JSONObject jsonTecnico = tecnicoArray.getJSONObject(i);
                int id = jsonTecnico.getInt("id");
                if (id > maxId) {
                    maxId = id;
                }
                System.out.println("Maior ID: " + maxId);
            }
        }
        return maxId;
    }

    public static void postTecnico(Tecnico tecnico) {
        JSONObject tecnicoObject = new JSONObject();
        int novoId = getMaxId() + 1;

        tecnicoObject.put("id", String.valueOf(novoId));
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        ApiConnection.postData("tecnicos", tecnicoObject.toString());
    }

    public static void putTecnico(Tecnico tecnico) {
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("id", tecnico.getId());
        tecnicoObject.put("nome", tecnico.getNome());
        tecnicoObject.put("especialidade", tecnico.getEspecialidade());
        tecnicoObject.put("disponibilidade", tecnico.getDisponibilidade());

        ApiConnection.putData("tecnicos", tecnicoObject.toString(), String.valueOf(tecnico.getId()));
    }

    public static void deleteTecnico(int id) {
        ApiConnection.deleteData("tecnicos", id);
    }
}
