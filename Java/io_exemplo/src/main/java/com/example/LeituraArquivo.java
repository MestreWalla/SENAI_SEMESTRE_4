package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeituraArquivo {
    public void exemplo(String nomeArquivo) {
        nomeArquivo = "dados.txt";
        File file = new File(nomeArquivo);
        String absolutePath = file.getAbsolutePath();
        System.out.println("Caminho Absoluto: " + absolutePath);
        
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            do {
                nomeArquivo = br.readLine();
                System.out.println(nomeArquivo == null ? "Fim do Documento" : nomeArquivo);
            } while (nomeArquivo != null);
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
