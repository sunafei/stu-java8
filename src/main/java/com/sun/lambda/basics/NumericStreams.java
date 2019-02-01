package com.sun.lambda.basics;


import com.sun.lambda.basics.base.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 归约
 */
public class NumericStreams {
    List<Dish> menu = null;

    @Before
    public void init() {
        menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    /**
     * 对象的某一项求和
     */
    @Test
    public void test1() {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Number of calories:" + calories);
    }

    @Test
    public void test2() {
        // max and OptionalInt
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max;
        if (maxCalories.isPresent()) {
            max = maxCalories.getAsInt();
        } else {
            // we can choose a default value
            max = 1;
        }
        System.out.println(max);
    }

    /**
     * 数组范围 range不包含结束值 rangeClosed包含结束值
     */
    @Test
    public void test3() {
        // numeric ranges
        IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count());

        IntStream evenNumbers2 = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);

        System.out.println(evenNumbers2.count());
    }

    /**
     * 生成1-100的勾股数 boxed?什么意思
     */
    @Test
    public void test4() {
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
