package com.example;

// importar lombok
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// anotação para usar lombok
    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
public class Pessoa {
    // Atributos
    private String nome;
    private String cpf;
    // Metodo
    public String exibirInfo() {
        return "Nome: " + nome.toUpperCase() + ", CPF: " + cpf;
    }
}
