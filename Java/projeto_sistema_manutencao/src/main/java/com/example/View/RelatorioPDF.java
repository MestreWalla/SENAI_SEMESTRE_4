package com.example.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.example.Models.Tecnico;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioPDF {

    public static void exportarTecnicosParaPDF(List<Tecnico> tecnicos, String fileName) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();

        // Título do relatório
        document.add(new Paragraph("Relatório de Técnicos"));
        document.add(new Paragraph(" ")); // Espaço

        // Criar tabela no PDF
        PdfPTable table = new PdfPTable(4); // 4 colunas
        table.setWidthPercentage(100);

        // Cabeçalhos da tabela
        table.addCell("ID");
        table.addCell("Nome");
        table.addCell("Especialidade");
        table.addCell("Disponibilidade");

        // Adicionando os dados da lista de técnicos
        for (Tecnico tecnico : tecnicos) {
            table.addCell(String.valueOf(tecnico.getId()));
            table.addCell(tecnico.getNome());
            table.addCell(tecnico.getEspecialidade());
            table.addCell(tecnico.getDisponibilidade());
        }

        // Adicionar tabela ao documento
        document.add(table);
        document.close();
        System.out.println("Exportação de Técnicos para PDF concluída em: " + fileName);
    }
}
