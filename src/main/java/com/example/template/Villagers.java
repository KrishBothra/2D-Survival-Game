package com.example.template;

import javafx.animation.AnimationTimer;

public class Villagers {
    private inventoryItems resourceDrop;
    String name;
    int health;
    int x, y;

    long startTime;

    double movementTime;
    String biome;

    public Villagers(String name, int health, double movementTime, int x, int y, String biome) {
        this.name = name;
        this.health = health;
        this.x = x;
        this.y = y;
        this.startTime = System.nanoTime();
        this.movementTime = movementTime;
        this.biome = biome;

    }


    public double getMovementTime() {
        return movementTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void changeMovementTime(int change){
        movementTime+= change;
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


    public void changeLoc(String[][] map, String[][] mapBackground) {
        boolean check = false;
        int timeout = 0;
        while (!check && timeout < 1000) {
            int tempx = x;
            int tempy = y;
            if (Math.random() > .5) {
                tempx++;
            } else {
                tempx--;
            }
            if (Math.random() > .5) {
                tempy++;
            } else {
                tempy--;
            }
            if (map[tempx][tempy].equals("grass")&&mapBackground[tempx][tempy].equals(biome)) {
                check = true;
                map[tempx][tempy] = name;
                map[x][y] = "grass";
                x = tempx;
                y = tempy;
            }
            timeout++;
        }

//          System.out.println("x: " + x);

    }

    public void changeHealth(int change) {
        health += change;
    }
}
