package main.java.com.liudi.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    int x;
    int y;
    int width;
    int height;
    Color color;
    Dir dir;
    Group group;
    boolean isAlive;
    Rectangle rect;

    public void setDimension(int width, int height){
        this.width =width;
        this.height =height;
    }
    public void setRect(int x,int y, int width, int height){
        rect.x =x;
        rect.y=y;
        rect.width=width;
        rect.height=height;
    }
    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    BufferedImage image;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
