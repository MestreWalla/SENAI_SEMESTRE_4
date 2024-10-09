package com.example.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Manutencao {
    private int id;
    private int maquinaId;
    private Date data;
    private String tipo;
    private String pecasTrocadas;
    private int tempoDeParada;
    private int tecnicoId;
    private String observacoes;
}
