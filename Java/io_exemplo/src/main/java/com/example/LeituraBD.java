package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
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
            // Formatar e preparar o comando SQL para inserção de dados
            String sql = "INSERT INTO clientes (nome, email) VALUES (?,?,?)";
            // Executar um comando SQL simples
            stmt.executeUpdate(sql, new String[] { nome, email });
            System.out.println("Usuário adicionado com sucesso!");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUsuario(int id, String nome, String email) {
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
            // Formatar e preparar o comando SQL para atualização de dados
            String sql = "UPDATE clientes SET nome=?, email=? WHERE id=?";
            // Executar um comando SQL simples
            stmt.executeUpdate(sql, new String[] { nome, email, Integer.toString(id) });
            System.out.println("Usuário atualizado com sucesso!");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(int id) {
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
            // Formatar e preparar o comando SQL para exclusão de dados
            String sql = "DELETE FROM clientes WHERE id=?";
            // Executar um comando SQL simples
            stmt.executeUpdate(sql, new String[] { Integer.toString(id) });
            System.out.println("Usuário excluído com sucesso!");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
