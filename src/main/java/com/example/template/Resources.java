package com.example.template;

public class Resources extends inventoryItems {
    String name;

    public Resources(String name){
        super(name);
        this.name = name;
        super.placeable = true;
    }
    public Resources(String name,String type){
        super(name,type);
        this.name = name;
        super.placeable = true;
    }


}
