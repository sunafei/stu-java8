package com.sun.lambda.collector;

import com.sun.lambda.collector.base.Dish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static com.sun.lambda.collector.base.Dish.dishTags;
import static com.sun.lambda.collector.base.Dish.menu;

/**
 * 分组
 */
public class Grouping {

    enum CaloricLevel {DIET, NORMAL, FAT}

    /**
     * 以对象的某个属性分组
     */
    @Test
    public void test1() {
        Map<Dish.Type, List<Dish>> map = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(map);
    }

    /**
     * 以对象的某个树形分组并且map的value通过mapping处理
     */
    @Test
    public void test2() {
        Map<Dish.Type, List<String>> map = menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println(map);
    }

    /**
     * jdk1.9支持
     */
    @Test
    public void test10() {
        Map<Dish.Type, Set<String>> map = menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println(map);
    }

    /**
     * jdk1.9支持
     */
    @Test
    public void test11() {
        Map<Dish.Type, List<Dish>> collect = menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        System.out.println(collect);
        Map<Dish.Type, List<Dish>> collect1 = menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(collect1);
    }

    /**
     * 组装key的值
     */
    @Test
    public void test3() {
        Map<CaloricLevel, List<Dish>> map = menu.stream().collect(
                groupingBy(dish -> {
                    return getCaloricLevel(dish);
                }));
        System.out.println(map);
    }

    private CaloricLevel getCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    /**
     * 二次分组
     */
    @Test
    public void test4() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy((Dish dish) -> {
                            return getCaloricLevel(dish);
                        })
                )
        );
        System.out.println(map);
    }

    /**
     * 组装value的值
     */
    @Test
    public void test5() {
        Map<Dish.Type, Long> map = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(map);

        Map<Dish.Type, Integer> map2 = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(map2);
    }

    /**
     * 分组后过滤
     */
    @Test
    public void test6() {
        Map<Dish.Type, Optional<Dish>> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.get().getCalories());
        });
    }

    @Test
    public void test7() {
        Map<Dish.Type, Dish> map = menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
        System.out.println(map);
    }

    @Test
    public void test8() {
        Map<Dish.Type, Integer> map = menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
        System.out.println(map);
    }

    @Test
    public void test9() {
        Map<Dish.Type, Set<CaloricLevel>> map = menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            return getCaloricLevel(dish);
                        },
                        toSet())));
        System.out.println(map);
    }

    /**
     * 按照某个属性分组并且保留原来list的顺序
     */
    @Test
    public void test12() {
        Map<Integer, List<Dish>> map = menu.stream()
                .collect(Collectors.groupingBy(Dish::getCalories, TreeMap::new,Collectors.toList()));
        System.out.println(map);
    }
}
