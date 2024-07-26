package com.example;

public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public int multiplicacao(int a, int b) {
        return a * b;
    }

    public double divisao(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return (double) a / b;
    }
    public double potencia(int a, int b) {
        return Math.pow(a, b);
    }
    public int fatorial(int a) {
        if (a < 0) {
            throw new IllegalArgumentException("O número não pode ser negativo.");
        }
        if (a == 0 || a == 1) {
            return 1;
        }
        int fatorial = 1;
        for (int i = 2; i <= a; i++) {
            fatorial *= i;
        }
        return fatorial;
    }
    public double raiz(double a, double b) {
        if (b<=0) {
            throw new IllegalArgumentException();
        }
        double raiz = 1/b;
        if (a<0 && b%2==0) {
            throw new ArithmeticException("Inválido");
        }
        return Math.pow(a, raiz);
    }
}
