package com.cjr.qita.有序表与并查集;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/**
 *输入描述:
 * 第一行为两个正整数N和M，分别表示罪犯的数目以及存在仇恨的罪犯对数。
 * 接下来的M行每行为三个正整数aj，bj，cj，表示aj号和bj号罪犯之间存在仇恨，其怨气值为cj。
 * 数据保证1≤aj＜bj≤N，0＜cj≤1,000,000,000，且每对罪犯组合只出现一次。
 * 输出描述:
 * 共1行，为Z市长看到的那个冲突事件的影响力。如果本年内监狱中未发生任何冲突事件，请输出0。
 */
public class guanYaZuiFan {
    static int n, m;
    static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    static Map<Integer, Boolean> color = new HashMap<>();

    static boolean check(int limit) {
        // reset
        color = new HashMap<>();

        // 遍历所有的边，尝试对未上色的边进行 dfs 上色
        for (int i = 1; i <= n; i++) {
            if (!color.containsKey(i)) {
                if (!dfs(i, true, limit)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean dfs(int a, boolean col, int limit) {
        color.put(a, col);

        Map<Integer, Integer> mm = map.get(a);
        if (mm != null) {
            for (int b : mm.keySet()) {
                int val = mm.get(b);
                if (val <= limit) continue;

                if (color.containsKey(b)) {
                    if (color.get(b) == col) {
                        return false;
                    }
                } else if (!dfs(b, !col, limit)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = reader.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        // arr 只存放存在的边
        int[] arr = new int[m + 1];
        arr[0] = 0; // 将 0 也加入候选
        for (int i = 1; i <= m; i++) {
            s = reader.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int val = Integer.parseInt(s[2]);

            Map<Integer, Integer> m1 = map.getOrDefault(a, new HashMap<Integer, Integer>());
            m1.put(b, val);
            map.put(a, m1);

            Map<Integer, Integer> m2 = map.getOrDefault(b, new HashMap<Integer, Integer>());
            m2.put(a, val);
            map.put(b, m2);

            arr[i] = val;
        }

        // 排序二分
        Arrays.sort(arr);
        int l = 0, r = m;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(arr[mid])) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        w.write(arr[l] + " ");

        reader.close();
        w.flush();
        w.close();
    }
}
