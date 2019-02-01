package com.sun.lambda.basics;

import com.sun.lambda.basics.base.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 归约
 */
public class Reducing {

    static List<Dish> menu = null;

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
     * 求和
     */
    @Test
    public void test1() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    /**
     * 求和
     */
    @Test
    public void test2() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);
    }

    @Test
    public void test3() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        int max2 = numbers.stream().reduce(8, (a, b) -> Integer.max(a, b));
        System.out.println(max2);
    }

    @Test
    public void test4() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);
    }

    @Test
    public void test5() {
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
