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
            "ID", "Nome", "Data", "Tipo", "Pecas", "Tempo de Parada", "Técnico", "Observações"
        }, 0);
        manutencoesTable = new JTable(tableModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarManutencao = new JButton("Cadastrar manutenção");
        btnEditarManutencao = new JButton("Editar manutenção");
        btnExcluirManutencao = new JButton("Excluir manutenção");
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
                    JOptionPane.showMessageDialog(PainelManutencoes.this, "Selecione uma manutenção para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
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
        JOptionPane.showMessageDialog(this, "Selecione uma manutenção para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Mensagem de confirmação
    int resposta = JOptionPane.showConfirmDialog(this,
            "Você tem certeza que deseja excluir esta manutenção?",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    if (resposta == JOptionPane.YES_OPTION) {
        Object valor = tableModel.getValueAt(linhaSelecionada, 0); // Obtém o valor da célula
        int id;

        // Verifica se o valor é uma String e faz a conversão para int
        if (valor instanceof String) {
            try {
                id = Integer.parseInt((String) valor);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido: não é um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (valor instanceof Integer) {
            id = (Integer) valor; // Se o valor já for Integer
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chama o método do controlador para excluir a manutenção
            manutencaoController.DeleteManutencao(id);
            // Remove a linha da tabela
            tableModel.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Manutenção excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir manutenção: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Se o usuário cancelar, não faz nada
        JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelamento", JOptionPane.INFORMATION_MESSAGE);
    }
}


    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Manutencao> manutencoes = manutencaoController.ReadManutencao();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                manutencao.getId(),
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
