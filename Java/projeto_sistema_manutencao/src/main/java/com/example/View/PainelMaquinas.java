package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.MaquinaController;

public class PainelMaquinas extends JPanel {

    private final MaquinaController maquinaController;
    private JTable maquinasTable;
    private final DefaultTableModel tableModel;
    private final JButton btnSalvarAlteracoes;
    private final JButton btnCadastrarMaquina;

    // Construtor
    public PainelMaquinas() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Fabricante", "Modelo", "Detalhes", "Localização", "Tempo de Vida Estimado"
        }, 0);

        // Criar a tabela
        // List<Maquina> maquinas = maquinaController.ReadMaquina();
        // for (Maquina maquina : maquinas) {
        //     tableModel.addRow(new Object[]{
        //         maquina.getId(),
        //         maquina.getNome(),
        //         maquina.getFabricante(),
        //         maquina.getModelo(),
        //         maquina.getDetalhes(),
        //         maquina.getLocalizacao(),
        //         maquina.getTempoVidaEstimado()
        //     });
        // }
        // JScrollPane scrollPane = new JScrollPane(maquinasTable);
        // scrollPane.add(maquinasTable);
        // this.add(scrollPane, BorderLayout.CENTER);

        // Cria os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar Maquina");
        btnSalvarAlteracoes = new JButton("Salvar Alterações");
        painelInferior.add(btnCadastrarMaquina, btnSalvarAlteracoes);
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adiciona o listener para os botões
    }
}
