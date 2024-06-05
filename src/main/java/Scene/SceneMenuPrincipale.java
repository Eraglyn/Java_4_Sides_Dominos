package src.main.java.Scene;

import src.main.java.Game.DominoGame;
import src.main.java.Main.Main;
import java.awt.*;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SceneMenuPrincipale extends Scene {
    protected JPanel zoneDeSelection;
    protected GridBagConstraints gbc = new GridBagConstraints();
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int screenSizeWidth = (int) screenSize.getWidth();
    protected int screenSizeHeight = (int) screenSize.getHeight();
    protected JButton boutonDominos, boutonCarcassone, boutonQuitter;
    protected Scene menuNombreJoueur, menuCarcassone, sceneTransition;

    public SceneMenuPrincipale(boolean visible) {
        super(visible);
        sceneMenuPrincipaleSetup();
        menuNombreJoueur = new SceneNombreDeJoueur(false);
        menuCarcassone = new SceneMenuCarcassone(false);
        // sceneTransition = new SceneTransition(true);
        Main.getApplication().add(menuNombreJoueur);
        Main.getApplication().add(menuCarcassone);
        // Main.getApplication().add(sceneTransition);
        backgroundSetup("D:\\Documents\\Fac\\L2\\POO3\\projet-poo\\Java\\Ressources\\Images\\FondImages.jpg");
        zoneDeSelectionSetup();
        boutonDominosSetup();
        boutonCarcassoneSetup();
        boutonQuitterSetup();

    }

    private void sceneMenuPrincipaleSetup() {
        setSize(screenSize);
        setLayout(null);
        setBackground(new Color(39, 174, 96));
    }

    private void zoneDeSelectionSetup() {
        zoneDeSelection = new JPanel();
        zoneDeSelection.setLayout(new GridLayout(3, 1, 0, 10));
        zoneDeSelection.setBackground(new Color(39, 174, 96));
        zoneDeSelection.setBounds((screenSizeWidth / 2) - 100, (screenSizeHeight / 2) - 150, 200, 200);
        zoneDeSelection.setVisible(true);
        add(zoneDeSelection);
    }

    private void backgroundSetup(String path) {
        ImageIcon background = new ImageIcon(path);
        Image img = background.getImage();
        Image temp = img.getScaledInstance(screenSizeWidth, screenSizeHeight, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setBounds(0, 0, screenSizeWidth, screenSizeHeight);
        add(back);
    }

    public void boutonDominosSetup() {
        boutonDominos = new JButton();
        boutonDominos.setPreferredSize(new Dimension(200, 50));
        boutonDominos.setBackground(new Color(46, 204, 113));
        boutonDominos.setBorder(new LineBorder(Color.GRAY));
        boutonDominos.addActionListener(e -> {
            Scene.changerDeScene(this, menuNombreJoueur);
            // new DominoGame((SceneDomino) menuDomino);
        });
        boutonDominos.setText("Jouer aux Dominos");
        gbc.gridx = 0;
        gbc.gridy = 0;
        zoneDeSelection.add(boutonDominos, gbc);
    }

    private void boutonCarcassoneSetup() {
        boutonCarcassone = new JButton();
        boutonCarcassone.setPreferredSize(new Dimension(200, 50));
        boutonCarcassone.setBackground(new Color(46, 204, 113));
        boutonCarcassone.setBorder(new LineBorder(Color.GRAY));
        boutonCarcassone.addActionListener(e -> {
            Scene.changerDeScene(this, menuCarcassone);
        });
        boutonCarcassone.setText("Jouer Ã  Carcassone");
        gbc.gridx = 0;
        gbc.gridy = 1;
        zoneDeSelection.add(boutonCarcassone, gbc);
    }

    private void boutonQuitterSetup() {
        boutonQuitter = new JButton();
        boutonQuitter.addActionListener(e -> {
            System.exit(0);
        });
        add(boutonQuitter);
    }

    // private void menuPrincipaleVersMenuDomino() {
    // changerDeScene(this, menuDomino);
    // }

    // public void menuPrincipaleVersMenuCarcassone() {
    // changerDeScene(this, menuCarcassone);
    // }
}