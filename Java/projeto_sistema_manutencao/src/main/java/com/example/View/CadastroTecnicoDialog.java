package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.TecnicoController;
import com.example.Models.Tecnico;

public class CadastroTecnicoDialog extends JDialog {

    private final TecnicoController tecnicoController;
    private final DefaultTableModel tableModel;

    private final JTextField txtNome;
    private final JTextField txtEspecialidade;
    private final JTextField txtDisponibilidade;

    public CadastroTecnicoDialog(TecnicoController tecnicoController, DefaultTableModel tableModel) {
        this.tecnicoController = tecnicoController;
        this.tableModel = tableModel;

        setTitle("Cadastrar Técnico");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        txtNome = new JTextField(10);
        txtEspecialidade = new JTextField(10);
        txtDisponibilidade = new JTextField(10);

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Especialidade:"));
        panel.add(txtEspecialidade);
        panel.add(new JLabel("Disponibilidade:"));
        panel.add(txtDisponibilidade);

        add(panel, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarTecnico);
        add(btnCadastrar, BorderLayout.SOUTH);
    }

    private void cadastrarTecnico(ActionEvent e) {
        String nome = txtNome.getText();
        String especialidade = txtEspecialidade.getText();
        String disponibilidade = txtDisponibilidade.getText();

        Tecnico novoTecnico = new Tecnico(0, nome, especialidade, disponibilidade);
        tecnicoController.CreateTecnico(novoTecnico);

        tableModel.addRow(new Object[]{
            novoTecnico.getId(),
            novoTecnico.getNome(),
            novoTecnico.getEspecialidade(),
            novoTecnico.getDisponibilidade()
        });

        dispose();
    }
}
