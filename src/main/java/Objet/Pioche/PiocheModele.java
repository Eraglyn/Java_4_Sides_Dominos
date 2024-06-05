package src.main.java.Objet.Pioche;

import src.main.java.Scene.Scene;

public class PiocheModele {
    protected int taille;
    protected Scene scene;

    public PiocheModele(Scene scene) {
        this.scene = scene;
    }

    public PiocheModele(int n, Scene scene) {
        taille = n;
        this.scene = scene;
    }

    public int getTaille() {
        return taille;
    }

    public boolean estVide() {
        return taille == 0;
    }
}
