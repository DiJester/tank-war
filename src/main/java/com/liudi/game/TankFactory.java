package main.java.com.liudi.game;

import java.util.Random;

public class TankFactory {
    static int[] birthx = {0+TankFrame.GAMEMARGIN,TankFrame.GAMEWIDTH/2-20,TankFrame.GAMEWIDTH-TankFrame.GAMEMARGIN-40};
    static int[] birthy= {30+TankFrame.GAMEMARGIN,TankFrame.GAMEHEIGHT-TankFrame.GAMEMARGIN-40};
    Random random  = new Random();
    TankFrame tf;
    int producedNum;

    public TankFactory(TankFrame tf){
        this.tf=tf;
        producedNum = 0;
    }
    public Tank createEnemyTank(){
        int rand = random.nextInt(3);
        Tank t =  new Tank(birthx[rand],birthy[0],Dir.DOWN,true,Group.HOSTILE);
        tf.tankList.add(t);
        tf.enemyNumber++;
        producedNum++;
        AiTankThread tiThread = new AiTankThread(t,tf.bulletList);
        tiThread.start();
        return t;
    }
    public Tank createMainTank(){
        tf.maintank =  new Tank(birthx[0],birthy[1],Dir.UP,false,Group.FRIEND);
        tf.tankList.add(tf.maintank);
        tf.friendNumber++;
        producedNum++;
        return tf.maintank;
    }
    public Tank createFriendTank(){
        tf.friendtank =  new Tank(birthx[2],birthy[1],Dir.UP,false,Group.FRIEND);
        tf.tankList.add(tf.friendtank);
        tf.friendNumber++;
        producedNum++;
        return tf.friendtank;
    }
}
