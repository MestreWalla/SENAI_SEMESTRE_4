package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                "Digite a operacao:\n1 - CalculadoraMedia01\n2 - CalculadoraMedia02\n3 - Calculadora\n4 - CalculadoraFatorial\n5 - Agenda");
        int operacao = Integer.parseInt(System.console().readLine());

        switch (operacao) {
            case 1:
                new ExemploConceitoBasico().MediaAluno01();
                break;
            case 2:
                new ExemploConceitoBasico().MediaAluno02();
                break;
            case 3:
                new ExemploConceitoBasico().Calculadora();
                break;
            case 4:
                new Exercicio04Recursao().fatorial(operacao);
                break;
            case 5:
                do {
                    ContatoController agenda = new ContatoController(5);
                    System.out.println(
                            "Digite a operacao:\n1 - Cadastrar Contato\n2 - Listar Contatos\n3 - Buscar Contato pelo nome\n4 - Sair");
                    try {
                        int opcao = Integer.parseInt(System.console().readLine());
                        switch (opcao) {
                            case 1:
                                System.out.println("nome");
                                String nome = System.console().readLine();
                                System.out.println("Endereco");
                                String endereco = System.console().readLine();
                                System.out.println("Email");
                                String email = System.console().readLine();
                                System.out.println("Telefone");
                                String telefone = System.console().readLine();
                                Contato contato = new Contato(nome, email, endereco, telefone);
                                agenda.addContato(contato);
                                break;
                            case 2:
                                agenda.listarContatos();
                                break;
                            case 3:
                                System.out.println("Digite o nome a buscar: ");
                                String name = System.console().readLine();
                                agenda.buscarNome(name);
                                break;
                            case 4:
                                System.out.println("Saindo...");
                                break;
                            default:
                                System.out.println("Digite um numero valido.");
                                break;
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                } while (operacao != 4);
        }
    }
}