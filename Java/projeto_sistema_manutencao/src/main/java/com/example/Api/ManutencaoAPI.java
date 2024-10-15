package com.example.Api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Models.Manutencao;

public class ManutencaoAPI {

    public static List<Manutencao> getManutencoes() {
        String json = ApiConnection.getData("manutencoes");
        List<Manutencao> manutencoes = new ArrayList<>();

        if (json != null && !json.trim().isEmpty()) {
            try {
                // Usa JSONArray diretamente, pois a resposta é um array
                JSONArray manutencaoArray = new JSONArray(json);

                for (int i = 0; i < manutencaoArray.length(); i++) {
                    JSONObject jsonManutencao = manutencaoArray.getJSONObject(i);
                    Manutencao manutencao = new Manutencao(
                            jsonManutencao.getString("id"),
                            String.valueOf(jsonManutencao.getInt("maquinaId")), // Converte para String
                            jsonManutencao.getString("data"),
                            jsonManutencao.getString("tipo"),
                            jsonManutencao.getString("pecasTrocadas"),
                            jsonManutencao.getInt("tempoDeParada"),
                            jsonManutencao.getString("tecnico"),
                            jsonManutencao.getString("observacoes")
                    );
                    manutencoes.add(manutencao);
                }
            } catch (JSONException e) {
                System.err.println("Erro ao processar o JSON: " + e.getMessage());
            }
        } else {
            System.out.println("Formato inválido ou vazio: " + json);
        }
        return manutencoes;
    }

    public static void postManutencao(Manutencao manutencao) {
        JSONObject manutencaoObject = new JSONObject();
        manutencaoObject.put("id", manutencao.getId());
        manutencaoObject.put("maquinaId", manutencao.getMaquinaId());
        manutencaoObject.put("data", manutencao.getData());
        manutencaoObject.put("tipo", manutencao.getTipo());
        manutencaoObject.put("pecasTrocadas", manutencao.getPecasTrocadas());
        manutencaoObject.put("tempoDeParada", manutencao.getTempoDeParada());
        manutencaoObject.put("tecnicoId", manutencao.getTecnico());
        manutencaoObject.put("observacoes", manutencao.getObservacoes());

        if (!manutencaoObject.isEmpty()) {
            ApiConnection.postData("manutencoes", manutencaoObject.toString());
        }
    }
}
