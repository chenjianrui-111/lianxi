package com.leixing.动态规划.背包DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * 给你一个整数数组 price 示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。
 * 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
 * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
 * 示例 1：
 * 输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * 输出：14
 * 解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
 * 大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
 * 大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
 * 需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
 * 示例 2：
 * 输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * 输出：11
 * 解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * 可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * 需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * 不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。
 * 提示：
 * n == price.length
 * n == needs.length
 * 1 <= n <= 6
 * 0 <= price[i] <= 10
 * 0 <= needs[i] <= 10
 * 1 <= special.length <= 100
 * special[i].length == n + 1
 * 0 <= special[i][j] <= 50
 * 转换 DFS（转换为礼包处理）
 * 对于某个 need[i] 而言，既可以「单买」也可以使用「礼包形式购买」，同时两种购买方式都存在对「份数」的决策（单买多少份/买多少个相应的礼包）。
 * 利用物品数量和礼包数量数据范围都较少，我们可以先对「单买」情况进行预处理，将其转换为「礼包」形式。若 price[0] = 100，则使用礼包 [1, 0, 0, ...,0, 100]来代指。
 * 然后再预处理每个礼包最多选多少个，并使用哈希表进行存储。
 * 最后使用 DFS 对每个「礼包」如何选择进行爆搜即可
 */
public class 大礼包 {
    int ans=0x3f3f3f3f;
    List<Integer> price,needs;
    List<List<Integer>> special;
    Map<Integer,Integer> map=new HashMap<>();
    int n,m;
    public int shoppingOffers(List<Integer> _price, List<List<Integer>> _special, List<Integer> _needs) {
        price=_price;special=_special;needs=_needs;
        //有n个商品
        n=price.size();
        //将单买的情况转化成背包
        List<Integer> temp=new ArrayList<>();
        for (int i=0;i<n;i++) temp.add(0);//首先全部补0
        for (int i=0;i<n;i++){
            List<Integer> clone=new ArrayList<>(temp);
            clone.set(i,1);//每个礼包里面只有单种商品
            clone.add(price.get(i));//最后加上商品的价格
            special.add(clone);
        }
        //对礼包进行暴力搜索
        m=special.size();
        for (int i=0;i<m;i++){
            List<Integer> x=special.get(i);//对于每个礼包
            int k=0;
            for (int j=0;j<n;j++){
                int a=x.get(j),b=needs.get(j);
                if (a==0 || b==0) continue;//如果需要商品或者这个礼包就没有相应的商品，就直接跳过
                if (a > b){
                    k=0;
                    break;
                }
                k=k == 0 ?b / a :Math.min(k , b / a);//计算需要多少个礼包
            }
            map.put(i,k);//每个礼包最多选多少个
        }
        dfs(0, needs, 0);
        return ans;
    }
    void dfs(int u, List<Integer> list, int cur) {
        //list是当前需求
        //cur表示当前的花费
        if (cur >= ans) return;//剪枝
        int cnt = 0;
        for (int i = 0; i < n; i++) cnt += list.get(i);
        if (cnt == 0) {
            ans = cur;
            return;
        }
        if (u == m) return; //遍历到所有的背包，就更新方案
        List<Integer> x = special.get(u);//对于第u个礼包
        out:
        for (int k = 0; k <= map.get(u); k++) {
            List<Integer> clist = new ArrayList<>(list);
            for (int i = 0; i < n; i++) {
                int a = x.get(i), b = clist.get(i);
                if (a * k > b) break out;//由于题目要求，不允许多买
                clist.set(i, b - a * k);
            }
            dfs(u + 1, clist, cur + k * x.get(n));//x的第n位存的是礼包价格
        }
    }
}
/**
 *时间复杂度：令物品数量为 n，原礼包数量为 m。将「单买」预处理成「礼包」，共有 n 种「单买」情况需要转换，同时每个转换需要对数组进行复制，复杂度为 O(n^2)
 *预处理完后，共有 n + m 个礼包；预处理每个礼包所能选的最大数量，复杂度为 O((n + m) * n)。整体复杂度为 O(k ^ {n + m} + (n + m) * n)
 * 空间复杂度：O(k ^ {n + m})
 */
