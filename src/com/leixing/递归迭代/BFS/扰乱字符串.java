package com.leixing.递归迭代.BFS;

/**
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 *
 * 我们考虑在朴素解法的基础上，增加「记忆化搜索」功能。
 * 我们可以重新设计我们的「爆搜」逻辑：假设 s1 从 i 位置开始，s2 从 j 位置开始，后面的长度为 len 的字符串是否能形成「扰乱字符串」（互为翻转）。
 * 那么在单次处理中，我们可分割的点的范围为 [1, len)，然后和「递归」一下，将 s1 分割出来的部分尝试去和 s2 的对应位置匹配。
 * 同样的，我们将「入参对应的子串相等」和「入参对应的子串词频不同」作为「递归」出口。
 */
public class 扰乱字符串 {
    int[][][] cache;
    String s1,s2;
    int N=-1 ,Y= 1,EMPTTY=0;
    public boolean isScramble(String _s1, String _s2) {
        //递归 + 记忆化搜索 定义cache[i][j][k] 为s1从i开始，后面的k位和s2从j开始后面的k位字符串是否形成扰乱字符串
        //所以需要定义s1和s2为全局变量
        this.s1=_s1;this.s2=_s2;
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() !=s2.length()){
            return false;
        }
        int n=s1.length();
        //默认为EMPTY
        //前两位都是下标，后一位是长度，所以需要开辟n+1的空间
        cache=new int[n][n][n+1];
        return dfs(0,0,n);
    }
    boolean dfs(int i, int j, int len){
        if (cache[i][j][len] !=EMPTTY){
            return cache[i][j][len] == Y;
        }
        //根据定义，取出为s1从i开始，后面的len位和s2从j开始后面的len位
        String a=s1.substring(i,i + len);
        String b=s2.substring(j,j + len);
        if (a.equals(b)){
            cache[i][j][len] = Y;
            return true;
        }
        if (!check(a,b)){
            cache[i][j][len] =N;
            return false;
        }
        //a 和 b 值不相等 且 出现的字符的频率相同，则需要进行判断
        //从s1和s2往后 1位开始比较到 length - 1位 所以k初始值位1
        for (int k = 1;k < len;k++){
            //没有反转的情况下，i,j同序; 相当于s1[0,i)要匹配s2的[0,i) && s1[i,n)要匹配s2的[i,n);
            //那么需要比较s1从当前位置i往后k位，和s2从当前位置j往后k位 形成的是否为扰乱字符串
            //以及，从s1剩下的len - k 位，和s2剩下的len - k 位 形成的是否为扰乱字符串
            //以上两个条件必须同时满
            if (dfs(i,j,k) && dfs(i + k,j+k,len - k)){
                cache[i][j][len] = Y;
                return true;
            }
            //发生了反转的情况下， 相当于s1[0,i)要匹配s2的[n - i,n) && s1[i,n)要匹配s2的[0,n - i);
            //那么需要比较s1从当前位置i,往后k位,和s2从当前位置len - k + j (s2这里是从后面开始 反转后 后面的和前面的匹配，前面的和后面的匹配)
            //len - k + j 这个位置不好理解，同学们可以试着画图一看便知
            //以及 s1剩下的len - k位，和s2剩下的len - k位 (s2这里是从前面开始，也就是当前位置为 j)
            //以上两个条件必须同时满足
            if (dfs(i, len - k + j, k)  && dfs(i + k, j, len - k)) {
                cache[i][j][len] = Y;
                return true;
            }
        }
        //以上两种情况验证过了以后还不成立
        cache[i][j][len] = N;
        return false;
    }
    // 检查 s1 和 s2 词频是否相同
    boolean check(String s1, String s2){
        if (s1.length() != s2.length()) {
            return false;
        }
        int n=s1.length();
        int [] cnt1=new int[26],cnt2=new int[26];
        char[] cs1=s1.toCharArray(),cs2=s2.toCharArray();
        for (int i=0;i < n;i++){
            cnt1[cs1[i] - 'a']++;
            cnt2[cs2[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) {
                return false;
            }
        }
        return true;
    }
}
//时间复杂度：O(n^4)
//空间复杂度：O(n^3)
