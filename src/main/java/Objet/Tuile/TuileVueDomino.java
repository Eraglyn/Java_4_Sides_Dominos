package src.main.java.Objet.Tuile;

// awt
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

// swing
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import src.main.java.Objet.Plateau.PlateauVue;

public class TuileVueDomino extends TuileVue {

    protected TuileModeleDomino modele;

    protected JLabel[][] listeLabels;

    public TuileVueDomino(PlateauVue plateau) {
        super(plateau);
        modele = new TuileModeleDomino(this, plateau);
        addMouseListener(modele.mouvement);
        addMouseMotionListener(modele.mouvement);
        setupTuile();
    }

    public void setupTuile() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for (int ligne = 0; ligne < 5; ligne++) {

            for (int colonne = 0; colonne < 5; colonne++) {

                gbc.gridy = ligne;
                gbc.gridx = colonne;

                if (ligne == 0 || ligne == 4) {

                    if (colonne == 0 || colonne == 4) {

                        this.add(ajouterJLabel("", Color.GRAY, true), gbc);
                    } else {

                        this.add(ajouterJLabel(" " + valeurDuModele(ligne, colonne), Color.WHITE, true), gbc);
                    }
                } else {

                    if (colonne == 0 || colonne == 4) {

                        this.add(ajouterJLabel(" " + valeurDuModele(ligne, colonne), Color.WHITE, true), gbc);
                    } else {

                        this.add(ajouterJLabel("", Color.LIGHT_GRAY, false), gbc);
                    }
                }
            }
        }
    }

    public JLabel ajouterJLabel(String texte, Color c, boolean border) {
        JLabel label = new JLabel(texte);
        label.setPreferredSize(new Dimension(15, 15));
        label.setBackground(c);
        label.setOpaque(true);
        if (border)
            label.setBorder(new LineBorder(Color.BLACK));
        return label;
    }

    public Integer valeurDuModele(int ligne, int colonne) {
        if (ligne == 0) {
            return ((TuileModeleDomino) modele).getListeHaut()[colonne - 1];
        }
        if (ligne == 4) {
            return ((TuileModeleDomino) modele).getListeBas()[colonne - 1];
        }
        if (colonne == 0) {
            return ((TuileModeleDomino) modele).getListeGauche()[ligne - 1];
        }
        if (colonne == 4) {
            return ((TuileModeleDomino) modele).getListeDroite()[ligne - 1];
        }
        return null;
    }

    public TuileModeleDomino getModele() {
        return this.modele;
    }

    public void tournerTuile() {
        if (!modele.estPlace) {
            removeAll();
            modele.tourne();
            setupTuile();
            revalidate();
        }
    }
}
