package src.main.java.Game;

import src.main.java.Objet.Joueur.JoueurModeleTerminal;
import src.main.java.Objet.Plateau.PlateauModeleTerminal;
import src.main.java.Objet.Tuile.TuileModeleTerminal;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DominoGameTerminal {
    protected static int vitesseDeTexte = 30;
    protected static PlateauModeleTerminal plateau;
    protected static int nbTour = 1;

    // Print petit à petit en fonction de la vitesseDeTexte
    // afin d'avoir une impression d'animation
    public static void printDynamique(String text, int delay, boolean endOfLine) throws InterruptedException {

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Thread.sleep(delay);
        }

        if (endOfLine)
            System.out.println();
    }

    // Demande à l'utilisateur les paramètres pour créer la partie
    public static void setupDeJeu(Scanner scan) throws InterruptedException {

        printDynamique("DOMINOS :", vitesseDeTexte, true);
        printDynamique(
                "Choisissez la vitesse de défilement du texte, instantané (0), rapide (1), par défaut (2) ou lente (3) : ",
                vitesseDeTexte, false);
        String reponse = scan.nextLine();

        switch (reponse) {
            case "0" -> vitesseDeTexte = 0;
            case "1" -> vitesseDeTexte = 10;
            case "2" -> vitesseDeTexte = 30;
            case "3" -> vitesseDeTexte = 45;
        }
        while (!reponse.equals("0") && !reponse.equals("1") && !reponse.equals("2") && !reponse.equals("3")) {
            printDynamique("Erreur, réponse incorrect, veuillez réessayer. ", vitesseDeTexte, true);
            reponse = scan.nextLine();
            switch (reponse) {
                case "0" -> vitesseDeTexte = 0;
                case "1" -> vitesseDeTexte = 15;
                case "2" -> vitesseDeTexte = 30;
                case "3" -> vitesseDeTexte = 45;
            }
        }

        printDynamique("Nombre de joueur (De 2 à 4) : ", vitesseDeTexte, false);
        reponse = scan.nextLine();
        int nombreJoueur = 0;

        switch (reponse) {
            case "2" -> nombreJoueur = 2;
            case "3" -> nombreJoueur = 3;
            case "4" -> nombreJoueur = 4;
        }
        while (!reponse.equals("2") && !reponse.equals("3") && !reponse.equals("4")) {
            printDynamique("Erreur, réponse incorrect, veuillez réessayer. ", vitesseDeTexte, true);
            printDynamique("Nombre de joueur (De 2 à 4) : ", vitesseDeTexte, false);
            reponse = scan.nextLine();
            switch (reponse) {
                case "2" -> nombreJoueur = 2;
                case "3" -> nombreJoueur = 3;
                case "4" -> nombreJoueur = 4;
            }
        }

        for (int i = 1; i <= nombreJoueur; i++) {
            printDynamique("Nom du joueur " + i + " : ", vitesseDeTexte, false);
            String nom = scan.nextLine();

            printDynamique("Ce joueur est-il une IA ? (oui/non) : ", vitesseDeTexte, false);
            String ia = scan.nextLine();

            while (!(ia.equalsIgnoreCase("oui")) && !(ia.equalsIgnoreCase("non"))) {
                printDynamique("Erreur, réponse incorrect, veuillez réessayer. ", vitesseDeTexte, true);
                printDynamique("Ce joueur est-il une IA ? (oui/non) : ", vitesseDeTexte, false);
                ia = scan.nextLine();
            }

            if (ia.equalsIgnoreCase("oui"))
                new JoueurModeleTerminal(nom, true);
            else
                new JoueurModeleTerminal(nom, false);

        }

    }

    // Menu d'options pour afficher les scores,
    // changer la vitesse du texte ou quitter
    public static void optionDeJeu(Scanner scan) throws InterruptedException {
        printDynamique(
                "Voulez vous continuer le tour (c), afficher les scores (a), changer la vitesse de défilement du texte (v)  ou quitter la partie (q) ? ",
                vitesseDeTexte, true);
        String reponse = scan.nextLine();

        while (!(reponse.equalsIgnoreCase("c")) && !(reponse.equalsIgnoreCase("q"))) {

            if (reponse.equalsIgnoreCase("v")) {
                printDynamique(
                        "Choisissez la vitesse de défilement du texte, instantané (0), rapide (1), par défaut (2) ou lente (3) : ",
                        vitesseDeTexte, true);
                reponse = scan.nextLine();

                switch (reponse) {
                    case "0" -> vitesseDeTexte = 0;
                    case "1" -> vitesseDeTexte = 15;
                    case "2" -> vitesseDeTexte = 30;
                    case "3" -> vitesseDeTexte = 45;
                }
                while (!reponse.equals("0") && !reponse.equals("1") && !reponse.equals("2") && !reponse.equals("3")) {
                    printDynamique("Erreur, réponse incorrect, veuillez réessayer. ", vitesseDeTexte, true);
                    reponse = scan.nextLine();
                    switch (reponse) {
                        case "0" -> vitesseDeTexte = 0;
                        case "1" -> vitesseDeTexte = 15;
                        case "2" -> vitesseDeTexte = 30;
                        case "3" -> vitesseDeTexte = 45;
                    }
                }

            } else if (reponse.equalsIgnoreCase("a")) {

                JoueurModeleTerminal.afficherLesScores();

            } else {

                printDynamique("Erreur, réponse incorrect, veuillez réessayer ", vitesseDeTexte, true);

            }

            printDynamique(
                    "Voulez vous continuer le tour (c), afficher les scores (a), changer la vitesse de défilement de texte (v)  ou quitter la partie (q) ? ",
                    vitesseDeTexte, true);
            reponse = scan.nextLine();
        }

        if (reponse.equalsIgnoreCase("q") || reponse.equalsIgnoreCase("quit")) {
            System.exit(0);
        }
        printDynamique("Options sauvegardées !", vitesseDeTexte, true);
    }

    public static void tourDeJeu(PlateauModeleTerminal plateau, Scanner scan) throws InterruptedException {

        printDynamique("Début du tour " + nbTour + " !", vitesseDeTexte, true);
        for (JoueurModeleTerminal joueur : JoueurModeleTerminal.getListeJoueur()) {

            if (!joueur.aAbandonne() && !plateau.getPioche().estVide()) {

                printDynamique(joueur.toString(), vitesseDeTexte, true);
                printDynamique(joueur.getNom() + ", c'est à vous !", vitesseDeTexte, true);

                if (!joueur.estIA()) {
                    TuileModeleTerminal tuile = plateau.piocher();
                    tourDeJoueur(plateau, scan, joueur, tuile);
                } else {
                    TuileModeleTerminal tuile = plateau.piocher();
                    tourDeIA(plateau, scan, joueur, tuile);
                }

            }
        }
        System.out.println();
    }

    public static void tourDeJoueur(PlateauModeleTerminal plateau, Scanner scan, JoueurModeleTerminal joueur,
            TuileModeleTerminal tuile)
            throws InterruptedException {

        printDynamique("Il reste " + plateau.getTaillePioche() + " tuiles dans la pioche. ", vitesseDeTexte, true);
        printDynamique("Vous piochez la tuile suivante : ", vitesseDeTexte, true);
        tuile.afficheTerminal();

        printDynamique("Voici l'état du plateau :", vitesseDeTexte, true);
        plateau.afficheTerminal();

        printDynamique(
                "Voulez-vous la placer (p), defausser votre tuile (d), la tourner à 90° dans le sens horaire (t), abandonner la partie (a) ou acceder aux options (o) ? ",
                vitesseDeTexte, true);
        String reponse = scan.nextLine();

        while (!reponse.equalsIgnoreCase("p") && !reponse.equalsIgnoreCase("a") && !reponse.equalsIgnoreCase("d")) {

            if (reponse.equalsIgnoreCase("o")) {
                optionDeJeu(scan);
                printDynamique("Vous piochez la tuile suivante : ", vitesseDeTexte, true);
            } else {
                if (!reponse.equalsIgnoreCase("t")) {
                    printDynamique("Erreur, réponse incorrect, veuillez réessayer ", vitesseDeTexte, true);
                    printDynamique("Vous avez pioché la tuile suivante : ", vitesseDeTexte, true);
                } else {
                    tuile.tourne();
                    printDynamique("Vous avez tourner la tuile : ", vitesseDeTexte, true);
                }
            }
            tuile.afficheTerminal();

            printDynamique("Voici l'état du plateau :", vitesseDeTexte, true);
            plateau.afficheTerminal();

            printDynamique(
                    "Voulez-vous la placer (p), defausser votre tuile (d), la tourner à 90° dans le sens horaire (t), abandonner la partie (a) ou acceder aux options (o) ? ",
                    vitesseDeTexte, true);
            reponse = scan.nextLine();

        }

        if (reponse.equalsIgnoreCase("a")) {
            joueur.abandonne();
            printDynamique("Le joueur " + joueur.getNom() + " a abandonné la partie. Il avait pourtant gagné "
                    + joueur.getScore() + " points.", vitesseDeTexte, true);
        }
        if (reponse.equalsIgnoreCase("p"))
            tourPlacement(plateau, scan, joueur, tuile);
        if (reponse.equalsIgnoreCase("d"))
            plateau.defausse(tuile);
    }

    public static void tourDeIA(PlateauModeleTerminal plateau, Scanner scan, JoueurModeleTerminal joueur,
            TuileModeleTerminal tuile)
            throws InterruptedException {

        printDynamique("Il reste " + plateau.getTaillePioche() + " tuiles dans la pioche.",
                vitesseDeTexte, true);
        printDynamique("L'IA " + joueur.getNom() + " pioche la tuile suivante :", vitesseDeTexte, true);
        tuile.afficheTerminal();

        printDynamique("Voici l'état du plateau :", vitesseDeTexte, true);
        plateau.afficheTerminal();

        if (tuile.placerTuileIA(plateau)) {
            joueur.addScore(tuile.getScore());
            printDynamique("L'IA " + joueur.getNom() + " a réussi à placer sa tuile.", vitesseDeTexte, true);
        } else {
            printDynamique("L'IA " + joueur.getNom() + " n'a pas réussi à placer sa tuile, elle l'a donc défaussé.",
                    vitesseDeTexte, true);
        }
    }

    public static void tourPlacement(PlateauModeleTerminal plateau, Scanner scan, JoueurModeleTerminal joueur,
            TuileModeleTerminal tuile)
            throws InterruptedException {

        printDynamique(
                "Indiquez à côté de quelle tuile souhaitez-vous placer votre tuile (caractère entre () au milieu d'une tuile), retour en arrière (retour) : ",
                vitesseDeTexte, true);
        String reponse = scan.nextLine();

        if (reponse.equalsIgnoreCase("retour")) {
            tourDeJoueur(plateau, scan, joueur, tuile);
        } else if (idCorrecte(reponse)) {
            String id = reponse;
            printDynamique(
                    "Dans quelle direction souhaitez-vous placer votre tuile (Gauche (g), Haut (h), Droite (d), Bas (b)), retour en arrière (retour) : ",
                    vitesseDeTexte, true);
            reponse = scan.nextLine();

            if (reponse.equalsIgnoreCase("retour")) {
                tourPlacement(plateau, scan, joueur, tuile);
            } else if (estUneDirection(reponse)) {
                String direction = reponse;

                if (placerTuile(joueur, plateau, tuile, id + " " + direction)) {
                    joueur.addScore(tuile.getScore());
                    printDynamique("Votre tuile a bien été placée. Vous avez marqué " + tuile.getScore() + " points. ",
                            vitesseDeTexte, true);
                } else {
                    printDynamique("Vous ne pouvez pas placer une tuile à cette position, veuillez réessayer. ",
                            vitesseDeTexte, true);
                    tourPlacement(plateau, scan, joueur, tuile);
                }

            } else {
                printDynamique("Erreur, réponse incorrect, veuillez réessayer ", vitesseDeTexte, true);
                tourPlacement(plateau, scan, joueur, tuile);
            }

        } else {
            printDynamique("Erreur, réponse incorrect, veuillez réessayer ", vitesseDeTexte, true);
            tourPlacement(plateau, scan, joueur, tuile);
        }
    }

    public static boolean estUnId(String reponse) {

        if (reponse.length() != 1) {
            return false;
        }

        return (reponse.charAt(0) >= 'a' && reponse.charAt(0) <= 'z')
                || (reponse.charAt(0) >= 'A' && reponse.charAt(0) <= 'Z')
                || (reponse.charAt(0) == '*');
    }

    public static boolean estSurLePlateau(char i) {
        return plateau.getListeTuile().contains(TuileModeleTerminal.getTuileParId(Character.toUpperCase(i)));
    }

    public static boolean idCorrecte(String reponse) {
        return estUnId(reponse) && estSurLePlateau(reponse.charAt(0));
    }

    public static boolean estUneDirection(String reponse) {
        return reponse.equals("g") || reponse.equals("h") || reponse.equals("d") || reponse.equals("b");
    }

    public static boolean aucunJoueurEnJeu() throws InterruptedException {

        for (JoueurModeleTerminal joueur : JoueurModeleTerminal.getListeJoueur()) {

            if (joueur.estIA() || !joueur.aAbandonne())
                return false;

        }

        printDynamique("Il ne reste plus aucun joueur pouvant jouer la partie. Elle s'est donc terminée.",
                vitesseDeTexte, true);
        return true;
    }

    public static boolean partieFini() throws InterruptedException {
        return plateau.getPioche().estVide() || aucunJoueurEnJeu();
    }

    public static void JoueurGagnant() throws InterruptedException {
        ArrayList<JoueurModeleTerminal> podium = new ArrayList<JoueurModeleTerminal>();
        for (JoueurModeleTerminal joueur : JoueurModeleTerminal.getListeJoueur()) {
            podium.add(joueur);
        }
        Collections.sort(podium, new Comparator<JoueurModeleTerminal>() {
            @Override
            public int compare(JoueurModeleTerminal j1, JoueurModeleTerminal j2) {
                if (j1.getScore() > j2.getScore())
                    return -1;
                else if (j1.getScore() < j2.getScore())
                    return 1;
                else
                    return 0;
            }
        });

        printDynamique("Voici l'état final du plateau :", vitesseDeTexte, true);
        plateau.afficheTerminal();
        printDynamique("La partie est finit ! Elle s'est déroulé en " + nbTour + " tours.", vitesseDeTexte, true);

        if (podium.get(0).estIA()) {
            printDynamique("L'IA " + podium.get(0).getNom() + " finit premier avec un total de "
                    + podium.get(0).getScore() + " points. Félicitations !", vitesseDeTexte, true);
        } else {
            if (podium.get(0).aAbandonne()) {
                printDynamique("Le joueur " + podium.get(0).getNom()
                        + " qui a abandonné finit quand même premier avec un total de "
                        + podium.get(0).getScore() + " points. Félicitations !", vitesseDeTexte, true);
            } else {
                printDynamique(
                        "Le joueur " + podium.get(0).getNom() + " finit premier avec un total de "
                                + podium.get(0).getScore() + " points. Félicitations !",
                        vitesseDeTexte, true);
            }

        }

        for (int c = 1; c < podium.size(); c++) {
            JoueurModeleTerminal joueur = podium.get(c);

            if (joueur.estIA()) {
                printDynamique("L'IA " + joueur.getNom() + " finit en " + (c + 1) + "ème position avec un total de "
                        + joueur.getScore() + " points.", vitesseDeTexte, true);
            } else {
                if (joueur.aAbandonne()) {
                    printDynamique("Le joueur " + joueur.getNom() + " qui a abandonné finit en " + (c + 1)
                            + "ème position avec un total de "
                            + joueur.getScore() + " points.", vitesseDeTexte, true);
                } else {
                    printDynamique(
                            "Le joueur " + joueur.getNom() + " finit en " + (c + 1) + "ème position avec un total de "
                                    + joueur.getScore() + " points.",
                            vitesseDeTexte, true);
                }

            }

        }

    }

    public static boolean placerTuile(JoueurModeleTerminal joueur, PlateauModeleTerminal plateau,
            TuileModeleTerminal tuile, String placement) {
        int posXPlacement, posYPlacement, posXDepart, posYDepart;
        posXDepart = TuileModeleTerminal.getTuileParId(Character.toUpperCase(placement.charAt(0))).getX();
        posYDepart = TuileModeleTerminal.getTuileParId(Character.toUpperCase(placement.charAt(0))).getY();

        if (placement.substring(2).equalsIgnoreCase("g")) {
            posXPlacement = posXDepart;
            posYPlacement = posYDepart - 1;

        } else if (placement.substring(2).equalsIgnoreCase("h")) {
            posXPlacement = posXDepart - 1;
            posYPlacement = posYDepart;

        } else if (placement.substring(2).equalsIgnoreCase("d")) {
            posXPlacement = posXDepart;
            posYPlacement = posYDepart + 1;

        } else {
            posXPlacement = posXDepart + 1;
            posYPlacement = posYDepart;

        }
        if (tuile.placerTuile(plateau, posXPlacement, posYPlacement)) {
            plateau.getPlateau()[posXPlacement][posYPlacement] = tuile;
            return true;
        }
        return false;
    }

    public static void start(InputStream in) {
        try {

            Scanner scanner = new Scanner(in);
            plateau = new PlateauModeleTerminal();
            setupDeJeu(scanner);

            while (!partieFini()) {
                tourDeJeu(plateau, scanner);
                nbTour++;
            }

            JoueurGagnant();

            scanner.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
