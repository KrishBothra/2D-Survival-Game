package com.example.template;

public class Zombies {
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

    double playerHealth;

    long startTime;
    int damage;

    double movementTime;

    public Zombies(String name, int health, inventoryItems resourceDrop, double movementTime, int amount, int damage, int x, int y) {
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

    public void changeLoc(String[][] map, String[][] mapBackground, int playerX, int playerY, double healthB) {
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
                    playerHealth-=damage;
//                tempE.setHealth(-(damage-randNum));
                break;
            }
            if (map[tempx][tempy].equals("grass")) {
                map[tempx][tempy] = "zombieOverGrass";
                name = map[tempx][tempy];
                if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                    map[x][y] = "grass";
                } else {
                    map[x][y] = "stone";
                }
                last2Y = lastY;
                last2X = lastX;
                lastY = y;
                lastX = x;
                x = tempx;
                y = tempy;
                break;
            } else if (map[tempx][tempy].equals("stone")) {
                check = true;
                map[tempx][tempy] = "zombieOverStone";
                name = map[tempx][tempy];
                if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                    map[x][y] = "grass";
                } else {
                    map[x][y] = "stone";
                }
                last2Y = lastY;
                last2X = lastX;
                lastY = y;
                lastX = x;
                x = tempx;
                y = tempy;
                break;
            } else {
                System.out.println(x);
                System.out.println(y);
                System.out.println(lastX);
                System.out.println(last2Y);
                System.out.println("SOUIEGHEOWSHGddwd");
                System.out.println(last2X);
                System.out.println(lastY);
                goAround(left, right, down, up, y, x, map, mapBackground);
                System.out.println(x);
                System.out.println(y);
                break;
            }
        }
    }


        private void goAround ( boolean left, boolean right, boolean down, boolean up, int tempy2, int tempx2, String[][] map, String[][]mapBackground){
            int counter = 0;
            while (true) {
                System.out.println("TROOP COUNTER: " + counter);
                int tempy = tempy2;
                int tempx = tempx2;
                if (left) {
                    tempy--;
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != lastX || tempy != last2Y)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        System.out.println("HOW: "+(tempx != last2X || tempy != lastY));
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        last2Y = lastY;
                        last2X = lastX;
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
                        }
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    tempy = tempy2;
                    tempy++;
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        System.out.println("LAST2X: "+last2X);
                        System.out.println("LASTY: "+lastY);
                        System.out.println("TEMP X: "+tempx);
                        System.out.println("TEMP Y "+tempy);
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy2].equals("grass") || map[tempx][tempy2].equals("stone")) && (tempx != lastX || tempy2 != last2Y) && (tempx != last2X || tempy2 != lastY)) {
                        if (mapBackground[tempx][tempy2].equals("grass") || mapBackground[tempx][tempy2].equals("normal") | mapBackground[tempx][tempy2].equals("fruit") || mapBackground[tempx][tempy2].equals("autumn")) {
                            map[tempx][tempy2] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy2] = "zombieOverStone";
                        }
                        name = map[tempx][tempy2];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("grass") || map[tempx][tempy2].equals("stone")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (mapBackground[tempx][tempy2].equals("grass") || mapBackground[tempx][tempy2].equals("normal") | mapBackground[tempx][tempy2].equals("fruit") || mapBackground[tempx][tempy2].equals("autumn")) {
                            map[tempx][tempy2] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy2] = "zombieOverStone";
                        }
                        name = map[tempx][tempy2];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
                        }
                        last2Y = lastY;
                        last2X = lastX;
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("grass") || map[tempx][tempy2].equals("stone")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (mapBackground[tempx][tempy2].equals("grass") || mapBackground[tempx][tempy2].equals("normal") | mapBackground[tempx][tempy2].equals("fruit") || mapBackground[tempx][tempy2].equals("autumn")) {
                            map[tempx][tempy2] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy2] = "zombieOverStone";
                        }
                        name = map[tempx][tempy2];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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
                    if ((map[tempx][tempy].equals("grass") || map[tempx][tempy].equals("stone")) && (tempx != lastX || tempy != last2Y)  && (tempx != last2X || tempy != lastY)) {
                        if (mapBackground[tempx][tempy].equals("grass") || mapBackground[tempx][tempy].equals("normal") | mapBackground[tempx][tempy].equals("fruit") || mapBackground[tempx][tempy].equals("autumn")) {
                            map[tempx][tempy] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy] = "zombieOverStone";
                        }
                        name = map[tempx][tempy];
                        last2Y = lastY;
                        last2X = lastX;
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
                        }
                        lastY = y;
                        lastX = x;
                        x = tempx;
                        y = tempy;
                        break;
                    }
                    if ((map[tempx][tempy2].equals("grass") || map[tempx][tempy2].equals("stone")) && (tempx != lastX || tempy2 != last2Y)  && (tempx != last2X || tempy2 != lastY)) {
                        if (mapBackground[tempx][tempy2].equals("grass") || mapBackground[tempx][tempy2].equals("normal") | mapBackground[tempx][tempy2].equals("fruit") || mapBackground[tempx][tempy2].equals("autumn")) {
                            map[tempx][tempy2] = "zombieOverGrass";
                        } else {
                            map[tempx][tempy2] = "zombieOverStone";
                        }
                        name = map[tempx][tempy2];
                        if (mapBackground[x][y].equals("grass") || mapBackground[x][y].equals("normal") || mapBackground[x][y].equals("fruit") || mapBackground[x][y].equals("autumn")) {
                            map[x][y] = "grass";
                        } else {
                            map[x][y] = "stone";
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


    }