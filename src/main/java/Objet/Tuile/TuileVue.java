package src.main.java.Objet.Tuile;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import src.main.java.Objet.Plateau.PlateauVue;

public class TuileVue extends JPanel {

    public TuileVue(PlateauVue plateau) {
        setBounds(40, 40, 75, 75);
        setVisible(true);
    }

    public Point getCoordoCentre() {
        Point centre = new Point(getX() + 37, getY() + 37);
        return centre;
    }

}
