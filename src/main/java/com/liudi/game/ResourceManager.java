package main.java.com.liudi.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ResourceManager {
    public static BufferedImage tankU,tankD,tankL,tankR,bulletU,bulletD,bulletL,bulletR;
    public static BufferedImage[] explosion1=new BufferedImage[11];
    public static BufferedImage[] explosion2=new BufferedImage[16];
    public static BufferedImage[] bricks=new BufferedImage[2];
    public static BufferedImage[] rivers=new BufferedImage[2];
    public static BufferedImage snowfield,grass,steel;
    public static BufferedImage[] mainTank = new BufferedImage[4];
    public static BufferedImage[] tank = new BufferedImage[4];
    static {
        try {
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.png"));
            //tankR = ImageUtil.rotateImage(tankU,90);
            //tankD = ImageUtil.rotateImage(tankR,90);
            //tankL = ImageUtil.rotateImage(tankD,90);
            tankD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.png"));
            tankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankL.png"));
            tankR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.png"));
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            //bulletR = ImageUtil.rotateImage(bulletU,90);
            //bulletD = ImageUtil.rotateImage(bulletR,90);
            //bulletL = ImageUtil.rotateImage(bulletD,90);
            bulletD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            bulletL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bricks[0] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/brick1.png"));
            bricks[1] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/brick2.png"));
            rivers[0] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/river1.png"));
            rivers[1] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/river2.png"));
            snowfield = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/snowfield.png"));
            grass = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/grass.png"));
            steel = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/steel.png"));
            mainTank[0] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/mainTankU.png"));
            mainTank[1] = ImageUtil.rotateImage(mainTank[0],180);
            mainTank[2] = ImageUtil.rotateImage(mainTank[1],90);
            mainTank[3] = ImageUtil.rotateImage(mainTank[0],90);
            tank[0] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.png"));
            tank[1] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.png"));
            tank[2] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankL.png"));
            tank[3] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.png"));
            for(int i=0;i<16;i++){
                if(i<11) {
                    explosion1[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/" + i + ".gif"));
                }
                explosion2[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
