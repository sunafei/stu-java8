package com.sun.lambda.basics;

import com.sun.lambda.basics.base.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * 映射
 */
public class Mapping {

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
     * 挑选某个属性的所有值组成list/set
     */
    @Test
    public void test1() {
        menu.stream()
                .map(Dish::getName)
                .collect(toList()).forEach(System.out::println);

        menu.stream()
                .map(Dish::getName)
                .collect(toSet()).forEach(System.out::println);
    }

    /**
     * 计算每个字符串的长度
     */
    @Test
    public void test2() {
        Arrays.asList("Hello", "World!").stream()
                .map(String::length)
                .collect(toList()).forEach(System.out::println);
    }

    /**
     * 分别处理和合并后处理  map/flatMap
     */
    @Test
    public void test3() {
        List<String> words = Arrays.asList("Hello", "World");
        // map --> Stream<String[]> 这里的distinct是在根据Stream<String[]>对象去重
        Stream<String[]> distinct = words.stream()
                .map(word -> word.split(""))
                .distinct();
        distinct.forEach(t -> {
            Arrays.stream(t).forEach(System.out::println);
        });
        System.out.println("======================");

        // flatMap  各个数组并不是分别映射成一个流 而是映射成流的内容
        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 组装二维数组
     */
    @Test
    public void test4() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        numbers1.stream()
                .flatMap((Integer i) -> numbers2.stream()
                        .map((Integer j) -> new int[]{i, j})
                ).collect(toList()).forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }

    /**
     * 组装二维数组并过滤
     */
    @Test
    public void test5() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .map((Integer j) -> new int[]{i, j})
                        )
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }

    /**
     * 读取对象的某个属性并拼接字符串
     */
    @Test
    public void test6() {
        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(",")));
    }
}
