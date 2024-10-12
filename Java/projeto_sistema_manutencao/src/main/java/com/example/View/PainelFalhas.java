package com.example.View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.example.Controllers.FalhaController;
import com.example.Models.Falha;

public class PainelFalhas extends JPanel {

    private final FalhaController falhaController;
    private final JTable falhaTable;
    private final DefaultTableModel tableModel;

    public PainelFalhas() {
        super(new BorderLayout());
        falhaController = new FalhaController();
        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Máquina ID", "Data", "Problema", "Prioridade", "Operador"
        }, 0);
        falhaTable = new JTable(tableModel);

        // Carregar as falhas
        List<Falha> falhas = falhaController.ReadFalhas();
        for (Falha falha : falhas) {
            tableModel.addRow(new Object[]{
                falha.getId(),
                falha.getMaquinaId(),
                falha.getData(),
                falha.getProblema(),
                falha.getPrioridade(),
                falha.getOperador()
            });
        }

        JScrollPane scrollPane = new JScrollPane(falhaTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Botão para adicionar falha
        JButton btnAdicionar = new JButton("Adicionar Falha");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para adicionar uma nova falha
                // Isso pode incluir abrir um novo formulário para inserir dados
            }
        });
        this.add(btnAdicionar, BorderLayout.SOUTH);
    }
}
