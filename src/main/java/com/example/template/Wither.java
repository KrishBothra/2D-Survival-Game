package com.example.template;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class Wither {
    private inventoryItems resourceDrop;
    String name;
    int health;
    private int x;
    private int lastX;
    private int last2X;
    private int y;
    private int lastY;
    private int last2Y;
    int amount;
    double speed;

    boolean attacked = false;

    double playerHealth;

    long startTime;
    int damage;

    long witherSpawnTime;

    double movementTime;

    private ArrayList<witherHead> witherHeads = new ArrayList<>();
    private boolean spawnWither = false;

    public Wither(String name, int health, inventoryItems resourceDrop, int amount, int damage, int x, int y) {
        this.name = name;
        this.health = health;
        this.resourceDrop = resourceDrop;
        this.x = x;
        this.y = y;
        lastX = x;
        lastY = y;
        last2Y = lastY;
        last2X = lastX;
        this.startTime = System.nanoTime();
        this.amount = amount;
        this.movementTime = movementTime;
        this.damage = damage;
        this.speed = speed;
        witherSpawnTime = System.nanoTime();
        start();
    }

    public ArrayList<witherHead> getWitherHeads() {
        return witherHeads;
    }

    public double getSpeed() {
        return speed;
    }

    public int getAmountDrop() {
        return amount;
    }

    public double getMovementTime() {
        return movementTime;
    }

    public void changeMovementTime(int change) {
        movementTime += change;
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

    public void changeHealth(int change) {
        health += change;
    }

    public double getPlayerHealth() {
        return playerHealth;
    }

    public boolean changeLoc(String[][] map, int playerX, int playerY, double healthB,double playerOverHealth) {
        boolean check = false;
        playerHealth = healthB;

        while (!check) {
            boolean left = false;
            boolean right = false;
            boolean up = false;
            boolean down = false;
            int tempx = x;
            int tempy = y;
            if (playerX > x) {
                tempx++;
                down = true;
            } else if (playerX < x) {
                tempx--;
                up = true;
            }
            if (playerY > y) {
                tempy++;
                right = true;
            } else if (playerY < y) {
                tempy--;
                left = true;
            }
            if (Math.abs(tempx - playerX) == 0 && Math.abs(tempy - playerY) == 0) {
                playerHealth -= damage - (damage*(playerOverHealth*0.005833333333));
                System.out.println(playerOverHealth);
                System.out.println( damage - (damage*(playerOverHealth*0.005833333333)));
//                tempE.setHealth(-(damage-randNum));
                return true;
            }
            if(spawnWither) {
                if (Math.abs(y - playerY) == 0) {
                    if (up) {
                        if (map[tempx - 1][tempy].equals("netherrack") || map[tempx - 1][tempy].equals("lava")) {
                            witherHeads.add(new witherHead("witherHead", "row", -1, 10, tempx - 1, tempy));
                            spawnWither = false;
                        }
                    } else {
                        if (map[tempx + 1][tempy].equals("netherrack") || map[tempx + 1][tempy].equals("lava")) {
                            witherHeads.add(new witherHead("witherHead", "row", +1, 10, tempx + 1, tempy));
                            spawnWither = false;
                        }
                    }

//                tempE.setHealth(-(damage-randNum));
                    break;
                }
                if (Math.abs(x - playerX) == 0) {

                    if (right) {
                        if (map[tempx][tempy + 1].equals("netherrack") || map[tempx][tempy + 1].equals("lava")) {
                            witherHeads.add(new witherHead("witherHead", "col", +1, 10, tempx, tempy + 1));
                            spawnWither = false;
                        }
                    } else {
                        if (map[tempx][tempy - 1].equals("netherrack") || map[tempx][tempy - 1].equals("lava")) {
                            witherHeads.add(new witherHead("witherHead", "col", -1, 10, tempx, tempy - 1));
                            spawnWither = false;
                        }
                    }

//                tempE.setHealth(-(damage-randNum));
                    break;
                }
            }

            if (map[tempx][tempy].equals("netherrack")) {
                if(name.endsWith("Netherrack")){
                    map[x][y] = "netherrack";
                }else{
                    map[x][y] = "lava";
                }
                map[tempx][tempy] = "witherOverNetherrack";
                name = map[tempx][tempy];
                last2Y = lastY;
                last2X = lastX;
                lastY = y;
                lastX = x;
                x = tempx;
                y = tempy;
                break;
            } else if (map[tempx][tempy].equals("lava")) {
                if(name.endsWith("Netherrack")){
                    map[x][y] = "netherrack";
                }else{
                    map[x][y] = "lava";
                }
                map[tempx][tempy] = "witherOverLava";
                name = map[tempx][tempy];
                last2Y = lastY;
                last2X = lastX;
                lastY = y;
                lastX = x;
                x = tempx;
                y = tempy;
                break;
            } else {
                goAround(left, right, down, up, y, x, map);

                break;
            }
        }
        return false;
    }



//          System.out.println("x: " + x);
    


    private void goAround( boolean left, boolean right, boolean down, boolean up, int tempy2, int tempx2, String[][] map){
            int counter = 0;
            while (true) {
                System.out.println("TROOP COUNTER: " + counter);
                int tempy = tempy2;
                int tempx = tempx2;
                if (left) {
                    tempy--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    tempx--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != lastX || tempy != last2Y)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    left = false;
                    right = true;
//                tempy =  tempy2;
                    tempx = tempx2;
                } else if (right) {
                    tempy++;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        System.out.println("HOW: "+(tempx != last2X || tempy != lastY));
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    tempx--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    left = true;
                    right = false;
//                tempy =  tempy2;
                    tempx = tempx2;
                } else {
                    tempy--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        last2Y = lastY;
                        last2X = lastX;
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    tempy = tempy2;
                    tempy++;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;

                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    right = true;
                }
                if (down) {
                    tempx++;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("netherrack") || map[tempx][tempy2].equals("lava")) && (tempx != lastX || tempy2 != last2Y) && (tempx != last2X || tempy2 != lastY)) {
                        if (map[tempx][tempy2].equals("netherrack")) {
                            map[tempx][tempy2] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy2] = "witherOverLava";
                        }
                        name = map[tempx][tempy2];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy2;
                        break;
                    }
                    up = true;
                    down = false;
                } else if (up) {
                    tempx--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("netherrack") || map[tempx][tempy2].equals("lava")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (map[tempx][tempy2].equals("netherrack")) {
                            map[tempx][tempy2] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy2] = "witherOverLava";
                        }
                        name = map[tempx][tempy2];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy2;
                        break;
                    }
                    up = false;
                    down = true;
                } else {
                    tempx++;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("netherrack") || map[tempx][tempy2].equals("lava")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (map[tempx][tempy2].equals("netherrack")) {
                            map[tempx][tempy2] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy2] = "witherOverLava";
                        }
                        name = map[tempx][tempy2];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy2;
                        break;
                    }
                    tempx = tempx2;
                    tempx--;
                    if ((map[tempx][tempy].equals("netherrack") || map[tempx][tempy].equals("lava")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (map[tempx][tempy].equals("netherrack")) {
                            map[tempx][tempy] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy] = "witherOverLava";
                        }
                        name = map[tempx][tempy];
                        last2Y = lastY;
                        last2X = lastX;
                        if(name.endsWith("Netherrack")){
                    map[x][y] = "netherrack";
                }else{
                    map[x][y] = "lava";
                }
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("netherrack") || map[tempx][tempy2].equals("lava")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (map[tempx][tempy2].equals("netherrack")) {
                            map[tempx][tempy2] = "witherOverNetherrack";
                        } else {
                            map[tempx][tempy2] = "witherOverLava";
                        }
                        name = map[tempx][tempy2];
                        if(name.endsWith("Netherrack")){
                            map[x][y] = "netherrack";
                        }else{
                            map[x][y] = "lava";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy2;
                        break;
                    }
                    up = true;
                }
                counter++;
                if (counter > 4) {
//                    last2Y = lastY;
//                    last2X = lastX;
//                    lastY = y;
//                    lastX = x;
                    break;
                }
            }
        }
        

//          System.out.println("x: " + x);


    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //3,7 is fuel        1,7 is smelted        2,9 is result
                if(!spawnWither) {
                    if (now - witherSpawnTime > 1000000000.0 * 3) {
                        witherSpawnTime = System.nanoTime();
                        spawnWither = true;
                    }
                }


            }
        }.start();
    }


    public boolean isAttacked() {
        return attacked;
    }

    public void changeAttacked(boolean change) {
        attacked = change;
    }
}