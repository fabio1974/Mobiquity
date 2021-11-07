package com.mobiquity.model;

public class Item {

    private final int id;
    private final double weigh;
    private final int cost;

    public Item(int id, double weigh, int cost) {
        this.id = id;
        this.weigh = weigh;
        this.cost = cost;
    }

    public double getWeigh() {
        return weigh;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
