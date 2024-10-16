package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.example.Controllers.ManutencaoController;
import com.example.Models.Manutencao;


public class PainelManutencoes extends JPanel {

    private final ManutencaoController manutencaoController;
    private final JTable manutencoesTable;
    private final DefaultTableModel tableModel;
    private final JButton btnCadastrarManutencao;
    private final JButton btnEditarManutencao;
    private final JButton btnExcluirManutencao;
    private final JButton btnAtualizarLista;

    public PainelManutencoes() {
        super(new BorderLayout());
        manutencaoController = new ManutencaoController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);
        manutencoesTable = new JTable(tableModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar Técnico");
        btnEditarManutencao = new JButton("Editar Técnico");
        btnExcluirManutencao = new JButton("Excluir Técnico");
        btnAtualizarLista = new JButton("Atualizar Lista");

        painelInferior.add(btnCadastrarManutencao);
        painelInferior.add(btnEditarManutencao);
        painelInferior.add(btnExcluirManutencao);
        painelInferior.add(btnAtualizarLista);
        this.add(painelInferior, BorderLayout.SOUTH);

        btnCadastrarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        btnEditarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = manutencoesTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelManutencoes.this, "Selecione um técnico para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abrirEditarManutencaoDialog(linhaSelecionada);
            }
        });

        btnExcluirManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirManutencaoSelecionado();
            }
        });

        btnAtualizarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLista();
            }
        });
    }

    private void abrirFormularioCadastro() {
        CadastroManutencaoDialog dialog = new CadastroManutencaoDialog(manutencaoController, tableModel);
        dialog.setVisible(true);
    }

    private void abrirEditarManutencaoDialog(int posicao) {
        Manutencao manutencaoSelecionado = manutencaoController.ReadManutencao().get(posicao);
        EditarManutencaoDialog dialog = new EditarManutencaoDialog(manutencaoController, tableModel, manutencaoSelecionado, posicao);
        dialog.setVisible(true);
    }

    private void excluirManutencaoSelecionado() {
        int linhaSelecionada = manutencoesTable.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma mautencao para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
        manutencaoController.DeleteManutencao(id);
        tableModel.removeRow(linhaSelecionada);
    }

    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Manutencao> manutencoes = manutencaoController.ReadManutencao();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                manutencao.getMaquinaId(),
                manutencao.getData(),
                manutencao.getTipo(),
                manutencao.getPecasTrocadas(),
                manutencao.getTempoDeParada(),
                manutencao.getTecnico(),
                manutencao.getObservacoes()
            });
        }
    }
}
