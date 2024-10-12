package com.example.View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        setLayout(new FlowLayout());

        // Inicializa os campos de entrada
        txtCodigo = new JTextField(10);
        txtNome = new JTextField(10);
        txtFabricante = new JTextField(10);
        txtModelo = new JTextField(10);
        txtDetalhes = new JTextField(10);
        txtLocalizacao = new JTextField(10);
        txtTempoVidaEstimado = new JTextField(10);
        txtDataAquisicao = new JTextField(10);

        // Adiciona labels e campos ao dialog
        add(new JLabel("Código:"));
        add(txtCodigo);
        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Fabricante:"));
        add(txtFabricante);
        add(new JLabel("Modelo:"));
        add(txtModelo);
        add(new JLabel("Detalhes:"));
        add(txtDetalhes);
        add(new JLabel("Localização:"));
        add(txtLocalizacao);
        add(new JLabel("Tempo de Vida Estimado:"));
        add(txtTempoVidaEstimado);
        add(new JLabel("Data de Aquisição (dd-MM-yyyy):"));
        add(txtDataAquisicao);

        // Botão para cadastrar a máquina
        JButton btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);

        btnCadastrar.addActionListener((ActionEvent e) -> {
            cadastrarMaquina();
        });
    }

    private void cadastrarMaquina() {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tempo de vida estimado deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Validação da data de aquisição
        LocalDate dataAquisicao;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Atualiza para o formato dd/MM/yyyy
            dataAquisicao = LocalDate.parse(txtDataAquisicao.getText(), formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use: dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE); // Atualiza mensagem de erro
            return;
        }
    
        // Criar a nova máquina
        Maquina novaMaquina = new Maquina(0, codigo, nome, modelo, fabricante, dataAquisicao, tempoVidaEstimado, localizacao, detalhes, ""); // Adiciona um valor vazio para manual
    
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
    
}
