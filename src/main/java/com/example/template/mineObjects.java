package com.example.template;

public class mineObjects {
    private Resources resourceDropSecond;
    private int amountDropSecond;
    String name;
    int mineTime;
    Resources resourceDrop;
    int amountDrop;

    public mineObjects(String name,int mineTime,Resources resourceDrop,int amountDrop) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.amountDrop = amountDrop;
    }

    public mineObjects(String name,int mineTime,Resources resourceDrop,int amountDrop,Resources resourceDropSecond,int amountDropSecond) {
        this.name = name;
        this.mineTime = mineTime;
        this.resourceDrop = resourceDrop;
        this.resourceDropSecond = resourceDropSecond ;
        this.amountDrop = amountDrop;
        this.amountDropSecond = amountDropSecond ;
    }
}
