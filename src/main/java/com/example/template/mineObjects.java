package com.example.template;

public class mineObjects {
    private Resources resourceDropSecond;
    private int amountDropSecond;
    String name;
    int mineTime;
    Resources resourceDrop;
    int amountDrop;
    int x,y;

    public mineObjects(String name,int mineTime,Resources resourceDrop,int amountDrop,int x,int y) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.amountDrop = amountDrop;
        this.x = x;
        this.y = y;
    }

    public mineObjects(String name,int mineTime,Resources resourceDrop,int amountDrop,Resources resourceDropSecond,int amountDropSecond,int x,int y) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.resourceDropSecond = resourceDropSecond ;
        this.amountDrop = amountDrop;
        this.amountDropSecond = amountDropSecond ;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public int getMineTime() {
        return mineTime;
    }
}
