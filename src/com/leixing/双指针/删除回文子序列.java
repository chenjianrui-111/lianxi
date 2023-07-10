package com.leixing.双指针;

/**
 * 双指针
 * 由于 s 只有字母 a 和 b，并且删除的是「子序列」，因此最大的删除次数为 2（先删除所有的 a，再删除所有的 b）。
 * 同时 s 本身不为空串（不存在删除次数为 0 的情况），因此如果我们不能一次删除的话（s 本身为回文），
 * 只能通过 2次进行删除。
 */
public class 删除回文子序列 {
    public int removePalindromeSub(String s) {

        int n=s.length();
        int i=0, j=n-1;
        while (i<j){
            if (s.charAt(i) !=s.charAt(j))
                return 2;
            i++; j--;
        }
        return 1;
    }
}
