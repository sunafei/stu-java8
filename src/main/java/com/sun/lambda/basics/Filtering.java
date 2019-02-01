package com.sun.lambda.basics;


import com.sun.lambda.basics.base.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 筛选和切片
 */
public class Filtering {
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
     * 使用谓词筛选
     */
    @Test
    public void test1() {
        menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList()).forEach(System.out::println);
    }

    /**
     * 筛选去重
     */
    @Test
    public void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 截短流
     */
    @Test
    public void test3() {
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList()).forEach(System.out::println);
    }

    /**
     * 跳过元素
     * 返回一个扔掉前N个元素的流 如果流元素没有N个返回空 与limit互补 skip不包含N
     */
    @Test
    public void test4() {
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList()).forEach(System.out::println);

    }
}
