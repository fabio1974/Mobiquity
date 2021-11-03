package com.mobiquity.combinations;


import java.util.*;
import java.util.stream.Collectors;

public class Combinations {

    private void helper(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }

    public List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }

    public static void main(String[] args) {

        var result = combs(new int[]{1,2,3});
        System.out.println(result);
    }

    private static List<int[]> combs(int[] arr) {
/*        if (arr.length == 0) {
            int[] e = {};
            var el = new ArrayList(List.of());
            el.add(e);
            return el;
        }
        var first = arr[0];
        var n1 = Arrays.stream(arr).skip(0).toArray();
        var rest = combs();
        var newOrder = rest.stream().map(it -> {
          it.add(first);
          return it;
        }).collect(Collectors.toList());
        return concat(rest,newOrder);*/
        return null;
    }

    //private List<List<Integer>> concat(List<Integer> a, List<Integer> b) {
    //return  concat(a.stream(),b.stream()).collect(toList());


    public static <T> LinkedList<T> concat(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();
        set.addAll(list1);
        set.addAll(list2);
        return new LinkedList<T>(set);
    }

}
