package main.java.com.liudi.game;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioThread extends Thread{
    public String file;
    public AudioInputStream audioInputStream =null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;
    private AudioFormat audioFormat = null;
    private boolean loop = false;
    public AudioThread(String audioFile,boolean loop){
        this.file = audioFile;
        this.loop = loop;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(AudioThread.class.getClassLoader().getResource(file));
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class,audioFormat);
            sourceDataLine  = (SourceDataLine)AudioSystem.getLine(dataLine_info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void play(){
        byte[] b = new byte[1024*5];
        int len = 0;
        try {
            sourceDataLine.open(audioFormat, 1024 * 5);
            sourceDataLine.start();
            audioInputStream.mark(12358946);
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loop(){
        byte[] b = new byte[1024*5];
        while(true) {
            int len = 0;
            try {
                sourceDataLine.open(audioFormat, 1024 * 5);
                sourceDataLine.start();
                audioInputStream.mark(12358946);
                while ((len = audioInputStream.read(b)) > 0) {
                    sourceDataLine.write(b, 0, len);
                }
                audioInputStream.reset();
                sourceDataLine.drain();
                sourceDataLine.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run(){
        if(loop)
       loop();
        else
            play();
        close();
    }

//    public static void main(String[] args) {
//        AudioThread audio = new AudioThread("audio/war1.wav",true);
//        audio.start();
//    }

}
