package com.mobiquity.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Combination algorithm used in the solution. This is a iterative solutions from a 
 */
public class AlgIterative implements CombinationAlgorithm {

    public List<List<Integer>> calculateCombinations(int N, int R) {
        //first combination [1,2,3,...,R]
        var nums = new LinkedList<Integer>();
        for(int i = 1; i < R + 1; ++i)
            nums.add(i);
        nums.add(N + 1);

        var output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < R) {
            // adding the current combination
            output.add(new LinkedList<Integer>(nums.subList(0, R)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < R) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}
