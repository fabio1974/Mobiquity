package com.mobiquity.observables;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationRecursiveTest {

    @Test
    public void recursiveCombinationOf3HasSize8(){
        var observable = new CombinationRecursive();
        var combs = observable.combs(new ArrayList<>(List.of(1,2,3)));
        assertEquals(8,combs.size());
    }

    @Test
    public void recursiveCombinationOf4HasSize16(){
        var observable = new CombinationRecursive();
        var combs = observable.combs(new ArrayList<>(List.of(1,2,3,4)));
        assertEquals(16,combs.size());
    }


}
