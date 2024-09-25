package com.example;

public class Exercicio04Recursao {
    int numero = -1;

    public long fatorial(int numero) {
        if (numero == 0 || numero == 1) {
            return 1;
        } else {
            return numero * fatorial(numero - 1);
        }
    }

    public void calculadora() throws Exception {
        while (true) {
            System.out.println("Digite um numero: ");
            int numero = Integer.parseInt(System.console().readLine());

            if (numero < 0) {
                throw new Exception("Não é permitido numero negativo");
            }
            
            try {
                long resultado = fatorial(numero);
                System.out.println("O fatorial é " + resultado);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}