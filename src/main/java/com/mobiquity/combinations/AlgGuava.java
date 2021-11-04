package com.mobiquity.combinations;

import com.google.common.collect.*;

import java.util.*;

public class AlgGuava implements CombinationAlgorithm{

    @Override
    public List<List<Integer>> combine(int N, int R) {
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


