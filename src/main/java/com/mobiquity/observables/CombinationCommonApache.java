package com.mobiquity.observables;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to implement third part combination calculus (Apache Commons in this case).
 * This class is not used in the program. The gaol here
 * is just to show the extensibility by providing any algorithm.
 * The convertType method is not even performative. This was written just for completeness of tests.
 */
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

            //this line sends each combination to the observer
            notifyObserver(converted);
        }
    }
}
