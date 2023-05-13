package com.example.template;

public class inventoryItems {

    String name;
    int amount;
    int amountSecond;

    boolean eatable = false;
    int hungerGain;

    int damage;
    int durbality;
    int tier = 0;
    int protection = 0;

    int durability;
    double boost;

    String type;

    private int amountNeeded;

    boolean placeable = false;

    public inventoryItems(String name){
        this.name = name;
    }


    public double getBoost() {
        return boost;
    }



    public String getType() {
        return type;
    }

    public inventoryItems(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    public inventoryItems(String name, String type){
        this.name = name;
        this.type = type;
    }

    public boolean isPlaceable() {
        return placeable;
    }

    public inventoryItems(String name, int amount, int amountSecond){
        this.name = name;
        this.amount = amount;
        this.amountSecond = amountSecond;
    }

    public String getName() {
        return name;
    }

    public int getProtection() {
        return protection;
    }

    public int getTier() {
        return tier;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void changeAmount(int amountS) {
        this.amount += amountS;
    }

    public void setAmountNeeded(int amount){
        amountNeeded = amount;
    }

    public int getAmountNeeded(){
        return amountNeeded;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isEatable() {
        return eatable;
    }

    public int getHungerGain() {
        return hungerGain;
    }

    public int getDurability() {
        return durability;
    }
}




















