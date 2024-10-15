package com.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Curso {
    // Atributos
    private String nomeCurso;
    private List<Aluno> alunos;
    private Professor professor;

    // Construtor
    public Curso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
        alunos = new ArrayList<>();
    }

    // Métodos
    // PROFESSOR
    public void addProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    // ALUNO
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public void removeAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    // CURSO

    public void infoCurso() {
        System.out.println("Curso: " + nomeCurso);
        System.out.println("Professor: " + professor);
        System.out.println("Alunos: ");
        for (Aluno aluno : alunos) {

            System.out.println("Aluno: " + aluno.getNome() + "\nRA:" + aluno.getMatricula());
        }
    }

    public void atribuirNotaNome(String nomeAluno, double notaAluno) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                aluno.setNota(notaAluno);
                return;
            }
            System.out.println("Aluno não encontrado.");
        }
        System.out.println("Lista de alunos esta vazio ou nao pode ser carregado.");
    }

    public void exibirResultadoFinal() {
        for (Aluno aluno : alunos) {
            System.out.println(aluno.exibirInfo());
            System.out.println(aluno.avaliarDesempenho());
        }
    }
}