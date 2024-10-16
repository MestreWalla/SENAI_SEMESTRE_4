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
import com.example.Controllers.TecnicoController;
import com.example.Models.Tecnico;

public class PainelTecnicos extends JPanel {

    private final TecnicoController tecnicoController;
    private final JTable tecnicosTable;
    private final DefaultTableModel tableModel;
    private final JButton btnCadastrarTecnico;
    private final JButton btnEditarTecnico;
    private final JButton btnExcluirTecnico;
    private final JButton btnAtualizarLista;

    public PainelTecnicos() {
        super(new BorderLayout());
        tecnicoController = new TecnicoController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);
        tecnicosTable = new JTable(tableModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(tecnicosTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarTecnico = new JButton("Cadastrar Técnico");
        btnEditarTecnico = new JButton("Editar Técnico");
        btnExcluirTecnico = new JButton("Excluir Técnico");
        btnAtualizarLista = new JButton("Atualizar Lista");

        painelInferior.add(btnCadastrarTecnico);
        painelInferior.add(btnEditarTecnico);
        painelInferior.add(btnExcluirTecnico);
        painelInferior.add(btnAtualizarLista);
        this.add(painelInferior, BorderLayout.SOUTH);

        btnCadastrarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        btnEditarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tecnicosTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelTecnicos.this, "Selecione um técnico para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abrirEditarTecnicoDialog(linhaSelecionada);
            }
        });

        btnExcluirTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTecnicoSelecionado();
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
        CadastroTecnicoDialog dialog = new CadastroTecnicoDialog(tecnicoController, tableModel);
        dialog.setVisible(true);
    }

    private void abrirEditarTecnicoDialog(int posicao) {
        Tecnico tecnicoSelecionado = tecnicoController.ReadTecnico().get(posicao);
        EditarTecnicoDialog dialog = new EditarTecnicoDialog(tecnicoController, tableModel, tecnicoSelecionado, posicao);
        dialog.setVisible(true);
    }

    private void excluirTecnicoSelecionado() {
        int linhaSelecionada = tecnicosTable.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um técnico para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
        tecnicoController.DeleteTecnico(id);
        tableModel.removeRow(linhaSelecionada);
    }

    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Tecnico> tecnicos = tecnicoController.ReadTecnico();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[]{
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEspecialidade(),
                tecnico.getDisponibilidade()
            });
        }
    }
}
