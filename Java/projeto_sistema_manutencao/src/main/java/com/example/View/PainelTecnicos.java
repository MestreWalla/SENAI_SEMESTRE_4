package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.TecnicoController;
import com.example.Models.Tecnico;

public class PainelTecnicos extends JPanel {

    private final TecnicoController tecnicoController;
    private final JTable tecnicosTable;
    private final DefaultTableModel tableModel;
    private final JButton btnSalvarAlteracoes;
    private final JButton btnCadastrarTecnico;

    // Construtor
    public PainelTecnicos() {
        super(new BorderLayout());
        tecnicoController = new TecnicoController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Ocupação", "Status"
        }, 0);
        tecnicosTable = new JTable(tableModel);

        // Criar a tabela
        List<Tecnico> tecnicos = tecnicoController.ReadTecnico();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[]{
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEspecialidade(),
                tecnico.getDisponibilidade()
            });
        }
        JScrollPane scrollPane = new JScrollPane(tecnicosTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Cria os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarTecnico = new JButton("Cadastrar Tecnico");
        btnSalvarAlteracoes = new JButton("Salvar Alterações");
        painelInferior.add(btnCadastrarTecnico, btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adiciona o listener para os botões
    }
}
