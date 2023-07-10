package com.xiaochao.笔试;

import java.util.*;

public class 游戏拿钥匙开宝箱 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String json = in.nextLine();
        json = json.substring(1, json.length() - 1);
        String[] strs = json.split(",");
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int n = 0;
        for (int i = 0; i < json.length(); ++i) {
            if (json.charAt(i) == '[') {
                continue;
            } else if (json.charAt(i) == ']') {
                list.add(builder.toString());
                builder = new StringBuilder();
                i++;
                n++;
            } else {
                builder.append(json.charAt(i));
            }
        }
        List<List<Integer>> list1 = new ArrayList<>();
        for (String s : list) {
            if (s.equals("")) {
                list1.add(new ArrayList<>());
                continue;
            }
            String[] strings = s.split(",");
            List<Integer> list2 = new ArrayList<>();
            for (String t : strings) {
                list2.add(Integer.valueOf(t));
            }
            list1.add(list2);
        }
        boolean[] used = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list2 = list1.get(0);
        used[0] = true;
        int k = 1;
        for (int i : list2) {
            deque.addLast(i);
            used[i] = true;
            k++;
        }
        while (!deque.isEmpty()) {
            int i = deque.removeFirst();
            List<Integer> l3 = list1.get(i);
            if (l3.size() == 0) {
                continue;
            }
            for (int v : l3) {
                if (!used[v]) {
                    k++;
                    used[v] = true;
                    deque.addLast(v);
                }
            }
            if (k == n) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }
}
