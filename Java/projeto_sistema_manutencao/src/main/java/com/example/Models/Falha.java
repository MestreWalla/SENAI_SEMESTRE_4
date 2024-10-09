package com.example.Models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Falha {

    private int id;
    private int maquinaId;
    private Date data;
    private String problema;
    private String prioridade;
    private String operador;
}
