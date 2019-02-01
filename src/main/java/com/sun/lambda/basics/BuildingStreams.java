package com.sun.lambda.basics;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构建流
 */
public class BuildingStreams {

    /**
     * 构建String类型stream并转成大写输出
     */
    @Test
    public void test1() {
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 创建空的流
     */
    @Test
    public void test2() {
        Stream<String> emptyStream = Stream.empty();
    }

    /**
     * 数组转stream(IntStream)并求和
     */
    @Test
    public void test3() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());
    }

    /**
     * 循环加2
     * 0为起始值
     */
    @Test
    public void test4() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * fibonnaci序列 输出数组
     */
    @Test
    public void test5() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
    }

    /**
     * fibonnaci序列 通过map输出单个值
     */
    @Test
    public void test6() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

    }

    /**
     * 生成10个随机数
     * Stream.generate和Stream.iterate创建无限流
     * 一般来说需要一次生成一系列值得时候应该使用iterate
     */
    @Test
    public void test7() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

    }

    /**
     * 生成5个相同的1
     */
    @Test
    public void test8() {
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

    }

    /**
     * 读取文件内容并空格分割去重
     * @throws IOException
     */
    @Test
    public void test9() throws IOException {
        Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .forEach(System.out::println);
    }
}
