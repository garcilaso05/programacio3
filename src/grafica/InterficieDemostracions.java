package grafica;

import consola.UtilsConsola;
import dades.accions.LlistaAccions;
import dades.accions.Demostracio;
import dades.associacions.LlistaAssociacions;
import dades.membres.LlistaMembres;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InterficieDemostracions extends JFrame {

    private JTextArea resultatArea;
    private JButton filtrarTotesButton;
    private JButton filtrarPataquersButton;
    private JButton filtrarQueersButton;

    private LlistaAssociacions llistaAssociacions;
    private LlistaAccions llistaAccions;
    private LlistaMembres llistaMembres;

    public InterficieDemostracions() {
        super("Demostracions Actives");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        inicialitzarDades();
        inicialitzarComponents();
    }

    private void inicialitzarDades() {
        UtilsConsola utils = new UtilsConsola();
        final String FILE_ASSOCIACIONS = "data/LlistaAssociacions.dat";
        final String FILE_MEMBRES = "data/LlistaMembres.txt";
        final String FILE_ACCIONS = "data/LlistaAccions.txt";

        llistaAssociacions = new LlistaAssociacions(10);
        llistaAccions = new LlistaAccions(100);
        llistaMembres = new LlistaMembres(100);

        try {
            utils.carregarMembres(FILE_MEMBRES, llistaMembres);
            utils.carregarAssociacions(FILE_ASSOCIACIONS, llistaAssociacions, llistaMembres);
            utils.carregarAccions(FILE_ACCIONS, llistaAccions, llistaAssociacions, llistaMembres);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error carregant dades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicialitzarComponents() {
        // Layout principal
        Container contenidor = this.getContentPane();
        contenidor.setLayout(new BorderLayout());

        // Panell superior amb botons
        JPanel panellBotons = new JPanel();
        panellBotons.setLayout(new FlowLayout());

        filtrarTotesButton = new JButton("Totes les associacions");
        filtrarPataquersButton = new JButton("Pataquers");
        filtrarQueersButton = new JButton("Quers");

        filtrarTotesButton.addActionListener(e -> mostrarDemostracions("Totes"));
        filtrarPataquersButton.addActionListener(e -> mostrarDemostracions("Pataquers"));
        filtrarQueersButton.addActionListener(e -> mostrarDemostracions("Quers"));

        panellBotons.add(filtrarTotesButton);
        panellBotons.add(filtrarPataquersButton);
        panellBotons.add(filtrarQueersButton);

        contenidor.add(panellBotons, BorderLayout.NORTH);

        // Àrea de text per mostrar resultats
        resultatArea = new JTextArea();
        resultatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultatArea);

        contenidor.add(scrollPane, BorderLayout.CENTER);
    }

    private void mostrarDemostracions(String filtre) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < llistaAccions.mida(); i++) {
            var accio = llistaAccions.consultar(i);
            if (accio instanceof Demostracio) {
                Demostracio demostracio = (Demostracio) accio;
                if (demostracio.isActiva() && (filtre.equals("Totes") || demostracio.getAssociacio().equals(filtre))) {
                    resultat.append(demostracio.toString()).append("\n\n");
                }
            }
        }

        if (resultat.length() == 0) {
            resultat.append("No hi ha demostracions actives per aquesta selecció.");
        }

        resultatArea.setText(resultat.toString());
    }

    public static void main(String[] args) {
        InterficieDemostracions finestra = new InterficieDemostracions();
        finestra.setVisible(true);
    }
}
