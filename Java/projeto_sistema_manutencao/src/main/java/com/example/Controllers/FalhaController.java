package com.example.Controllers;

import java.util.List;

import com.example.Api.FalhaAPI;
import com.example.Models.Falha;

public class FalhaController {

    private List<Falha> falhas;

    public List<Falha> ReadFalhas() {
        falhas = FalhaAPI.getFalhas();
        return falhas;
    }

    public void CreateFalha(Falha falha) {
        FalhaAPI.postFalha(falha);
        falhas.add(falha);
    }

    public void UpdateFalha(int posicao, Falha falha) {
        // Atualiza a falha na API
        FalhaAPI.putFalha(falha);
        // Atualiza a falha na lista local
        falhas.set(posicao, falha);
    }

    public void DeleteFalha(int id) {
        System.out.println("Tentando excluir a falha com ID: " + id);

        // Verifica se a falha existe na lista local antes de tentar excluí-la
        boolean falhaExists = falhas.stream().anyMatch(maquina -> maquina.getId() == id);

        if (!falhaExists) {
            System.out.println("Falha com ID " + id + " não encontrada na lista local.");
            return; // Retorna sem fazer nada se a falha não existir
        }

        try {
            // Chama o método da API para deletar a falha
            FalhaAPI.deleteFalha(id);
            System.out.println("Chamada para excluir falha enviada para a API.");

            // Remove a falha da lista local (falhas)
            falhas.removeIf(maquina -> maquina.getId() == id);

            // Atualiza a lista após a exclusão, se necessário
            ReadFalhas();

            System.out.println("Máquina excluída com sucesso!");
        } catch (Exception e) {
            // Exibe mais informações sobre o erro
            System.out.println("Erro ao excluir a falha: " + e.getMessage());
            e.printStackTrace(); // Adiciona o stack trace para maior visibilidade do erro
        }
    }

}
