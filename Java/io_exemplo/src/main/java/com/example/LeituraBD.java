package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeituraBD {
    public void exemplo() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");
            // Verificar se a conexão foi bem-sucedida
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
            // Executar um comando SQL simples
            Statement stmt = conn.createStatement();
            // Armazenar os resultados da consulta
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            // Imprimir os resultados da consulta
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id")
                                + ", Nome: " + rs.getString("nome")
                                + ", Idade: " + rs.getInt("idade")
                                + ", Endereço: " + rs.getString("endereco"));
            }
            // Fechar conexão
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewClientes() { // id, nome, email.
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
            // Executar um comando SQL simples
            Statement stmt = conn.createStatement();
            // Armazenar os resultados da consulta
            ResultSet rs = stmt.executeQuery("SELECT id, nome, email FROM clientes");
            // Imprimir os resultados da consulta
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id")
                                + ", Nome: " + rs.getString("nome")
                                + ", Email: " + rs.getString("email"));
            }
            // Fechar conexão
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCliente(String nome, String email) {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Verificar se a conexão foi bem-sucedida
            System.out.println("Conexão bem-sucedida!");
            // Adicionar os parâmetros ao comando SQL
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            // Executar o comando SQL
            pstmt.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCliente(int id, String nome, String email) {
        String sql = "UPDATE clientes SET nome=?, email=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Verificar se a conexão foi bem-sucedida
            System.out.println("Conexão bem-sucedida!");

            // Definir os parâmetros para a consulta SQL
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);

            // Executar o comando SQL
            pstmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(int id) {
    String sql = "DELETE FROM clientes WHERE id=?";
    try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "postgres");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        System.out.println("Conexão bem-sucedida!");

        // Definir o parâmetro para a consulta SQL
        pstmt.setInt(1, id);

        // Executar o comando SQL
        pstmt.executeUpdate();
        System.out.println("Usuário excluído com sucesso!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
