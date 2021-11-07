package com.mobiquity.observables;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to implement third part combination calculus (Apache Commons in this case).
 * This class is not used in the program. The goal here  is just to show the loose coupling
 * and the extensibility by providing any different algorithm.
 * This algorithm can be used by uncommenting one line in the Packer class
 *  */
public class CombinationCommonApache extends Observable {

    @Override
    public void runCombinations() {
        availableItemIndexes.forEach(R-> combs(availableItemIndexes.size(),R));
    }

    private void combs(int N, int R) {
        Iterator<int[]> combinations = CombinatoricsUtils.combinationsIterator(N, R);
        while (combinations.hasNext()) {
            int[] combination = combinations.next();
            List<Integer> converted =  Arrays.stream(combination)
                    .map(it->it+1).boxed()
                    .collect(Collectors.toList());

            //like in the recursive algorithm, each time we find a different combination,
            //we emit it to the observer
            notifyObserver(converted);
        }
    }
}
