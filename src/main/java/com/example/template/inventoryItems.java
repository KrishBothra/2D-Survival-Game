package com.example.template;

public class inventoryItems {

    String name;
    int amount;
    int amountSecond;

    int damage;
    int durbality;
    int tier = 0;

    int durability;
    double boost;

    String type;

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

}
