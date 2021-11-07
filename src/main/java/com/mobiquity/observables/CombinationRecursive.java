package com.mobiquity.observables;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is a recursive combination algorithm implementation.
 * Explanation:
 * This is a O(2^n) algorithm which starts from a 2D empty list containing the first empty combination.
 * Recursively, it builds new combinations by putting or not putting each element of the list to
 * the previous combinations set, to build the new one. For instance, lets say an input = [1,2,3]:
 * [] -> [[]]  first empty combination
 * 1  -> [[],[1]]      => first iteration...
 * 2  -> [[],[1],[2],[1,2]]  => second iteration...
 * 3  -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]  => array length = 2^n
 * In each iteration, we join the previous set that "not" contains the current element with the
 * set that "contains" the element. The set that contains the element is formed by iterating and inserting
 * the current element into the "not contains" set.
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
