package com.example.View;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.ManutencaoController;

public class PainelManutencao extends JPanel {

    private ManutencaoController manutencaoController;
    private JTable manutencaosTable;
    private DefaultTableModel tableModel;
    private JButton btnSalvarAlteracoes;
    private JButton btnCadastrarManutencao;

    // Construtor
    public PainelManutencao() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();
    }
}
