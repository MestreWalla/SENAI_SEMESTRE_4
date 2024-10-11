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
        maquinas.set(posicao, maquina);
    }

    public void DeleteMaquina(int posicao) {
        maquinas.remove(posicao);
        }
}
