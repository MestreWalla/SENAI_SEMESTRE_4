package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Manutencao {
    private String id;
    private String maquinaId;
    private String data;
    private String tipo;
    private String pecasTrocadas;
    private int tempoDeParada;
    private int tecnicoId;
    private String observacoes;
}
