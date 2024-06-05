package src.main.java.Objet.Pioche;

import src.main.java.Objet.Plateau.PlateauVue;
import src.main.java.Objet.Tuile.TuileVueDomino;
import src.main.java.Scene.Scene;

import java.util.ArrayList;

public class PiocheModeleDomino extends PiocheModele {
    protected PlateauVue plateauVue;
    protected ArrayList<TuileVueDomino> pioche;
    protected int indexProchaineTuile;

    public PiocheModeleDomino(Scene scene) {
        super(scene);
        pioche = new ArrayList<TuileVueDomino>();
    }

    public PiocheModeleDomino(int n, PlateauVue plateau, Scene scene) {
        super(n, scene);
        plateauVue = plateau;
        pioche = new ArrayList<TuileVueDomino>();
        for (int i = 0; i < taille; i++) {
            TuileVueDomino tuile = new TuileVueDomino(plateauVue);
            scene.add(tuile);
            tuile.setVisible(false);
            pioche.add(tuile);
        }
        indexProchaineTuile = pioche.size() - 1;
    }

    public ArrayList<TuileVueDomino> getPioche() {
        return pioche;
    }

    public TuileVueDomino piocher() {
        try {
            if (!pioche.isEmpty()) {
                TuileVueDomino tuile = pioche.get(indexProchaineTuile);
                pioche.remove(indexProchaineTuile);
                indexProchaineTuile--;
                taille--;
                tuile.setVisible(true);
                return tuile;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
