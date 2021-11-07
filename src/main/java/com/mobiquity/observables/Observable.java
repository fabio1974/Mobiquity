package com.mobiquity.observables;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Observable class
public abstract class Observable {

    protected Pack pack;
    protected List<Integer> availableItemIndexes;
    private Observer observer;

    public Observable() {
    }

    public abstract void runCombinations();

    public void setPack(Pack pack){
        this.pack = pack;
        this.availableItemIndexes = new ArrayList<>();
        this.availableItemIndexes = pack.getAvailableItems().stream().map(Item::getId).collect(Collectors.toList());
    }

    public void setObserver(Observer observer){
        this.observer = observer;
    }

    public void notifyObserver(List<Integer> combination){
         observer.update(combination);
    }
}
