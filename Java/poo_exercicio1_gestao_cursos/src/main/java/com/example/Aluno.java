package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Pessoa implements Avaliavel {
    private String matricula;
    private double nota;

    // Contrutor
    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
        this.nota = 0.0;
    }

    // Metodo Exibirinfo
    @Override
    public String exibirInfo() {
        super.exibirInfo();
        return "Matricula: " + matricula + "\nNota: " + nota;
    }

    // Metodo Adicionar Nota
    public void setNota(double nota) {
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
        } else {
            System.out.println("Nota invalida!");
        }
    }

    // Metodo Avaliar Desempenho
    @Override
    public String avaliarDesempenho() {
        if (nota >= 7) {
            return "Aprovado";
        } else if (nota >= 5 && nota < 7) {
            return "Recuperacao";
        } else {
            return "Reprovado";
        }
    }
}
