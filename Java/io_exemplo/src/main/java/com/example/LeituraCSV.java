package com.example;

import java.io.BufferedReader;

public class LeituraCSV {
    public void exemplo() {
        try {
            BufferedReader br = new BufferedReader(
                    new java.io.FileReader("dados.csv"));
            String linha = br.readLine();
            if (linha != null) {
                String colunas[] = linha.split(",");
                System.out.println("Cabeçalho");
                for (String coluna : colunas) {
                    System.out.print(coluna + "\t");
                }
            }
            System.out.println("Conteudo");
            while ((linha = br.readLine()) != null) {
                String colunas[] = linha.split(",");
                for (String coluna : colunas) {
                    System.out.print(coluna + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo CSV");
            e.printStackTrace();
        }
    }
}
