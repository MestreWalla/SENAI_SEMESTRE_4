package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Api.ManutencaoAPI;
import com.example.Models.Manutencao;

public class ManutencaoController {
    private List<Manutencao> manutencoes;

    public ManutencaoController() {
        manutencoes = new ArrayList<>();
    }

    // Metodos CRUD
    public void CreateManutencao(Manutencao manutencao) {
        ManutencaoAPI.postManutencoes(manutencao);
        manutencoes.add(manutencao);
    }

    public List<Manutencao> ReadManutencao() {
        manutencoes = ManutencaoAPI.getManutencoes();
        return manutencoes;
    }

    public void UpdateManutencao(int posicao, Manutencao manutencao) {
        ManutencaoAPI.putManutencao(manutencao);
        manutencoes.set(posicao, manutencao);
    }

    public void DeleteManutencao(int posicao) {
        ManutencaoAPI.deleteManutencao(posicao);
        manutencoes.removeIf(manutencao -> manutencao.getId() == posicao);
    }
}
