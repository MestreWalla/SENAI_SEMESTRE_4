package com.example;

import java.io.BufferedReader;
// import java.io.File;
import java.io.FileReader;

public class LeituraArquivo {
    public void exemplo() {
        try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))) {
            String linha;
            do {
                // String line = br.readLine();
                // File file = new File(line);
                // String absolutePath = file.getAbsolutePath();
                linha = br.readLine();
                System.out.println(linha == null ? "Fim do Documento" : linha);
            } while (linha != null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
