package main.java.com.liudi.game;

public class RenderThread extends Thread{
    private TankFrame tf;
    public RenderThread(TankFrame tf){
        this.tf = tf;
    }
    @Override
    public void run(){
        while(true) {
            if(tf==null)
                break;
            tf.update(tf.getGraphics());
            try {
                Thread.currentThread().sleep(TankWarMain.frameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
