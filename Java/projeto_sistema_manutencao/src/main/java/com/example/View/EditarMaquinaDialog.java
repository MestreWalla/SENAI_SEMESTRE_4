package com.example.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.MaquinaController;
import com.example.Models.Maquina;

public class EditarMaquinaDialog extends JDialog {

    private final MaquinaController maquinaController;
    private final DefaultTableModel tableModel;
    private final Maquina maquina; // A máquina a ser editada
    private final int posicao; // Posição da máquina na tabela

    // Campos de entrada
    private final JTextField txtCodigo;
    private final JTextField txtNome;
    private final JTextField txtFabricante;
    private final JTextField txtModelo;
    private final JTextField txtDetalhes;
    private final JTextField txtLocalizacao;
    private final JTextField txtTempoVidaEstimado;
    private final JTextField txtDataAquisicao;

    public EditarMaquinaDialog(MaquinaController maquinaController, DefaultTableModel tableModel, Maquina maquina, int posicao) {
        this.maquinaController = maquinaController;
        this.tableModel = tableModel;
        this.maquina = maquina; // Máquina a ser editada
        this.posicao = posicao; // Posição da máquina na tabela

        setTitle("Editar Máquina");
        setModal(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cria um painel com padding
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializa os campos de entrada
        txtCodigo = new JTextField(maquina.getCodigo());
        txtNome = new JTextField(maquina.getNome());
        txtFabricante = new JTextField(maquina.getFabricante());
        txtModelo = new JTextField(maquina.getModelo());
        txtDetalhes = new JTextField(maquina.getDetalhes());
        txtLocalizacao = new JTextField(maquina.getLocalizacao());
        txtTempoVidaEstimado = new JTextField(String.valueOf(maquina.getTempoVidaEstimado()));
        txtDataAquisicao = new JTextField(maquina.getDataAquisicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

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
        panel.add(new JLabel("Código:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Fabricante:"));
        panel.add(txtFabricante);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Detalhes:"));
        panel.add(txtDetalhes);
        panel.add(new JLabel("Localização:"));
        panel.add(txtLocalizacao);
        panel.add(new JLabel("Tempo de Vida Estimado:"));
        panel.add(txtTempoVidaEstimado);
        panel.add(new JLabel("Data de Aquisição (dd/MM/yyyy):"));
        panel.add(txtDataAquisicao);
    }

    private void salvarAlteracoes(ActionEvent e) {
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String fabricante = txtFabricante.getText();
        String modelo = txtModelo.getText();
        String detalhes = txtDetalhes.getText();
        String localizacao = txtLocalizacao.getText();
        long tempoVidaEstimado;

        // Validação do tempo de vida estimado
        try {
            tempoVidaEstimado = Long.parseLong(txtTempoVidaEstimado.getText());
        } catch (NumberFormatException ex) {
            showErrorDialog("Tempo de vida estimado deve ser um número.");
            return;
        }

        // Validação da data de aquisição
        LocalDate dataAquisicao;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataAquisicao = LocalDate.parse(txtDataAquisicao.getText(), formatter);
        } catch (Exception ex) {
            showErrorDialog("Formato de data inválido. Use: dd/MM/yyyy");
            return;
        }

        // Atualizar a máquina existente
        maquina.setCodigo(codigo);
        maquina.setNome(nome);
        maquina.setFabricante(fabricante);
        maquina.setModelo(modelo);
        maquina.setDetalhes(detalhes);
        maquina.setLocalizacao(localizacao);
        maquina.setTempoVidaEstimado(tempoVidaEstimado);
        maquina.setDataAquisicao(dataAquisicao);

        // Chamar o controller para atualizar a máquina
        maquinaController.UpdateMaquina(posicao, maquina); // Use a posição correta aqui

        // Atualizar a tabela
        tableModel.setValueAt(maquina.getCodigo(), posicao, 1);
        tableModel.setValueAt(maquina.getNome(), posicao, 2);
        tableModel.setValueAt(maquina.getFabricante(), posicao, 3);
        tableModel.setValueAt(maquina.getModelo(), posicao, 4);
        tableModel.setValueAt(maquina.getDataAquisicao(), posicao, 5);
        tableModel.setValueAt(maquina.getTempoVidaEstimado(), posicao, 6);
        tableModel.setValueAt(maquina.getLocalizacao(), posicao, 7);
        tableModel.setValueAt(maquina.getDetalhes(), posicao, 8);

        // Fechar o diálogo
        dispose();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
