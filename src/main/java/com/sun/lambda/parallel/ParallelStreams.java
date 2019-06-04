package com.sun.lambda.parallel;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流
 */
public class ParallelStreams {
    long N = 10_000L;

    @Test
    public void test1() {
        long result = 0;
        for (long i = 0; i <= N; i++) {
            result += i;
        }
        System.out.println(result);
    }

    @Test
    public void test2() {
        Stream.iterate(1L, i -> i + 1).limit(N).reduce(Long::sum).get();
    }

    @Test
    public void test3() {
        Stream.iterate(1L, i -> i + 1).limit(N).parallel().reduce(Long::sum).get();
    }

    @Test
    public void test4() {
        LongStream.rangeClosed(1, N).reduce(Long::sum).getAsLong();
    }

    @Test
    public void test5() {
        LongStream.rangeClosed(1, N).parallel().reduce(Long::sum).getAsLong();
    }
}
