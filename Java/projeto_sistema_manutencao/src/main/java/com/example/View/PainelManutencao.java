package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.ManutencaoController;
import com.example.Models.Manutencao;

public class PainelManutencao extends JPanel {

    private final ManutencaoController manutencaoController;
    private final JTable manutencoesTable;
    private final DefaultTableModel tableModel;
    private final JButton btnSalvarAlteracoes;
    private final JButton btnCadastrarManutencao;

    // Construtor
    public PainelManutencao() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Fabricante", "Modelo", "Detalhes", "Localização", "Tempo de Vida Estimado"
        }, 0);
        manutencoesTable = new JTable(tableModel);

        // Criar a tabela
        List<Manutencao> manutencoes = manutencaoController.ReadManutencao();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                manutencao.getId(),
                manutencao.getMaquinaId(),
                manutencao.getData(),
                manutencao.getTipo(),
                manutencao.getPecasTrocadas(),
                manutencao.getTempoDeParada(),
                manutencao.getTecnicoId(),
                manutencao.getObservacoes()
            });
        }
        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Cria os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar Manutencao");
        btnSalvarAlteracoes = new JButton("Salvar Alterações");
        painelInferior.add(btnCadastrarManutencao, btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adiciona o listener para os botões
    }
}
