package com.example;

public class Vestuario
        extends Produto implements Transportavel {
    private double volume;

    public Vestuario(String nome, double preco, double volume) {
        super(nome, preco);
        this.volume = volume;
    }

    @Override
    public double calcularPeso() {
        double peso = volume * 1.5;
        return peso;
    }

    @Override
    public double calcularFrete() {
        double valorFrete = calcularPeso() * 2;
        return valorFrete;
    }
}
