package com.sun.lambda.collector;

import com.sun.lambda.collector.base.Dish;
import org.junit.Test;

import static java.util.stream.Collectors.reducing;
import static com.sun.lambda.collector.base.Dish.menu;

/**
 * 规约与汇总
 */
public class Reducing {

    /**
     * 对象字段求和
     */
    @Test
    public void test1() {
        int sum = menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
        System.out.println(sum);
    }

    /**
     * 对象字段求和
     */
    @Test
    public void test2() {
        int sum = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(sum);
    }

    /**
     * 对象字段求最大值
     */
    @Test
    public void test3() {
        int max = menu.stream().collect(reducing(0, Dish::getCalories, Integer::max));
        System.out.println(max);
    }

    /**
     * 对象字段求和
     */
    @Test
    public void test4() {
        int sum = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(sum);
    }

    /**
     * 对象字段求和
     */
    @Test
    public void test5() {
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
    }
}