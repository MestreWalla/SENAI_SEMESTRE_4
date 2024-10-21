package com.example.View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.example.View.NimbusLookAndFeel.AplicaLookAndFeel;

public class SistemaManutencaoGUI extends JFrame {

    private final JTabbedPane tabbedPane;
    private final JPanel painelManutencao;
    private final JPanel painelMaquinas;
    private final JPanel painelFalhas;
    private final JPanel painelTecnicos;
    private final JComboBox<String> comboBoxTema; // Caixa de seleção para temas

    public SistemaManutencaoGUI() {
        super("Sistema de Manutenção");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Posiciona a janela no centro da tela
        this.setLocation(
                ((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.getWidth() / 2)),
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));

        // Inicializa a caixa de seleção de temas
        String[] temasDisponiveis = getTemasDisponiveis();
        comboBoxTema = new JComboBox<>(temasDisponiveis);
        comboBoxTema.setSelectedItem("Nimbus"); // Tema padrão selecionado

        // Adiciona um ActionListener para trocar o tema quando o usuário selecionar um novo
        comboBoxTema.addActionListener((ActionEvent e) -> {
            String temaSelecionado = (String) comboBoxTema.getSelectedItem();
            trocaTema(temaSelecionado);
        });

        // Adiciona a combobox ao topo da janela
        this.add(comboBoxTema, BorderLayout.NORTH);

        // Aplica o tema Nimbus por padrão
        AplicaLookAndFeel.aplicaNimbus();

        // Inicializa os painéis
        painelMaquinas = new PainelMaquinas();
        painelManutencao = new PainelManutencoes();
        painelFalhas = new PainelFalhas();
        painelTecnicos = new PainelTecnicos();

        // Cria o JTabbedPane
        tabbedPane = new JTabbedPane();

        // Tab01: Manutenção com ícone
        ImageIcon iconManutencao = new ImageIcon(getClass().getResource("/Icons/manutencaoIcon.png"));
        Image image = iconManutencao.getImage();
        Image scaledImage = image.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconManutencao = new ImageIcon(scaledImage);
        tabbedPane.addTab("Manutenções", iconManutencao, painelManutencao);

        // Tab02: Máquinas com ícone
        ImageIcon iconMaquinas = new ImageIcon(getClass().getResource("/Icons/maquinaIcon.png"));
        Image imageMaquinas = iconMaquinas.getImage();
        Image scaledImageMaquinas = imageMaquinas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconMaquinas = new ImageIcon(scaledImageMaquinas);
        tabbedPane.addTab("Máquinas", iconMaquinas, painelMaquinas);

        // Tab03: Falhas com ícone
        ImageIcon iconFalhas = new ImageIcon(getClass().getResource("/Icons/falhaIcon.png"));
        Image imageFalhas = iconFalhas.getImage();
        Image scaledImageFalhas = imageFalhas.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconFalhas = new ImageIcon(scaledImageFalhas);
        tabbedPane.addTab("Falhas", iconFalhas, painelFalhas);

        // Tab04: Técnicos com ícone
        ImageIcon iconTecnicos = new ImageIcon(getClass().getResource("/Icons/tecnicoIcon.png"));
        Image imageTecnicos = iconTecnicos.getImage();
        Image scaledImageTecnicos = imageTecnicos.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        iconTecnicos = new ImageIcon(scaledImageTecnicos);
        tabbedPane.addTab("Técnicos", iconTecnicos, painelTecnicos);

        // Adiciona o JTabbedPane ao centro da janela
        this.add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para obter os temas disponíveis compatíveis com o sistema operacional
    private String[] getTemasDisponiveis() {
        List<String> temas = new ArrayList<>();
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            temas.add(info.getName());
        }
        return temas.toArray(new String[0]);
    }

    // Método para trocar o tema baseado na seleção da comboBox
    private void trocaTema(String temaSelecionado) {
        switch (temaSelecionado) {
            case "Nimbus" -> AplicaLookAndFeel.aplicaNimbus();
            case "Metal" -> AplicaLookAndFeel.aplicaMetal();
            case "Motif" -> AplicaLookAndFeel.aplicaMotif();
            case "Windows" -> AplicaLookAndFeel.aplicaWindows();
            case "Windows Classic" -> AplicaLookAndFeel.aplicaWindowsClassic();
            case "GTK+" -> AplicaLookAndFeel.aplicaGTK();
            case "Aqua" -> AplicaLookAndFeel.aplicaAqua(); // Adicionado tema Aqua para macOS
            case "Plastic" -> AplicaLookAndFeel.aplicaPlastic();
            case "Texture" -> AplicaLookAndFeel.aplicaTexture();
            case "Padrão do Sistema" -> AplicaLookAndFeel.aplicaSistema();
            default -> AplicaLookAndFeel.aplicaNimbus(); // Padrão para Nimbus
        }

        // Atualiza a interface para refletir o novo tema
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String[] args) {
        new SistemaManutencaoGUI();
    }
}
