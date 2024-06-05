package src.main.java.Objet.Case;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import javax.swing.event.MouseInputListener;

import src.main.java.Objet.Plateau.PlateauVue;
import src.main.java.Objet.Tuile.TuileModele;

public class CaseModele implements MouseInputListener {
    protected TuileModele contenuModele;
    protected CaseVue vue;
    protected PlateauVue plateau;

    public CaseModele(CaseVue vue, PlateauVue p) {
        this.vue = vue;
        plateau = p;
    }

    public Rectangle getForme() {
        return new Rectangle(vue.getColonne() * 75 + plateau.getX(), vue.getLigne() * 75 + plateau.getY(), 75, 75);
    }

    public void ajouterTuile(TuileModele tuile) {
        contenuModele = tuile;
    }

    // public void printCase() {
    // System.out.println(contenuModele != null);
    // }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        vue.setBackground(Color.WHITE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        vue.setBackground(new Color(52, 152, 219));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
