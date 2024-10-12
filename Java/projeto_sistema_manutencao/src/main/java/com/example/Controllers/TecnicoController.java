package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Api.TecnicoAPI;
import com.example.Models.Tecnico;

public class TecnicoController {

    private List<Tecnico> maquinas;

    public TecnicoController() {
        maquinas = new ArrayList<>();
    }

    // metodos CRUD
    public void CreateTecnico(Tecnico maquina) {
        maquinas.add(maquina);
    }

    public List<Tecnico> ReadTecnico() {
        maquinas = TecnicoAPI.getTecnicos();
        return maquinas;
    }

    public void UpdateTecnico(int posicao, Tecnico maquina) {
        maquinas.set(posicao, maquina);
    }

    public void DeleteTecnico(int posicao) {
        maquinas.remove(posicao);
        }
}
