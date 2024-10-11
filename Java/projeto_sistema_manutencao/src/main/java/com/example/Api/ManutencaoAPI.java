package com.example.Api;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Manutencao;

public class ManutencaoAPI {

    public static List<Manutencao> getManutencao() {
        String json = ApiConnection.getData("manutencaos");
        List<Manutencao> manutencaos = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Manutencao manutencao = new Manutencao(
                        jsonObject.getString("id"),
                        jsonObject.getString("maquinaId"),
                        jsonObject.getString("data"),
                        jsonObject.getString("tipo"),
                        jsonObject.getString("pecasTrocadas"),
                        jsonObject.getInt("tempoDeParada"),
                        jsonObject.getInt("tecnicoId"),
                        jsonObject.getString("observacoes")
                );
                manutencaos.add(manutencao);
            }
        }
        return manutencaos;
    }

    public static void postManutencaos(Manutencao manutencao) {
        //Criar um Objeto Json
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("id", manutencao.getId());
        manutencaoObject.put("maquinaId", manutencao.getMaquinaId());
        manutencaoObject.put("data", manutencao.getData());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnicoId());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        //gravando no 
        if (!manutencaoObject.isEmpty()) {
            ApiConnection.postData("manutencaos", manutencaoObject.toString());
        }
    }
}
