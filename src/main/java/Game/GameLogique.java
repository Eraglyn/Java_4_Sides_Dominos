package src.main.java.Game;

import java.awt.Rectangle;
import java.awt.Point;

public class GameLogique {
    public static boolean contiens(Rectangle r, Point p) {
        return r.getX() < p.getX() && r.getX() + 75 >= p.getX()
                && r.getY() < p.getY() && r.getY() + 75 >= p.getY();
    }
}
