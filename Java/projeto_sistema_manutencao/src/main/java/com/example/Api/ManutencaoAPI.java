package com.example.Api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Models.Manutencao;

public class ManutencaoAPI {

    // Define o formato da data, sem o tempo
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Get all maintenance records
    public static List<Manutencao> getManutencao() {
        String json = ApiConnection.getData("manutencao");
        List<Manutencao> manutencao = new ArrayList<>();

        if (json != null) {
            // Acessa o objeto raiz do JSON
            JSONObject jsonObject = new JSONObject(json);
            // Acessa o array de histórico de manutenção
            JSONArray historicoManutencaoArray = jsonObject.getJSONArray("historicoManutencao");

            for (int i = 0; i < historicoManutencaoArray.length(); i++) {
                JSONObject manutencaoObject = historicoManutencaoArray.getJSONObject(i);
                Manutencao maquina;

                maquina = new Manutencao(
                        manutencaoObject.getInt("id"),
                        manutencaoObject.getInt("maquinaId"),
                        parseDate(manutencaoObject.getString("data")),  // Ajusta o parsing da data
                        manutencaoObject.getString("tipo"),
                        manutencaoObject.getString("pecasTrocadas"),
                        manutencaoObject.getInt("tempoDeParada"),
                        0,  // Colocando zero no técnico, já que não tem um campo tecnicoId
                        manutencaoObject.getString("observacoes")
                );
                manutencao.add(maquina);
            }
        }
        return manutencao;
    }

    // Método para converter strings de data (formato simples "yyyy-MM-dd")
    private static Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString); // Converte a string para java.util.Date
        } catch (ParseException e) {
            return null;
        }
    }
}
