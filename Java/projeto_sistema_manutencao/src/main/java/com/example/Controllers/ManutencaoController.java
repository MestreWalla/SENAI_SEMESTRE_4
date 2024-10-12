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
        ManutencaoAPI.postManutencao(manutencao);
        this.manutencoes.add(manutencao);
    }

    public List<Manutencao> ReadManutencao() {
        manutencoes = ManutencaoAPI.getManutencoes();
        return manutencoes;
    }

    public void UpdateManutencao(int posicao, Manutencao manutencao) {
        manutencoes.set(posicao, manutencao);
    }

    public void DeleteManutencao(int posicao) {
        manutencoes.remove(posicao);
    }
}
