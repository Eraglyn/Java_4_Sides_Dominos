package src.main.java.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import src.main.java.Objet.Joueur.JoueurModele;
import src.main.java.Objet.Plateau.PlateauModeleDomino;
import src.main.java.Objet.Plateau.PlateauVue;
import src.main.java.Objet.Tuile.TuileVue;
import src.main.java.Objet.Tuile.TuileVueDomino;
import src.main.java.Scene.SceneDomino;

public class DominoGame {
    protected SceneDomino scene;
    protected PlateauVue plateau;
    protected TuileVue tuileCourante;
    protected JoueurModele joueurCourant;
    protected int indexJoueur;
    protected JoueurModele joueurTest;
    protected ArrayList<JoueurModele> listeJoueur;

    public DominoGame(SceneDomino scene, ArrayList<JoueurModele> listeJoueur) {
        this.scene = scene;
        scene.setGame(this);
        setupScene();
        plateau = scene.getPlateau();
        this.listeJoueur = listeJoueur;
        indexJoueur = 0;
        joueurCourant = listeJoueur.get(indexJoueur);
        tourDeJeu();
    }

    public void setupScene() {
        // ajout du plateau
        scene.setupPlateau();
        // ajout du bouton defausser
        scene.setupBoutonDefausse();
        // ajout du bouton tourner la tuile
        scene.setupBoutonTourne();
    }

    public TuileVue getTuileCourante() {
        return tuileCourante;
    }

    public boolean aucunJoueurEnJeu() {

        for (JoueurModele joueur : listeJoueur) {
            if (joueur.estIA() || !joueur.aAbandonne())
                return false;

        }
        return true;
    }

    public boolean partieFini() {
        return ((PlateauModeleDomino) plateau.getModele()).getPioche().estVide() || aucunJoueurEnJeu();
    }

    public JoueurModele prochainJoueur() {
        indexJoueur = (indexJoueur + 1) % listeJoueur.size();
        joueurCourant = listeJoueur.get(indexJoueur);
        return joueurCourant;
    }

    public ArrayList<JoueurModele> JoueurGagnant() {
        ArrayList<JoueurModele> podium = new ArrayList<JoueurModele>();
        for (JoueurModele joueur : JoueurModele.getListeJoueur()) {
            podium.add(joueur);
        }
        Collections.sort(podium, new Comparator<JoueurModele>() {
            @Override
            public int compare(JoueurModele j1, JoueurModele j2) {
                if (j1.getScore() > j2.getScore())
                    return -1;
                else if (j1.getScore() < j2.getScore())
                    return 1;
                else
                    return 0;
            }
        });
        return podium;
    }

    public void tourDeJeu() {
        if (partieFini()) {
            // Changer la scène
            ArrayList<JoueurModele> podium = JoueurGagnant();
            System.out.println("La partie est finit ! ");

            if (podium.get(0).estIA()) {
                System.out.println("L'IA " + podium.get(0).getNom() + " finit premier avec un total de "
                        + podium.get(0).getScore() + " points. Félicitations !");
            } else {
                if (podium.get(0).aAbandonne()) {
                    System.out.println("Le joueur " + podium.get(0).getNom()
                            + " qui a abandonné finit quand même premier avec un total de "
                            + podium.get(0).getScore() + " points. Félicitations !");
                } else {
                    System.out.println(
                            "Le joueur " + podium.get(0).getNom() + " finit premier avec un total de "
                                    + podium.get(0).getScore() + " points. Félicitations !");
                }

            }

            for (int c = 1; c < podium.size(); c++) {
                JoueurModele joueur = podium.get(c);

                if (joueur.estIA()) {
                    System.out.println(
                            "L'IA " + joueur.getNom() + " finit en " + (c + 1) + "ème position avec un total de "
                                    + joueur.getScore() + " points.");
                } else {
                    if (joueur.aAbandonne()) {
                        System.out.println("Le joueur " + joueur.getNom() + " qui a abandonné finit en " + (c + 1)
                                + "ème position avec un total de "
                                + joueur.getScore() + " points.");
                    } else {
                        System.out.println(
                                "Le joueur " + joueur.getNom() + " finit en " + (c + 1)
                                        + "ème position avec un total de "
                                        + joueur.getScore() + " points.");
                    }

                }

            }
        } else {
            System.out.println(joueurCourant.getNom());
            if (!joueurCourant.aAbandonne()) {
                tuileCourante = ((PlateauModeleDomino) plateau.getModele()).piocher();
                ((TuileVueDomino) tuileCourante).getModele().setJoueur(joueurCourant);
                if (joueurCourant.estIA()) {
                    tourDeIA();
                }
            }
            prochainJoueur();
        }
    }

    public void tourDeIA() {
        if (!((PlateauModeleDomino) plateau.getModele()).placerTuileIA(joueurCourant,
                ((TuileVueDomino) tuileCourante).getModele())) {
            prochainJoueur();
            defausse();
        } else {
            prochainJoueur();
            tourDeJeu();
        }
    }

    public void defausse() {
        tuileCourante.setVisible(false);
        tourDeJeu();
    }
}
