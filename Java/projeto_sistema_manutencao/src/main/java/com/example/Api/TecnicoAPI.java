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

    public static void postTecnico(Tecnico tecnico) {
        JSONObject tecnicoObject = new JSONObject();
        tecnicoObject.put("id", String.valueOf(tecnico.getId()));
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
