package main.java.com.liudi.game;

import java.awt.*;

public class Bullet extends GameObject{

    public static int BULLET_SPEED = PropertyManager.getInt("BulletSpeed");
    //boolean isAlive;

    public Bullet(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        isAlive =true;
        switch(this.dir){
            case UP:
                image = ResourceManager.bulletU;
                setDimension(image.getWidth(),image.getHeight());
                break;
            case DOWN:
                image = ResourceManager.bulletD;
                setDimension(image.getWidth(),image.getHeight());
                break;
            case LEFT:
                image = ResourceManager.bulletL;
                setDimension(image.getWidth(),image.getHeight());
                break;
            case RIGHT:
                image = ResourceManager.bulletR;
                setDimension(image.getWidth(),image.getHeight());
                break;
            default:
                break;
        }
        rect = new Rectangle (this.x,this.y,image.getWidth(),image.getHeight());
    }
    public void paint(Graphics g){

        g.drawImage(image,x,y,null);
        move();
        //System.out.println("width:"+width+",height:"+height);
        //System.out.println("update");
        //g.drawRect();
    }
    public void move(){
        switch(dir){
            case UP:
                y-=BULLET_SPEED;
                break;
            case DOWN:
                y+=BULLET_SPEED;
                break;
            case LEFT:
                x-=BULLET_SPEED;
                break;
            case RIGHT:
                x+=BULLET_SPEED;
                break;
            default:
                break;
        }
        if(this.x<0||this.y<0||this.x>TankFrame.GAMEWIDTH||this.y>TankFrame.GAMEHEIGHT)
            isAlive =false;
        setRect(x,y,image.getWidth(),image.getHeight());
    }

}
