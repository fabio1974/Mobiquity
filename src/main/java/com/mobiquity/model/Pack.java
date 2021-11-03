package com.mobiquity.model;

import com.mobiquity.exception.APIException;

import java.util.*;

public class Pack {

    private final List<Item> avalilableItems = new ArrayList<Item>();

    private Double weighLimit = 0.0;

    public Pack() {
    }

    public List<Item> getAvalilableItems() {
        return avalilableItems;
    }

    public void setWeighLimit(Double weighLimit) {
        this.weighLimit = weighLimit;
    }

    public Double getWeighLimit() {
        return weighLimit;
    }

    public void addAvailableItem(Item item) throws APIException {
        if(avalilableItems.size()>14)
            throw new APIException("Pack with more than 15 items ");
        avalilableItems.add(item);
    }
}
