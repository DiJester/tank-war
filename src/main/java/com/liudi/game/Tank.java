package main.java.com.liudi.game;

import java.awt.*;
import java.util.ArrayList;

public class Tank extends GameObject{

    boolean tankMoving =false;
    //ArrayList<Tank> blockTank = new ArrayList<Tank>();
    public static int TANKSPEED =  PropertyManager.getInt("TankSpeed");
    public static int FireInterval =  PropertyManager.getInt("FireInterval");
    boolean UpBlocked = false;
    boolean DownBlocked = false;
    boolean LeftBlocked = false;
    boolean RightBlocked = false;
    long lastFireTime = 0L;

    public Tank(int x,int y,Dir dir,boolean tankMoving,Group group){
        this.x =x;
        this.y =y;
        this.dir=dir;
        this.tankMoving = tankMoving;
        this.group =group;
        isAlive =true;
//        switch (dir) {
//            case UP:
//                image = ResourceManager.tankU;
//                setDimension(image.getWidth(),image.getHeight());
//                break;
//            case DOWN:
//                image = ResourceManager.tankD;
//                setDimension(image.getWidth(),image.getHeight());
//                break;
//            case LEFT:
//                image = ResourceManager.tankL;
//                setDimension(image.getWidth(),image.getHeight());
//                break;
//            case RIGHT:
//                image = ResourceManager.tankR;
//                setDimension(image.getWidth(),image.getHeight());
//                break;
//            default:
//                break;
//        }
        if(group==Group.FRIEND){
            image = ResourceManager.mainTank[dir.ordinal()];
        }
        if(group==Group.HOSTILE){
            image = ResourceManager.tank[dir.ordinal()];
        }
        rect = new Rectangle (this.x,this.y,image.getWidth(),image.getHeight());
    };
    public Tank(){
        dir=Dir.UP;
        image = ResourceManager.tankU;
        setDimension(image.getWidth(),image.getHeight());
    };

    public void paint(Graphics g){

        //Color tmp  = g.getColor();
//        switch(dir){
//            case UP:
//                g.drawImage(ResourceManager.tankU,x,y,null);
//                setDimension(ResourceManager.tankU.getWidth(),ResourceManager.tankU.getHeight());
//                break;
//            case DOWN:
//                g.drawImage(ResourceManager.tankD,x,y,null);
//                setDimension(ResourceManager.tankD.getWidth(),ResourceManager.tankD.getHeight());
//                break;
//            case LEFT:
//                g.drawImage(ResourceManager.tankL,x,y,null);
//                setDimension(ResourceManager.tankL.getWidth(),ResourceManager.tankL.getHeight());
//                break;
//            case RIGHT:
//                g.drawImage(ResourceManager.tankR,x,y,null);
//                setDimension(ResourceManager.tankR.getWidth(),ResourceManager.tankR.getHeight());
//                break;
//            default:
//                break;
//        }
        //g.drawImage(ResourceManager.tankU,x,y,null);
        //g.setColor(tankColor);
        //g.fillRect(x,y,TANKWIDTH, TANKHEIGHT);
        //g.setColor(tmp);
        if(group==Group.FRIEND){
            image = ResourceManager.mainTank[dir.ordinal()];
        }
        if(group==Group.HOSTILE){
            image = ResourceManager.tank[dir.ordinal()];
        }
        if(tankMoving){
                switch (dir) {
                    case UP:
                        //image = ResourceManager.tankU;
                        if(!UpBlocked)
                        {
                        y -= TANKSPEED;
                        }
                        break;
                    case DOWN:
                        //image = ResourceManager.tankD;
                        if(!DownBlocked) {
                            y += TANKSPEED;
                        }
                        break;
                    case LEFT:
                        //image = ResourceManager.tankL;
                        if(!LeftBlocked) {
                            x -= TANKSPEED;
                        }
                        break;
                    case RIGHT:
                        //image = ResourceManager.tankR;
                        if(!RightBlocked) {
                            x += TANKSPEED;
                        }
                        break;
                    default:
                        break;
                }

        }
        g.drawImage(image,x,y,null);
        setDimension(image.getWidth(),image.getHeight());
        setRect(x,y,image.getWidth(),image.getHeight());

        //g.drawRect();
    }
    public void fire(ArrayList<Bullet> bulletList){
        long time = System.currentTimeMillis();
        if(time - lastFireTime<FireInterval)
             return;
        else
            lastFireTime = time;
        if(this.group==Group.FRIEND)
            new AudioThread("audio/tank_fire.wav",false).start();
        synchronized(bulletList){
        switch(dir){
            case UP:
                bulletList.add(new Bullet(x+width/2-2,y,Dir.UP,group));
                break;
            case DOWN:
                bulletList.add(new Bullet(x+width/2-2,y+height,Dir.DOWN,group));
                break;
            case LEFT:
                bulletList.add(new Bullet(x,y+height/2-2,Dir.LEFT,group));
                break;
            case RIGHT:
                bulletList.add(new Bullet(x+width,y+height/2-2,Dir.RIGHT,group));
                break;
            default:
                break;
        }
        }
    }
    public String getStatus(){
        return "RightBlocked:"+this.RightBlocked+",LeftBlocked:"+this.LeftBlocked+",UpBlocked:"+this.UpBlocked+",DownBlocked:"+this.DownBlocked+",Direction"+this.dir;
    }


}
