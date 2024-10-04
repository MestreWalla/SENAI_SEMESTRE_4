package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class CalculadoraSimples extends JPanel {
    JTextField displaySimples;
    String[] botoes = {
            "", "", "", "",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "+", "=",
            "C", "%"
    };

    public CalculadoraSimples() {
        super(new BorderLayout());
        displaySimples = new JTextField();
        displaySimples.setHorizontalAlignment(JTextField.RIGHT);
        displaySimples.setEditable(false);
        add(displaySimples, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel painelBotoes = new JPanel(new GridLayout(6, 4, 5, 5));

        // Add buttons to the panel
        for (String textoBotoes : botoes) {
            JButton botao = new JButton(textoBotoes);
            botao.addActionListener(new SimpleActionListener(this));
            painelBotoes.add(botao);
        }
        this.add(painelBotoes, BorderLayout.CENTER);
    }

    public void setDisplaySimples(String texto) {
        this.displaySimples.setText(texto);
    }

    public String getDisplaySimples() {
        return displaySimples.getText();
    }
}
