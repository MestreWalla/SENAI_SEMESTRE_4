package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.ManutencaoController;
import com.example.Models.Manutencao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.awt.HeadlessException;

public class PainelManutencoes extends JPanel {

    private final ManutencaoController manutencaoController; // Controlador de manutenções
    private final JTable manutencoesTable; // Tabela de manutenções
    private final DefaultTableModel tableModel; // Modelo da tabela de manutenções
    private final JButton btnCadastrarManutencao; // Botão de cadastrar manutenção
    private final JButton btnEditarManutencao; // Botão de editar manutenção
    private final JButton btnExcluirManutencao; // Botão de excluir manutenção
    private final JButton btnAtualizarLista; // Botão de atualizar lista
    private final JButton btnExportarPDF; // Botão de exportar para PDF

    @SuppressWarnings("Convert2Lambda")
    public PainelManutencoes() {
        super(new BorderLayout()); // Painel de manutenções
        manutencaoController = new ManutencaoController(); // Instanciando o controlador de manutenções
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Data", "Tipo", "Pecas", "Tempo de Parada", "Técnico", "Observações"
        }, 0); // Criando o modelo da tabela
        manutencoesTable = new JTable(tableModel); // Criando a tabela
        atualizarLista(); // Atualiza a lista de manutenções no painel

        JScrollPane scrollPane = new JScrollPane(manutencoesTable); // Adicionando a barra de rolagem
        this.add(scrollPane, BorderLayout.CENTER); // Adicionando a tabela ao painel

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Painel inferior
        btnCadastrarManutencao = new JButton("Cadastrar manutenção"); // Adicionando o botão ao painel
        btnEditarManutencao = new JButton("Editar manutenção"); // Adicionando o botão ao painel
        btnExcluirManutencao = new JButton("Excluir manutenção"); // Adicionando o botão ao painel
        btnAtualizarLista = new JButton("Atualizar Lista"); // Adicionando o botão ao painel
        btnExportarPDF = new JButton("Exportar para PDF"); // Adicionando o botão ao painel

        painelInferior.add(btnCadastrarManutencao); // Adicionando o botão ao painel
        painelInferior.add(btnEditarManutencao); // Adicionando o botão ao painel
        painelInferior.add(btnExcluirManutencao); // Adicionando o botão ao painel
        painelInferior.add(btnAtualizarLista); // Adicionando o botão ao painel
        painelInferior.add(btnExportarPDF); // Adicionando o botão ao painel
        this.add(painelInferior, BorderLayout.SOUTH); // Adicionando o painel inferior ao painel

        // Adicionando os listeners aos botões

        btnCadastrarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        btnEditarManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = manutencoesTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelManutencoes.this, "Selecione uma manutenção para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abrirEditarManutencaoDialog(linhaSelecionada);
            }
        });

        btnExcluirManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirManutencaoSelecionado();
            }
        });

        btnAtualizarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLista();
            }
        });

        // Adiciona o listener para o botão de exportar para PDF
        btnExportarPDF.addActionListener((ActionEvent e) -> {
            exportarParaPDF();
        });
    }

    // Métodos privados
    private void abrirFormularioCadastro() {
        CadastroManutencaoDialog dialog = new CadastroManutencaoDialog(manutencaoController, tableModel);
        dialog.setVisible(true);
    }

    private void abrirEditarManutencaoDialog(int posicao) {
        Manutencao manutencaoSelecionado = manutencaoController.ReadManutencao().get(posicao);
        EditarManutencaoDialog dialog = new EditarManutencaoDialog(manutencaoController, tableModel, manutencaoSelecionado, posicao);
        dialog.setVisible(true);
    }

    @SuppressWarnings("UseSpecificCatch")
    private void excluirManutencaoSelecionado() {
    int linhaSelecionada = manutencoesTable.getSelectedRow();
    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma manutenção para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Mensagem de confirmação
    int resposta = JOptionPane.showConfirmDialog(this,
            "Você tem certeza que deseja excluir esta manutenção?",
            "Confirmação de Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    if (resposta == JOptionPane.YES_OPTION) {
        Object valor = tableModel.getValueAt(linhaSelecionada, 0); // Obtém o valor da célula
        int id;

        // Verifica se o valor é uma String e faz a conversão para int
        if (valor instanceof String) {
            try {
                id = Integer.parseInt((String) valor);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido: não é um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (valor instanceof Integer) {
            id = (Integer) valor; // Se o valor já for Integer
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chama o método do controlador para excluir a manutenção
            manutencaoController.DeleteManutencao(id);
            // Remove a linha da tabela
            tableModel.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Manutenção excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir manutenção: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Se o usuário cancelar, não faz nada
        JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelamento", JOptionPane.INFORMATION_MESSAGE);
    }
}

    // Método para atualizar a lista de manutenções
    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Manutencao> manutencoes = manutencaoController.ReadManutencao();
        for (Manutencao manutencao : manutencoes) {
            tableModel.addRow(new Object[]{
                manutencao.getId(),
                manutencao.getMaquinaId(),
                manutencao.getData(),
                manutencao.getTipo(),
                manutencao.getPecasTrocadas(),
                manutencao.getTempoDeParada(),
                manutencao.getTecnico(),
                manutencao.getObservacoes()
            });
        }
    }

    private void exportarParaPDF() {
        try {
            // Criar o documento PDF
            PdfWriter writer = new PdfWriter(new FileOutputStream("manutencoes.pdf")); // Arquivo de saída do PDF
            PdfDocument pdfDoc = new PdfDocument(writer); // Criando o documento PDF
            // Adiciona título
            try (Document document = new Document(pdfDoc)) {
                // Adiciona título
                document.add(new Paragraph("Lista de Manutenções").setFontSize(20).setBold());
                
                // Cria a tabela
                Table table = new Table(new float[]{1, 2, 2, 2, 2, 2, 2, 2}); // 9 colunas
                table.setWidth(500);
                
                // Adiciona cabeçalhos
                table.addHeaderCell(new Cell().add(new Paragraph("ID")));
                table.addHeaderCell(new Cell().add(new Paragraph("Id da Máquina")));
                table.addHeaderCell(new Cell().add(new Paragraph("data")));
                table.addHeaderCell(new Cell().add(new Paragraph("tipo")));
                table.addHeaderCell(new Cell().add(new Paragraph("Peças Trocadas")));
                table.addHeaderCell(new Cell().add(new Paragraph("Tempo de Parada")));
                table.addHeaderCell(new Cell().add(new Paragraph("Técnico")));
                table.addHeaderCell(new Cell().add(new Paragraph("Observações")));

                // Adiciona dados da tabela
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        String value = String.valueOf(tableModel.getValueAt(i, j));
                        table.addCell(new Cell().add(new Paragraph(value)));
                    }
                }
                
                document.add(table);
            }
            JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
