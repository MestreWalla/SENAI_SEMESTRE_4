package com.example;

import javax.swing.SwingUtilities;

import com.example.View.SistemaManutencaoGUI;

public class Main {

    public static void main(String[] args) {
        // SistemaManutencaoGUI sistemaManutencaoGUI = new SistemaManutencaoGUI();
        System.setProperty("file.encoding", "UTF-8");
        SwingUtilities.invokeLater(() -> {
            SistemaManutencaoGUI sistemaManutencaoGUI = new SistemaManutencaoGUI();
            sistemaManutencaoGUI.setVisible(true);
        });
    }
}

// MaquinaAPI
// GET, POST, PUT
// ManutencaoAPI
// GET, POST, PUT
// FalhaAPI
// GET, POST, PUT
// TecnicoAPI
// GET, POST, PUT, DELETE