package main.java.com.liudi.game;

import javax.swing.*;


public class TankWarMain {
    static int frameInterval = PropertyManager.getInt("FrameInterval");
    static int totalEnemyNumber = PropertyManager.getInt("EnemyNumber");
    static int initialTankCount = PropertyManager.getInt("InitialTankCount");
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();
        //int initialTankCount = PropertyManager.getInt("InitialTankCount");
        //int totalEnemyNumber = PropertyManager.getInt("EnemyNumber");
        TankFactory factory = new TankFactory(tf);
        TerrainBuilder builder = new TerrainBuilder(tf.terrainList);
        RenderThread renderThread = new RenderThread(tf);
        renderThread.start();
        while (true) {
            //tf = new TankFrame();
            reset(tf);
            factory.producedNum=0;
            //factory = new TankFactory(tf);
            //builder = new TerrainBuilder(tf.terrainList);

            factory.createMainTank();
            for (int i = 0; i < initialTankCount; i++)
                factory.createEnemyTank();
//        factory.createEnemyTank();
//        factory.createEnemyTank();
//        factory.createEnemyTank();
//        factory.createEnemyTank();
//        factory.createEnemyTank();

            builder.buildWall(60, 80, 10, 17)
                    .buildWall(310, 80, 9, 17)
                    .buildWall(540, 80, 10, 17)
                    .buildSnowfield(10, 250, 39, 2)
                    .buildSnowfield(10, 550, 39, 4)
                    .buildGrass(10, 40, 39, 4)
                    .buildGrass(10, 270, 39, 2)
                    .buildGrass(10, 310, 39, 4)
                    .buildRiver(60, 290, 10, 2)
                    .buildRiver(310, 290, 9, 2)
                    .buildRiver(540, 290, 10, 2)
                    .buildSteel(10, 350, 2, 2)
                    .buildSteel(750, 350, 2, 2)
                    .buildWall(60, 390, 10, 4)
                    .buildWall(310, 390, 9, 4)
                    .buildWall(540, 390, 10, 4)
                    .buildWall(60, 470, 10, 4)
                    .buildWall(310, 470, 9, 4)
                    .buildWall(540, 470, 10, 4)
                    .build();
            while (tf.friendNumber != 0 && tf.enemyNumber != 0) {
//            System.out.println(tf.friendNumber);
//            System.out.println(producedNum);
//            System.out.println(tf.tankList.size()-1);
//            System.out.println(initialTankCount);
                if (factory.producedNum - tf.friendNumber < totalEnemyNumber && tf.tankList.size() - 1 < initialTankCount) {
                    factory.createEnemyTank();
                }
                try {
                    Thread.currentThread().sleep(frameInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (tf.friendNumber == 0){
                JOptionPane.showMessageDialog(tf, "你输了！");

            }
            if (tf.enemyNumber == 0){
                JOptionPane.showMessageDialog(tf, "你赢了！");
            }
            //tf.dispose();
            //builder=null;
            //factory=null;

        }
    }
    public static void reset(TankFrame tf){
        for(int i = 0 ;i<tf.tankList.size();i++)
        {
            tf.tankList.get(i).isAlive=false;
        }
        for(int i = 0 ;i<tf.bulletList.size();i++)
        {
            tf.bulletList.get(i).isAlive=false;
        }
        for(int i = 0 ;i<tf.terrainList.size();i++)
        {
            tf.terrainList.get(i).isAlive=false;
        }
        for(int i = 0 ;i<tf.expList.size();i++)
        {
            tf.expList.get(i).isAlive=false;
        }
            //tf.tankList.clear();
            tf.enemyNumber=0;
            tf.friendNumber=0;
            tf.enemyLeft=TankWarMain.totalEnemyNumber;
            //tf.bulletList.clear();
            //tf.terrainList.clear();
            //tf.expList.clear();
            //tf.maintank=null;
            //tf.friendtank=null;

    }
}
