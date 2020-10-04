package main.java.com.liudi.game;

import java.awt.*;

public class Collider {
    static Rectangle rect1;
    static Rectangle rect2;
    static boolean collide(Rectangle rect1,Rectangle rect2){
        rect1 = rect1;
        rect2 = rect2;
        return rect1.intersects(rect2);
    }
}
