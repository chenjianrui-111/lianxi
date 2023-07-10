package com.javaSnior.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 * 消费性接口 Consumer<T> void accept(T t)
 * 供给型接口 Supplier<T> T get()
 * 函数型接口 Function<T,R> R apply(T t)
 * 断定型接口 Predicate<T> boolean test(T t)
 *
 */
public class LambdaTest2 {

    @Test
    public void  test1(){
        happyTime(2000, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("消费~~~~~~~~~~~~~~~~" + aDouble);
            }
        });
        System.out.println("**********************");

        happyTime(1000,(mon) -> System.out.println("消费~~~~~~~~~~~~~~~~" + mon ));
    }

    public void  happyTime(double money, Consumer<Double> cn){
        cn.accept(money);
    }

    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京","南京","天津","东京");
        List<String> filterString = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterString);
        System.out.println("*****************");

        List<String> filterStrs = filterString(list,s -> s.contains("京"));
        System.out.println(filterStrs);
    }
    //根据给定的规则，过滤集合中的字符串，此规则由Predicate决定
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        ArrayList<String> filterList = new ArrayList<>();

        for (String str : list){
            if (predicate.test(str)){
                filterList.add(str);
            }
        }
        return filterList;
    }

}
