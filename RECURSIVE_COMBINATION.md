# Algorithm: My Recursive Combination 

This is a recursive combination algorithm fast explanation:
With time complexity O(2^n), this algorithm starts from a 2D empty list containing the first empty combination.
Recursively, it builds new combinations by putting or not putting each element of the list to
the previous combinations set, to build the new one. For instance, lets say an input = [1,2,3]:
* [] -> [[]]  first empty combination
* 1  -> [[],[1]]      => first iteration...
* 2  -> [[],[1],[2],[1,2]]  => second iteration...
* 3  -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]  => array length = 2^n

In each iteration, we join the previous set that "not" contains the current element with the
set that "contains" the current element. The set that contains the element is formed by iterating and inserting
the current element into the "not contains" set.

Implementation [here](src/main/java/com/mobiquity/observables/CombinationRecursive.java).
