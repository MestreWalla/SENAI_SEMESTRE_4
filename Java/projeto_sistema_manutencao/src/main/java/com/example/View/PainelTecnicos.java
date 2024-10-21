package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
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

import com.example.Controllers.TecnicoController;
import com.example.Models.Tecnico;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class PainelTecnicos extends JPanel {

    private final TecnicoController tecnicoController; // Controller do Técnico
    private final JTable tecnicosTable; // Tabela de Técnicos
    private final DefaultTableModel tableModel; // Modelo da tabela de Técnicos
    private final JButton btnCadastrarTecnico; // Botão de cadastrar um técnico
    private final JButton btnEditarTecnico; // Botão de editar um técnico
    private final JButton btnExcluirTecnico; // Botão de excluir um técnico
    private final JButton btnAtualizarLista; // Botão de atualizar a lista de tecnicos
    private final JButton btnExportarPDF; // Botão de exportar para PDF

    @SuppressWarnings("Convert2Lambda")
    public PainelTecnicos() {
        super(new BorderLayout());
        tecnicoController = new TecnicoController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nome", "Especialidade", "Disponibilidade"
        }, 0);
        tecnicosTable = new JTable(tableModel);
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(tecnicosTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarTecnico = new JButton("Cadastrar Técnico");
        btnEditarTecnico = new JButton("Editar Técnico");
        btnExcluirTecnico = new JButton("Excluir Técnico");
        btnAtualizarLista = new JButton("Atualizar Lista");
        btnExportarPDF = new JButton("Exportar para PDF"); // Adicionando o botão ao painel

        painelInferior.add(btnCadastrarTecnico);
        painelInferior.add(btnEditarTecnico);
        painelInferior.add(btnExcluirTecnico);
        painelInferior.add(btnAtualizarLista);
        painelInferior.add(btnExportarPDF); // Adicionando o botão ao painel
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adicionando os listeners aos botões
        btnCadastrarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        btnEditarTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tecnicosTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelTecnicos.this, "Selecione um técnico para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abrirEditarTecnicoDialog(linhaSelecionada);
            }
        });

        btnExcluirTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTecnicoSelecionado();
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
        CadastroTecnicoDialog dialog = new CadastroTecnicoDialog(tecnicoController, tableModel);
        dialog.setVisible(true);
    }

    private void abrirEditarTecnicoDialog(int posicao) {
        Tecnico tecnicoSelecionado = tecnicoController.ReadTecnico().get(posicao);
        EditarTecnicoDialog dialog = new EditarTecnicoDialog(tecnicoController, tableModel, tecnicoSelecionado, posicao);
        dialog.setVisible(true);
    }

    private void excluirTecnicoSelecionado() {
        int linhaSelecionada = tecnicosTable.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um técnico para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(linhaSelecionada, 0);
        tecnicoController.DeleteTecnico(id);
        tableModel.removeRow(linhaSelecionada);
    }

    private void atualizarLista() {
        tableModel.setRowCount(0);
        List<Tecnico> tecnicos = tecnicoController.ReadTecnico();
        for (Tecnico tecnico : tecnicos) {
            tableModel.addRow(new Object[]{
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEspecialidade(),
                tecnico.getDisponibilidade()
            });
        }
    }

    private void exportarParaPDF() {
        try {
            // Criar o documento PDF
            PdfWriter writer = new PdfWriter(new FileOutputStream("tecnicos.pdf")); // Arquivo de saída do PDF
            PdfDocument pdfDoc = new PdfDocument(writer); // Criando o documento PDF
            // Adiciona título
            try (Document document = new Document(pdfDoc)) {
                // Adiciona título
                document.add(new Paragraph("Lista de Tecnicos").setFontSize(20).setBold());
                
                // Cria a tabela
                Table table = new Table(new float[]{1, 2, 2, 2}); // Criando a tabela com 4 colunas
                table.setWidth(500);
                
                // Adiciona cabeçalhos
                table.addHeaderCell(new Cell().add(new Paragraph("ID")));
                table.addHeaderCell(new Cell().add(new Paragraph("Nome")));
                table.addHeaderCell(new Cell().add(new Paragraph("Especialidade")));
                table.addHeaderCell(new Cell().add(new Paragraph("Disponibilidade")));

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
