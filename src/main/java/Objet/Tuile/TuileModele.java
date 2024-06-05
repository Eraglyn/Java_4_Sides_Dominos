package src.main.java.Objet.Tuile;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import src.main.java.Game.DominoGame;
import src.main.java.Game.GameLogique;
import src.main.java.Objet.Case.CaseVue;
import src.main.java.Objet.Joueur.JoueurModele;
import src.main.java.Objet.Plateau.*;
import src.main.java.Scene.SceneDomino;

public class TuileModele {
    protected int posX;
    protected int posY;
    protected boolean estPlace;
    protected boolean estDefausse;

    protected MouvementTuile mouvement;
    protected TuileVue vue;
    protected PlateauVue plateauVue;
    protected JoueurModele joueur;

    public TuileModele() {

    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public boolean getEstPlace() {
        return estPlace;
    }

    public void setEstPlace(boolean place) {
        estPlace = place;
        mouvement = null;
    }

    public boolean getEstDefausse() {
        return estDefausse;
    }

    public void setEstDefausse(boolean defausse) {
        estDefausse = defausse;
    }

    public void setJoueur(JoueurModele joueurModele) {
        joueur = joueurModele;
    }

    public TuileVue getVue() {
        return vue;
    }

    public void setPositionCentre() {
        vue.setBounds(873, 380, 75, 75);
    }

    public class MouvementTuile implements MouseInputListener {

        protected boolean estMobile;
        protected TuileModele tuile;

        public MouvementTuile(TuileModele tuileModele) {
            tuile = tuileModele;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!estPlace) {
                estMobile = !estMobile;
                int mouseX = vue.getX() + e.getX();
                int mouseY = vue.getY() + e.getY();

                if (!estMobile) {
                    for (int ligne = 0; ligne < plateauVue.getPlateau().length; ligne++) {
                        for (int colonne = 0; colonne < plateauVue.getPlateau()[ligne].length; colonne++) {
                            if (GameLogique.contiens(plateauVue.getPlateau()[ligne][colonne].getForme(),
                                    vue.getCoordoCentre())) {
                                if (((PlateauModeleDomino) plateauVue.getModele()).estPlacable(
                                        (TuileModeleDomino) tuile, ligne, colonne)) {

                                    ((PlateauModeleDomino) plateauVue.getModele()).placerTuile(joueur,
                                            (TuileModeleDomino) tuile, ligne, colonne);
                                    ((SceneDomino) plateauVue.getScene()).getGame().prochainJoueur();
                                    ((SceneDomino) plateauVue.getScene()).getGame().tourDeJeu();
                                } else {
                                    vue.setBounds(40, 40, 75, 75);
                                }
                            }
                        }
                    }
                } else {
                    if (mouseX <= 37 || mouseX >= vue.getParent().getWidth() - 37) {
                        if (mouseY >= 37 && mouseY <= vue.getParent().getHeight() - 37) {
                            vue.setLocation(vue.getX(), mouseY - 37);
                        } else {
                            vue.setLocation(vue.getX(), vue.getY());
                        }
                    } else if (mouseY <= 37 || mouseY >= vue.getParent().getHeight() - 37) {
                        if (mouseX >= 37 && mouseX <= vue.getParent().getWidth() - 37) {
                            vue.setLocation(mouseX - 37, vue.getY());
                        } else {
                            vue.setLocation(vue.getX(), vue.getY());
                        }
                    } else
                        vue.setLocation(mouseX - 37, mouseY - 37);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (estMobile) {
                int mouseX = vue.getX() + e.getX();
                int mouseY = vue.getY() + e.getY();
                // System.out.println(vue.getX() + " " + vue.getY());
                if (mouseX <= 37 || mouseX >= vue.getParent().getWidth() - 37) {
                    if (mouseY >= 37 && mouseY <= vue.getParent().getHeight() - 37) {
                        vue.setLocation(vue.getX(), mouseY - 37);
                    } else {
                        vue.setLocation(vue.getX(), vue.getY());
                    }
                } else if (mouseY <= 37 || mouseY >= vue.getParent().getHeight() - 37) {
                    if (mouseX >= 37 && mouseX <= vue.getParent().getWidth() - 37) {
                        vue.setLocation(mouseX - 37, vue.getY());
                    } else {
                        vue.setLocation(vue.getX(), vue.getY());
                    }
                } else
                    vue.setLocation(mouseX - 37, mouseY - 37);

                for (CaseVue[] ligne : plateauVue.getPlateau()) {
                    for (CaseVue caseVue : ligne) {
                        if (GameLogique.contiens(caseVue.getForme(), vue.getCoordoCentre())) {
                            caseVue.setBackground(Color.GREEN);
                        } else {
                            // System.out.println(caseVue.getX() + " " + caseVue.getY());
                            caseVue.setBackground(new Color(52, 152, 219));
                        }
                    }
                }
            }
        }

    }
}
