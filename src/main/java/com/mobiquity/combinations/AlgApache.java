package com.mobiquity.combinations;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
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
public class AlgApache implements CombinationAlgorithm{

    @Override
    public List<List<Integer>> calculateCombinations(int N, int R) {
        Iterator<int[]> combinations = CombinatoricsUtils.combinationsIterator(N, R);
        return convertType(combinations);
    }

    private List<List<Integer>> convertType(Iterator<int[]> combinations) {
        List<List<Integer>> r =  new ArrayList<>();
        while (combinations.hasNext()) {
            int[] combination = combinations.next();
            List<Integer> list =  Arrays.stream(combination).map(it->it+1).boxed().collect(Collectors.toList());
            r.add(list);
        }
        return r;
    }
}
