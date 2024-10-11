package com.example.View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.FalhaController;

public class PainelFalhas extends JPanel {

    private final FalhaController falhaController;
    private JTable falhasTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarFalha;

    // Construtor
    public PainelFalhas() {
        super(new BorderLayout());
        falhaController = new FalhaController();
    }
}
