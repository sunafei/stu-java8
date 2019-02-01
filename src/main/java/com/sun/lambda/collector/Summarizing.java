package com.sun.lambda.collector;

import com.sun.lambda.collector.base.Dish;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;
import static com.sun.lambda.collector.base.Dish.menu;

/**
 * 规约与汇总
 */
public class Summarizing {
    @Test
    public void test1() {
        System.out.println(menu.size());
        long count = menu.stream().collect(counting());
        System.out.println(count);
    }

    @Test
    public void test2() {
        Dish dish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
        System.out.println(dish);
    }

    @Test
    public void test3() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        Dish dish = menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    @Test
    public void test4() {
        int sum = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(sum);
    }

    @Test
    public void test5() {
        Double avg = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avg);
    }

    /**
     * 收集对象的统计信息
     * IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
     */
    @Test
    public void test6() {
        IntSummaryStatistics _intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(_intSummaryStatistics);
    }

    /**
     * 合并获取的值
     */
    @Test
    public void test7() {
        String _string = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(_string);
    }

    @Test
    public void test8() {
        String _string = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(_string);
    }
}
