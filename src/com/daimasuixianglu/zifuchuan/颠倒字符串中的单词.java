package com.daimasuixianglu.zifuchuan;

/**
 *给你一个字符串 s ，颠倒字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 不要使用辅助空间，空间复杂度要求为O(1)。
 *
 * 不能使用辅助空间之后，那么只能在原字符串上下功夫了。
 * 想一下，我们将整个字符串都反转过来，那么单词的顺序指定是倒序了，只不过单词本身也倒叙了，那么再把单词反转一下，单词不就正过来了。
 * 所以解题思路如下：
 * 移除多余空格
 * 将整个字符串反转
 * 将每个单词反转
 * 举个例子，源字符串为："the sky is blue "
 * 移除多余空格 : "the sky is blue"
 * 字符串反转："eulb si yks eht"
 * 单词反转："blue is sky the"
 * 这样我们就完成了翻转字符串里的单词。
 */
public class 颠倒字符串中的单词 {
    public String reverseWords(String s) {
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s){
        int start=0;
        int end=s.length()-1;
        while (s.charAt(start)==' ') start++;
        while (s.charAt(end)==' ')end--;
        StringBuilder sb = new StringBuilder();
        while (start<end){
            char c=s.charAt(start);
            if (c !=' '||sb.charAt(sb.length()-1)!=' '){
                sb.append(c);
            }
            start++;
        }
        return sb;
    }
    private void reverseString(StringBuilder sb,int start,int end){
        while (start<end){
            char temp=sb.charAt(start);
            sb.setCharAt(start,sb.charAt(end));
            sb.setCharAt(end,temp);
            start++;
            end--;
        }
    }
    private void reverseEachWord(StringBuilder sb){
        int start=0;
        int end=1;
        int n=sb.length();
        while (start<n){
            while (end<n &&sb.charAt(end)!=' '){
                end++;
            }
            reverseString(sb,start,end-1);
            start=end+1;
            end=start+1;
        }
    }
}
