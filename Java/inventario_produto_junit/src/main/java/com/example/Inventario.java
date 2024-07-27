package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Produto> list;

    public Inventario() {
        list = new ArrayList<>();
    }

    // Métodos
    public void criar(Produto produto) {
        list.add(produto);
    }

    public List<Produto> listar() {
        return new ArrayList<>(list);
    }

    public void remover(int id) {
        list.removeIf(produto -> produto.getId() == id);
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : list) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public void atualizar(int id, int quantidade, double preco) {
        for (Produto produto : list) {
            if (produto != null) {
                if (produto.getId() == id) {
                    produto.setQuantidade(quantidade);
                    produto.setPreco(preco);
                    break;
                }
            } else {
                System.err.println("Nulo");
            }
        }
    }
}