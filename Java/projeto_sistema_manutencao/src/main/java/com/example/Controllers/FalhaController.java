package com.example.Controllers;

import java.util.List;

import com.example.Api.FalhaAPI;
import com.example.Models.Falha;

public class FalhaController {

    public List<Falha> ReadFalhas() {
        return FalhaAPI.getFalhas();
    }

    public void CreateFalha(Falha falha) {
        FalhaAPI.postFalha(falha);
    }
}
