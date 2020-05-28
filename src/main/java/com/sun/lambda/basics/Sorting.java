package com.sun.lambda.basics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * 排序
 */
public class Sorting {
    List<Apple> inventory = new ArrayList<>();

    @Before
    public void init() {
        inventory.addAll(Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red")));
    }

    /**
     * java8以前的排序方式
     */
    @Test
    public void test1() {
        inventory.sort(new AppleComparator());
        System.out.println(inventory);
    }

    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    /**
     * java8之前通过内部类实现
     */
    @Test
    public void test2() {
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
        System.out.println(inventory);
    }

    @Test
    public void test3() {
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
    }

    @Test
    public void test4() {
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    @Test
    public void test5() {
        inventory.stream().sorted(comparing(Apple::getWeight)).forEach(System.out::println);
    }

    /**
     * 按照某个属性排序后 再按照另一个属性排序
     */
    @Test
    public void test6() {
        inventory.stream().sorted(comparing(Apple::getWeight).thenComparing(Apple::getColor)).forEach(System.out::println);
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
