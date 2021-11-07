package com.mobiquity.observables;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.observer.Observer;

import java.util.List;
import java.util.stream.Collectors;

//Observable class
public abstract class Observable {

    Pack pack;
    private Observer observer;

    //This list represents the indexes from all items, and is the base to calculate all combinations to send to the observer.
    //As the pack object, this property can be used by any type of concrete observable
    List<Integer> availableItemIndexes;

    public Observable() {
    }

    //as a contract,any combination algorithm (a different concrete observable)
    //must implement this method and emit each combination inside it
    //through notifyObserver
    public abstract void runCombinations();

    public void setPack(Pack pack){
        this.pack = pack;
        this.availableItemIndexes = pack.getAvailableItems().stream().map(Item::getId).collect(Collectors.toList());
    }

    public void setObserver(Observer observer){
        this.observer = observer;
    }

    public void notifyObserver(List<Integer> combination){
        if(observer!=null)
            observer.update(combination);
    }
}
