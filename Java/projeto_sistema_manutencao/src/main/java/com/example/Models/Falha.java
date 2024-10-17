package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Falha {

    private int id;
    private int maquinaId;
    private String data;
    private String problema;
    private String prioridade;
    private String operador;
}
