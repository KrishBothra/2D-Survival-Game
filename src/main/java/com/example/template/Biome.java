package com.example.template;

public class Biome {
    int sx, sy, ex, ey, type;
    public Biome(int sx, int sy, int ex, int ey, int type){
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        this.type = type;
    }

    public int getEx() {
        return ex;
    }

    public int getEy() {
        return ey;
    }

    public int getSx() {
        return sx;
    }

    public int getSy() {
        return sy;
    }

    public int getType() {
        return type;
    }
}
