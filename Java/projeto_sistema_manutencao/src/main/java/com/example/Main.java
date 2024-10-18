package com.example;

import javax.swing.SwingUtilities;

import com.example.View.SistemaManutencaoGUI;

public class Main {

    public static void main(String[] args) {
        // Configura a codificação de arquivo para UTF-8
        System.setProperty("file.encoding", "UTF-8");
        
        // Executar o GUI na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            SistemaManutencaoGUI sistemaManutencaoGUI = new SistemaManutencaoGUI();
            sistemaManutencaoGUI.setVisible(true);
        });
    }
}
