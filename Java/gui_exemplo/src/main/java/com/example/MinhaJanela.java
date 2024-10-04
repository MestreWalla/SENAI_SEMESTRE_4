package com.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinhaJanela extends JFrame {
    public MinhaJanela() {
        super("MinhaJanela");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);

        // Criando um painel para centralizar o botao
        JPanel painel = new JPanel();
        this.add(painel);

        // Adicionando um botao
        JButton botao01 = new JButton("Botao01");
        painel.add(botao01);

        // Definindo o comportamento do botao01
        botao01.addActionListener(e -> {
            JOptionPane.showMessageDialog(botao01, "Botao01 clicado!");
        });
        
    }
}
