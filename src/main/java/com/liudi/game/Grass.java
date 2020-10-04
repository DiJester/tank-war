package main.java.com.liudi.game;

import java.awt.*;

public class Grass extends Terrain{


    public Grass(int x, int y) {
        super(x, y);
        image = ResourceManager.grass;
        setDimension(image.getWidth(),image.getHeight());
        rect = new Rectangle(this.x,this.y,image.getWidth(),image.getHeight());
    }
}
