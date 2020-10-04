package main.java.com.liudi.game;

import java.util.ArrayList;

public class TerrainBuilder {
    ArrayList<Terrain> terrainList;
    static final int TerrainUnitWidth = PropertyManager.getInt("TerrainUnitWidth");
    static final int TerrainUnitHeight = PropertyManager.getInt("TerrainUnitHeight");
    public TerrainBuilder(ArrayList<Terrain> terrainList){

      this.terrainList = terrainList;
    }
    public TerrainBuilder buildWall(int x,int y,int column, int line) {
        int type = 0;
        for (int i = 0; i < line; i++) {
            type = Math.floorMod(i,2);
           for(int j =0;j<column;j++){
               terrainList.add(new BrickWall(x+j*TerrainUnitWidth,y+i*TerrainUnitHeight,type));
            }

        }
        return this;
    }
    public TerrainBuilder buildRiver(int x,int y,int column, int line) {
        int type = 0;
        for (int i = 0; i < line; i++) {
            type = Math.floorMod(i,2);
            for(int j =0;j<column;j++){
                terrainList.add(new River(x+j*TerrainUnitWidth,y+i*TerrainUnitHeight,type));
            }

        }
        return this;
    }
    public TerrainBuilder buildSnowfield(int x,int y,int column, int line) {
        for (int i = 0; i < line; i++) {
            for(int j =0;j<column;j++){
                terrainList.add(new SnowField(x+j*TerrainUnitWidth,y+i*TerrainUnitHeight));
            }

        }
        return this;
    }
    public TerrainBuilder buildGrass(int x,int y,int column, int line) {
        for (int i = 0; i < line; i++) {
            for(int j =0;j<column;j++){
                terrainList.add(new Grass(x+j*TerrainUnitWidth,y+i*TerrainUnitHeight));
            }

        }
        return this;
    }
    public TerrainBuilder buildSteel(int x,int y,int column, int line) {
        for (int i = 0; i < line; i++) {
            for(int j =0;j<column;j++){
                terrainList.add(new Steel(x+j*TerrainUnitWidth,y+i*TerrainUnitHeight));
            }

        }
        return this;
    }
    public ArrayList<Terrain> build(){
        return terrainList;
    }
}
