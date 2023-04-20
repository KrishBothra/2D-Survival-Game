package com.example.template;

public class inventoryItems {
    String name;
    int amount;
    int amountSecond;

    public inventoryItems(String name){
        this.name = name;
    }

    public inventoryItems(String name,int amount){
        this.name = name;
        this.amount = amount;
    }

    public inventoryItems(String name,int amount,int amountSecond){
        this.name = name;
        this.amount = amount;
        this.amountSecond = amountSecond;
    }

    public String getName() {
        return name;
    }




    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountSecond() {
        return amountSecond;
    }

    public void setAmountSecond(int amountS) {
        this.amountSecond = amountS;
    }

    public void changeAmountSecond(int amountS) {
        this.amountSecond += amountS;
    }

    public void changeAmount(int amountS) {
        this.amount += amountS;
    }

}
