package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Api.MaquinaAPI;
import com.example.Api.TecnicoAPI;
import com.example.Controllers.ManutencaoController;
import com.example.Models.Manutencao;
import com.example.Models.Maquina;
import com.example.Models.Tecnico;

public class CadastroManutencaoDialog extends JDialog {

    private final ManutencaoController manutencaoController;
    private final DefaultTableModel tableModel;

    private final JComboBox<Maquina> comboMaquina;
    private final JComboBox<Tecnico> comboTecnico;

    private final JTextField txtData;
    private final JTextField txtTipo;
    private final JTextField txtPecasTrocadas;
    private final JTextField txtTempoDeParada;
    private final JTextField txtObservacoes;

    public CadastroManutencaoDialog(ManutencaoController manutencaoController, DefaultTableModel tableModel) {
        this.manutencaoController = manutencaoController;
        this.tableModel = tableModel;

        setTitle("Cadastrar Manutenção");
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e ComboBox de Máquinas
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Máquina:"), gbc);
        gbc.gridx = 1;
        comboMaquina = new JComboBox<>(getMaquinas());
        panel.add(comboMaquina, gbc);

        // Label e ComboBox de Técnicos
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Técnico:"), gbc);
        gbc.gridx = 1;
        comboTecnico = new JComboBox<>(getTecnicos());
        panel.add(comboTecnico, gbc);

        // Label e Campo de Texto para Máquina ID
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridx = 1;

        // Label e Campo de Texto para Data
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Data (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        txtData = new JTextField(10);
        panel.add(txtData, gbc);

        // Label e Campo de Texto para Tipo
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        txtTipo = new JTextField(10);
        panel.add(txtTipo, gbc);

        // Label e Campo de Texto para Peças Trocadas
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Peças Trocadas:"), gbc);
        gbc.gridx = 1;
        txtPecasTrocadas = new JTextField(10);
        panel.add(txtPecasTrocadas, gbc);

        // Label e Campo de Texto para Tempo de Parada
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Tempo de Parada (min):"), gbc);
        gbc.gridx = 1;
        txtTempoDeParada = new JTextField(10);
        panel.add(txtTempoDeParada, gbc);

        // Label e Campo de Texto para Observações
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Observações:"), gbc);
        gbc.gridx = 1;
        txtObservacoes = new JTextField(10);
        panel.add(txtObservacoes, gbc);

        add(panel, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarManutencao);
        add(btnCadastrar, BorderLayout.SOUTH);
    }

    private Maquina[] getMaquinas() {
        List<Maquina> maquinas = MaquinaAPI.getMaquinas();
        return maquinas.toArray(Maquina[]::new);
    }

    private Tecnico[] getTecnicos() {
        List<Tecnico> tecnicos = TecnicoAPI.getTecnicos();
        return tecnicos.toArray(Tecnico[]::new);
    }

    private void cadastrarManutencao(ActionEvent e) {
        Maquina maquinaSelecionada = (Maquina) comboMaquina.getSelectedItem();
        Tecnico tecnicoSelecionado = (Tecnico) comboTecnico.getSelectedItem();
    
        String dataStr = txtData.getText();
        String tipo = txtTipo.getText();
        String pecasTrocadas = txtPecasTrocadas.getText();
        int tempoDeParada;
        String observacoes = txtObservacoes.getText();
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato dd/MM/yyyy.");
            return;
        }
    
        try {
            tempoDeParada = Integer.parseInt(txtTempoDeParada.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tempo de Parada deve ser um número.");
            return;
        }
    
        // Altere para armazenar o ID da máquina como int
        Manutencao novoManutencao = new Manutencao(
            0, 
            maquinaSelecionada.getId(), // Agora o ID da máquina será armazenado
            data.toString(), 
            tipo, 
            pecasTrocadas, 
            tempoDeParada, 
            tecnicoSelecionado.getNome(), 
            observacoes
        );
        
        try {
            manutencaoController.CreateManutencao(novoManutencao);
    
            tableModel.addRow(new Object[]{
                novoManutencao.getId(),
                novoManutencao.getData(),
                novoManutencao.getTipo(),
                novoManutencao.getPecasTrocadas(),
                novoManutencao.getTempoDeParada(),
                novoManutencao.getTecnico(),
                novoManutencao.getObservacoes()
            });
    
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar manutenção: " + ex.getMessage());
        }
    }
    
}
