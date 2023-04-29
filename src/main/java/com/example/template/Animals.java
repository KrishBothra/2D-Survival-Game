package com.example.template;

import javafx.animation.AnimationTimer;

public class Animals {
    private  inventoryItems resourceDrop;
    String name;
    int health;
    int x,y;
    int amount;

    long startTime;

    double movementTime;

    public Animals(String name,int health,inventoryItems resourceDrop,double movementTime,int amount,int x,int y){
        this.name = name;
        this.health = health;
        this.resourceDrop = resourceDrop;
        this.x = x;
        this.y = y;
        this.startTime = System.nanoTime();
        this.amount  = amount;
        this.movementTime = movementTime;

    }

    public int getAmountDrop() {
        return amount;
    }

    public double getMovementTime() {
        return movementTime;
    }

    public void changeMovementTime(int change){
        movementTime+= change;
    }

    public long getStartTime() {
        return startTime;
    }

    public void resetStartTime() {
        startTime = System.nanoTime();
    }

    public int getHealth() {
        return health;
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

    public inventoryItems getResourceDrop() {
        return resourceDrop;
    }

    public void changeLoc(String[][] map){
        boolean check = false;
        int timeout = 0;
        while(!check&&timeout<1000){
            int tempx = x;
            int tempy = y;
            if(Math.random()>.5){
                tempx++;
            }else {
                tempx--;
            }
            if(Math.random()>.5){
                tempy++;
            }else {
                tempy--;
            }
            if (map[tempx][tempy].equals("grass")){
                check=true;
                map[tempx][tempy]=name;
                map[x][y]="grass";
                x=tempx;
                y=tempy;
            }
            timeout++;
        }

//          System.out.println("x: " + x);

    }

    public void changeHealth(int change){
        health+=change;
    }


}
