package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutosController {
    private List<Produtos> produtos;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public ProdutosController() {
        produtos = new ArrayList<>();
    }

    public void getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProdutos() {
        getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM produtos");
            while (rs.next()) {
                Produtos produto = new Produtos();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getString("preco"));
                produtos.add(produto);
            }
            System.out.println(produtos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
