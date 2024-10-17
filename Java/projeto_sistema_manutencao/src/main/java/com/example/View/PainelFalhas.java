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

import com.example.Controllers.FalhaController;
import com.example.Models.Falha;

public class PainelFalhas extends JPanel {

    private final FalhaController falhaController;
    private final JTable falhasTable;
    private final DefaultTableModel tableModel;
    private final JButton btnCadastrarFalha;
    private final JButton btnEditarFalha;
    private final JButton btnExcluirFalha;
    private final JButton btnAtualizarLista;

    public PainelFalhas() {
        super(new BorderLayout());
        falhaController = new FalhaController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Máquina ID", "Data", "Problema", "Prioridade", "Operador"
        }, 0);
        falhasTable = new JTable(tableModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(falhasTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarFalha = new JButton("Cadastrar Falha");
        btnEditarFalha = new JButton("Editar Falha");
        btnExcluirFalha = new JButton("Excluir Falha");
        btnAtualizarLista = new JButton("Atualizar Lista");

        painelInferior.add(btnCadastrarFalha);
        painelInferior.add(btnEditarFalha);
        painelInferior.add(btnExcluirFalha);
        painelInferior.add(btnAtualizarLista);
        this.add(painelInferior, BorderLayout.SOUTH);

        btnCadastrarFalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        btnEditarFalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = falhasTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelFalhas.this, "Selecione uma falha para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abrirEditarFalhaDialog(linhaSelecionada);
            }
        });

        btnExcluirFalha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFalhaSelecionada();
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
        CadastroFalhaDialog dialog = new CadastroFalhaDialog(falhaController, tableModel);
        dialog.setVisible(true);
    }

    private void abrirEditarFalhaDialog(int posicao) {
        Falha falhaSelecionada = falhaController.ReadFalhas().get(posicao);
        EditarFalhaDialog dialog = new EditarFalhaDialog(falhaController, tableModel, falhaSelecionada, posicao);
        dialog.setVisible(true);
    }

    private void excluirFalhaSelecionada() {
        int linhaSelecionada = falhasTable.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma falha para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mensagem de confirmação
        int resposta = JOptionPane.showConfirmDialog(this,
                "Você tem certeza que deseja excluir esta falha?",
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
                // Chama o método do controlador para excluir a falha
                falhaController.DeleteFalha(id);
                // Remove a linha da tabela
                tableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(this, "Falha excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir falha: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Se o usuário cancelar, não faz nada
            JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelamento", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Falha> falhas = falhaController.ReadFalhas();
        for (Falha falha : falhas) {
            tableModel.addRow(new Object[]{
                falha.getId(),
                falha.getMaquinaId(),
                falha.getData(),
                falha.getProblema(),
                falha.getPrioridade(),
                falha.getOperador()
            });
        }
    }
}
