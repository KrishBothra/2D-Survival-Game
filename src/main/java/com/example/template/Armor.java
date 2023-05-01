package com.example.template;

public class Armor extends inventoryItems{


    String name;



    public Armor(String name, int tier,int protection, int durability){
        super(name);
        this.name = name;
        super.tier = tier;
        super.protection =protection * tier;
        super.durability = durability;

    }
}
