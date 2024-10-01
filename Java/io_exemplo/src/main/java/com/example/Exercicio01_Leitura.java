package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Exercicio01_Leitura {
    private List<Exercicio01_Aluno> alunos;

    public void leituraFile(String nomeArquivo) {
        alunos = new ArrayList<>();

        File file = new File("alunos.txt");
        String absolutePath = file.getAbsolutePath();
        System.out.println("Absoluto: " + absolutePath);
        
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] aluno = line.split(",");
                Exercicio01_Aluno alunoNota = new Exercicio01_Aluno(
                        aluno[0],
                        Double.parseDouble(aluno[1]),
                        Double.parseDouble(aluno[2]),
                        Double.parseDouble(aluno[3]));
                alunos.add(alunoNota);
                System.out.println(alunoNota+"\t");
            }
            System.out.println("Total de alunos: " + alunos.size());
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void alunoMaiorNota() {
        String nome = "";
        double maiorNota = Double.MIN_VALUE;
        for (Exercicio01_Aluno aluno : alunos) {
            double notaAtual = aluno.maiorNota();
            if (notaAtual > maiorNota) {
                maiorNota = notaAtual;
                nome = aluno.getNome();
            }
        }
        System.out.println("O aluno com a maior nota é: " + nome + " com " + maiorNota + " pontos.");
    }

    public void alunoMenorNota() {
        String nome = "";
        double menorNota = Double.MAX_VALUE;
        for (Exercicio01_Aluno aluno : alunos) {
            double notaAtual = aluno.menorNota();
            if (notaAtual < menorNota) {
                menorNota = notaAtual;
                nome = aluno.getNome();
            }
        }
        System.out.println("O aluno com a menor nota é: " + nome + " com " + menorNota + " pontos.");
    }

    public void alunoMediaCadaAluno() {
        double somaMedia = 0;
        for (Exercicio01_Aluno aluno : alunos) {
            somaMedia += aluno.calcularMedia();
        }
        double media = somaMedia / alunos.size();
        System.out.println("A média de notas dos alunos é: " + media);
    }
}
