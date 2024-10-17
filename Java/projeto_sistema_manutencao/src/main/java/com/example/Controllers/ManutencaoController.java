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

    public void DeleteManutencao(int id) {
        System.out.println("Tentando excluir a manutencao com ID: " + id);

        try {
            // Chama o método da API para deletar a máquina
            ManutencaoAPI.deleteManutencao(id);

            // Remove a máquina da lista local (maquinas)
            manutencoes.removeIf(manutencao -> manutencao.getId() == id);

            // Opcional: Atualizar a lista após a exclusão
            ReadManutencao();

            System.out.println("Manutenção excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir a manutencao: " + e.getMessage());
        }
    }
}
