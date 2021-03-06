package com.mobiquity.observer;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The concrete observer to solve each line of the input file.
 * There is one instance of the class for each line.
 */
public class ConcreteObserver implements Observer{
    //represents the input file line being analysed
    private final Pack pack;

    //theses three variables represents the state of each observer
    //they are used to find the best pack index combination
    private int[] idealPackIndexes = {};
    private double lastMaxPackWeigth = -1;
    private double maxCost = -1;

    public ConcreteObserver(Pack pack){
        this.pack = pack;
    }

    /**
     * Main point of an observer, represents arriving of the messages emitted from the observable
     * This method try to find the max cost from all combinations received by the instance
     * @param combination of indexes taken from all available pack indexes
     */
    @Override
    public void update(List<Integer> combination) {
        final var items = pack.getAvailableItems();

        //pack mounted with the combination passed. (id-1) is used because the file is 1-one indexed
        var mountedPack = combination.stream().map(id->items.get(id-1)).collect(Collectors.toList());

        //weight of the mountedPack
        double weigth = mountedPack.stream().mapToDouble(Item::getWeigh).sum();

        if (weigth <= pack.getWeighLimit() && weigth <= 100.0) {
            //cost of the mounted pack.
            var cost = mountedPack.stream().mapToDouble(Item::getCost).sum();

            //if we have two equals max cost configurations with acceptable weight, we choose the lighter one
            boolean chooseTheLighter = cost == maxCost && weigth < lastMaxPackWeigth;

            if (cost > maxCost || chooseTheLighter ) {
                setIdealPackage(mountedPack, weigth, cost);
            }
        }
    }

    /**
     * updates the state of the instance to the best solution so far.
     */
    private void setIdealPackage(List<Item> mountedPack, double weigth, double cost) {
        maxCost = cost;
        idealPackIndexes = mountedPack.stream().mapToInt(Item::getId).toArray();
        lastMaxPackWeigth = weigth;
    }

    /**
     * final string representation of a line in the output, from idealPackIndexes set
     * */
    public String getFormattedOutputLine(){
        return idealPackIndexes.length==0? "-" :
                Arrays.stream(idealPackIndexes)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(","));
    }
}
