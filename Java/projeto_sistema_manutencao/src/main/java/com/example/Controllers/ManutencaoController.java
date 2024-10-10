package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Api.ManutencaoAPI;
import com.example.Models.Manutencao;

public class ManutencaoController {
    
    private List<Manutencao> manutencao;

    public ManutencaoController() {
        manutencao = new ArrayList<>();
    }

    // metodos CRUD
    public void CreateManutencao(Manutencao maquina) {
        manutencao.add(maquina);
    }

    public List<Manutencao> ReadManutencao() {
        manutencao = ManutencaoAPI.getManutencao();
        return manutencao;
    }

    public void UpdateManutencao(int posicao, Manutencao maquina) {
        manutencao.set(posicao, maquina);
    }

    public void DeleteManutencao(int posicao) {
        manutencao.remove(posicao);
        }
}
