package src.main.java.Scene;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import src.main.java.Main.Main;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

public class SceneNombreDeJoueur extends Scene {

    protected JButton bouton2Joueur, bouton3Joueur, bouton4Joueur;
    protected JPanel conteneur;
    protected SceneChoixJoueur sceneChoix;

    public SceneNombreDeJoueur(boolean visible) {
        super(visible);
        setSize(screenSize);
        setBackground(new Color(39, 174, 96));
        setLocation(0, 0);
        setLayout(null);
        setupConteneur();
        setupBouton2Joueur();
        setupBouton3Joueur();
        setupBouton4Joueur();
    }

    public void setupConteneur() {
        conteneur = new JPanel();
        conteneur.setLayout(new GridLayout(3, 1, 0, 10));
        conteneur.setBackground(new Color(39, 174, 96));
        conteneur.setBounds(new Rectangle((screenSizeWidth / 2) - 100, (screenSizeHeight / 2) - 150, 200, 200));
        add(conteneur);
    }

    public void setupBouton2Joueur() {
        bouton2Joueur = new JButton("2 Joueurs");
        bouton2Joueur.setVisible(true);
        bouton2Joueur.setPreferredSize(new Dimension(200, 50));
        bouton2Joueur.setBackground(new Color(46, 204, 113));
        bouton2Joueur.setBorder(new LineBorder(Color.GRAY));
        bouton2Joueur.addActionListener(e -> {
            sceneChoix = new SceneChoixJoueur(false, 2);
            Main.getApplication().add(sceneChoix);
            changerDeScene(this, sceneChoix);
        });
        conteneur.add(bouton2Joueur);
    }

    public void setupBouton3Joueur() {
        bouton3Joueur = new JButton("3 Joueurs");
        bouton3Joueur.setVisible(true);
        bouton3Joueur.setPreferredSize(new Dimension(200, 50));
        bouton3Joueur.setBackground(new Color(46, 204, 113));
        bouton3Joueur.setBorder(new LineBorder(Color.GRAY));
        bouton3Joueur.addActionListener(e -> {
            sceneChoix = new SceneChoixJoueur(false, 3);
            Main.getApplication().add(sceneChoix);
            changerDeScene(this, sceneChoix);
        });
        conteneur.add(bouton3Joueur);
    }

    public void setupBouton4Joueur() {
        bouton4Joueur = new JButton("4 Joueurs");
        bouton4Joueur.setVisible(true);
        bouton4Joueur.setPreferredSize(new Dimension(200, 50));
        bouton4Joueur.setBackground(new Color(46, 204, 113));
        bouton4Joueur.setBorder(new LineBorder(Color.GRAY));
        bouton4Joueur.addActionListener(e -> {
            sceneChoix = new SceneChoixJoueur(false, 4);
            Main.getApplication().add(sceneChoix);
            changerDeScene(this, sceneChoix);
        });
        conteneur.add(bouton4Joueur);
    }
}
