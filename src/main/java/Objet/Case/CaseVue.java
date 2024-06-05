package src.main.java.Objet.Case;

// awt
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;

// swing
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import src.main.java.Objet.Plateau.PlateauVue;
import src.main.java.Objet.Tuile.TuileModele;
// others
import src.main.java.Objet.Tuile.TuileVue;
import src.main.java.Objet.Tuile.TuileVueDomino;

public class CaseVue extends JPanel {

    protected TuileVue contenu;
    protected CaseModele modele;
    protected int ligne, colonne;

    public CaseVue(PlateauVue plateau, int li, int co) {
        modele = new CaseModele(this, plateau);
        addMouseListener(modele);
        addMouseMotionListener(modele);
        caseSetup();
        ligne = li;
        colonne = co;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void caseSetup() {
        setPreferredSize(new Dimension(75, 75));
        setBackground(new Color(52, 152, 219));
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK));
    }

    // public void printCase() {
    // modele.printCase();
    // }

    public Rectangle getForme() {
        return modele.getForme();
    }

    public TuileModele getTuileModele() {
        return ((TuileVueDomino) contenu).getModele();
    }

    public TuileVue getContenu() {
        return contenu;
    }

    public void ajouterTuile(TuileVue tuile) {
        contenu = tuile;
        modele.ajouterTuile(((TuileVueDomino) tuile).getModele());
        contenu.setBounds(getForme());
        ((TuileVueDomino) tuile).getModele().setEstPlace(true);
    }
}
