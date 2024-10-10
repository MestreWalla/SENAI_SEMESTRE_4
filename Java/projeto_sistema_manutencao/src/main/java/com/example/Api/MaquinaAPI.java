package com.example.Api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Maquina;

public class MaquinaAPI {

    // Get all machines
    public static List<Maquina> getMaquinas() {
        String json = ApiConnection.getData("maquinas");
        List<Maquina> maquinas = new ArrayList<>();

        if (json != null) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Maquina maquina = new Maquina(
                        jsonObject.getString("id"),
                        jsonObject.getString("codigo"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("modelo"),
                        jsonObject.getString("fabricante"),
                        LocalDate.parse(jsonObject.getString("dataAquisicao")),
                        jsonObject.getLong("tempoVidaEstimado"),
                        jsonObject.getString("localizacao"),
                        jsonObject.getString("detalhes"),
                        jsonObject.getString("manual")
                );
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }

    // Create a new machine
    public static boolean createMaquina(Maquina maquina) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", maquina.getId());
        jsonObject.put("codigo", maquina.getCodigo());
        jsonObject.put("nome", maquina.getNome());
        jsonObject.put("modelo", maquina.getModelo());
        jsonObject.put("fabricante", maquina.getFabricante());
        jsonObject.put("dataAquisicao", maquina.getDataAquisicao());
        jsonObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        jsonObject.put("localizacao", maquina.getLocalizacao());
        jsonObject.put("detalhes", maquina.getDetalhes());
        jsonObject.put("manual", maquina.getManual());

        String response = ApiConnection.postData("maquinas", jsonObject.toString());
        return response != null && response.equals("success");
    }

    // Update a machine
    public static boolean updateMaquina(Maquina maquina) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codigo", maquina.getCodigo());
        jsonObject.put("nome", maquina.getNome());
        jsonObject.put("modelo", maquina.getModelo());
        jsonObject.put("fabricante", maquina.getFabricante());
        jsonObject.put("dataAquisicao", maquina.getDataAquisicao());
        jsonObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        jsonObject.put("localizacao", maquina.getLocalizacao());
        jsonObject.put("detalhes", maquina.getDetalhes());
        jsonObject.put("manual", maquina.getManual());

        String response = ApiConnection.putData("maquinas/" + maquina.getId(), jsonObject.toString());
        return response != null && response.equals("success");
    }

    // Delete a machine
    public static boolean deleteMaquina(String id) {
        String response = ApiConnection.deleteData("maquinas/" + id);
        return response != null && response.equals("success");
    }

}
