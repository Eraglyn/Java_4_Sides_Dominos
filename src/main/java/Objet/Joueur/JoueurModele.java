package src.main.java.Objet.Joueur;

import java.util.ArrayList;

public class JoueurModele {
    // Liste des joueurs dans la partie
    protected static ArrayList<JoueurModele> listeJoueur = new ArrayList<JoueurModele>();

    // Atributs du joueur
    protected String nom;
    protected int score;
    protected boolean abandonne;
    protected boolean ia;

    public JoueurModele(String nom, boolean ia) {
        this.nom = nom;
        this.ia = ia;
        abandonne = false;
        score = 0;
        listeJoueur.add(this);
    }

    public static ArrayList<JoueurModele> getListeJoueur() {
        return listeJoueur;
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