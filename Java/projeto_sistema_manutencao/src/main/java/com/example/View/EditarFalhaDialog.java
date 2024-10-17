package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Api.TecnicoAPI; // Certifique-se de que esta importação esteja correta
import com.example.Controllers.FalhaController;
import com.example.Models.Falha;
import com.example.Models.Tecnico;

public class EditarFalhaDialog extends JDialog {

    private final FalhaController falhaController;
    private final DefaultTableModel tableModel;
    private final Falha falha; // A falha a ser editada
    private final int posicao; // Posição da falha na tabela

    // Campos de entrada
    private final JTextField txtMaquinaId;
    private final JTextField txtData;
    private final JTextField txtProblema;
    private final JTextField txtPrioridade;
    private final JComboBox<Tecnico> comboOperador; // ComboBox para o operador

    public EditarFalhaDialog(FalhaController falhaController, DefaultTableModel tableModel, Falha falha, int posicao) {
        this.falhaController = falhaController;
        this.tableModel = tableModel;
        this.falha = falha; // Falha a ser editada
        this.posicao = posicao; // Posição da falha na tabela

        setTitle("Editar Falha");
        setModal(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cria um painel com padding
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializa os campos de entrada
        txtMaquinaId = new JTextField(String.valueOf(falha.getMaquinaId()));
        txtData = new JTextField(falha.getData());
        txtProblema = new JTextField(falha.getProblema());
        txtPrioridade = new JTextField(falha.getPrioridade());
        
        // ComboBox para selecionar o operador
        comboOperador = new JComboBox<>(getTecnicos());
        comboOperador.setSelectedItem(getTecnicoByName(falha.getOperador())); // Seleciona o operador atual

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
        panel.add(new JLabel("Máquina ID:"));
        panel.add(txtMaquinaId);
        panel.add(new JLabel("Data (dd/MM/yyyy):"));
        panel.add(txtData);
        panel.add(new JLabel("Problema:"));
        panel.add(txtProblema);
        panel.add(new JLabel("Prioridade:"));
        panel.add(txtPrioridade);
        panel.add(new JLabel("Operador:")); // Label do operador
        panel.add(comboOperador); // ComboBox do operador
    }

    private Tecnico[] getTecnicos() {
        return TecnicoAPI.getTecnicos().toArray(new Tecnico[0]); // Obtém a lista de técnicos
    }

    private Tecnico getTecnicoByName(String nome) {
        // Busca técnico pelo nome
        for (Tecnico tecnico : getTecnicos()) {
            if (tecnico.getNome().equals(nome)) {
                return tecnico;
            }
        }
        return null; // Retorna null se não encontrar
    }

    private void salvarAlteracoes(ActionEvent e) {
        String maquinaIdStr = txtMaquinaId.getText();
        String dataStr = txtData.getText();
        String problema = txtProblema.getText();
        String prioridade = txtPrioridade.getText();
        Tecnico operadorSelecionado = (Tecnico) comboOperador.getSelectedItem(); // Obtém o técnico selecionado

        // Validação do ID da máquina
        int maquinaId;
        try {
            maquinaId = Integer.parseInt(maquinaIdStr);
        } catch (NumberFormatException ex) {
            showErrorDialog("Máquina ID deve ser um número.");
            return;
        }

        // Validação da data de falha
        String data;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dataStr, formatter);
            data = date.toString();
        } catch (Exception ex) {
            showErrorDialog("Formato de data inválido. Use: dd/MM/yyyy");
            return;
        }

        // Atualizar a falha existente
        falha.setMaquinaId(maquinaId);
        falha.setData(data);
        falha.setProblema(problema);
        falha.setPrioridade(prioridade);
        falha.setOperador(operadorSelecionado.getNome()); // Usa o nome do técnico selecionado

        // Chamar o controller para atualizar a falha
        falhaController.UpdateFalha(posicao, falha); // Use a posição correta aqui

        // Atualizar a tabela
        tableModel.setValueAt(falha.getMaquinaId(), posicao, 1);
        tableModel.setValueAt(falha.getData(), posicao, 2);
        tableModel.setValueAt(falha.getProblema(), posicao, 3);
        tableModel.setValueAt(falha.getPrioridade(), posicao, 4);
        tableModel.setValueAt(falha.getOperador(), posicao, 5);

        // Fechar o diálogo
        dispose();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
