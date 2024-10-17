package com.example.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IdGenerator {

    private static final String ID_FILE = "idCounter.txt"; // arquivo que armazena o último ID

    public static int getNextId() {
        int nextId = 1; // valor padrão
        try (BufferedReader reader = new BufferedReader(new FileReader(ID_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                nextId = Integer.parseInt(line) + 1; // incrementa o último ID
            }
        } catch (IOException e) {
            e.printStackTrace(); // tratamento de erro
        }

        // Atualiza o arquivo com o novo ID
        try (FileWriter writer = new FileWriter(ID_FILE)) {
            writer.write(String.valueOf(nextId));
        } catch (IOException e) {
            e.printStackTrace(); // tratamento de erro
        }

        return nextId;
    }
}
