package com.example.template;

public class Food extends inventoryItems{

    public Food(String name,int hungerGainP) {
        super(name);
        super.eatable = true;
        super.hungerGain = hungerGainP;
    }
}
