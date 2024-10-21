package com.example.View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SistemaManutencaoGUI extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel painelManutencao;
    private JPanel painelMaquinas;
    private JPanel painelFalhas;
    private JPanel painelTecnicos;
    // private PainelExportacao painelExportacao;

    public SistemaManutencaoGUI() {
        super("Sistema de Manutenção");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setLocation(
                ((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));

        // Aplica o tema Nimbus Look and Feel:
        // AplicaNimbusLookAndFeel.pegaNimbus();

        // Inicializa os painéis:
        painelMaquinas = new PainelMaquinas();
        painelManutencao = new PainelManutencoes();
        painelFalhas = new PainelFalhas();
        painelTecnicos = new PainelTecnicos();
        // painelExportacao = new PainelExportacao();

        // Cria o JTabbedPane:
        tabbedPane = new JTabbedPane();

        // Tab01: Manutenção
        ImageIcon iconManutencao = new ImageIcon(getClass().getResource("/Icons/manutencaoIcon.png"));
        Image image = iconManutencao.getImage();
        Image scaledImage = image.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconManutencao = new ImageIcon(scaledImage);
        tabbedPane.addTab("Manutenções", iconManutencao, painelManutencao);

        // Tab02: Máquinas
        ImageIcon iconMaquinas = new ImageIcon(getClass().getResource("/Icons/maquinaIcon.png"));
        Image imageMaquinas = iconMaquinas.getImage();
        Image scaledImageMaquinas = imageMaquinas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconMaquinas = new ImageIcon(scaledImageMaquinas);
        tabbedPane.addTab("Máquinas", iconMaquinas, painelMaquinas);

        // Tab03: Falhas
        ImageIcon iconFalhas = new ImageIcon(getClass().getResource("/Icons/falhaIcon.png"));
        Image imageFalhas = iconFalhas.getImage();
        Image scaledImageFalhas = imageFalhas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconFalhas = new ImageIcon(scaledImageFalhas);
        tabbedPane.addTab("Falhas", iconFalhas, painelFalhas);

        // Tab04: Técnicos
        ImageIcon iconTecnicos = new ImageIcon(getClass().getResource("/Icons/tecnicoIcon.png"));
        Image imageTecnicos = iconTecnicos.getImage();
        Image scaledImageTecnicos = imageTecnicos.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconTecnicos = new ImageIcon(scaledImageTecnicos);
        tabbedPane.addTab("Técnicos", iconTecnicos, painelTecnicos);

        // Tab05: Técnicos
        // ImageIcon iconExportar = new ImageIcon(getClass().getResource("/Icons/exportarIcon.png"));
        // Image imageExportar = iconExportar.getImage();
        // Image scaledImageExportar = imageExportar.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        // iconExportar = new ImageIcon(scaledImageExportar);
        // tabbedPane.addTab("Exportar", iconExportar, painelExportacao);

        // Adiciona o JTabbedPane ao JFrame:
        this.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaManutencaoGUI();
    }
}
