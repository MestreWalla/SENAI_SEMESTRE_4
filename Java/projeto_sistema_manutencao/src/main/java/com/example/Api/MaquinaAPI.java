package com.example.Api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Maquina;

public class MaquinaAPI {

    // Define o formato da data, sem o tempo
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Get all maintenance records
    public static List<Maquina> getMaquina() {
        String json = ApiConnection.getData("maquina");
        List<Maquina> maquinas = new ArrayList<>();

        if (json != null) {
            // Acessa o objeto raiz do JSON
            JSONObject jsonObject = new JSONObject(json);
            // Acessa o array de histórico de manutenção
            JSONArray historicoMaquinaArray = jsonObject.getJSONArray("maquinas");

            for (int i = 0; i < historicoMaquinaArray.length(); i++) {
                JSONObject maquinaObject = historicoMaquinaArray.getJSONObject(i);
                Maquina maquina;

                maquina = new Maquina(
                        maquinaObject.getString("id"),  // Tratando o id como String
                        maquinaObject.getString("codigo"),
                        maquinaObject.getString("nome"),
                        maquinaObject.getString("modelo"),
                        maquinaObject.getString("fabricante"),
                        parseDate(maquinaObject.getString("dataAquisicao")),  // Convertendo para LocalDate
                        maquinaObject.getLong("tempoVidaEstimado"),
                        maquinaObject.getString("localizacao"),
                        maquinaObject.getString("detalhes"),
                        maquinaObject.getString("manual")
                );
                maquinas.add(maquina);
            }
        }
        return maquinas;
    }

    // Método para converter strings de data (formato "yyyy-MM-dd") para LocalDate
    private static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMAT);
    }
}
