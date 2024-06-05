package src.main.java.Objet.Tuile;

import src.main.java.Objet.Plateau.PlateauModeleTerminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TuileModeleTerminal {
    protected int[] listeHaut;
    protected int[] listeBas;
    protected int[] listeDroite;
    protected int[] listeGauche;
    protected char id;
    protected static ArrayList<TuileModeleTerminal> listeTuile = new ArrayList<TuileModeleTerminal>();
    protected static char[] listeId = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '*' };
    protected static int nombreTuile = 0;
    protected int posX;
    protected int posY;
    protected int scoreMarque;
    protected TuileVue vue;
    protected boolean estPlace;

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

    public char getId() {
        return id;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public int getScore() {
        return scoreMarque;
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public TuileModeleTerminal() {
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
        if (listeTuile.contains(this)) { // pour eviter les doublons
            new TuileModeleTerminal();
        } else {
            id = listeId[nombreTuile];
            nombreTuile++;
            listeTuile.add(this);
        }
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

    public static TuileModeleTerminal getTuileParId(char id) {
        for (TuileModeleTerminal tuile : listeTuile) {
            if (tuile.getId() == id) {
                return tuile;
            }
        }
        return null;
    }

    public boolean placerTuile(PlateauModeleTerminal plateau, int posXPlacement, int posYPlacement) {
        if (plateau.getPlateau()[posXPlacement][posYPlacement] != null) {
            return false;
        }
        if (plateau.getPlateau()[posXPlacement - 1][posYPlacement] != null) { // on vérifie la case au dessus
            if (!(this.compareHaut(plateau.getPlateau()[posXPlacement - 1][posYPlacement]))) {
                return false;
            } else
                scoreMarque += this.listeHaut[0] + this.listeHaut[1] + this.listeHaut[2];
        }
        if (plateau.getPlateau()[posXPlacement][posYPlacement + 1] != null) { // on vérifie la case a droite
            if (!(this.compareDroite(plateau.getPlateau()[posXPlacement][posYPlacement + 1]))) {
                return false;
            } else
                scoreMarque += this.listeDroite[0] + this.listeDroite[1] + this.listeDroite[2];
        }
        if (plateau.getPlateau()[posXPlacement + 1][posYPlacement] != null) { // on vérifie la case en bas
            if (!(this.compareBas(plateau.getPlateau()[posXPlacement + 1][posYPlacement]))) {
                return false;
            } else
                scoreMarque += this.listeBas[0] + this.listeBas[1] + this.listeBas[2];
        }
        if (plateau.getPlateau()[posXPlacement][posYPlacement - 1] != null) { // on vérifie la case a gauche
            if (!(this.compareGauche(plateau.getPlateau()[posXPlacement][posYPlacement - 1]))) {
                return false;
            } else
                scoreMarque += this.listeGauche[0] + this.listeGauche[1] + this.listeGauche[2];
        }
        posX = posXPlacement;
        posY = posYPlacement;
        plateau.getPlateau()[posXPlacement][posYPlacement] = this;
        plateau.ajouterTuile(this);
        return true;
    }

    public boolean placerTuileIA(PlateauModeleTerminal plateau) {
        for (int i = 0; i < 4; i++) {
            this.tourne();
            for (TuileModeleTerminal tuile : plateau.getListeTuile()) {
                if (this.placerTuile(plateau, tuile.getX() - 1, tuile.getY())
                        || this.placerTuile(plateau, tuile.getX(), tuile.getY() + 1)
                        || this.placerTuile(plateau, tuile.getX() + 1, tuile.getY())
                        || this.placerTuile(plateau, tuile.getX(), tuile.getY() - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean compareHaut(TuileModeleTerminal tuile) {
        return Arrays.equals(listeHaut, tuile.listeBas);
    }

    public boolean compareDroite(TuileModeleTerminal tuile) {
        return Arrays.equals(listeDroite, tuile.listeGauche);
    }

    public boolean compareBas(TuileModeleTerminal tuile) {
        return Arrays.equals(listeBas, tuile.listeHaut);
    }

    public boolean compareGauche(TuileModeleTerminal tuile) {
        return Arrays.equals(listeGauche, tuile.listeDroite);
    }

    public void afficheTerminal() {
        System.out.println(" " + listeHaut[0] + " " + listeHaut[1] + " " + listeHaut[2] + " ");
        System.out.println(listeGauche[0] + " ".repeat(5) + listeDroite[0]);
        System.out.println(listeGauche[1] + " (" + id + ") " + listeDroite[1]);
        System.out.println(listeGauche[2] + " ".repeat(5) + listeDroite[2]);
        System.out.println(" " + listeBas[0] + " " + listeBas[1] + " " + listeBas[2] + " ");
    }
}
