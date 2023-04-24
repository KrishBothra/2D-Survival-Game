package com.example.template;

public class mineObjects {
    private Resources resourceDropSecond = null;
    private int amountDropSecond = 0;
    String name;
    int mineTime;
    String type;
    Resources resourceDrop;
    int amountDrop;
    int x,y;

    public mineObjects(String name,String type,int mineTime,Resources resourceDrop,int amountDrop,int x,int y) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.amountDrop = amountDrop;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public mineObjects(String name,String type,int mineTime,Resources resourceDrop,int amountDrop,Resources resourceDropSecond,int amountDropSecond,int x,int y) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.resourceDropSecond = resourceDropSecond ;
        this.amountDrop = amountDrop;
        this.amountDropSecond = amountDropSecond ;
        this.x = x;
        this.y = y;
        this.type = type;
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

    public int getAmountDrop() {
        return amountDrop;
    }

    public int getAmountDropSecond() {
        return amountDropSecond;
    }

    public Resources getResourceDrop() {
        return resourceDrop;
    }

    public Resources getResourceDropSecond() {
        return resourceDropSecond;
    }

    public String getType() {
        return type;
    }
}
