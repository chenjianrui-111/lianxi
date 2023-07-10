package com.lizi;

 class Solution01{
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 请写一个方法，输入是一个字符串 word，这个方法会验证这个输入的字符串是不是只由"ci", "ti" 和 "bank"组成的连续字符串，因而可以被琪琪读出来。
     如果能被读出来，返回"YES", 否则返回"NO"。（返回值是大写字母字符串，不需要包含引号）
     * @param word string字符串 只会是小写字母a-z，无需考虑大写字母，数字符号，空格等其他情况。长度为最小1，最长58。
     * @return string字符串
     */
    public String check (String word) {
        // write code here
        int n=word.length();
        if(n == 0) return "No";
        if (n%2!=0) return "No";
        if (word.contains("ci")) return "YES";
        if (word.contains("ti")) return "YES";
        if (word.contains("bank")) return "YES";
        return "NO";
    }
}
