package src.main.java.Scene;

import javax.swing.JPanel;
import java.awt.*;

public abstract class Scene extends JPanel {
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static int screenSizeWidth = (int) screenSize.getWidth();
    protected static int screenSizeHeight = (int) screenSize.getHeight();

    public Scene(boolean visible) {
        setSize(screenSize);
        setVisible(visible);
    }

    public static void changerDeScene(Scene depart, Scene arrive) {
        /*
         * Fonction permettant une transition statique entre les différentes pages.
         */
        depart.setVisible(false);
        arrive.setVisible(true);
    }

    // public static void animate(JComponent composant, int arriveX, int arriveY,
    // int frequence, int intervalle,
    // Scene depart, Scene arrive) {
    // /*
    // * Fonction permettant une transition dynamique entre les différentes pages.
    // */
    // composant.setVisible(true);
    // double oldX = composant.getBounds().x;
    // double oldY = composant.getBounds().y;
    // double animFrameX = (arriveX - oldX) / frequence; // la position du composant
    // // a chaque frame de l'animation (en
    // // X)
    // double animFrameY = (arriveY - oldY) / frequence; // la position du composant
    // // a chaque frame de l'animation (en
    // // Y)

    // new Timer(intervalle, (ActionListener) new ActionListener() {
    // int frameCourante = 0;

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // composant.setBounds((int) (oldX + (animFrameX * frameCourante)),
    // (int) (oldY + (animFrameY * frameCourante)),
    // composant.getWidth(), composant.getHeight());
    // if (frameCourante != frequence) {
    // frameCourante++;
    // } else {
    // ((Timer) e.getSource()).stop();
    // changerDeScene(depart, arrive);
    // double oldX = composant.getBounds().x;
    // double oldY = composant.getBounds().y;
    // double animFrameX = (screenSizeWidth - oldX) / frequence;
    // // la position du composant a chaque frame de l'animation (en X)
    // double animFrameY = (0 - oldY) / frequence;
    // // la position du composant a chaque frame de l'animation (en Y)
    // new Timer(intervalle, (ActionListener) new ActionListener() {
    // int frameCourante = 0;

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // composant.setBounds((int) (oldX + (animFrameX * frameCourante)),
    // (int) (oldY + (animFrameY * frameCourante)),
    // composant.getWidth(), composant.getHeight());
    // if (frameCourante != frequence) {
    // frameCourante++;
    // } else {
    // ((Timer) e.getSource()).stop();
    // }
    // }
    // }).start();
    // }
    // }
    // }).start();
    // System.out.println(arriveX);
    // }

    // public static void changerDeSceneAnime(Scene depart, Scene arrive, Scene
    // transition) {
    // animate(transition, 0, 0, 50, 2, depart, arrive);
    // // animate(transition, screenSizeWidth, 0, 25, 1);
    // }
}
