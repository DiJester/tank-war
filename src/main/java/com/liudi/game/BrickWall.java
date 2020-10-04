package main.java.com.liudi.game;

import java.awt.*;

public class BrickWall extends Terrain{
    private int type;
    public BrickWall(int x, int y,int type) {
        super(x, y);
        this.type = type;
        image = ResourceManager.bricks[type];
        setDimension(image.getWidth(),image.getHeight());
        rect = new Rectangle(this.x,this.y,image.getWidth(),image.getHeight());
    }
}
