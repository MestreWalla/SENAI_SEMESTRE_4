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
        @SuppressWarnings("CallToPrintStackTrace")
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
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                System.out.println("Erro ao aplicar o tema padrão do sistema: " + e.getMessage());
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

        // Métodos para outros temas disponíveis
        public static void aplicaWindows() {
            aplicaLookAndFeel("Windows");
        }

        public static void aplicaWindowsClassic() {
            aplicaLookAndFeel("Windows Classic");
        }

        public static void aplicaGTK() {
            aplicaLookAndFeel("GTK+");
        }

        public static void aplicaAqua() {
            aplicaLookAndFeel("Aqua");
        }

        public static void aplicaXero() {
            aplicaLookAndFeel("Xero");
        }

        public static void aplicaTexture() {
            aplicaLookAndFeel("Texture");
        }

        public static void aplicaPlastic() {
            aplicaLookAndFeel("Plastic");
        }
    }
}
