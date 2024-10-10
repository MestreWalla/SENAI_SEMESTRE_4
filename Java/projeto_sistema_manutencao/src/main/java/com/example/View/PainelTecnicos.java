package com.example.View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.PainelController;

public class PainelTecnicos extends JPanel {

    private PainelController painelController;
    private JTable painelTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarPainel;

    // Construtor
    public PainelTecnicos() {
        super(new BorderLayout());
        painelController = new PainelController();
    }
}
