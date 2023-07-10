package com.javaSnior.lambda;

import org.junit.Test;

import java.util.Comparator;

public class LambdaTest {

    @Test
    public void test1(){

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("woaini");
            }
        };
        r1.run();
        System.out.println("************");

        Runnable r2 =() -> System.out.println("iloveyou");
        r2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare1 = com1.compare(21,12);
        System.out.println(compare1);

        System.out.println("************");
        //lambda 表达式
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1,o2);
        int compare2 = com1.compare(31,13);
        System.out.println(compare2);

        System.out.println("************");
        //方法引用
        Comparator<Integer> com3 =Integer::compare;
        int compare3 = com3.compare(14,41);
        System.out.println(compare3);
    }
}
