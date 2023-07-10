package com.xiaochao.前缀和;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 示例 1：
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * 提示：
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class 形成两个异或相等数组的三元组数目 {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] ^ arr[i - 1];
        }
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = i + 1; j <= n ; j++) {
                for (int k = j; k <= n ; k++) {
                    int a = sum[j - 1] ^ sum[i - 1];
                    int b = sum[k] ^ sum[j - 1];
                    if (a == b) ans++;
                }
            }
        }
        return ans;
    }
}
//• 时间复杂度：O(n3) • 空间复杂度：O(n)
/**
 *前缀异或 & 哈希表
 * 我们重新审视一下这道题。 题目其实是要我们 取得连续的一段区间 [i, k]，并在这一段中找到分割点 j，使得区间内分割点
 * 左边的异或结果为 a，分割点右边的异或结果为 b。并最终让 a 和 b 相等。
 * 由 a 与 b 相等，我们可以推导出 a ⊕ b = 0，再结合 a 和 b 的由来，可以推导出 [i, k] 连续一
 * 段的异或结果为 0。 再结合我们预处理的「前缀异或」数组，可得：
 * Xor(i,k) = sum[k] + sum[i - 1] = 0;
 * 根据公式和「相同数值异或结果为 0」特性，我们可以知道 sum[k] 和 sum[i − 1] 数值相
 * 等，因此我们可以使用「哈希表」记录每个出现过的异或结果对应的下标集合，从而实现在确定
 * k 的情况下，通过 O(1) 的复杂度找到所有符合条件的 i。
 * 需要注意的是，因为我们「前缀异或」数组的下标是从 1 开始，所以我们需要先往「哈希表」
 * 存入一个哨兵 0 作为边界，当然这一步不需要特殊操作，只需要让 k 从 0 开始执行循环即可
 * （利用「前缀异或」数组中下标 0 的值本身为 0）。
 */
class Solution17{
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
         sum[i] = sum[i - 1] ^ arr[i - 1];
        }
        int ans = 0;
        // 记录出现过的异或结果，存储格式：{ 异或结果 : [下标1, 下标2 ...] }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int k = 0; k <= n ; k++) {
            List<Integer> list = map.getOrDefault(sum[k],new ArrayList<>());
            for (Integer idx : list) {
                int i = idx + 1;
                ans += k - i;
            }
            list.add(k);
            map.put(sum[k],list);
        }
        return ans;
    }
}
class Solution18{
    public int countTriplets(int[] arr){
        int n = arr.length;
        // 事实上，甚至可以不预处理「前缀异或数组」，使用一个变量 xor 边遍历边计算即可
        int xor= 0, ans = 0;
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int k = 0; k <= n ; k++) {
            if (k >= 1) xor ^= arr[k - 1];
            List<Integer> list = map.getOrDefault(xor,new ArrayList<>());
            for (Integer idx : list) {
                int i = idx + 1;
                ans += k - i;
            }
            list.add(k);
            map.put(xor,list);
        }
        return ans;
    }
}
///• 时间复杂度：O(n2) • 空间复杂度：O(n)
