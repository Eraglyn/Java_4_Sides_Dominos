package src.main.java.Objet.Plateau;

import src.main.java.Objet.Joueur.*;
import src.main.java.Objet.Pioche.*;
import src.main.java.Objet.Tuile.TuileModeleTerminal;

import java.util.ArrayList;

public class PlateauModeleTerminal {
    protected PiocheModeleTerminal pioche;
    protected DefausseModeleTerminal defausse;
    protected ArrayList<JoueurModele> listeJoueur;
    protected ArrayList<TuileModeleTerminal> listeTuile;
    protected TuileModeleTerminal[][] plateau;

    public PlateauModeleTerminal() {
        listeJoueur = JoueurModele.getListeJoueur();
        listeTuile = new ArrayList<TuileModeleTerminal>();
        pioche = new PiocheModeleTerminal(27);
        defausse = new DefausseModeleTerminal();
        plateau = new TuileModeleTerminal[56][56];
        TuileModeleTerminal tuile = pioche.piocher();
        tuile.setX(26);
        tuile.setY(26);
        plateau[26][26] = tuile;
        listeTuile.add(tuile);
    }

    // public PlateauModeleTerminal(int n) {
    // listeJoueur = JoueurModele.getListeJoueur();
    // pioche = new PiocheModele(n);
    // defausse = new DefausseModele();
    // plateau = new TuileModeleTerminal[(n * 2) + 2][(n * 2) + 2];
    // }

    public PiocheModeleTerminal getPioche() {
        return pioche;
    }

    public TuileModeleTerminal piocher() {
        return pioche.piocher();
    }

    public int getTaillePioche() {
        return pioche.getTaille();
    }

    public TuileModeleTerminal[][] getPlateau() {
        return plateau;
    }

    public DefausseModeleTerminal getDefausse() {
        return defausse;
    }

    public ArrayList<TuileModeleTerminal> getListeTuile() {
        return listeTuile;
    }

    public void defausse(TuileModeleTerminal tuile) {
        defausse.defausserTuile(tuile);
    }

    public boolean estVide(TuileModeleTerminal[] ligne) {
        for (int i = 0; i < ligne.length; i++) {
            if (ligne[i] != null)
                return false;
        }
        return true;
    }

    public void afficheTerminal() {
        int distanceMin = elementLePlusAGaucheTableau(plateau);
        for (int i = 0; i < plateau.length; i++) {
            if (!estVide(plateau[i])) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(" ".repeat(8 * Math.abs(distanceMin - elementLePlusAGaucheListe(plateau[i]))));
                    boolean rienAvant = false;
                    for (int k = 0; k < plateau[i].length; k++) {
                        if (plateau[i][k] != null) {
                            rienAvant = true;
                            switch (j) {
                                case 0 -> System.out.print(
                                        " " + plateau[i][k].getListeHaut()[0] + " "
                                                + plateau[i][k].getListeHaut()[1]
                                                + " " + plateau[i][k].getListeHaut()[2] + " ".repeat(2));
                                case 1 -> System.out.print(plateau[i][k].getListeGauche()[0] + " ".repeat(5)
                                        + plateau[i][k].getListeDroite()[0] + " ");
                                case 2 ->
                                    System.out
                                            .print(plateau[i][k].getListeGauche()[1] + " (" + plateau[i][k].getId()
                                                    + ") " + plateau[i][k].getListeDroite()[1] + " ");
                                case 3 -> System.out.print(plateau[i][k].getListeGauche()[2] + " ".repeat(5)
                                        + plateau[i][k].getListeDroite()[2] + " ");
                                case 4 -> System.out.print(
                                        " " + plateau[i][k].getListeBas()[0] + " " + plateau[i][k].getListeBas()[1]
                                                + " " + plateau[i][k].getListeBas()[2] + " ".repeat(2));
                            }
                            // rienAvant = false;
                        } else if (rienAvant && !ligneVideApresIndex(k, plateau[i])) {
                            System.out.print(" ".repeat(8));
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static boolean ligneVideApresIndex(int k, TuileModeleTerminal[] ligne) {
        for (int i = k + 1; i < ligne.length; i++) {
            if (ligne[i] != null)
                return false;
        }
        return true;
    }

    public static int elementLePlusAGaucheListe(TuileModeleTerminal[] liste) {
        int distanceGauche = 0;
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == null)
                distanceGauche++;
            else
                return distanceGauche;
        }
        return distanceGauche;
    }

    public static int elementLePlusAGaucheTableau(TuileModeleTerminal[][] tableau) {
        int distanceMin = elementLePlusAGaucheListe(tableau[0]);
        for (int i = 1; i < tableau.length; i++) {
            int distanceGauche = 0;
            boolean stop = false;
            for (int j = 0; j < tableau.length; j++) {
                if (tableau[i][j] == null && !stop)
                    distanceGauche++;
                else {
                    stop = true;
                }
            }
            if (distanceGauche < distanceMin)
                distanceMin = distanceGauche;
        }
        return distanceMin;
    }

    public void ajouterTuile(TuileModeleTerminal tuile) {
        listeTuile.add(tuile);
    }
}