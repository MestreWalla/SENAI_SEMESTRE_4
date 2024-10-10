package com.example;

import javax.swing.SwingUtilities;

import com.example.View.SistemaManutencaoGUI;

public class Main {

    public static void main(String[] args) {
        // SistemaManutencaoGUI sistemaManutencaoGUI = new SistemaManutencaoGUI();

        SwingUtilities.invokeLater(() -> {
            SistemaManutencaoGUI sistemaManutencaoGUI = new SistemaManutencaoGUI();
            sistemaManutencaoGUI.setVisible(true);
        });
    }
}

// ApiConnection
// GET, POST, PUT, DELETE
// MaquinaAPI
// GET, POST, PUT
// ManutencaoAPI
// GET, POST, PUT
// FalhaAPI
// GET, POST, PUT
// TecnicoAPI
// GET, POST, PUT, DELETE