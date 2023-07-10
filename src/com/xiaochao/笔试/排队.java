package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 在遥远的国度有一位国王，他每天的工作都十分繁忙，因为每天都有许多大臣来向他汇报各种信息等。这天有n位大臣来汇报信息，
 * 其中第i位的序号为i，为了更有效的完成每天的工作，国王决定给每位大臣汇报的事情按重要性进行一个排序，让各位大臣按排序依次汇报。
 * 首先对每人的汇报在m个方面各评估一个重要性，然后每个汇报的重要性就是m个方面的重要性之和，重要性越高的汇报会排在越前面，对于重要性相同的，
 * 则按大臣的序号排序，越小的在越前面。这时，序号为id的大臣找到了你，他想请你帮他计算一下他排在第几个。
 * 输入描述
 * 第一行三个正整数n、m和id，表示大臣数量、重要性方面数量和来找你帮忙的大臣序号。
 * 接下来n行每行m个正整数，第i行为ai1, ai2,...... aim，其中aij表示序号为i的大臣的汇报在第j个方面的重要性。
 * 输出描述
 * 输出一行一个正整数ans，表示序号为i的大臣排在第ans位。
 * 样例输入
 * 3 3 2
 * 90 90 90
 * 80 100 90
 * 80 85 85
 * 样例输出
 * 2
 * 提示
 * 1 ≤ n ≤ 500, 1 ≤ m ≤ 500, 1 ≤ aij ≤ 100。
 */

import java.util.TreeSet;

public class 排队 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int id = sc.nextInt();
        TreeSet<Person> set = new TreeSet<>((a, b) -> {
            if (a.point == b.point) {
                return a.id - b.id;
            } else {
                return b.point - a.point;
            }
        });
        for (int i = 0; i < n; i++) {
            int index = i + 1;
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += sc.nextInt();
            }
            Person person = new Person(index, sum);
            set.add(person);
        }
        int res = 0;
        for (Person person : set) {
            res++;
            if (person.id == id) {
                System.out.println(res);
                return;
            }
        }
    }
}

class Person {
    int id;
    int point;

    public Person(int id, int point) {
        this.id = id;
        this.point = point;
    }
}

