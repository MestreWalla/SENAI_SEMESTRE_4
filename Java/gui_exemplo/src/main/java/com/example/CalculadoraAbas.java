package com.example;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class CalculadoraAbas extends JFrame {

    // Construtor
    public CalculadoraAbas() {

        super("Calculadora Abas");

        this.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));
        AplicaNimbusLookAndFeel.pegaNimbus();

        // Atributos
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);
        // this.setVisible(true);

        // Adicionando os componentes
        JTabbedPane abas = new JTabbedPane();

        JPanel calcSimples = new CalculadoraSimples();
        abas.addTab("Simples", calcSimples);

        JPanel calcAvançada = new CalculadoraAvancada();
        abas.addTab("Avançada", calcAvançada);

        this.add(abas);
        this.setVisible(true);
    }

    // - Singleton para aplicação do Nimbus:
    public static class AplicaNimbusLookAndFeel {

        private AplicaNimbusLookAndFeel() {
        }

        public static void pegaNimbus() {
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (UnsupportedLookAndFeelException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (InstantiationException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();

            } catch (IllegalAccessException e) {

                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}