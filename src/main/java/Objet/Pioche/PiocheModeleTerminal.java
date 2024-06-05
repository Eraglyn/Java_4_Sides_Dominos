package src.main.java.Objet.Pioche;

import src.main.java.Objet.Tuile.TuileModeleTerminal;

import java.util.ArrayList;

public class PiocheModeleTerminal extends PiocheModele {
    private ArrayList<TuileModeleTerminal> pioche;
    private int indexProchaineTuile;

    public PiocheModeleTerminal() {
        super(null);
        pioche = new ArrayList<TuileModeleTerminal>();
    }

    public PiocheModeleTerminal(int n) {
        super(n, null);
        pioche = new ArrayList<TuileModeleTerminal>();
        for (int i = 0; i < taille; i++) {
            pioche.add(new TuileModeleTerminal());
        }
        indexProchaineTuile = taille - 1;
    }

    public ArrayList<TuileModeleTerminal> getPioche() {
        return pioche;
    }

    public TuileModeleTerminal piocher() {
        try {
            TuileModeleTerminal tuile = pioche.get(indexProchaineTuile);
            pioche.remove(indexProchaineTuile);
            indexProchaineTuile = pioche.size() - 1;
            taille--;
            return tuile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
