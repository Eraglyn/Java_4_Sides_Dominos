package src.main.java.Scene;

import java.awt.Color;

public class SceneTransition extends Scene {

    public SceneTransition(boolean visible) {
        super(visible);
        setBackground(Color.WHITE);
        setLocation(screenSizeWidth, 0);
        System.out.println(getBounds());
    }

}

