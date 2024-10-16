package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Api.MaquinaAPI;
import com.example.Models.Maquina;

public class MaquinaController {

    private List<Maquina> maquinas;

    public MaquinaController() {
        maquinas = new ArrayList<>();
    }

    // metodos CRUD
    public void CreateMaquina(Maquina maquina) {
        MaquinaAPI.postMaquinas(maquina);
        this.maquinas.add(maquina);
    }

    public List<Maquina> ReadMaquina() {
        maquinas = MaquinaAPI.getMaquinas();
        return maquinas;
    }

    public void UpdateMaquina(int posicao, Maquina maquina) {
        // Atualiza a máquina na API
        MaquinaAPI.putMaquina(maquina);
    
        // Atualiza a máquina na lista local
        maquinas.set(posicao, maquina);
    }

    
    public void DeleteMaquina(int id) {
        System.out.println("Tentando excluir a máquina com ID: " + id);

        try {
            // Chama o método da API para deletar a máquina
            MaquinaAPI.deleteMaquina(id);

            // Remove a máquina da lista local (maquinas)
            maquinas.removeIf(maquina -> maquina.getId() == id);

            // Opcional: Atualizar a lista após a exclusão
            ReadMaquina();

            System.out.println("Máquina excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir a máquina: " + e.getMessage());
        }
    }

}
