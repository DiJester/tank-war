package main.java.com.liudi.game;

import java.awt.*;

public class SnowField extends Terrain{


    public SnowField(int x, int y) {
        super(x, y);
        image = ResourceManager.snowfield;
        setDimension(image.getWidth(),image.getHeight());
        rect = new Rectangle(this.x,this.y,image.getWidth(),image.getHeight());
    }
}
