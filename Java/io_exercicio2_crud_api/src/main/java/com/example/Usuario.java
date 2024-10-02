package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    private String id;
    private String nome;
    private int idade;
    private String endereco;

    @Override
    public String toString() {
        return "Usuario{" +
                "\n   id=" + id + "\n" +
                "   nome=" + nome + "\n" +
                "   idade=" + idade + "\n" +
                "   endereco='" + endereco + "\n" +
                '}';
    }
}
