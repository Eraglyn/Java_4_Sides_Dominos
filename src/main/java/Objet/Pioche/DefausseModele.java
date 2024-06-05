package src.main.java.Objet.Pioche;

import src.main.java.Objet.Tuile.TuileModele;
import java.util.ArrayList;

public class DefausseModele {
    protected ArrayList<TuileModele> pioche;

    public DefausseModele() {
        pioche = new ArrayList<TuileModele>();
    }

    public void defausserTuile(TuileModele tuile) {
        pioche.add(tuile);
    }
}
