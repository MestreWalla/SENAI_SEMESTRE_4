package com.example.Api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Maquina;

public class MaquinaAPI {

    public static List<Maquina> getMaquinas() {
        String json = ApiConnection.getData("maquinas");
        List<Maquina> maquinas = new ArrayList<>();

        if (json != null && !json.trim().isEmpty()) {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Maquina maquina = new Maquina(
                        jsonObject.getInt("id"),
                        jsonObject.getString("codigo"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("modelo"),
                        jsonObject.getString("fabricante"),
                        LocalDate.parse(jsonObject.getString("dataAquisicao")),
                        jsonObject.getInt("tempoVidaEstimado"),
                        jsonObject.getString("localizacao"),
                        jsonObject.getString("detalhes"),
                        jsonObject.getString("manual")
                );
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }

    public static void postMaquinas(Maquina maquina) {
        // Criar um Objeto Json
        JSONObject maquinaObject = new JSONObject();
        maquinaObject.put("id", maquina.getId()); // Se id for int, não precisa mudar
        maquinaObject.put("codigo", maquina.getCodigo());
        maquinaObject.put("nome", maquina.getNome());
        maquinaObject.put("modelo", maquina.getModelo());
        maquinaObject.put("fabricante", maquina.getFabricante());
        maquinaObject.put("dataAquisicao", maquina.getDataAquisicao().toString());
        maquinaObject.put("tempoVidaEstimado", maquina.getTempoVidaEstimado());
        maquinaObject.put("localizacao", maquina.getLocalizacao());
        maquinaObject.put("detalhes", maquina.getDetalhes());
        maquinaObject.put("manual", maquina.getManual());

        // Gravando no API
        if (!maquinaObject.isEmpty()) {
            ApiConnection.postData("maquinas", maquinaObject.toString());
        }
    }

    public static void deleteMaquina(int id) {
        ApiConnection.deleteData("maquinas", String.valueOf(id)); // Certifique-se de passar ambos os parâmetros
    }
    
}

