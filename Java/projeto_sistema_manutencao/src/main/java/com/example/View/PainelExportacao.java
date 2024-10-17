package com.example.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Controllers.MaquinaController;
import com.example.Models.Maquina;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

public class PainelExportacao extends JPanel {

    private final MaquinaController maquinaController;
    private final JTable maquinasTable;
    private final DefaultTableModel tableModel;
    private final JButton btnEditarMaquina;
    private final JButton btnCadastrarMaquina;
    private final JButton btnExcluirMaquina;
    private final JButton btnAtualizarLista;
    private final JButton btnExportarPDF; // Botão para exportar para PDF

    // Construtor
    public PainelExportacao() {
        super(new BorderLayout());
        maquinaController = new MaquinaController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Código", "Nome", "Fabricante", "Modelo", "Data de Aquisição", "Tempo de Vida Estimado", "Localização", "Detalhes"
        }, 0);
        maquinasTable = new JTable(tableModel);

        // Criar a tabela inicial
        atualizarLista();

        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Cria os botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCadastrarMaquina = new JButton("Cadastrar Máquina");
        btnEditarMaquina = new JButton("Editar Máquina");
        btnExcluirMaquina = new JButton("Excluir Máquina");
        btnAtualizarLista = new JButton("Atualizar Lista");
        btnExportarPDF = new JButton("Exportar para PDF"); // Botão de exportação

        painelInferior.add(btnCadastrarMaquina);
        painelInferior.add(btnEditarMaquina);
        painelInferior.add(btnExcluirMaquina);
        painelInferior.add(btnAtualizarLista);
        painelInferior.add(btnExportarPDF); // Adicionando o botão ao painel
        this.add(painelInferior, BorderLayout.SOUTH);

        // Adiciona o listener para o botão de cadastrar
        btnCadastrarMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioCadastro();
            }
        });

        // Adiciona o listener para o botão de editar
        btnEditarMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = maquinasTable.getSelectedRow();
                if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(PainelExportacao.this, "Selecione uma máquina para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                abrirEditarMaquinaDialog(linhaSelecionada);
            }
        });

        // Adiciona o listener para o botão de excluir
        btnExcluirMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirMaquinaSelecionada();
            }
        });

        // Adiciona o listener para o botão de atualizar lista
        btnAtualizarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLista();
            }
        });

        // Adiciona o listener para o botão de exportar para PDF
        btnExportarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarParaPDF();
            }
        });
    }

    private void abrirFormularioCadastro() {
        CadastroMaquinaDialog dialog = new CadastroMaquinaDialog(maquinaController, tableModel);
        dialog.setVisible(true); // Exibe o dialog
    }

    private void abrirEditarMaquinaDialog(int posicao) {
        Maquina maquinaSelecionada = maquinaController.ReadMaquina().get(posicao);
        EditarMaquinaDialog dialog = new EditarMaquinaDialog(maquinaController, tableModel, maquinaSelecionada, posicao);
        dialog.setVisible(true);
    }

    private void excluirMaquinaSelecionada() {
        int linhaSelecionada = maquinasTable.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma máquina para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mensagem de confirmação
        int resposta = JOptionPane.showConfirmDialog(this,
                "Você tem certeza que deseja excluir esta máquina?",
                "Confirmação de Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            int id = (int) tableModel.getValueAt(linhaSelecionada, 0); // Obtém o ID da máquina selecionada

            try {
                // Chama o método do controlador para excluir a máquina
                maquinaController.DeleteMaquina(id);
                // Remove a linha da tabela
                tableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(this, "Máquina excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir máquina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Se o usuário cancelar, não faz nada
            JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelamento", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarLista() {
        // Limpa a tabela existente
        tableModel.setRowCount(0);

        // Obtém a lista atualizada de máquinas
        List<Maquina> maquinas = maquinaController.ReadMaquina();

        // Adiciona as máquinas à tabela
        for (Maquina maquina : maquinas) {
            tableModel.addRow(new Object[]{
                maquina.getId(),
                maquina.getCodigo(),
                maquina.getNome(),
                maquina.getFabricante(),
                maquina.getModelo(),
                maquina.getDataAquisicao(),
                maquina.getTempoVidaEstimado(),
                maquina.getLocalizacao(),
                maquina.getDetalhes()
            });
        }
    }

    private void exportarParaPDF() {
        try {
            // Criar o documento PDF
            PdfWriter writer = new PdfWriter(new FileOutputStream("maquinas.pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Adiciona título
            document.add(new Paragraph("Lista de Máquinas").setFontSize(20).setBold());

            // Cria a tabela
            Table table = new Table(new float[]{1, 2, 2, 2, 2, 2, 2, 2, 2}); // 9 colunas
            table.setWidth(500);

            // Adiciona cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Código")));
            table.addHeaderCell(new Cell().add(new Paragraph("Nome")));
            table.addHeaderCell(new Cell().add(new Paragraph("Fabricante")));
            table.addHeaderCell(new Cell().add(new Paragraph("Modelo")));
            table.addHeaderCell(new Cell().add(new Paragraph("Data de Aquisição")));
            table.addHeaderCell(new Cell().add(new Paragraph("Tempo de Vida Estimado")));
            table.addHeaderCell(new Cell().add(new Paragraph("Localização")));
            table.addHeaderCell(new Cell().add(new Paragraph("Detalhes")));

            // Adiciona dados da tabela
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    String value = String.valueOf(tableModel.getValueAt(i, j));
                    table.addCell(new Cell().add(new Paragraph(value))); // Certifique-se de que esta linha esteja correta
                }
            }

            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(this, "PDF gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
