package com.pluralsight.testing.m2;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class HamcrestExampleTest {

    @Test
    public void mapShouldContainValue(){
        Map<String, Integer> values = getValues();

        //assertTrue(values.containsKey("B"));

        assertThat(values, Matchers.hasKey("B"));
    }

    private Map<String, Integer> getValues(){
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        return map;
    }

    @Test
    public void listOrderingIsIrrelevant(){
        List<Integer> numbers = getNumbers();

        //assertEquals(5, (int) numbers.get(4));

        assertThat(numbers, hasItem(5));

    }

    private List<Integer> getNumbers(){
        return Arrays.asList(1,2,3,5,4);
    }








}
