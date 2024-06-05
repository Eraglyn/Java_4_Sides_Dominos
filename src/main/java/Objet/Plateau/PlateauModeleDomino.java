package src.main.java.Objet.Plateau;

import src.main.java.Objet.Joueur.JoueurModele;
import src.main.java.Objet.Pioche.*;
import src.main.java.Objet.Tuile.TuileModeleDomino;
import src.main.java.Objet.Tuile.TuileVueDomino;
import java.util.ArrayList;

public class PlateauModeleDomino extends PlateauModele {

    protected ArrayList<TuileModeleDomino> listeTuile;
    protected PiocheModeleDomino pioche;

    public PlateauModeleDomino(PlateauVue plateauVue) {
        super(plateauVue);
        pioche = new PiocheModeleDomino(27, vue, vue.scene);
        listeTuile = new ArrayList<TuileModeleDomino>();
    }

    // public void miseEnPlacePremiereTuile() {
    // TuileVue tuile = pioche.piocher();
    // tuile.getModele().setEstPlace(true);
    // plateau[5][8].ajouterTuile(tuile);
    // }

    public ArrayList<TuileModeleDomino> getListeTuile() {
        return listeTuile;
    }

    public PiocheModeleDomino getPioche() {
        return pioche;
    }

    public TuileVueDomino piocher() {
        return pioche.piocher();
    }

    public void analyseTerrain() {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                System.out.println(plateau[ligne][colonne].getContenu());
            }
        }
    }

    public boolean terrainVide() {
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne].getContenu() != null)
                    return false;
            }
        }
        return true;
    }

    public boolean rienAutour(int ligne, int colonne) {
        // on vérifie la case a droite
        if (colonne + 1 < plateau[ligne].length && plateau[ligne][colonne + 1].getContenu() != null)
            return false;
        // on vérifie la case a gauche
        if (colonne - 1 >= 0 && plateau[ligne][colonne - 1].getContenu() != null)
            return false;
        // on vérifie la case en bas
        if (ligne + 1 < plateau.length && plateau[ligne + 1][colonne].getContenu() != null)
            return false;
        // on vérifie la case au dessus
        if (ligne - 1 >= 0 && plateau[ligne - 1][colonne].getContenu() != null)
            return false;
        return true;
    }

    public boolean estPlacable(TuileModeleDomino modele, int ligne, int colonne) {
        if (plateau[ligne][colonne].getContenu() != null) {
            // System.out.println("Case used");
            return false;
        }

        if (rienAutour(ligne, colonne)) {
            // System.out.println("Rien Autour");
            return false;
        }

        if (colonne + 1 < plateau[ligne].length && plateau[ligne][colonne + 1].getContenu() != null) { // on compare la
                                                                                                       // case a droite
            if (!((TuileModeleDomino) plateau[ligne][colonne + 1].getTuileModele()).compareGauche(modele)) {
                modele.setScore(0);
                // System.out.println("Case Droite pas bonne");
                return false;
            } else
                modele.addScore(modele.getListeDroite()[0] + modele.getListeDroite()[1] + modele.getListeDroite()[2]);

        }

        if (colonne - 1 >= 0 && plateau[ligne][colonne - 1].getContenu() != null) { // on vérifie la case a gauche
            if (!((TuileModeleDomino) plateau[ligne][colonne - 1].getTuileModele()).compareDroite(modele)) {
                modele.setScore(0);
                // System.out.println("Case gauche pas bonne");
                return false;
            } else
                modele.addScore(modele.getListeGauche()[0] + modele.getListeGauche()[1] + modele.getListeGauche()[2]);

        }

        if (ligne + 1 < plateau.length && plateau[ligne + 1][colonne].getContenu() != null) { // on vérifie la case en
                                                                                              // bas
            if (!((TuileModeleDomino) plateau[ligne + 1][colonne].getTuileModele()).compareHaut(modele)) {
                modele.setScore(0);
                // System.out.println("Case bas pas bonne");
                return false;
            } else
                modele.addScore(modele.getListeBas()[0] + modele.getListeBas()[1] + modele.getListeBas()[2]);

        }

        if (ligne - 1 >= 0 && plateau[ligne - 1][colonne].getContenu() != null) { // on vérifie la case au dessus
            if (!((TuileModeleDomino) plateau[ligne - 1][colonne].getTuileModele()).compareBas(modele)) {
                modele.setScore(0);
                // System.out.println("Case haut pas bonne");
                return false;
            } else
                modele.addScore(modele.getListeHaut()[0] + modele.getListeHaut()[1] + modele.getListeHaut()[2]);

        }
        return true;
    }

    public void placerTuile(JoueurModele joueur, TuileModeleDomino modele, int ligne, int colonne) {
        if (estPlacable(modele, ligne, colonne)) {
            listeTuile.add(modele);
            modele.setX(ligne);
            modele.setY(colonne);
            modele.ajouterTuileSurCase(plateau[ligne][colonne]);
            joueur.addScore(modele.getScore());

        }

    }

    public boolean placerTuileIA(JoueurModele joueur, TuileModeleDomino modele) {
        for (int i = 0; i < 4; i++) {
            ((TuileVueDomino) modele.getVue()).tournerTuile();
            for (TuileModeleDomino tuile : listeTuile) {
                if (estPlacable(modele, tuile.getX() - 1, tuile.getY())) {
                    placerTuile(joueur, modele, tuile.getX() - 1, tuile.getY());
                    return true;
                }

                if (estPlacable(modele, tuile.getX(), tuile.getY() + 1)) {
                    placerTuile(joueur, modele, tuile.getX(), tuile.getY() + 1);
                    return true;
                }

                if (estPlacable(modele, tuile.getX() + 1, tuile.getY())) {
                    placerTuile(joueur, modele, tuile.getX() + 1, tuile.getY());
                    return true;
                }

                if (estPlacable(modele, tuile.getX(), tuile.getY() - 1)) {
                    placerTuile(joueur, modele, tuile.getX(), tuile.getY() - 1);
                    return true;
                }

            }
        }
        return false;
    }

    public void placer1ereTuile(TuileModeleDomino modele) {
        listeTuile.add(modele);
        modele.setX(5);
        modele.setY(8);
        modele.ajouterTuileSurCase(plateau[5][8]);
    }
}
