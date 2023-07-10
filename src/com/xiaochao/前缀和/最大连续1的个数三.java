package com.xiaochao.前缀和;

/**
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 * 示例 1：
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 */

/**
 * 动态规划（TLE）
 * 看到本题，其实首先想到的是 DP，但是 DP 是 O(nk) 算法。
 * 看到了数据范围是 104，那么时空复杂度应该都是 108。
 * 空间可以通过「滚动数组」优化到 104，但时间无法优化，会超时。
 * PS. 什么时候我们会用 DP 来解本题？通过如果 K 的数量级不超过 1000 的话，DP 应该是最常规的做法。
 * 定义 f[i, j] 代表考虑前 i 个数（并以 A[i] 为结尾的），最大翻转次数为 j 时，连续 1 的最大长度。
 * • 如果 A[i] 本身就为 1 的话，无须消耗翻转次数，f[i][j] = f[i − 1][j] + 1。 • 如果 A[i] 本身不为 1 的话，由于定义是必须以 A[i] 为结尾，因此必须要选择翻转 该位置，f[i][j] = f[i − 1][j − 1] + 1。
 */
public class 最大连续1的个数三 {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int[][] f= new int[n + 1][k + 1];
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 0; j <= k ; j++) {
                if (nums[i - 1] == 1){
                    f[i][j] = f[i - 1][j] + 1;
                }else {
                    f[i][j] = f[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans,f[i][j]);
            }
        }
        return ans;
    }
}
//• 时间复杂度：O(nk) • 空间复杂度：O(k)
/**
 *前缀和 + 二分
 * 从数据范围上分析，平方级别的算法过不了，往下优化就应该是对数级别的算法。
 * 因此，很容易我们就会想到「二分」。
 * 当然还需要我们对问题做一下等价变形。
 * 最大替换次数不超过 k 次，可以将问题转换为找出连续一段区间 [l,r] ，使得区间中出现 0的次数不超过 k 次。
 * 我们可以枚举区间 左端点/右端点 ，然后找到其满足「出现 0 的次数不超过 k 次」的最远右端点/最远左端点。
 * 为了快速判断 [l,r] 之间出现 0 的个数，我们需要用到前缀和。
 * 假设 [l,r] 的区间长度为 len ，区间和为 tot ，那么出现 0 的格式为 len - tol ，再与 k进行比较。
 * 由于数组中不会出现负权值，因此前缀和数组具有「单调性」，那么必然满足「其中一段满足 len − tol <= k，另外一段不满足 len − tol <= k」。
 * 因此，对于某个确定的「左端点/右端点」而言，以「其最远右端点/最远左端点」为分割点的前
 * 缀和数轴，具有「二段性」。可以通过二分来找分割点。
 */
class Solution13{
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < n ; i++) {
            int l = 0, r = i;
            while (l < r){
                int mid = l + r >> 1;
                if (check(sum,mid,i,k)){
                    r = mid;
                }else {
                    l = mid + 1;
                }
            }
            if (check(sum, r, i, k)) ans = Math.max(ans, i - r + 1);
        }
        return ans;
    }
    boolean check(int[] sum,int l,int r,int k){
        int tol = sum[r + 1] - sum[l] ;
        int len = r - l + 1;
        return len - tol <= k;
    }
}
///• 时间复杂度：O(n log n) • 空间复杂度：O(n)
/**
 *关于二分结束后再次 check 的说明：由于「二分」本质是找满足某个性质的分割点，通常我们
 * 的某个性质会是「非等值条件」，不一定会取得 = 。
 * 例如我们很熟悉的：从某个非递减数组中找目标值，找到返回下标，否则返回 -1。
 * 当目标值不存在，「二分」找到的应该是数组内比目标值小或比目标值大的最接近的数。因此二
 * 分结束后先进行 check 再使用是一个好习惯
 */
/**
 *双指针
 * 由于我们总是比较 len 、 tot 和 k 三者的关系。
 * 因此我们可以使用「滑动窗口」的思路，动态维护一个左右区间 [j, i] 和维护窗口内和tot 。
 * 右端点一直右移，左端点在窗口不满足「 len - tol <= k 」的时候进行右移。
 */
class Solution14{
    public int longestOnes(int[] nums,int k){
        int n = nums.length;
        int ans = 0;
        for (int i = 0,j = 0, tot = 0; i < n ; i++) {
            tot += nums[i];
            while ((i - j + 1) - tot > k) tot -= nums[j++];
            ans = Math.max(ans,i - j + 1);
        }
        return ans;
    }
}
//• 时间复杂度：O(n) • 空间复杂度：O(1)
