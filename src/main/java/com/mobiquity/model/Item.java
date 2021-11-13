package com.mobiquity.model;

/**
 * each instance represents a chunk from the second part of the file line like
 * item = (1,53.38,€45)
 */
public class Item {

    private final int id;
    private final double weigh;
    private final double cost;

    public Item(int id, double weigh, double cost) {
        this.id = id;
        this.weigh = weigh;
        this.cost = cost;
    }

    public double getWeigh() {
        return weigh;
    }

    public double getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
