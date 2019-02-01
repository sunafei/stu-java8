package com.sun.lambda.basics;


import com.sun.lambda.basics.base.Trader;
import com.sun.lambda.basics.base.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 示例
 */
public class PuttingIntoPractice {
    List<Transaction> transactions = null;

    @Before
    public void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * Query 1: Find all transactions from year 2011 and sort them by value (small to high).
     * 查询1：查找2011年的所有交易，并按值（从小到高）排序。
     */
    @Test
    public void test1() {
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(tr2011);
    }

    /**
     * Query 2: What are all the unique cities where the traders work?
     * 问题2：交易者工作的唯一城市是什么？
     */
    @Test
    public void test2() {
        List<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);
    }

    /**
     * Query 3: Find all traders from Cambridge and sort them by name.
     * 查询3：查找剑桥的所有交易者并按名称排序。
     */
    @Test
    public void test3() {
        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);
    }

    /**
     * Query 4: Return a string of all traders’ names sorted alphabetically.
     * 查询4：返回按字母顺序排序的所有交易者名称的字符串。
     */
    @Test
    public void test4() {
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);
    }

    /**
     * Query 5: Are there any trader based in Milan?
     * 问题5：米兰有商人吗？
     */
    @Test
    public void test5() {
        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);
    }

    /**
     * Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
     * 查询6：更新所有交易，以便米兰的交易员设置为剑桥。
     */
    @Test
    public void test6() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);
    }

    /**
     * Query 7: What's the highest value in all the transactions?
     * 查询7：所有交易中的最高值是什么？
     */
    @Test
    public void test7() {
        int highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);
    }
}