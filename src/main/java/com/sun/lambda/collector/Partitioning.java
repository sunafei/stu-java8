package com.sun.lambda.collector;

import com.sun.lambda.collector.base.Dish;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static com.sun.lambda.collector.base.Dish.menu;

/**
 * 分区
 * partitioningBy和groupingBy都是用于将数据进行分组的函数
 * 参数一个Predicate接口，那么这个接口的返回值是boolean类型的，也只能是boolean类型，
 * 然后他的返回值是Map的key是boolean类型，也就是这个函数的返回值只能将数据分为两组也就是ture和false两组数据。
 */
public class Partitioning {

    @Test
    public void test1() {
        Map<Boolean, List<Dish>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(map);
    }

    @Test
    public void test2() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> map = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(map);
    }

    @Test
    public void test3() {
        Map<Boolean, Dish> map = menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(map);
    }

}

