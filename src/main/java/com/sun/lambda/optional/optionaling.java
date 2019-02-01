package com.sun.lambda.optional;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 缺省值
 */
public class optionaling {

    /**
     * 尝试访问emptyOpt变量的值会导致 NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void test1() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**
     * 可以使用  of() 和 ofNullable() 方法创建包含值的 Optional。
     * 两个方法的不同之处在于如果你把 null 值作为参数传递进去，of() 方法会抛出 NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void test2() {
        User user = null;
        Optional<User> opt = Optional.of(user);
    }

    /**
     * 如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法
     */
    @Test
    public void test3() {
        User user = null;
        Optional<User> opt = Optional.ofNullable(user);
    }

    /**
     * orElse 如果有值则返回该值，否则返回传递给它的参数值
     * 如果对象的初始值不是 null，那么默认值会被忽略
     */
    @Test
    public void test4() {
        User user = null;
        User user2 = new User("123", "321");
        User result = Optional.ofNullable(user).orElse(user2);
        System.out.println(result.name);
    }

    /**
     * orElseGet
     * 两个 Optional 对象都包含非空值，两个方法都会返回对应的非空值。
     * 不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
     */
    @Test
    public void test5() {
        User user = new User("321", "321");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private User createNewUser() {
        System.out.println("create user");
        return new User("123", "123");
    }

    /**
     * 返回异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void test6() {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException());
    }

    @Test
    public void test7() {
        User user = new User("1234", "1234");
        String name = Optional.ofNullable(user)
                .map(u -> u.name).orElse("4321");
        System.out.println(name);
    }

    @Test
    public void test8() {
        User user = new User("1234", null);
        String name = Optional.ofNullable(user)
                .flatMap(u -> u.getNameStr()).orElse("default");
        System.out.println(name);
    }

    @Test
    public void test9() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.id != null && u.id.contains("@"));
        if (result.isPresent()) {
            System.out.println(result.get().name);
        }
    }

    @Test
    public void test10() {
        User user = new User("123", null);
        Address address = new Address();
        address.id = "232132";
        user.address = address;
        String result = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getId)
                .orElse("default");
        System.out.println(result);
    }

    class User {
        User(String id, String name){
            this.id = id;
            this.name = name;
        }
        String id;
        String name;
        Address address;
        Optional<String> getNameStr() {
            return Optional.ofNullable(name);
        }
        Optional<String> getIdStr() {
            return Optional.ofNullable(id);
        }

        Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
    }
    class Address {
        String id;
        Optional<String> getId() {
            return Optional.ofNullable(id);
        }
    }
}

