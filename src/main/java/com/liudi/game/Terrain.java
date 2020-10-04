package main.java.com.liudi.game;

import java.awt.*;

public abstract class Terrain extends GameObject{
    public Terrain(int x, int y){
        this.x =x;
        this.y =y;
        this.group = Group.NEUTRAL;
        isAlive =true;
    }
    public void paint(Graphics g){
        g.drawImage(image,x,y,null);
        //System.out.println("width:"+width+",height:"+height);
        //System.out.println("update");
        //g.drawRect();
    }
}
