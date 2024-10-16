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

import com.example.Controllers.ManutencaoController;
import com.example.Models.Manutencao;

public class EditarManutencaoDialog extends JDialog {

    private final ManutencaoController manutencaoController;
    private final DefaultTableModel tableModel;
    private final Manutencao manutencao;
    private final int posicao;

    // Campos de entrada
    private final JTextField txtMaquinaId;
    private final JTextField txtData;
    private final JTextField txtTipo;
    private final JTextField txtPecasTrocadas;
    private final JTextField txtTempoDeParada;
    private final JTextField txtTecnico;
    private final JTextField txtObservacoes;

    public EditarManutencaoDialog(ManutencaoController manutencaoController, DefaultTableModel tableModel, Manutencao manutencao, int posicao) {
        this.manutencaoController = manutencaoController;
        this.tableModel = tableModel;
        this.manutencao = manutencao;
        this.posicao = posicao;

        setTitle("Editar Técnico");
        setModal(true);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Cria um painel com padding
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializa os campos de entrada com os dados do técnico
        txtMaquinaId = new JTextField(manutencao.getMaquinaId());
        txtData = new JTextField(manutencao.getData());
        txtTipo = new JTextField(manutencao.getTipo());
        txtPecasTrocadas = new JTextField(manutencao.getPecasTrocadas());
        txtTempoDeParada = new JTextField(manutencao.getTempoDeParada());
        txtTecnico = new JTextField(manutencao.getTecnico());
        txtObservacoes = new JTextField(manutencao.getObservacoes());

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
         // Adiciona os campos de entrada ao painel
         panel.add(new JLabel("Maquina Id:"));
         panel.add(txtMaquinaId);
         panel.add(new JLabel("Data:"));
         panel.add(txtData);
         panel.add(new JLabel("Tipo:"));
         panel.add(txtTipo);
         panel.add(new JLabel("Pecas Trocadas:"));
         panel.add(txtPecasTrocadas);
         panel.add(new JLabel("Tempo de Parada:"));
         panel.add(txtTempoDeParada);
         panel.add(new JLabel("Técnico:"));
         panel.add(txtTecnico);
         panel.add(new JLabel("Observações:"));
         panel.add(txtObservacoes);
    }

    private void salvarAlteracoes(ActionEvent e) {
        String maquinaId = txtMaquinaId.getText();
        String data = txtData.getText();
        String tipo = txtTipo.getText();
        String pecasTrocadas = txtPecasTrocadas.getText();
        int tempoDeParada = txtTempoDeParada.getText().isEmpty() ? 0 : Integer.parseInt(txtTempoDeParada.getText());
        String tecnico = txtTecnico.getText();
        String observacoes = txtObservacoes.getText();

        // Atualizar o técnico existente
        manutencao.setMaquinaId(maquinaId);
        manutencao.setData(data);
        manutencao.setTipo(tipo);
        manutencao.setPecasTrocadas(pecasTrocadas);
        manutencao.setTempoDeParada(tempoDeParada);
        manutencao.setTecnico(tecnico);
        manutencao.setObservacoes(observacoes);

        // Chamar o controller para atualizar o técnico
        manutencaoController.UpdateManutencao(posicao, manutencao);

        // Atualizar a tabela
        tableModel.setValueAt(manutencao.getMaquinaId(), posicao, 0);
        tableModel.setValueAt(manutencao.getData(), posicao, 1);
        tableModel.setValueAt(manutencao.getTipo(), posicao, 2);
        tableModel.setValueAt(manutencao.getPecasTrocadas(), posicao, 3);
        tableModel.setValueAt(manutencao.getTempoDeParada(), posicao, 4);
        tableModel.setValueAt(manutencao.getTecnico(), posicao, 5);
        tableModel.setValueAt(manutencao.getObservacoes(), posicao, 6);

        // Fechar o diálogo
        dispose();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
