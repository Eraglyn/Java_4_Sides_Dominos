package src.main.java.Scene;

import java.awt.*;

import src.main.java.Game.DominoGame;
import src.main.java.Objet.Plateau.PlateauVue;
import src.main.java.Objet.Tuile.TuileVueDomino;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DropMode;
import javax.swing.JButton;

public class SceneDomino extends Scene {
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int screenSizeWidth = (int) screenSize.getWidth();
    protected int screenSizeHeight = (int) screenSize.getHeight();
    protected PlateauVue plateau;
    protected JButton boutonDefausse, boutonTourne;
    protected DominoGame game;

    public SceneDomino(boolean visible) {
        super(visible);
        setSize(screenSize);
        setBackground(Color.WHITE);
        setLocation(0, 0);
        setLayout(null);
        // plateau = new PlateauVue("domino", this);
        // add(plateau);
        // setupBoutonDefausse();
    }

    public void setGame(DominoGame dominoGame) {
        game = dominoGame;
    }

    public DominoGame getGame() {
        return game;
    }

    public PlateauVue getPlateau() {
        return plateau;
    }

    public void setupPlateau() {
        plateau = new PlateauVue("domino", this);
        add(plateau);
    }

    public void setupBoutonDefausse() {
        boutonDefausse = new JButton("Defausser");
        boutonDefausse.setVisible(true);
        boutonDefausse.addActionListener(e -> {
            game.defausse();
        });
        boutonDefausse.setBounds(0, 0, 110, 30);
        add(boutonDefausse);
    }

    public void setupBoutonTourne() {
        boutonTourne = new JButton("Tourner la tuile");
        boutonTourne.setVisible(true);
        boutonTourne.addActionListener(e -> {
            ((TuileVueDomino) game.getTuileCourante()).tournerTuile();
        });
        boutonTourne.setBounds(120, 0, 200, 30);
        add(boutonTourne);
    }

    // public void printPlateau() {
    // plateau.print();
    // }
}
