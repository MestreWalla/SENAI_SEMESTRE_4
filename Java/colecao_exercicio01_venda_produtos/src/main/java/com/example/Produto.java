package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {
    private String nome;
    private double valor;

    @Override
    public String toString() {
        return "Nome do Produto: " + nome + ", Valor: " + valor;
    }
}