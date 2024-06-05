package src.main.java.Scene;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import src.main.java.Game.DominoGame;
import src.main.java.Main.Main;
import src.main.java.Objet.Joueur.JoueurModele;

public class SceneChoixJoueur extends Scene {

    protected SceneDomino sceneDomino;
    protected int nombreJoueur;
    protected JPanel mainConteneur, conteneur1, conteneur2, conteneur3, conteneur4;
    protected JoueurModele joueur1, joueur2, joueur3, joueur4;
    protected JTextField nom1, nom2, nom3, nom4;
    protected JCheckBox ia1, ia2, ia3, ia4;
    protected JButton boutonSuivant;

    public SceneChoixJoueur(boolean visible, int nbJoueur) {
        super(visible);
        sceneDomino = new SceneDomino(false);
        Main.getApplication().add(sceneDomino);
        setSize(screenSize);
        setBackground(new Color(39, 174, 96));
        setLocation(0, 0);
        setLayout(null);
        nombreJoueur = nbJoueur;
        setupMainConteneur();
        setupBoutonSuivant();
        if (nombreJoueur == 2) {
            setup2Joueur();
        } else if (nombreJoueur == 3) {
            setup3Joueur();
        } else if (nombreJoueur == 4) {
            setup4Joueur();
        }
    }

    public void setupMainConteneur() {
        mainConteneur = new JPanel();
        mainConteneur.setVisible(true);
        mainConteneur.setLayout(new GridLayout(1, nombreJoueur, 10, 0));
        mainConteneur.setBounds(
                new Rectangle((int) (screenSize.getWidth() / 2) - ((110 * nombreJoueur) / 2),
                        (int) (screenSize.getHeight() / 2) - ((110 * nombreJoueur) / 2), 110 * nombreJoueur,
                        200));
        mainConteneur.setBackground(new Color(39, 174, 96));
        add(mainConteneur);
    }

    public void setup2Joueur() {
        setupConteneur1();
        setupNom1();
        setupIa1();
        setupConteneur2();
        setupNom2();
        setupIa2();
    }

    public void setup3Joueur() {
        setupConteneur1();
        setupNom1();
        setupIa1();
        setupConteneur2();
        setupNom2();
        setupIa2();
        setupConteneur3();
        setupNom3();
        setupIa3();
    }

    public void setup4Joueur() {
        setupConteneur1();
        setupNom1();
        setupIa1();
        setupConteneur2();
        setupNom2();
        setupIa2();
        setupConteneur3();
        setupNom3();
        setupIa3();
        setupConteneur4();
        setupNom4();
        setupIa4();
    }

    public void setupConteneur1() {
        conteneur1 = new JPanel();
        conteneur1.setLayout(new GridLayout(2, 1, 0, 10));
        conteneur1.setBackground(new Color(39, 174, 96));
        mainConteneur.add(conteneur1);
    }

    public void setupNom1() {
        nom1 = new JTextField();
        nom1.setPreferredSize(new Dimension(100, 25));
        JTextArea textNom1 = new JTextArea("Nom du joueur 1");
        textNom1.setBackground(new Color(39, 174, 96));
        JPanel panneauTexte1 = new JPanel();
        panneauTexte1.add(textNom1);
        panneauTexte1.add(nom1);
        panneauTexte1.setBackground(new Color(39, 174, 96));
        conteneur1.add(panneauTexte1);
    }

    public void setupIa1() {
        ia1 = new JCheckBox("ia");
        ia1.setBackground(new Color(39, 174, 96));
        conteneur1.add(ia1);
    }

    public void setupConteneur2() {
        conteneur2 = new JPanel();
        conteneur2.setLayout(new GridLayout(2, 1, 0, 10));
        conteneur2.setBackground(new Color(39, 174, 96));
        mainConteneur.add(conteneur2);
    }

    public void setupNom2() {
        nom2 = new JTextField();
        nom2.setPreferredSize(new Dimension(100, 25));
        JTextArea textNom2 = new JTextArea("Nom du joueur 2");
        textNom2.setBackground(new Color(39, 174, 96));
        JPanel panneauTexte2 = new JPanel();
        panneauTexte2.add(textNom2);
        panneauTexte2.add(nom2);
        panneauTexte2.setBackground(new Color(39, 174, 96));
        conteneur2.add(panneauTexte2);
    }

    public void setupIa2() {
        ia2 = new JCheckBox("ia");
        ia2.setBackground(new Color(39, 174, 96));
        conteneur2.add(ia2);
    }

    public void setupConteneur3() {
        conteneur3 = new JPanel();
        conteneur3.setLayout(new GridLayout(2, 1, 0, 10));
        conteneur3.setBackground(new Color(39, 174, 96));
        mainConteneur.add(conteneur3);
    }

    public void setupNom3() {
        nom3 = new JTextField();
        nom3.setPreferredSize(new Dimension(100, 25));
        JTextArea textNom3 = new JTextArea("Nom du joueur 3");
        textNom3.setBackground(new Color(39, 174, 96));
        JPanel panneauTexte3 = new JPanel();
        panneauTexte3.add(textNom3);
        panneauTexte3.add(nom3);
        panneauTexte3.setBackground(new Color(39, 174, 96));
        conteneur3.add(panneauTexte3);
    }

    public void setupIa3() {
        ia3 = new JCheckBox("ia");
        ia3.setBackground(new Color(39, 174, 96));
        conteneur3.add(ia3);
    }

    public void setupConteneur4() {
        conteneur4 = new JPanel();
        conteneur4.setLayout(new GridLayout(2, 1, 0, 10));
        conteneur4.setBackground(new Color(39, 174, 96));
        mainConteneur.add(conteneur4);
    }

    public void setupNom4() {
        nom4 = new JTextField();
        nom4.setPreferredSize(new Dimension(100, 25));
        JTextArea textNom4 = new JTextArea("Nom du joueur 4");
        textNom4.setBackground(new Color(39, 174, 96));
        JPanel panneauTexte4 = new JPanel();
        panneauTexte4.add(textNom4);
        panneauTexte4.add(nom4);
        panneauTexte4.setBackground(new Color(39, 174, 96));
        conteneur4.add(panneauTexte4);
    }

    public void setupIa4() {
        ia4 = new JCheckBox("ia");
        ia4.setBackground(new Color(39, 174, 96));
        conteneur4.add(ia4);
    }

    public void setupBoutonSuivant() {
        boutonSuivant = new JButton("Suivant");
        boutonSuivant.setBackground(new Color(46, 204, 113));
        boutonSuivant.setBorder(new LineBorder(Color.GRAY));
        boutonSuivant.setBounds((screenSizeWidth / 2) - 100, (screenSizeHeight / 2) + 200, 200, 50);
        ArrayList<JoueurModele> listeJoueur = new ArrayList<JoueurModele>();
        boutonSuivant.addActionListener(e -> {
            for (int i = 0; i < nombreJoueur; i++) {
                if (i == 0) {
                    JoueurModele joueur = new JoueurModele(nom1.getText(), ia1.isSelected());
                    listeJoueur.add(joueur);
                } else if (i == 1) {
                    JoueurModele joueur = new JoueurModele(nom2.getText(), ia2.isSelected());
                    listeJoueur.add(joueur);
                } else if (i == 2) {
                    JoueurModele joueur = new JoueurModele(nom3.getText(), ia3.isSelected());
                    listeJoueur.add(joueur);
                } else {
                    JoueurModele joueur = new JoueurModele(nom4.getText(), ia4.isSelected());
                    listeJoueur.add(joueur);
                }
            }
            changerDeScene(this, sceneDomino);
            new DominoGame(sceneDomino, listeJoueur);
        });
        add(boutonSuivant);
    }
}
