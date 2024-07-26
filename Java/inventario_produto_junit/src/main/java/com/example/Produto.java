package com.example;

// @allArgsConstructor
// @NoArgsConstructor
// @Getter
// @Setter
public class Produto {
    // Atributos
    private int id;
    private String nome;
    private String fabricante;
    private double preco;
    private int quantidade;

    // Contrutores
    public Produto() {

    }

    public Produto(int id, String nome, String fabricante, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Getters and Setters
    
}
