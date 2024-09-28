package com.example;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        GerenciamentoVendas gv = new GerenciamentoVendas();
        String operacao;
        do {
            operacao = JOptionPane.showInputDialog(
                    "\n---Gerenciamento de vendas---\n"
                            + "1 - Cadastrar venda\n"
                            + "2 - Listar vendas\n"
                            + "3 - Listar vendas acima de valor\n"
                            + "4 - Sair\n");

            switch (operacao) {
                case "1":
                    String CPF = JOptionPane.showInputDialog("Informe o valor: ");
                    String nomeProduto = JOptionPane.showInputDialog("Informe o nome do produto: ");
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor: "));
                    Produto produto = new Produto(nomeProduto, valor);
                    gv.venda(CPF, produto);
                    break;
                case "2":
                String CPF = JOptionPane.showInputDialog("Informe o CPF do cliente: ");
                double valorMinimo = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor mínimo: "));
                    gv.consultarVendaPorCliente(String CPF);
                    break;
                case "3":
                    gv.listarVendasAcimaDeValor(String CPF, double valorMinimo);
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Operação inválida!");
                    break;
            }
        } while (operacao != "4");
    }
}