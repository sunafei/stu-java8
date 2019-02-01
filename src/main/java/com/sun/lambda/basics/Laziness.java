package com.sun.lambda.basics;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * stream并不是顺序执行的
 */
public class Laziness {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("mapping " + n);
                    return n * n;
                })
                .limit(2)
                .collect(toList()).forEach(System.out::println);
    }
//    filtering 1
//    filtering 2
//    mapping 2
//    filtering 3
//    filtering 4
//    mapping 4
}
