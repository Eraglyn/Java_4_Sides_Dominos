package src.main.java.Objet.Plateau;

import src.main.java.Objet.Case.CaseVue;
import src.main.java.Objet.Joueur.*;
import src.main.java.Objet.Pioche.*;
import src.main.java.Objet.Tuile.TuileModele;

import java.util.ArrayList;

public class PlateauModele {

    protected DefausseModele defausse;
    protected ArrayList<JoueurModele> listeJoueur;
    protected CaseVue[][] plateau;
    protected PlateauVue vue;

    public PlateauModele(PlateauVue plateauVue) {
        vue = plateauVue;
        listeJoueur = JoueurModele.getListeJoueur();
        defausse = new DefausseModele();
        plateau = new CaseVue[11][17];
    }

    public CaseVue[][] getPlateau() {
        return plateau;
    }

    public DefausseModele getDefausse() {
        return defausse;
    }

    public void defausse(TuileModele tuile) {
        defausse.defausserTuile(tuile);
    }

    public ArrayList<JoueurModele> getListeJoueur() {
        return listeJoueur;
    }
}