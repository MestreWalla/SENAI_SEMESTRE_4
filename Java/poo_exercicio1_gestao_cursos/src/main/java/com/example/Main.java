package com.example;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Curso> cursos = new ArrayList<>();
        String operacao = "0";
        do {
            operacao = JOptionPane.showInputDialog("\n---Gerenciamento de curso---\n"
                    + "1 - Criar Curso\n"
                    + "2 - Adicionar Professor\n"
                    + "3 - Adicionar Aluno\n"
                    + "4 - Informações do Curso\n"
                    + "5 - Atribuir Notas\n"
                    + "6 - Resultado Final\n"
                    + "7 - Sair");

            switch (operacao) {
                case "1":
                    String nomeCursoP = JOptionPane.showInputDialog("Informe o nome do curso");
                    cursos.add(new Curso(nomeCursoP));
                    break;
                case "2":
                    for (Curso curso : cursos) {
                        if (Curso.getNomeCurso().equalsIgnoreCase(nomeCursoP)) {
                            String nomeProf = JOptionPane.showInputDialog("Informe o nome do professor");
                            String cpfProf = JOptionPane.showInputDialog("Informe o CPF do professor");
                            double salario = Double.parseDouble(
                                    JOptionPane.showInputDialog("Salario: "));
                            Professor professor = new Professor(nomeProf, cpfProf, salario);
                        }
                    }
                    break;
                case "3":
                    String nomeCursoA = JOptionPane.showInputDialog("Informe o nome do aluno: ");
                    try {
                        boolean encontrado = false;
                        for (Curso curso : cursos) {
                            if (curso.getNomeCurso().equalsIgnoreCase(nomeCursoA)) {
                                encontrado = true;
                                boolean novoAluno = true;
                                do {
                                    String nomeAluno = JOptionPane.showInputDialog("Nome aluno: ");
                                    String cpfAluno = JOptionPane.showInputDialog("CPF aluno: ");
                                    String matriculaAluno = JOptionPane.showInputDialog("Matricula: ");
                                    curso.addAluno(new Aluno(nomeAluno, cpfAluno, matriculaAluno));
                                    System.out.println("Aluno adicionado");
                                    novoAluno = JOptionPane.showInputDialog("Inserir novo aluno?\n1 - Sim\n2 - Não")
                                            .equals("1") ? true : false;

                                    Object[] options = { "Sim", "Não" };
                                    int opcao = JOptionPane.showOptionDialog(null, "Rodar o programa de novo?",
                                            "De novo?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                            options, options[0]);

                                } while (novoAluno);
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                case "4":
                    String cursoInfo = JOptionPane.showInputDialog("");
                case "5":
                    String notas = JOptionPane.showInputDialog("");
                default:
                    break;
            }
        } while (operacao != "7");
    }
}