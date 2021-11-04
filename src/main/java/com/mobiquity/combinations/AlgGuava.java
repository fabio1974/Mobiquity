package com.mobiquity.combinations;

import com.google.common.collect.*;

import java.util.*;

/**
 * Class to implement third part combination calculus (Guava lib from Google in this case).
 * This class is not used in the program. The gaol here
 * is just to show the extensibility by providing any algorithm.
 * The convertType method is not even performative. This was written just for completeness
 * of tests.
 */

public class AlgGuava implements CombinationAlgorithm{

    @Override
    public List<List<Integer>> calculateCombinations(int N, int R) {
        Range<Integer> range = Range.closed(1, N);
        var set = ContiguousSet.create(range,DiscreteDomain.integers());
        var combinations = Sets.combinations(set, R);
        return convertType(combinations);
    }

    private List<List<Integer>> convertType(Set<Set<Integer>> combinations) {
        List<List<Integer>> r =  new ArrayList<>();
        combinations.forEach(set->{
            var list = new ArrayList<>(set);
            r.add(list);
        });
        return r;
    }

}


