package main.java.com.liudi.game;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    static Properties props = new Properties();

    static{
        try {
            props.load(PropertyManager.class.getClassLoader().getResourceAsStream("main/java/com/liudi/game/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(props==null) return null;
        return props.get(key);
    }
    public static int getInt(String key){
        if(props==null) return 0;
        int value = Integer.parseInt(props.get(key).toString());
        return value;
    }
    public static String getString(String key){
        if(props==null) return null;
        return props.get(key).toString();
    }

//    public static void main(String[] args) {
//        System.out.println(PropertyManager.get("TankSpeed"));
//    }
}
