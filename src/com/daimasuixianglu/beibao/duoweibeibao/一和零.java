package com.daimasuixianglu.beibao.duoweibeibao;

/**
 *给你一个二进制字符串数组 strs 和两个整数 m 和 n。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个0  和 n 个 1。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的子集 。
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 多维）01 背包
 * 有了基本分析，我们可以直接套用 01 背包的「状态定义」来做：
 * f[k][i][j] 代表考虑前 k 件物品，在数字 1 容量不超过 i，数字 0 容量不超过 j 的条件下的「最大价值」（每个字符串的价值均为 1）。
 * 有了「状态定义」之后，「转移方程」也很好推导：f[k][i][j]=max(f[k-1][i][j],f[k-1][i-cnt[k][0]][j-cnt[k][1]+1)
 * 其中 cnt 数组记录的是字符串中出现的 01 数量。
 * 代码（为了方便理解， 将第一件物品的处理单独抽了出来，也可以不抽出来，只需要将让物品下标从 1 开始即可，见 ）：
 */
public class 一和零 {
    public int findMaxForm(String[] strs,int m,int n){
        int len=strs.length;
        //预处理每一个字符包含0和1的数量
        int[][] cnt=new int[len][2];
        for (int i = 0; i <len ; i++) {
            String str=strs[i];
            int zero=0,one=0;
            for (char c:str.toCharArray()){
                if (c =='0') zero++;
                if (c =='1') one++;
            }
            cnt[i]=new int[]{zero,one};
        }
        // 处理只考虑第一件物品的情况
        int[][][] f=new int[len][m+1][n+1];
        for (int i = 0; i <=m ; i++) {
            for (int j = 0; j <=n ; j++) {
                f[0][i][j]=(i>=cnt[0][0] && j>=cnt[0][1]) ? 1:0;
            }
        }
        // 处理考虑其余物品的情况
        for (int k = 1; k <len ; k++) {
            int zero=cnt[k][0],one=cnt[k][1];
            for (int i = 0; i <=m ; i++) {
                for (int j = 0; j <=n ; j++) {
                    // 不选择第 k 件物品
                    int a=f[k-1][i][j];
                    // 选择第 k 件物品（前提是有足够的 m 和 n 额度可使用）
                    int b=(i>=zero && j>=one) ? f[k-1][i-zero][j-one] + 1 : 0 ;
                    f[k][i][j]=Math.max(a,b);
                }
            }
        }
        return f[len-1][m][n];
    }
}
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zero++;
                else one++;

            }
            cnt[i] = new int[]{zero, one};
        }
        int[][][] f = new int[len + 1][m + 1][n + 1];
        for (int k = 1; k <= len; k++) {
            int zero = cnt[k - 1][0], one = cnt[k - 1][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    int a = f[k - 1][i][j];
                    int b = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a, b);
                }
            }
        }
        return f[len][m][n];
    }
}
class  Solution01{
    public int findMaxForm(String[] strs,int m,int n){
        int len=strs.length;
        int[][] cnt=new int[len][2];
        for (int i = 0; i <len ; i++) {
            String str=strs[i];
            int zero=0,one=0;
            for (char c:str.toCharArray()){
                if (c =='0'){
                    zero++;
                }else {
                    one++;
                }
            }
            cnt[i]=new int[]{zero,one};
        }
        // 处理只考虑第一件物品的情况
        // 「物品维度」修改为 2
        int [][][] f=new int[2][m+1][n+1];
        for (int i=0;i<=m;i++){
            for (int j = 0; j <=n ; j++) {
                f[0][i][j]=(i>=cnt[0][0] && j>=cnt[0][1]) ? 1: 0;
            }
        }
        // 处理考虑其余物品的情况
        for (int k = 1; k <len ; k++) {
            int zero=cnt[k][0],one=cnt[k][1];
            for (int i = 0; i <=m ; i++) {
                for (int j = 0; j <=n ; j++) {
                    // 不选择第 k 件物品
                    // 将 k-1 修改为 (k-1)&1
                    int a=f[(k-1)&1][i][j];
                    // 选择第 k 件物品（前提是有足够的 m 和 n 额度可使用）
                    // 将 k-1 修改为 (k-1)&1
                    int b=(i>=zero && j>=one) ?f[(k-1)&1][i-zero][j-one]+1 :0;
                    f[k&1][i][j]=Math.max(a,b);
                }
            }
        }
        return f[(len-1)&1][m][n];
    }
}
class Solution02 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zero++;
                else one++;
            }
            cnt[i] = new int[]{zero, one};
        }
        int[][][] f = new int[2][m + 1][n + 1];
        for (int k = 1; k <= len; k++) {
            int zero = cnt[k - 1][0], one = cnt[k - 1][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    int a = f[(k-1) & 1][i][j];
                    int b = (i >= zero && j >= one) ? f[(k-1) & 1][i-zero][j-one] + 1 : 0;
                    f[k&1][i][j] = Math.max(a, b);
                }
            }
        }
        return f[len&1][m][n];
    }
}
//一维数组优化
class Solution03 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            int zero = 0, one = 0;
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zero++;
                else one++;
            }
            cnt[i] = new int[]{zero, one};
        }
        int[][] f = new int[m + 1][n + 1];
        for (int k = 0; k < len; k++) {
            int zero = cnt[k][0], one = cnt[k][1];
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    f[i][j] = Math.max(f[i][j], f[i-zero][j-one] + 1);
                }
            }
        }
        return f[m][n];
    }
}
