package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.*;

/**
 * data representation of each line from the input file
 * pack = "81 : (1,53.38,€45) (2,88.62,€18) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)"
 */

public class Pack {

    private final List<Item> availableItems = new ArrayList<Item>();
    private Double weighLimit = 0.0;


    public Pack() {
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }

    public void setWeighLimit(Double weighLimit) {
        this.weighLimit = weighLimit;
    }

    public Double getWeighLimit() {
        return weighLimit;
    }

    public void addAvailableItem(Item item) throws APIException {
        if(availableItems.size()>14)
            throw new APIException("Pack with more than 15 items!");
        availableItems.add(item);
    }
}
