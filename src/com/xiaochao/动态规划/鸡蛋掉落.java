package com.xiaochao.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你 k 枚相同的鸡蛋，并可以使⽤⼀栋从第 1 层到第 n 层共有 n 层楼的建筑。 已知存在楼层 f，满⾜ 0 <= f <= n，
 * 任何从⾼于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或⽐它低的楼层 落下的鸡蛋都不会破。 每次操作，
 * 你可以取⼀枚没有碎的鸡蛋并把它从任⼀楼层 x 扔下（满⾜ 1 <= x <= n）。如果鸡蛋碎了， 你就不能再次使⽤它。
 * 如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中重复使⽤这枚鸡蛋。 请你计算并返回确定 f 的最⼩操作次数是多少？
 */
public class 鸡蛋掉落 {

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }

    public int dp(int k, int n) {
        if (!memo.containsKey(n * 100 + k)) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)), Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
            }

            memo.put(n * 100 + k, ans);
        }

        return memo.get(n * 100 + k);
    }
}
