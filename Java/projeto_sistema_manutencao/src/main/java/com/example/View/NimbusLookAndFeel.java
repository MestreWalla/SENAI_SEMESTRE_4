package com.example.View;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class NimbusLookAndFeel {

    // Singleton para aplicação de diferentes temas (LookAndFeel)
    public static class AplicaLookAndFeel {

        private AplicaLookAndFeel() {
        }

        // Método para aplicar um Look and Feel específico pelo nome
        public static void aplicaLookAndFeel(String lookAndFeelName) {
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (lookAndFeelName.equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("Tema " + lookAndFeelName + " aplicado com sucesso!");
                        return;
                    }
                }
                System.out.println("Tema " + lookAndFeelName + " não encontrado.");
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

        // Método para aplicar Nimbus como padrão
        public static void aplicaNimbus() {
            aplicaLookAndFeel("Nimbus");
        }

        // Método para aplicar o Look and Feel padrão do sistema
        public static void aplicaSistema() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                System.out.println("Tema padrão do sistema aplicado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao aplicar o tema padrão do sistema: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Método para aplicar o tema Metal (padrão do Java)
        public static void aplicaMetal() {
            aplicaLookAndFeel("Metal");
        }

        // Método para aplicar o tema Motif (padrão antigo do UNIX)
        public static void aplicaMotif() {
            aplicaLookAndFeel("CDE/Motif");
        }
    }
}
