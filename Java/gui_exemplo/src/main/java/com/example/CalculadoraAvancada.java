package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class CalculadoraAvancada extends JPanel {
    JTextField displaySimples;
    String[] botoes = {
            "^", "sqrt", "log", "!",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "+", "=",
            "C", "%"
    };

    public CalculadoraAvancada() {
        super(new BorderLayout());
        displaySimples = new JTextField();
        displaySimples.setHorizontalAlignment(JTextField.RIGHT);
        displaySimples.setEditable(false);
        add(displaySimples, BorderLayout.NORTH);

        // Criar painel para os botoes
        JPanel painelBotoes = new JPanel(new GridLayout(6, 4, 5, 5));

        // Adicionar botoes ao painel
        for (String textoBotoes : botoes) {
            JButton botao = new JButton(textoBotoes);
            painelBotoes.add(botao);
            // Adicionar acao ao botoes
            // botao.addActionListener(e -> {
            //     String texto = displaySimples.getText() + e.getActionCommand();
            //     displaySimples.setText(texto);
            // });
        }
        this.add(painelBotoes, BorderLayout.CENTER);
    }
}
