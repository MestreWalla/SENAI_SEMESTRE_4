package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleActionListener implements ActionListener {
    private double valorAtual; // Armazena o valor atual exibido na calculadora
    private String operador; // Armazena o operador selecionado (+, -, *, /)
    private CalculadoraSimples calcSimples; // Referência à instância da calculadora

    // Construtor que recebe a instância da calculadora
    public SimpleActionListener(CalculadoraSimples calcSimples) {
        this.calcSimples = calcSimples;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand(); // Captura o comando do botão clicado

        // Se o comando for um dígito ou um ponto decimal
        if (comando.matches("\\d|\\.")) {
            // Adiciona o comando ao display da calculadora
            calcSimples.setDisplaySimples(calcSimples.getDisplaySimples() + comando);
        } 
        // Se o comando for um operador
        else if (comando.matches("[+\\-*/]")) {
            // Armazena o valor atual e o operador selecionado
            valorAtual = Double.parseDouble(calcSimples.getDisplaySimples());
            operador = comando; // Define o operador
            calcSimples.setDisplaySimples(""); // Limpa o display para o próximo número
        } 
        // Se o comando for o botão "="
        else if (comando.equals("=")) {
            // Converte o valor atual do display para um número
            double valorNovo = Double.parseDouble(calcSimples.getDisplaySimples());
            double resultado = 0; // Inicializa o resultado

            // Realiza a operação com base no operador selecionado
            switch (operador) {
                case "+":
                    resultado = valorAtual + valorNovo; // Soma
                    break;
                case "-":
                    resultado = valorAtual - valorNovo; // Subtração
                    break;
                case "*":
                    resultado = valorAtual * valorNovo; // Multiplicação
                    break;
                case "/":
                    // Verifica se o divisor é diferente de zero
                    if (valorNovo != 0) {
                        resultado = valorAtual / valorNovo; // Divisão
                    } else {
                        // Exibe mensagem de erro se a divisão for por zero
                        JOptionPane.showMessageDialog(null, "Divisão por zero não é permitida!");
                        return; // Interrompe a execução
                    }
                    break;
            }

            // Exibe o resultado no display da calculadora
            calcSimples.setDisplaySimples(String.valueOf(resultado));
        } 
        // Se o comando for o botão "C"
        else if (comando.equals("C")) {
            // Limpa o display e reseta os valores
            calcSimples.setDisplaySimples("");
            valorAtual = 0; // Reseta o valor atual
            operador = ""; // Reseta o operador
        }
    }
}
