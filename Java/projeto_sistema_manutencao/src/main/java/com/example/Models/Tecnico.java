package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Tecnico {

    private int id;
    private String nome;
    private String especialidade;
    private String disponibilidade;
}
