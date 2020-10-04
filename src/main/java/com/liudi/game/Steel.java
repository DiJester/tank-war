package main.java.com.liudi.game;

import java.awt.*;

public class Steel extends Terrain{
    public Steel(int x, int y) {
        super(x, y);
        image = ResourceManager.steel;
        setDimension(image.getWidth(),image.getHeight());
        rect = new Rectangle(this.x,this.y,image.getWidth(),image.getHeight());
    }
}
