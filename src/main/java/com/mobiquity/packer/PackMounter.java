package com.mobiquity.packer;

import com.mobiquity.combinations.CombinationAlgorithm;
import com.mobiquity.model.Item;
import com.mobiquity.model.Pack;

import java.util.List;
import java.util.stream.Collectors;

public class PackMounter {

    CombinationAlgorithm combination;

    public PackMounter(CombinationAlgorithm combinationAlgorithm) {
        this.combination = combinationAlgorithm;
    }

    /**
     * This method mounts the pack, picking up the items so that the cost is maximized with weight
     * shorter than the limit for the pack.
     * */

    public int[] mount(Pack pack){

        var items = pack.getAvalilableItems();
        int N = items.size();  //N has a max value of 15
        int[] result = {};
        double lastMaxPackWeigth = -1;
        int maxCost = -1;

        for (int i = 1; i <= N; i++) {
            var combs = combination.combine(N,i);
            for (List<Integer> comb : combs) {

                //mounting the pack, picking up the items accordingly with id combinations.
                // I'm using (id-1) because items in the file are 1-based
                var mountedPack = comb.stream().map(id->items.get(id-1)).collect(Collectors.toList());

                //weight of the mountedPack
                double weigth = mountedPack.stream().mapToDouble(Item::getWeigh).sum();

                if (weigth <= pack.getWeighLimit() && weigth <= 100.0) {

                    //cost of the mounted pack.
                    var cost = mountedPack.stream().mapToInt(Item::getCost).sum();

                    //if we have two equals max cost configurations with acceptable weight, we choose the lighter one
                    boolean chooseTheLighter = cost == maxCost && weigth < lastMaxPackWeigth;

                    if (cost > maxCost || chooseTheLighter ) {
                        maxCost = cost;
                        result = mountedPack.stream().mapToInt(Item::getId).toArray();
                        lastMaxPackWeigth = weigth;
                    }
                }
            }
        }
        return result;
    }
}
