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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Api.MaquinaAPI;
import com.example.Api.TecnicoAPI;
import com.example.Controllers.FalhaController;
import com.example.Models.Falha;
import com.example.Models.Maquina;
import com.example.Models.Tecnico;

public class CadastroFalhaDialog extends JDialog {

    private final FalhaController falhaController;
    private final DefaultTableModel tableModel;

    private final JComboBox<Maquina> comboMaquina;
    private final JComboBox<Tecnico> comboTecnico; // Combo para selecionar técnicos
    private final JTextField txtData;
    private final JTextField txtProblema;
    private final JTextField txtPrioridade;

    public CadastroFalhaDialog(FalhaController falhaController, DefaultTableModel tableModel) {
        this.falhaController = falhaController;
        this.tableModel = tableModel;

        setTitle("Cadastrar Falha");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        // Combobox de Máquinas
        comboMaquina = new JComboBox<>(getMaquinas());
        panel.add(new JLabel("Máquina:"));
        panel.add(comboMaquina);
        
        // Combobox de Técnicos
        comboTecnico = new JComboBox<>(getTecnicos()); // Populando o combo com técnicos
        panel.add(new JLabel("Técnico:"));
        panel.add(comboTecnico);

        // Campo de Data
        txtData = new JTextField(10);
        panel.add(new JLabel("Data (dd/MM/yyyy):"));
        panel.add(txtData);
        
        // Campo de Problema
        txtProblema = new JTextField(10);
        panel.add(new JLabel("Problema:"));
        panel.add(txtProblema);
        
        // Campo de Prioridade
        txtPrioridade = new JTextField(10);
        panel.add(new JLabel("Prioridade:"));
        panel.add(txtPrioridade);

        add(panel, BorderLayout.CENTER);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarFalha);
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

    private void cadastrarFalha(ActionEvent e) {
        Maquina maquinaSelecionada = (Maquina) comboMaquina.getSelectedItem(); // Obtendo a máquina selecionada
        Tecnico tecnicoSelecionado = (Tecnico) comboTecnico.getSelectedItem(); // Obtendo o técnico selecionado
        String dataStr = txtData.getText();
        String problema = txtProblema.getText();
        String prioridade = txtPrioridade.getText();

        // Valida e converte a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Utilize o formato dd/MM/yyyy.");
            return;
        }

        // Cria nova falha
        Falha novaFalha = new Falha(0, maquinaSelecionada.getId(), data.toString(), problema, prioridade, tecnicoSelecionado.getNome()); // Usando o nome do técnico
        try {
            falhaController.CreateFalha(novaFalha);

            tableModel.addRow(new Object[]{
                novaFalha.getId(),
                novaFalha.getMaquinaId(),
                novaFalha.getData(),
                novaFalha.getProblema(),
                novaFalha.getPrioridade(),
                novaFalha.getOperador()
            });

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar falha: " + ex.getMessage());
        }
    }
}
