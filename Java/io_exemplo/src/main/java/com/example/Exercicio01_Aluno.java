package com.example;

import java.util.ArrayList;
import java.util.List;

public class Exercicio01_Aluno {
    private String nome;
    private List<Double> notas;

    public Exercicio01_Aluno(String nome, double nota1, double nota2, double nota3) {
        this.nome = nome;
        this.notas = new ArrayList<>();
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    public double maiorNota() {
        double maiorNota = Double.MIN_VALUE;
        for (double nota : notas) {
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }
        return maiorNota;
    }

    public double menorNota() {
        double menorNota = Double.MAX_VALUE;
        for (double nota : notas) {
            if (nota < menorNota) {
                menorNota = nota;
            }
        }
        return menorNota;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' + ", notas=" + notas.toString();
    }

    public String getNome() {
        return nome;
    }
}
