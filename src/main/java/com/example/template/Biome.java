package com.example.template;

public class Biome {
    int sx, sy, ex, ey;
    public Biome(int sx, int sy, int ex, int ey){
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
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
}
