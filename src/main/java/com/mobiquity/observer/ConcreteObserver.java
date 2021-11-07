package com.mobiquity.observer;

import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;
import com.mobiquity.packer.SingletonOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConcreteObserver implements Observer{

    private final Pack pack;
    private int[] idealPackIndexes = {};
    private double lastMaxPackWeigth = -1;
    private int maxCost = -1;

    public ConcreteObserver(Pack pack){
        this.pack = pack;
    }

    @Override
    public void update(List<Integer> combination) {
        final var items = pack.getAvailableItems();
        var mountedPack = combination.stream().map(id->items.get(id-1)).collect(Collectors.toList());

        //weight of the mountedPack
        double weigth = mountedPack.stream().mapToDouble(Item::getWeigh).sum();

        if (weigth <= pack.getWeighLimit() && weigth <= 100.0) {
            //cost of the mounted pack.
            var cost = mountedPack.stream().mapToInt(Item::getCost).sum();

            //if we have two equals max cost configurations with acceptable weight, we choose the lighter one
            boolean chooseTheLighter = cost == maxCost && weigth < lastMaxPackWeigth;

            if (cost > maxCost || chooseTheLighter ) {
                setIdealPackage(mountedPack, weigth, cost);
            }
        }
    }

    private void setIdealPackage(List<Item> mountedPack, double weigth, int cost) {
        maxCost = cost;
        idealPackIndexes = mountedPack.stream().mapToInt(Item::getId).toArray();
        lastMaxPackWeigth = weigth;
    }

    /**
     * sets the output with calculated indexes in update() method
     */
    public void printLineToOutput() {
        SingletonOutput.getInstance().append(formatReturnLine(idealPackIndexes)).append("\n");
    }

    /**
     * String representation of a line in the output
     * */
    private static String formatReturnLine(int[] indexes){
        return indexes.length==0? "-" :
                Arrays.stream(indexes).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}
