package com.leixing.动态规划.背包DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 金明今天很开心，家里购置的新房就要领钥匙了，新房里有一间金明自己专用的很宽敞的房间。
 * 更让他高兴的是，妈妈昨天对他说：“你的房间需要购买哪些物品，怎么布置，你说了算，只要不超过 N 元钱就行”。
 * 今天一早，金明就开始做预算了，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。
 * 每个主件可以有 0 个、1 个或 2 个附件
 * 附件不再有从属于自己的附件。
 * 金明想买的东西很多，共有 M 件，肯定会超过妈妈限定的 N 元。
 * 于是，他把每件物品规定了一个重要度，分为 5 等：用整数 ~ 表示，第 5 等最重要。
 * 他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。
 * 他希望在不超过 N 元的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第 j 件物品的价格为 v[j]，重要度为 p[j]，共选中了 k 件物品，编号依次为 j1，j2，…，jk，则所求的总和为：
 * v[j1]*p[j1]+v[j2]*p[j2]+...+v[jk]*p[jk]
 * 请你帮助金明设计一个满足要求的购物单。
 *
 * 分组背包 の 二进制枚举
 * 在 上一讲 的「分组背包」解法中，我们通过「预处理主件和附件关系 - 构建分组物品 - 套用分组背包解决方案」来解决了本题。
 * 其中预处理主件和附件关系复杂度为 O(m) ；构建物品组复杂度为O(m^2) ；套用分组背包解决方案的复杂度为 O(m*n)。
 * 「构建分组物品」部分，我们可以使用「二进制枚举」来代替，虽然这不会改变我们复杂度的上界，但可以使我们代码更短。
 * 对于某个主件 x 而言，如果其有 cnt  个附件，我们可以使用状态 state（范围为 [0,(1<<cnt)-1]）来代指所有附件选择方案。
 * state 的低 2 位来代表某个附件是否被选择，如果 state 的第 k 位为 1，说明附件 k 被选择；如果第 k 位为 0，说明附件  不被选择。
 * 举个🌰 ，如果主件 x 存在两个附件，其状态范围为 [0,(1<<2)-1]：
 *(...00)2 ：低  2位全为 0，对应只选择主件，不选择任何附件的方案；
 *(...01)2 ：对应选择主件和附件 1 的方案；
 *(...10)2 ：对应选择主件和附件 2 的方案；
 *(...11)2 ：对应选择主件、附件 1 和附件 2 的方案。
 */
public class 树形背包二进制枚举 {
    public int maxValue(int n, int m, int[] v, int[] p, int[] q) {
        // 预处理出主件和附件的关系
        // { 主件1 : [附件1, 附件2], 主件2 : [], 主件3 : [附件1] ... }
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=1;i<=m;i++){
            if (q[i] == 0){
                if (!map.containsKey(i))
                    map.put(i,new ArrayList<>());
            }else {
                List<Integer> list=map.getOrDefault(q[i],new ArrayList<>());
                list.add(i);
                map.put(q[i],list);
            }
        }

        // 分组背包一维空间优化：原始定义 f[i][j] 为考虑前 i 件物品，使用容量不超过 j 的最大价值
        int[] f=new int[n + 1];
        for (int root:map.keySet()){
            int rv=v[root],rw=v[root] * p[root];
            for (int j = n; j >= 0; j--) {
                // 金额「从大到小」进行枚举，如果当前金额不足以选择主件，直接 break
                if (j < rv) break;

                // 该主件共有 cnt 件附件
                List<Integer> sub = map.get(root);
                int cnt = sub.size();

                // 二进制枚举所有的附件选择方案
                for (int state = 0; state < (1 << cnt); state++) {
                    // 选择方案中必须包含主件
                    int vi = rv, wi = rw;
                    for (int k = 0; k < cnt; k++) {
                        // 如果状态值中该位为 1，则选上该附件
                        if (((state >> k) & 1) == 1) {
                            int cur = sub.get(k);
                            vi += v[cur];
                            wi += v[cur] * p[cur];
                        }
                    }
                    if (j >= vi) f[j] = Math.max(f[j], f[j - vi] + wi);
                }
            }
        }
        return f[n];
    }
}
//时间复杂度：预处理主件和附件关系复杂度为O(m) ；使用二进制枚举并套用分组背包解决方案的复杂度为 O(m * n)。
// 整体复杂度为 O(m * n)
//空间复杂度：O(m + n)
