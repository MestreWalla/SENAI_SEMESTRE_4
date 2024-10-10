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

    public SistemaManutencaoGUI() {
        super("Sistema de Manutenção");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setLocation(
                ((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));

        // Inicializa os painéis:
        painelMaquinas = new PainelMaquinas();
        painelManutencao = new PainelManutencao();
        painelFalhas = new PainelFalhas();
        painelTecnicos = new PainelTecnicos();

        // Cria o JTabbedPane:
        tabbedPane = new JTabbedPane();

        // Tab01: Manutenção
        ImageIcon iconManutencao = new ImageIcon("C:\\Users\\DevNoite\\Documents\\GitHub\\SENAI_SEMESTRE_4\\Java\\projeto_sistema_manutencao\\src\\main\\resources\\Icons\\manutencaoIcon.png");
        Image image = iconManutencao.getImage();
        Image scaledImage = image.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconManutencao = new ImageIcon(scaledImage);
        tabbedPane.addTab("Manutenção", iconManutencao, painelManutencao);

        // Tab02: Máquinas
        ImageIcon iconMaquinas = new ImageIcon("C:\\Users\\DevNoite\\Documents\\GitHub\\SENAI_SEMESTRE_4\\Java\\projeto_sistema_manutencao\\src\\main\\resources\\Icons\\maquinaIcon.png");
        Image imageMaquinas = iconMaquinas.getImage();
        Image scaledImageMaquinas = imageMaquinas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconMaquinas = new ImageIcon(scaledImageMaquinas);
        tabbedPane.addTab("Máquinas", iconMaquinas, painelMaquinas);

        // Tab03: Falhas
        ImageIcon iconFalhas = new ImageIcon("C:\\Users\\DevNoite\\Documents\\GitHub\\SENAI_SEMESTRE_4\\Java\\projeto_sistema_manutencao\\src\\main\\resources\\Icons\\falhaIcon.png");
        Image imageFalhas = iconFalhas.getImage();
        Image scaledImageFalhas = imageFalhas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconFalhas = new ImageIcon(scaledImageFalhas);
        tabbedPane.addTab("Falhas", iconFalhas, painelFalhas);

        // Tab04: Técnicos
        ImageIcon iconTecnicos = new ImageIcon("C:\\Users\\DevNoite\\Documents\\GitHub\\SENAI_SEMESTRE_4\\Java\\projeto_sistema_manutencao\\src\\main\\resources\\Icons\\tecnicoIcon.png");
        Image imageTecnicos = iconTecnicos.getImage();
        Image scaledImageTecnicos = imageTecnicos.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconTecnicos = new ImageIcon(scaledImageTecnicos);
        tabbedPane.addTab("Técnicos", iconTecnicos, painelTecnicos);

        // Adiciona o JTabbedPane ao JFrame:
        this.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaManutencaoGUI();
    }
}
