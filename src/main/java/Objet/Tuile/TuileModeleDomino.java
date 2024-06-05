package src.main.java.Objet.Tuile;

import src.main.java.Objet.Case.CaseVue;
import src.main.java.Objet.Plateau.PlateauVue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TuileModeleDomino extends TuileModele {

    protected int[] listeHaut;
    protected int[] listeBas;
    protected int[] listeDroite;
    protected int[] listeGauche;
    protected static ArrayList<TuileModeleDomino> listeTuile = new ArrayList<TuileModeleDomino>();
    protected int scoreMarque;

    public int[] getListeHaut() {
        return listeHaut;
    }

    public int[] getListeBas() {
        return listeBas;
    }

    public int[] getListeDroite() {
        return listeDroite;
    }

    public int[] getListeGauche() {
        return listeGauche;
    }

    public int getScore() {
        return scoreMarque;
    }

    public void setScore(int s) {
        scoreMarque = s;
    }

    public void addScore(int s) {
        scoreMarque += s;
    }

    public TuileModeleDomino(TuileVue vue, PlateauVue plateau) {

        Random rd = new Random();
        listeHaut = new int[3];
        for (int i = 0; i < 3; i++) {
            listeHaut[i] = rd.nextInt(3);
        }
        listeBas = new int[3];
        for (int i = 0; i < 3; i++) {
            listeBas[i] = rd.nextInt(3);
        }
        listeDroite = new int[3];
        for (int i = 0; i < 3; i++) {
            listeDroite[i] = rd.nextInt(3);
        }
        listeGauche = new int[3];
        for (int i = 0; i < 3; i++) {
            listeGauche[i] = rd.nextInt(3);
        }
        if (dejaExistant(this)) { // pour eviter les doublons
            System.out.println("DOUBLON");
            new TuileModeleDomino(vue, plateau);
        } else {
            plateauVue = plateau;
            mouvement = new MouvementTuile(this);
            super.vue = vue;
            listeTuile.add(this);
        }
    }

    public static boolean dejaExistant(TuileModeleDomino tuile) {
        for (TuileModeleDomino tuileBonne : listeTuile) {
            for (int i = 0; i < 4; i++) {
                if (tuileIdentique(tuileBonne, tuile)) {
                    return true;
                } else {
                    tuile.tourne();
                }
            }
        }
        return false;
    }

    public static boolean tuileIdentique(TuileModeleDomino tuile1, TuileModeleDomino tuile2) {
        if (!sameListe(tuile1.listeHaut, tuile2.listeHaut) || !sameListe(tuile1.listeBas, tuile2.listeBas)
                || !sameListe(tuile1.listeDroite, tuile2.listeDroite)
                || !sameListe(tuile1.listeGauche, tuile2.listeGauche)) {
            return false;
        }
        return true;
    }

    public static boolean sameListe(int[] tab1, int[] tab2) {
        for (int i = 0; i < 3; i++) {
            if (tab1[i] != tab2[i])
                return false;
        }
        return true;
    }

    public int[] copieListe(int[] list) {
        int[] temp = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[i];
        }
        return temp;
    }

    public int[] reverseListe(int[] list) {
        int[] temp = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[list.length - 1 - i];
        }
        return temp;
    }

    public void tourne() {
        int[] temp = copieListe(listeHaut);
        listeHaut = reverseListe(listeGauche);
        listeGauche = copieListe(listeBas);
        listeBas = reverseListe(listeDroite);
        listeDroite = copieListe(temp);
    }

    public boolean compareHaut(TuileModeleDomino tuile) {
        return Arrays.equals(listeHaut, tuile.listeBas);
    }

    public boolean compareDroite(TuileModeleDomino tuile) {
        return Arrays.equals(listeDroite, tuile.listeGauche);
    }

    public boolean compareBas(TuileModeleDomino tuile) {
        return Arrays.equals(listeBas, tuile.listeHaut);
    }

    public boolean compareGauche(TuileModeleDomino tuile) {
        return Arrays.equals(listeGauche, tuile.listeDroite);
    }

    public void ajouterTuileSurCase(CaseVue caseVue) {
        caseVue.ajouterTuile(vue);
    }
}
