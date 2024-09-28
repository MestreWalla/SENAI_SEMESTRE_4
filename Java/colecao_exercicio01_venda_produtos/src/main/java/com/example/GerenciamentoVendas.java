package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GerenciamentoVendas {
    private Map<String, List<Produto>> vendasClientes;

    public GerenciamentoVendas() {
        vendasClientes = new HashMap<>();
    }

    public void venda(String CPF, Produto produto) {
        for (String cpfCliente : vendasClientes.keySet()) {
            if (cpfCliente.equalsIgnoreCase(CPF)) {
                List<Produto> produtos = vendasClientes.get(CPF);
                produtos.add(produto);
                vendasClientes.put(CPF, produtos);
                return;
            }
        }
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        vendasClientes.put(CPF, produtos);
    }

    public void consultarVendaPorCliente(String CPF) {
        if (!vendasClientes.get(CPF).isEmpty()) {
            System.out.println("Produtos comprados pelo cliente: " + CPF);
            List<Produto> produtos = vendasClientes.getOrDefault(CPF, Collections.emptyList());
            for (Produto produto : produtos) {
                System.out.println(produto.getNome() + " - R$ " + produto.getValor() + ".");
            }
        } else {
            System.out.println("Cliente não encontrado ou não efetuou nenhuma compra!");
        }
    }

    public void listarVendasAcimaDeValor(String CPF, double valorMinimo) {
        List<Produto> produtos = vendasClientes.getOrDefault(CPF, Collections.emptyList());
        if (!produtos.isEmpty()) {
            System.out.println("Produtos comprados pelo cliente acima de R$" + valorMinimo + ": " + CPF);
            List<Produto> resultado = produtos.stream()
                    .filter(produto -> produto.getValor() >= valorMinimo)
                    .collect(Collectors.toList());
            if (resultado.isEmpty()) {
                System.out.println("Nenhuma compra atingiu o valor minimo de " + valorMinimo + ".");
            } else {
                for (Produto produto : resultado) {
                    System.out.println(produto.getNome() + " - R$ " + produto.getValor() + ".");
                }
            }
        } else {
            System.out.println("Cliente não encontrado ou não efetuou nenhuma compra!");
        }
    }
}
