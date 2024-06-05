package src.main.java.Objet.Joueur;

import src.main.java.Game.DominoGameTerminal;
import java.util.ArrayList;

public class JoueurModeleTerminal {
    // Liste des joueurs dans la partie
    protected static ArrayList<JoueurModeleTerminal> listeJoueur = new ArrayList<JoueurModeleTerminal>();

    // Atributs du joueur
    protected String nom;
    protected int score;
    protected boolean abandonne;
    protected boolean ia;

    public JoueurModeleTerminal(String nom, boolean ia) {
        this.nom = nom;
        this.ia = ia;
        abandonne = false;
        score = 0;
        listeJoueur.add(this);
    }

    public static ArrayList<JoueurModeleTerminal> getListeJoueur() {
        return listeJoueur;
    }

    public static void afficherLesScores() throws InterruptedException {
        for (JoueurModeleTerminal joueur : listeJoueur) {
            DominoGameTerminal.printDynamique(joueur.toString(), 25, true);
        }
    }

    @Override
    public String toString() {
        if (!ia) {
            if (abandonne) {
                return "Le joueur " + nom + " a abandonné la partie. Il avait pourtant gagné " + score + " points.";
            } else
                return "Le joueur " + nom + " est encore en jeu avec " + score + " points.";
        } else {
            return "L'IA " + nom + " est encore en jeu avec " + score + " points.";
        }
    }

    public int getScore() {
        return score;
    }

    public void addScore(int i) {
        score += i;
    }

    public String getNom() {
        return nom;
    }

    public boolean estIA() {
        return ia;
    }

    public boolean aAbandonne() {
        return abandonne;
    }

    public void abandonne() {
        abandonne = true;
    }
}