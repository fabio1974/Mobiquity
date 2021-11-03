package com.mobiquity.model;

public class Item {

    private int id;
    private double weigh;
    private int cost;

    public Item(int id, double weigh, int cost) {
        this.id = id;
        this.weigh = weigh;
        this.cost = cost;
    }

    public double getWeigh() {
        return weigh;
    }

    public int getCost() {return cost;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeigh(double weigh) {
        this.weigh = weigh;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
