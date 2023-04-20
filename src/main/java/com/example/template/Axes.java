package com.example.template;
public class Axes extends inventoryItems {

    String name;
    int tier;
    public Axes(String name, int tier){
        super(name);
        this.name = name;
        this.tier = tier;
    }


    public int getTier() {
        return tier;
    }



}
