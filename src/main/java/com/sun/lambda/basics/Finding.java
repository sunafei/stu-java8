package com.sun.lambda.basics;


import com.sun.lambda.basics.base.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 查找和匹配
 */
public class Finding {
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
     * 流中是否有一个元素能匹配给定的谓词
     */
    @Test
    public void test1() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("Vegetarian friendly");
        }
    }

    /**
     * 检查谓词是否匹配所有元素
     */
    @Test
    public void test2() {
        System.out.println(menu.stream().allMatch(d -> d.getCalories() < 1000));
        System.out.println(menu.stream().allMatch(d -> d.getCalories() < 100));
    }

    /**
     * 和allMatch相对 确保流中没有任何元素与给定的谓词匹配
     */
    @Test
    public void test3() {
        System.out.println(menu.stream().noneMatch(d -> d.getCalories() >= 1000));
        System.out.println(menu.stream().noneMatch(d -> d.getCalories() >= 500));
    }

    /**
     * 返回当前流中的任意元素 利用短路找到结果立即结束
     */
    @Test
    public void test4() {
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }
}
