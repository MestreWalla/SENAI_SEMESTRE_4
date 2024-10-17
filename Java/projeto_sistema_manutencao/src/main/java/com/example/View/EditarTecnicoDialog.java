package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.TecnicoController;
import com.example.Models.Tecnico;

public class EditarTecnicoDialog extends JDialog {

    private final TecnicoController tecnicoController;
    private final DefaultTableModel tableModel;
    private final Tecnico tecnico; // Técnico a ser editado
    private final int posicao; // Posição do técnico na tabela

    // Campos de entrada
    private final JTextField txtNome;
    private final JTextField txtEspecialidade;
    private final JTextField txtDisponibilidade;

    public EditarTecnicoDialog(TecnicoController tecnicoController, DefaultTableModel tableModel, Tecnico tecnico, int posicao) {
        this.tecnicoController = tecnicoController;
        this.tableModel = tableModel;
        this.tecnico = tecnico; // Técnico a ser editado
        this.posicao = posicao; // Posição do técnico na tabela

        setTitle("Editar Técnico");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cria um painel com padding
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializa os campos de entrada com os dados do técnico
        txtNome = new JTextField(tecnico.getNome());
        txtEspecialidade = new JTextField(tecnico.getEspecialidade());
        txtDisponibilidade = new JTextField(tecnico.getDisponibilidade());

        // Configura os componentes no diálogo
        setupComponents(panel);

        // Adiciona o painel ao diálogo
        add(panel, BorderLayout.CENTER);

        // Botão para salvar as alterações
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::salvarAlteracoes);
        add(btnSalvar, BorderLayout.SOUTH);
    }

    private void setupComponents(JPanel panel) {
        // Adiciona labels e campos ao painel
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Especialidade:"));
        panel.add(txtEspecialidade);
        panel.add(new JLabel("Disponibilidade:"));
        panel.add(txtDisponibilidade);
    }

    private void salvarAlteracoes(ActionEvent e) {
        String nome = txtNome.getText();
        String especialidade = txtEspecialidade.getText();
        String disponibilidade = txtDisponibilidade.getText();

        // Atualizar o técnico existente
        tecnico.setNome(nome);
        tecnico.setEspecialidade(especialidade);
        tecnico.setDisponibilidade(disponibilidade);

        // Chamar o controller para atualizar o técnico
        tecnicoController.UpdateTecnico(posicao, tecnico);

        // Atualizar a tabela
        tableModel.setValueAt(tecnico.getNome(), posicao, 1);
        tableModel.setValueAt(tecnico.getEspecialidade(), posicao, 2);
        tableModel.setValueAt(tecnico.getDisponibilidade(), posicao, 3);

        // Fechar o diálogo
        dispose();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
