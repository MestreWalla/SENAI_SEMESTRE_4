package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Adicionado import
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
    private final JComboBox<Tecnico> comboTecnico; // Combo para selecionar técnicos

    private final JTextField txtMaquinaId;
    private final JTextField txtData;
    private final JTextField txtTipo;
    private final JTextField txtPecasTrocadas;
    private final JTextField txtTempoDeParada;
    private final JTextField txtTecnico;
    private final JTextField txtObservacoes;

    public CadastroManutencaoDialog(ManutencaoController manutencaoController, DefaultTableModel tableModel) {
        this.manutencaoController = manutencaoController;
        this.tableModel = tableModel;

        setTitle("Cadastrar Manutenção");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 2));

        // Combobox de Máquinas
        comboMaquina = new JComboBox<>(getMaquinas());
        panel.add(new JLabel("Máquina:"));
        panel.add(comboMaquina);

        // Combobox de Técnicos
        comboTecnico = new JComboBox<>(getTecnicos()); // Populando o combo com técnicos
        panel.add(new JLabel("Técnico:"));
        panel.add(comboTecnico);

        txtMaquinaId = new JTextField(10);
        txtData = new JTextField(10);
        txtTipo = new JTextField(10);
        txtPecasTrocadas = new JTextField(10);
        txtTempoDeParada = new JTextField(10);
        txtTecnico = new JTextField(10);
        txtObservacoes = new JTextField(10);

        panel.add(new JLabel("Máquina ID:"));
        panel.add(txtMaquinaId);
        panel.add(new JLabel("Data (dd/MM/yyyy):"));
        panel.add(txtData);
        panel.add(new JLabel("Tipo:"));
        panel.add(txtTipo);
        panel.add(new JLabel("Peças Trocadas:"));
        panel.add(txtPecasTrocadas);
        panel.add(new JLabel("Tempo de Parada (min):"));
        panel.add(txtTempoDeParada);
        panel.add(new JLabel("Técnico:"));
        panel.add(txtTecnico);
        panel.add(new JLabel("Observações:"));
        panel.add(txtObservacoes);

        add(panel, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarManutencao);
        add(btnCadastrar, BorderLayout.SOUTH);
    }

    private Maquina[] getMaquinas() {
        List<Maquina> maquinas = MaquinaAPI.getMaquinas();
        return maquinas.toArray(new Maquina[0]);
    }

    private Tecnico[] getTecnicos() {
        List<Tecnico> tecnicos = TecnicoAPI.getTecnicos();
        return tecnicos.toArray(new Tecnico[0]);
    }

    private void cadastrarManutencao(ActionEvent e) {
        Maquina maquinaSelecionada = (Maquina) comboMaquina.getSelectedItem(); // Obtendo a máquina selecionada
        Tecnico tecnicoSelecionado = (Tecnico) comboTecnico.getSelectedItem(); // Obtendo o técnico selecionado

        // String maquinaId = txtMaquinaId.getText();
        String dataStr = txtData.getText();
        String tipo = txtTipo.getText();
        String pecasTrocadas = txtPecasTrocadas.getText();
        int tempoDeParada;
        // String tecnico = txtTecnico.getText();
        String observacoes = txtObservacoes.getText();

        // Valida e converte a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato yyyy/MM/dd.");
            return;
        }

        // Tenta converter o tempo de parada
        try {
            tempoDeParada = Integer.parseInt(txtTempoDeParada.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tempo de Parada deve ser um número.");
            return;
        }

        // Cria nova manutenção
        Manutencao novoManutencao = new Manutencao(0, maquinaSelecionada.getNome(), data.toString(), tipo, pecasTrocadas, tempoDeParada, tecnicoSelecionado.getNome(), observacoes);
        try {
            manutencaoController.CreateManutencao(novoManutencao);

            tableModel.addRow(new Object[]{
                novoManutencao.getId(),
                novoManutencao.getMaquinaId(),
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
