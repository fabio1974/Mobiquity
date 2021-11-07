package com.mobiquity.observables;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationRecursive extends Observable {

    @Override
    public void runCombinations(){
        combs(availableItemIndexes);
    }

    private List<List<Integer>> combs(List<Integer> arr) {
        if(arr.size()==0)
            return new ArrayList<>(List.of(new ArrayList<>())); //2D array containing one empty array -> [[]]

        var first = arr.get(0);
        var subListCombs = combs(arr.subList(1, arr.size()));

        //the next level combinations are composed by joining the sublist combinations with the first one
        var mapped = subListCombs.stream().map(it->{
            List<Integer> combination = new ArrayList<>();
            combination.add(first);
            combination.addAll(it);
            this.notifyObserver(combination); //this line sends each combination to the observer
          return combination;
        }).collect(Collectors.toList());

        //adding the sublist combinations for the entire 2D combinations
        mapped.addAll(subListCombs);
        return mapped;
    }
}
