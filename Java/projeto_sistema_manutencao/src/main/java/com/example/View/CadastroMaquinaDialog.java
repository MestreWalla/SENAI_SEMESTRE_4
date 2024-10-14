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

public class CadastroMaquinaDialog extends JDialog {

    private final MaquinaController maquinaController;
    private final DefaultTableModel tableModel;

    // Campos de entrada
    private final JTextField txtCodigo;
    private final JTextField txtNome;
    private final JTextField txtFabricante;
    private final JTextField txtModelo;
    private final JTextField txtDetalhes;
    private final JTextField txtLocalizacao;
    private final JTextField txtTempoVidaEstimado;
    private final JTextField txtDataAquisicao;

    public CadastroMaquinaDialog(MaquinaController maquinaController, DefaultTableModel tableModel) {
        this.maquinaController = maquinaController;
        this.tableModel = tableModel;

        setTitle("Cadastrar Máquina");
        setModal(true);
        setSize(400, 400);
        setLayout(new BorderLayout()); // Usar BorderLayout para adicionar padding

        // Cria um painel com padding
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding: top, left, bottom, right

        // Inicializa os campos de entrada
        txtCodigo = new JTextField(10);
        txtNome = new JTextField(10);
        txtFabricante = new JTextField(10);
        txtModelo = new JTextField(10);
        txtDetalhes = new JTextField(10);
        txtLocalizacao = new JTextField(10);
        txtTempoVidaEstimado = new JTextField(10);
        txtDataAquisicao = new JTextField(10);

        // Configura os componentes no diálogo
        setupComponents(panel);

        // Adiciona o painel ao diálogo
        add(panel, BorderLayout.CENTER);

        // Botão para cadastrar a máquina
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarMaquina);
        add(btnCadastrar, BorderLayout.SOUTH); // Adiciona o botão na parte inferior
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

    private void cadastrarMaquina(ActionEvent e) {
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

        // Criar a nova máquina
        Maquina novaMaquina = new Maquina("0", codigo, nome, modelo, fabricante, dataAquisicao, tempoVidaEstimado, localizacao, detalhes, "");

        // Chamar o controller para salvar a máquina
        maquinaController.CreateMaquina(novaMaquina);

        // Atualizar a tabela
        tableModel.addRow(new Object[]{
            novaMaquina.getId(),
            novaMaquina.getCodigo(),
            novaMaquina.getNome(),
            novaMaquina.getFabricante(),
            novaMaquina.getModelo(),
            novaMaquina.getDataAquisicao(),
            novaMaquina.getTempoVidaEstimado(),
            novaMaquina.getLocalizacao(),
            novaMaquina.getDetalhes()
        });

        // Limpar campos do formulário
        dispose();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
