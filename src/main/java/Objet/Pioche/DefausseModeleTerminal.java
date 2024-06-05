package src.main.java.Objet.Pioche;

import java.util.ArrayList;

import src.main.java.Objet.Tuile.TuileModeleTerminal;

public class DefausseModeleTerminal {
    protected ArrayList<TuileModeleTerminal> pioche;

    public DefausseModeleTerminal() {
        pioche = new ArrayList<TuileModeleTerminal>();
    }

    public void defausserTuile(TuileModeleTerminal tuile) {
        pioche.add(tuile);
    }
}
