package main.java.com.liudi.game;

import java.util.ArrayList;
import java.util.Random;

class AiTankThread extends Thread{
    Tank tank;
    Random random;
    ArrayList<Bullet> bulletList;
    ArrayList<Tank> blockTank;
    public AiTankThread(Tank tank,ArrayList<Bullet> bulletList){
        this.tank =tank;
        this.bulletList=bulletList;
    }
    @Override
    public void run(){
        random = new Random();
        int rand =0;
        while(tank.isAlive&&tank!=null){
            if(tank.UpBlocked||tank.DownBlocked||tank.LeftBlocked||tank.RightBlocked){
                rand  =  random.nextInt(4);
                tank.setDir(Dir.values()[rand]);
            }

            rand = random.nextInt(100);
            if(rand >95){
                tank.setDir(Dir.values()[random.nextInt(4)]);
            }
            if(rand >60){
                tank.fire(bulletList);
                //new AudioThread("audio/tank_fire.wav",false).start();
            }
            //System.out.println(tank.getStatus());
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
