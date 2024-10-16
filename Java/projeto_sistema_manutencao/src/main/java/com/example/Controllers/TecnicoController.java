package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Api.TecnicoAPI;
import com.example.Models.Tecnico;

public class TecnicoController {

    private List<Tecnico> tecnicos;

    public TecnicoController() {
        tecnicos = new ArrayList<>();
    }

    // Cria um novo técnico
    public void CreateTecnico(Tecnico tecnico) {
        TecnicoAPI.postTecnico(tecnico);
        tecnicos.add(tecnico);
    }

    // Retorna todos os técnicos
    public List<Tecnico> ReadTecnico() {
        tecnicos = TecnicoAPI.getTecnicos();
        return tecnicos;
    }

    // Atualiza as informações de um técnico
    public void UpdateTecnico(int posicao, Tecnico tecnico) {
        TecnicoAPI.putTecnico(tecnico);
        tecnicos.set(posicao, tecnico);
    }

    // Exclui um técnico pelo ID
    public void DeleteTecnico(int id) {
        TecnicoAPI.deleteTecnico(id);
        tecnicos.removeIf(tecnico -> tecnico.getId() == id);
    }
}
