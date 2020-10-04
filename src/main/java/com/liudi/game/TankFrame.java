package main.java.com.liudi.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class TankFrame extends JFrame {
    static  int GAMEWIDTH=PropertyManager.getInt("GameWidth");
    static  int GAMEHEIGHT=PropertyManager.getInt("GameHeight");
    static  int GAMEMARGIN= PropertyManager.getInt("GameMargin");
    int enemyLeft = TankWarMain.totalEnemyNumber;
    int enemyNumber =0;
    int friendNumber =0;
    Tank maintank;
    Tank friendtank;
    ArrayList<Tank> tankList=new ArrayList<Tank>(10);
    ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
    ArrayList<Explosion> expList=new ArrayList<Explosion>();
    ArrayList<Terrain> terrainList=new ArrayList<Terrain>();
    //ArrayList<Tank> blockTank = new ArrayList<Tank>();
    public TankFrame(){
        super("Tank War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = this.getContentPane();
        //JPanel jp = new JPanel();
        //jp.
        //cp.add(new JPanel());
        this.addKeyListener(new MyKeyListener());
        this.setSize(GAMEWIDTH,GAMEHEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }
    Image offScreenImage =null;
    @Override
    public void update(Graphics g){
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAMEWIDTH,GAMEHEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAMEWIDTH,GAMEHEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
        //this.repaint();
        //g.drawRect();
    }
    @Override
    public void paint(Graphics g){

        explosionDetection();
        collisionDetection();
        //maintank.paint(g);
        for(int i =0;i<tankList.size();i++){
            Tank t = tankList.get(i);
            if(t.isAlive) {
                t.paint(g);
            }else
                tankList.remove(t);
        }
        for(int i =0;i<bulletList.size();i++){
            Bullet b = bulletList.get(i);
            if(b.isAlive) {
                b.paint(g);
            }else
                bulletList.remove(b);
        }
        for(int i =0;i<terrainList.size();i++){
            Terrain t = terrainList.get(i);
            if(t.isAlive) {
                t.paint(g);
            }else
                terrainList.remove(t);
        }
        for(int i =0;i<expList.size();i++){
            Explosion e = expList.get(i);
            if(e.isAlive) {
                e.paint(g);
            }else
                expList.remove(e);
        }
        Color tmp = g.getColor();
        g.setColor(Color.white);
        //g.drawString("Bullet number:"+bulletList.size(),10,GAMEHEIGHT-20);
        //g.drawString("Tank number:"+tankList.size(),120,GAMEHEIGHT-20);
        g.drawString("Enemy number:"+enemyNumber,GAMEWIDTH/2-150,GAMEHEIGHT-20);
        g.drawString("Friend number:"+(friendNumber),GAMEWIDTH/2-50,GAMEHEIGHT-20);
        g.drawString("Total enemy number:"+enemyLeft,GAMEWIDTH/2+50,GAMEHEIGHT-20);
        //g.drawString("Blocked tank number:"+tankList.size(),230,60);
        g.setColor(tmp);
    }
    class MyKeyListener implements KeyListener {
        boolean up=false;
        boolean down=false;
        boolean left=false;
        boolean right=false;
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch(code){
                case KeyEvent.VK_W:
                    up=true;
                    break;
                case KeyEvent.VK_S:
                    down=true;
                    break;
                case KeyEvent.VK_A:
                    left=true;
                    break;
                case KeyEvent.VK_D:
                    right=true;
                    break;
                case KeyEvent.VK_ENTER:
                    if(maintank!=null)
                    maintank.fire(bulletList);
                default:
                    break;
            }
            if(maintank==null)
                return;
            if(up||down||left||right)
            {
                maintank.tankMoving=true;
                setMainTankDir();
                new AudioThread("audio/tank_move.wav",false).start();
            }
            else
                maintank.tankMoving=false;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            switch(code){
                case KeyEvent.VK_W:
                    up=false;
                    break;
                case KeyEvent.VK_S:
                    down=false;
                    break;
                case KeyEvent.VK_A:
                    left=false;
                    break;
                case KeyEvent.VK_D:
                    right=false;
                    break;
                default:
                    break;
            }
            if(maintank==null)
                return;
            if(!(up||down||left||right))
            {
                maintank.tankMoving=false;
                //setMainTankDir();
            }
            else
                maintank.tankMoving=true;
        }
        private void setMainTankDir(){
            if(up) maintank.setDir(Dir.UP);
            if(down) maintank.setDir(Dir.DOWN);
            if(left) maintank.setDir(Dir.LEFT);
            if(right) maintank.setDir(Dir.RIGHT);
        }
    }

    public void explosionDetection(){
        for(int i = 0 ;i <bulletList.size();i++){
            Bullet bi = bulletList.get(i);
            if(bi.isAlive) {
                for (int j = 0; j < bulletList.size(); j++) {
                    Bullet bj = bulletList.get(j);
                    if (bi != bj&&bj.isAlive && bi.group != bj.group) {
                        if (bi.rect.intersects(bj.rect)) {
                            bi.isAlive = false;
                            bj.isAlive = false;
                        }
                    }
                }
                if(bi.isAlive) {
                    for (int j = 0; j < tankList.size(); j++) {
                        Tank tj = tankList.get(j);
                        if (tj.isAlive && bi.group != tj.group) {
                            if (bi.rect.intersects(tj.rect)) {
                                bi.isAlive = false;
                                tj.isAlive = false;
                                expList.add(new Explosion(2,tj.x,tj.y));
                                new AudioThread("audio/explode.wav",false).start();
                                if(tj.group==Group.HOSTILE){
                                    enemyNumber--;
                                    enemyLeft--;
                                }
                                else
                                {
                                    friendNumber--;
                                    if(tj==maintank){
                                        maintank=null;
                                    }
                                }
                            }
                        }
                    }
                    for (int j = 0; j < terrainList.size(); j++) {
                        Terrain tt = terrainList.get(j);
                        if (tt.isAlive&&tt instanceof BrickWall ) {
                            Rectangle tmp = new Rectangle();
                            if(bi.dir==Dir.UP||bi.dir==Dir.DOWN) {
                                tmp.x= bi.x-11;
                                tmp.y = bi.y;
                                tmp.width=22;
                                tmp.height = bi.height;
                            }
                            else{
                                tmp.y= bi.y-11;
                                tmp.x = bi.x;
                                tmp.height=22;
                                tmp.width = bi.width;
                            }
                            if (tmp.intersects(tt.rect)) {
                                bi.isAlive = false;
                                tt.isAlive = false;
                                //expList.add(new Explosion(2,tj.x,tj.y));
                                //new AudioThread("audio/explode.wav",false).start();
                            }
                        }
                        if (tt.isAlive&&tt instanceof Steel ) {
                            if (bi.rect.intersects(tt.rect)) {
                                bi.isAlive = false;
                                //expList.add(new Explosion(2,tj.x,tj.y));
                                //new AudioThread("audio/explode.wav",false).start();
                            }
                        }
                    }
                }
            }

        }
    }
    public void collisionDetection(){
        for(int i = 0; i<tankList.size();i++){
            Tank ti = tankList.get(i);
            if(ti.isAlive){
                if(ti.y>30 + TankFrame.GAMEMARGIN) {
                    ti.UpBlocked = false;
                }
                else
                {
                    ti.UpBlocked=true;
                }
                if(ti.y+ti.height<TankFrame.GAMEHEIGHT- TankFrame.GAMEMARGIN) {

                    ti.DownBlocked = false;
                }
                else
                {
                    ti.DownBlocked=true;
                }
                if(ti.x>TankFrame.GAMEMARGIN) {

                    ti.LeftBlocked = false;
                }
                else
                {
                    ti.LeftBlocked=true;
                }
                if(ti.x+ti.width<TankFrame.GAMEWIDTH- TankFrame.GAMEMARGIN) {
                    ti.RightBlocked = false;
                }
                else
                {
                    ti.RightBlocked=true;
                }

                for (int j = 0; j < tankList.size(); j++) {
                    Tank tj = tankList.get(j);
                    if (ti != tj && tj.isAlive) {
                        if (ti.x+ti.width>tj.x&&tj.x+tj.width>ti.x&&tj.y<ti.y&&ti.y<=tj.y+tj.height) {
                            ti.UpBlocked =true;

                        }
                        if (ti.x+ti.width>tj.x&&tj.x+tj.width>ti.x&&ti.y+ti.height>=tj.y&&ti.y+ti.height<tj.y+tj.height) {
                            ti.DownBlocked =true;

                        }
                        if (ti.y+ti.height>tj.y&&tj.y+tj.height>ti.y&&tj.x<ti.x&&ti.x<=tj.x+tj.width) {
                            ti.LeftBlocked =true;

                        }
                        if (ti.y+ti.height>tj.y&&tj.y+tj.height>ti.y&&ti.x+ti.width>=tj.x&&ti.x+ti.width<tj.x+tj.width) {
                            ti.RightBlocked =true;

                        }
                    }
                }
                for (int j = 0; j < terrainList.size(); j++) {
                    Terrain tt = terrainList.get(j);
                    if ((tt instanceof BrickWall||tt instanceof River||tt instanceof Steel) && tt.isAlive) {
                        if (ti.x+ti.width>tt.x&&tt.x+tt.width>ti.x&&tt.y<ti.y&&ti.y<=tt.y+tt.height) {
                            ti.UpBlocked =true;

                        }
                        if (ti.x+ti.width>tt.x&&tt.x+tt.width>ti.x&&ti.y+ti.height>=tt.y&&ti.y+ti.height<tt.y+tt.height) {
                            ti.DownBlocked =true;

                        }
                        if (ti.y+ti.height>tt.y&&tt.y+tt.height>ti.y&&tt.x<ti.x&&ti.x<=tt.x+tt.width) {
                            ti.LeftBlocked =true;

                        }
                        if (ti.y+ti.height>tt.y&&tt.y+tt.height>ti.y&&ti.x+ti.width>=tt.x&&ti.x+ti.width<tt.x+tt.width) {
                            ti.RightBlocked =true;

                        }
                    }
                }


            }
        }

    }

}
