package src.main.java.Main;

import src.main.java.Scene.SceneMenuPrincipale;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
    protected static JFrame application;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void Start() {
        application = new JFrame();
        application.setTitle("Menu principale");
        application.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application.setVisible(true);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.getContentPane().add(new SceneMenuPrincipale(true));
    }

    public static JFrame getApplication() {
        return application;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Start();
            }
        });
    }
}
