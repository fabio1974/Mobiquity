package com.mobiquity.combinations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AlgIterative implements CombinationAlgorithm {

    public static void main(String[] args) {
        var s = new AlgIterative();
        int N = 4;
        for (int i = 0; i <= N ; i++) {
            s.combine(N,i).forEach((it)-> printLine(it));
        }
    }

    static int count = 0;

    private static void printLine(List<Integer> it) {
        System.out.println( (count++) + "["+it.stream().map(String::valueOf).collect(Collectors.joining(","))+"]");
    }

    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            output.add(new LinkedList(nums.subList(0, k)));
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}
