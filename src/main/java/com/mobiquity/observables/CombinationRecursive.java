package com.mobiquity.observables;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is a recursive combination algorithm implementation.
 * Explanation: read at RECURSIVE_COMBINATION.md
 */
public class CombinationRecursive extends Observable {

    @Override
    public void runCombinations(){
        combs(availableItemIndexes);
    }

    List<List<Integer>> combs(List<Integer> arr) {
        if(arr.size()==0)
            return new ArrayList<>(List.of(new ArrayList<>())); //2D array containing one empty array -> [[]]

        var current = arr.get(0);
        var combinationsWithoutCurrent = combs(arr.subList(1, arr.size()));

        //the next level combinations are composed by joining the sublist combinations with the 'first' element
        var combinationsWithCurrent = combinationsWithoutCurrent.stream().map(it->{
            List<Integer> combination = new ArrayList<>();
            combination.add(current);
            combination.addAll(it);
            //this next line 'emits' each combination to the observer. It leaves out the empty combination
            //I decided not to store the combinations to avoid over looping
            this.notifyObserver(combination);
          return combination;
        }).collect(Collectors.toList());

        //join all combinations into the same 2D array of combinations
        combinationsWithCurrent.addAll(combinationsWithoutCurrent);
        return combinationsWithCurrent;
    }
}
