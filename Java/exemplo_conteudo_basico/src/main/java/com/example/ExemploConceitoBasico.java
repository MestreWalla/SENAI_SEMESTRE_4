package com.example;


public class ExemploConceitoBasico {

    public void MediaAluno01() {
        double soma = 0;

        for (int i = 0; i < 4; i++) {
            System.out.println("Digite a nota do aluno " + (i + 1) + ": ");
            soma += Double.parseDouble(System.console().readLine());
        }

        double media = soma / 4;
        System.out.println("A média das notas é: " + media);

        String resultado;

        if (media >= 7) {
            resultado = "Aprovado"; // Se a média for maior ou igual a 7, o resultado será "Aprovado".
        } else if (media <= 6.9 && media >= 5) {
            resultado = "Recuperação"; // Se a média for entre 5 e 6.9, o resultado será "Recuperação".
        } else if (media < 5) {
            resultado = "Reprovado"; // Se a média for menor que 5, o resultado será "Reprovado".
        } else {
            resultado = "Nota invalida"; // Caso a média não seja nenhum dos anteriores, o resultado será "Nota
                                         // invalida".
        }

        System.out.println("A resultado da média é: " + resultado);
    }

    // Outro exemplo de uso de condicional if/else
    public void MediaAluno02() {
        double[] notas = new double[4];
        double media = 0;

        // Ler as notas
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = Double.parseDouble(System.console().readLine());
            media += notas[i];
        }
        if (notas[0] >= 9 && notas[1] >= 9 && notas[2] >= 9 && notas[3] >= 9) {
            media = (media * 1.1 >= 10 ? media = 10 : media * 1.1);
        }
        if (media > 7) {
            System.out.println("A media do aluno é: " + media + " resultado aprovado");
        } else if (media >= 5 && media < 7) {
            System.out.println("A media do aluno é: " + media + " resultado recuperacao");
        } else {
            System.out.println("A media do aluno é: " + media + " resultado reprovado");
        }
    }

    public void Calculadora() {
        try {
            double num1 = 0, num2 = 0;
            int operacao;
            int tipo;

            System.out.println("Escolha o tipo de calculo:\n1 - Calculos basicos\n2 - Raiz quadrada");
            tipo = Integer.parseInt(System.console().readLine());

            switch (tipo) {
                case 1:
                    System.out.println("Digite a operacao:\n1 - Soma\n2 - Subtracao\n3 - Multiplicacao\n4 - Divisao");
                    operacao = Integer.parseInt(System.console().readLine());

                    System.out.println("Digite o primeiro numero:");
                    num1 = Double.parseDouble(System.console().readLine());

                    System.out.println("Digite o segundo numero:");
                    num2 = Double.parseDouble(System.console().readLine());

                    switch (operacao) {
                        case 1:
                            System.out.println("Resultado da soma: " + num1 + " + " + num2 + " = " + (num1 + num2));
                            break;
                        case 2:
                            System.out
                                    .println("Resultado da subtracao: " + num1 + " - " + num2 + " = " + (num1 - num2));
                            break;
                        case 3:
                            System.out.println(
                                    "Resultado da multiplicacao: " + num1 + " * " + num2 + " = " + (num1 * num2));
                            break;
                        case 4:
                            if (num2 == 0) {
                                throw new ArithmeticException("Divisao por zero");
                            }
                            System.out.println("Resultado da divisao: " + num1 + " / " + num2 + " = " + (num1 / num2));
                            break;
                        default:
                            System.out.println("Operacao invalida.");
                    }
                    break;
                case 2:
                    System.out.println("Digite um numero:");
                    num1 = Double.parseDouble(System.console().readLine());

                    if (num1 < 0) {
                        throw new ArithmeticException(
                                "Não é possível calcular a raiz quadrada de um número negativo.");
                    }
                    System.out.println("Resultado da raiz quadrada de: " + num1 + " é: " + Math.sqrt(num1));
                    break;
                default:
                    System.out.println("Tipo de calculo invalido.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Por favor, insira um numero.");
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    
}