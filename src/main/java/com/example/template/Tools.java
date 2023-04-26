package com.example.template;
public class Tools extends inventoryItems {

    String name;


    String type;
    public Tools(String name, int tier,String type,int damage, int durability){
        super(name);
        this.name = name;
        super.tier = tier;
        super.type = type;
        super.damage =damage * tier;
        super.durability = durability;

    }






}
