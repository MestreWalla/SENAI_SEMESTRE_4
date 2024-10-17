package com.example.View;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.Models.Maquina;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioExportacao {

    // Função para exportar dados para CSV
    public static void exportarDadosParaPDF(String fileName) throws DocumentException, IOException {
        // Cria um documento PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        // Abre o documento para escrita
        document.open();

        // Adiciona conteúdo ao PDF (substitua pelos dados reais)
        document.add(new Paragraph("Relatório de Manutenção"));
        document.add(new Paragraph("ID: 1, Nome: Maquina A, Data: 2023-01-01"));
        document.add(new Paragraph("ID: 2, Nome: Maquina B, Data: 2023-02-01"));

        // Fecha o documento
        document.close();
    }

    public static void exportarDadosParaCSV(List<Maquina> maquinas, String fileName) throws IOException {
        FileWriter csvWriter = new FileWriter(fileName);

        // Cabeçalho do arquivo CSV
        csvWriter.append("ID,Código,Nome,Modelo,Fabricante,Data de Aquisição,Tempo de Vida Estimado,Localização,Detalhes,Manual\n");

        for (Maquina maquina : maquinas) {
            csvWriter.append(maquina.getId() + ",");
            csvWriter.append(maquina.getCodigo() + ",");
            csvWriter.append(maquina.getNome() + ",");
            csvWriter.append(maquina.getModelo() + ",");
            csvWriter.append(maquina.getFabricante() + ",");
            csvWriter.append(maquina.getDataAquisicao() + ",");
            csvWriter.append(maquina.getTempoVidaEstimado() + ",");
            csvWriter.append(maquina.getLocalizacao() + ",");
            csvWriter.append(maquina.getDetalhes() + ",");
            csvWriter.append(maquina.getManual() + "\n");
        }

        csvWriter.flush();
        csvWriter.close();
        System.out.println("Exportação CSV concluída: " + fileName);
    }

}
