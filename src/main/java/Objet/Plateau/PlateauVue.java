package src.main.java.Objet.Plateau;

// awt
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

// swing
import javax.swing.JPanel;
// others
import src.main.java.Objet.Case.CaseVue;
import src.main.java.Objet.Tuile.TuileVueDomino;
import src.main.java.Scene.Scene;

public class PlateauVue extends JPanel {
    protected PlateauModele modele;
    protected Scene scene;

    public PlateauVue() {
        modele = new PlateauModele(this);
        plateauSetup();
    }

    public PlateauVue(String gameMode, Scene scene) {
        this.scene = scene;
        if (gameMode.equals("domino")) {
            modele = new PlateauModeleDomino(this);
            plateauSetup();
        } else if (gameMode.equals("carcassonne")) {
            modele = new PlateauModeleCarcassonne(this);
            plateauSetup();
        }
    }

    public CaseVue[][] getPlateau() {
        return modele.getPlateau();
    }

    public PlateauModele getModele() {
        return modele;
    }

    public Scene getScene() {
        return scene;
    }

    public void plateauSetup() {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setVisible(true);
        setBounds(322, 100, 1275, 825);
        for (int ligne = 0; ligne < 11; ligne++) {
            for (int colonne = 0; colonne < 17; colonne++) {
                gbc.gridx = colonne;
                gbc.gridy = ligne;
                CaseVue tempo = new CaseVue(this, ligne, colonne);
                modele.getPlateau()[ligne][colonne] = tempo;
                add(tempo, gbc);
                if (ligne == 5 && colonne == 8) {
                }
            }
        }
        TuileVueDomino tuile = ((PlateauModeleDomino) modele).piocher();
        ((PlateauModeleDomino) modele).placer1ereTuile(tuile.getModele());
    }

    public void print() {
        for (int ligne = 0; ligne < 11; ligne++) {
            for (int colonne = 0; colonne < 17; colonne++) {
                System.out.println(modele.getPlateau()[ligne][colonne].getForme());
            }
        }
    }
}
