package com.example.template;

public class witherHead {
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

    double movementTime;

    String rowCol;
    int dir;

    public witherHead(String name,  String rowCol, int dir , int damage, int x, int y) {
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
        this.dir = dir;
        this.rowCol = rowCol;
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

    public String changeLoc(String[][] map, String[][] mapBackground,  double healthB,double playerOverHealth) {
        boolean check = false;
        playerHealth = healthB;

        while (!check) {
            boolean left = false;
            boolean right = false;
            boolean up = false;
            boolean down = false;
            int tempx = x;
            int tempy = y;
            if(rowCol.equals("row")){
                tempx += dir;
            }else{
                tempy += dir;
            }
            if (map[tempx][tempy].equals("playerOverNetherrack")||map[tempx][tempy].equals("playerInLava"))  {
                playerHealth -= damage - (damage*(playerOverHealth*0.005833333333));
                System.out.println(playerOverHealth);
                System.out.println( damage - (damage*(playerOverHealth*0.005833333333)));
//                tempE.setHealth(-(damage-randNum));
                return "hit";
            }
            if (map[tempx][tempy].equals("netherrack")||map[tempx][tempy].equals("lava")) {
                if(mapBackground[x][y].equals("netherrack") ) {
                    map[x][y] = "netherrack";
                }else{
                    map[x][y] = "lava";
                }
                map[tempx][tempy] = "witherHead";
                name = map[tempx][tempy];
                last2Y = lastY;
                last2X = lastX;
                lastY = y;
                lastX = x;
                x = tempx;
                y = tempy;
                return "move";
            }else{
                return "stuck";
            }
        }
        return "stuck";
    }



//          System.out.println("x: " + x);
    


        

//          System.out.println("x: " + x);


    public boolean isAttacked() {
        return attacked;
    }

    public void changeAttacked(boolean change) {
        attacked = change;
    }
}