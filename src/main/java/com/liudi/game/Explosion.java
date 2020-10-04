package main.java.com.liudi.game;

import java.awt.*;

public class Explosion {
    private int step = 0;
    private int type;
    boolean isAlive;
    int x;
    int y;

    public Explosion(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.step = 0;
        isAlive = true;
    }

    public void paint(Graphics g) {
        switch (type) {
            case 1:
                g.drawImage(ResourceManager.explosion1[step++], x, y, null);
                if(step==ResourceManager.explosion1.length)
                    this.isAlive=false;
                break;
            case 2:
                g.drawImage(ResourceManager.explosion2[step++], x, y, null);
                if(step==ResourceManager.explosion2.length)
                    this.isAlive=false;
                break;
            default:
                break;
        }
    }
}
