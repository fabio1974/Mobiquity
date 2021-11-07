package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.*;

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
            throw new APIException("Pack with more than 15 items ");
        availableItems.add(item);
    }
}
